#! /bin/bash

# Assign arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Check if a valid number of arguments have been entered
if [ $# -ne 5 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

lscpu_out=$(lscpu)
mem_out=$(cat /proc/meminfo)
hostname=$(hostname -f)

# Extract hardware specifications and assign them to variables
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture\:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name\:" | awk '{for(i=3;i<=NF;i++) printf $i" "; print ""}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz\:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache\:" | awk '{split($3,a,"K"); print a[1]}' | xargs)
total_mem=$(echo "$mem_out"  | egrep "^MemTotal\:" | awk '{print $2}' | xargs)
timestamp=$(vmstat -t | awk '{print $18,$19}'| tail -n1 | xargs)

# Command to insert the variables into the PSQL database host_agent
insert_stmt="INSERT INTO host_info(hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, timestamp) VALUES('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp')"

export PGPASSWORD=$psql_password

# Execute command in the database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?