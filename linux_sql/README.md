# Linux Cluster Monitoring Agent
# Introduction
The Linux Cluster Monitoring Agent is a software tool built for the Jarvis Linux Cluster Administration (LCA) to record and monitor the hardware specifications and resource usages of each node in a Linux Cluster in real-time, which are running on the CentOS 7 operating system. The tool was designed to keep track of these nodes by communicating through IPv4 addresses internally through a switch. This was tested using a virtual machine instance, which was created on the Google Cloud Platform. The data collected by the LCA Monitoring Agent is entered into a PostgreSQL database, which the LCA team can use to provide reports for future resource planning. The system was managed using Git and the source code along with the assisting files are uploaded to a Github Repository that connected with the remote desktop through an SSH encrypted connection.

Below is the list of technologies used:
- CentOS 7 (Linux)
- PostgreSQL
- Docker
- Bash
- Git
- Github
- Crontab
- IntelliJ IDEA
- Google Cloud Platform

# Quick Start
- Create a psql docker instance using psql_docker.sh

```./scripts/psql_docker.sh create [db_username][db_password]```
- Start a psql instance using psql_docker.sh

```./scripts/psql_docker.sh start [db_username][db_password]```
- Create tables using ddl.sql (Running the ddl.sql script in the host_agent database)

```psql -h localhost -U postgres -d host_agent -f sql/ddl.sql```
- Insert hardware specs data into the DB using host_info.sh

```./scripts/host_info.sh psql_port db_name psql_user psql_password```
- Insert hardware usage data into the DB using host_usage.sh

```./scripts/host_usage.sh psql_port db_name psql_user psql_password```
- Crontab setup


```* * * * * bash /home/centos/dev/jarvis_data_eng_Mahmoud/linux_sql/scripts/host_usage.sh "localhost" 5432 "host_agent" "postgres" "password" &> /tmp/psl_docker.log```

# Implementation
1. Setup a Github Repository using Git and create an SSH connection
2. Install Docker and Provision a PSQL instance using Docker
3. Create a DDL script that will create tables to keep records of host hardware specifications and resource usage data
4. Program two bash scripts to take the required hardware information and resource usages and store them inside the PSQL database tables
5. Implement a Crontab job that will execute the host_usage script every minute in order to continuously collect data
6. Create PSQL Queries that groups hosts by CPU number and memory size to organize the data, calculate average used memory over 5 minute intervals, and detect host failures

## Architecture
![image](https://user-images.githubusercontent.com/55495666/154577170-f2e762c9-ebdc-47fb-8f08-41b0f89fa0dd.png)

## Scripts
### psql_docker.sh
- **Description:** This script creates a PSQL docker container that contains the PSQL database and is able to start or stop the docker container.
- **Script Usage:** 

  Create the docker container

  ```./scripts/psql_docker.sh create [db_username][db_password]```

  Start the docker container

  ```./scripts/psql_docker.sh start```

  Stop the docker container

  ```./scripts/psql_docker.sh stop```

### host_info.sh
- **Description:** This script collects hardware specifications of the linux node using linux usage commands and enters them into a host database using SQL commands. The script will only execute once because we are assuming that the hardware specifications are static. Refer to the Database Modeling section below to see the specific hardware information collected.
- **Script Usage:** ```./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password```

### host_usage.sh
- **Description:** This script collects the hardware's resource usage and stores them in the PSQL database using SQL commands. The script will execute once every minute to allow for continuous data collection. This script will need to be run after the host_info.sh script since it contains a field that is dependant on the host_info primary key (host_id).
- **Script Usage:** ```./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password```

### crontab
- **Description:** This script creates a job that allows the host_usage.sh script to execute every minute.
- **Script Usage:** 

  Open the crontab editor:
  ```crontab -e```
  
  Add a job to the crontab editor: 
  
  ```* * * * * bash /home/centos/dev/jarvis_data_eng_Mahmoud/linux_sql/scripts/host_usage.sh "localhost" 5432 "host_agent" "postgres" "password" &> /tmp/psl_docker.log```
  
### queries.sql
- **Description:** This script resolves three business problems that are concerned with the application. These business concerns are described below.

  1. Grouping hosts by their hardware information: The script will use some sql commands to group hosts by CPU number and order them by their memory size in descending order.
  2. Averaging memory usage: Average used memory in percentage for each host over 5 minute intervals.
  3. Detect host failure: Checking the server database to see if the crontab job fails to insert 3 or more data entries within 5 minutes. 

## Database Modeling
### `host_info` Table:
Variable | Data Type | Constraint | Description
--- | --- | --- | ---
id | SERIAL | PRIMARY KEY | Increments automatically for every new entry added to distinguish different data in the database
hostname | VARCHAR | NOT NULL UNIQUE | Host server name
cpu_number | INT | NOT NULL | Number of CPUs used on the host node
cpu_architecture | VARCHAR | NOT NULL | CPU architecture type information
cpu_model | VARCHAR | NOT NULL | Model of the CPU Processor
cpu_mhz | DECIMAL | NOT NULL | CPU Speed in MHz
L2_cache | INT | NOT NULL | Size of the L2 cache in KB
total_mem | INT | NOT NULL | Host's total memory in KB
timestamp | TIMESTAMP | NOT NULL | Data entry time in UTC format

### `host_usage` Table:
Variable | Data Type | Constraint | Description
--- | --- | --- | ---
timestamp | TIMESTAMP | NOT NULL | Data entry time
host_id | SERIAL | FOREIGN KEY | Refers to the id field in the host_info table
memory_free | INT | NOT NULL | Host's total free memory in MB
cpu_idle | INT | NOT NULL | CPU idle time in percentage
cpu_kernel | INT | NOT NULL | CPU kernel runtime in percentage
disk_io | INT | NOT NULL | Number of disk I/O being used
disk_available | INT | NOT NULL | Available disk space in MB

# Test
**ql_docker.sh:** This was tested by using the commands below to display containers that are created and running

```docker container ls -a -f name=jrvs-psql```

```docker ps -f name=jrvs-psql```

**host_info.sh and host_usage.sh:** These scripts were tested by using DML commands such as "SELECT * FROM" on the PSQL command line to display the table entries

**ddl.sql and queries.sql:** These sql scripts were tested by executing them directly onto the PSQL command line.

# Deployment
The Linux Cluster Monitoring Agent application provisioned PostgreSQL database instances managed by Docker containers. Crontab was deployed to allow for periodic execution of the host_usage.sh script. The code was manipulated by using Git and was uploaded to the Github repository based on the GitFlow process.

# Improvements
1. Create a script that allows the hardware information to be updated if any changes were made
2. Testing the application on more virtual machines in order to add more nodes and obtain a more realistic simulation
3. Add more sample data entries with more variety to test the application with more precision
