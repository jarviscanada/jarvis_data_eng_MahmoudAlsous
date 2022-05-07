# Table of contents
* [Introduction](https://github.com/jarviscanada/jarvis_data_eng_MahmoudAlsous/edit/feature/springboot/springboot/README.md#introduction)
* [Quick Start](https://github.com/jarviscanada/jarvis_data_eng_MahmoudAlsous/edit/feature/springboot/springboot/README.md#quick-start)
* [Implementation](https://github.com/jarviscanada/jarvis_data_eng_MahmoudAlsous/edit/feature/springboot/springboot/README.md#implementation)
* [Test](https://github.com/jarviscanada/jarvis_data_eng_MahmoudAlsous/edit/feature/springboot/springboot/README.md#test)
* [Deployment](https://github.com/jarviscanada/jarvis_data_eng_MahmoudAlsous/edit/feature/springboot/springboot/README.md#deployment)
* [Improvements](https://github.com/jarviscanada/jarvis_data_eng_MahmoudAlsous/edit/feature/springboot/springboot/README.md#improvements)

# Introduction
Developed a new trading system with the microservice architecture and SpringBoot framework. The trading application allows users to manage client profiles and accounts, monitor portfolio performance, and trade securities as part of a small stock exchange. This trading system implemented with REST API and is utilized by the front-end applications using Java 8 and SpringBoot.  The SpringBoot application is stateless, so all data shall be stored in a PostgreSQL database. The SpringBoot app fetches data from IEX Cloud which offers free market data via its REST API. The trading application was test by writing unit and integration tests for each layer as well as executing them with line coverage checks. In addition, the REST endpoints were tested using Postman and Swagger UI. The application was deployment using docker and PSQL database to allow the app to be executed on any server.

List of technologies used:
- Java 8
- SpringBoot
- Maven
- Swagger UI
- Postman
- Docker
- PSQL
- REST API
- JUnit
- IEX Cloud

# Quick Start
To execute the application, follow the steps below:

  1. Create a new docker network
  ```
  sudo docker network create trading-net
  ```
  
  2. Create a Docker file with the following contents
  ```
  FROM postgres:9.6-alpine
  COPY ./schema.sql /docker-entrypoint-initdb.d/
  ```
  
  3. Build Image for trading-psql
  ```
  cd ./springboot/psql
  docker build -t trading-psql .  
  docker image ls -f reference=trading-psql
  ```
  
  4. Create another Docker file with the following contents
  ```
  #
  # Build stage
  #
  FROM maven:3.6-jdk-8-slim AS build
  COPY src /build/src
  COPY pom.xml /build/
  RUN mvn -f /build/pom.xml clean package -DskipTests

  #
  # Package stage
  #
  FROM openjdk:8-alpine
  COPY --from=build /build/target/trading-1.0-SNAPSHOT.jar /usr/local/app/trading/lib/trading_app.jar
  ENTRYPOINT ["java","-jar","/usr/local/app/trading/lib/trading_app.jar"]
  ```
    
  5. Build Image for trading-app
  ```
  cd ./springboot/
  docker build -t trading-app . 
  docker image ls -f reference=trading-app
  ```
  
  6. Setup environmental variables
  ```
  export IEX_PUB_TOKEN=[iex_token]; 
  export PSQL_USER=postgres; 
  export PSQL_PASSWORD=[password]; 
  export PSQL_HOST=localhost; 
  export PSQL_PORT=[port]; 
  export PSQL_DB=jrvstrading;
  ```
  
  7. Start the docker containers which are attached to the network
  ```
  docker run --name trading-psql-dev \
  -e POSTGRES_PASSWORD=password \
  -e POSTGRES_DB=jrvstrading \
  -e POSTGRES_USER=postgres \
  --network trading-net \
  -d -p 5432:5432 trading-psql
  
  IEX_PUB_TOKEN="your_token"
  
  docker run --name trading-app-dev \
  -e "PSQL_URL=jdbc:postgresql://trading-psql-dev:5432/jrvstrading" \
  -e "PSQL_USER=postgres" \
  -e "PSQL_PASSWORD=password" \
  -e "IEX_PUB_TOKEN=${IEX_PUB_TOKEN}" \
  --network trading-net \
  -p 5000:5000 -t trading-app
  ```
  
  8. Execute application with the following command
  ```
  java -jar [path]/trading-1.0-SNAPSHOT.jar [command] [..]
  ```

  (Optional)
  You can also run the application with Swagger UI. To do so, go to http://localhost:5000/swagger-ui.html in the browser while the application is running. Use the "try it out" button in the desired endpoint, fill in any necessary requirements, and then press execute.
  
  ![Image](https://github.com/jarviscanada/jarvis_data_eng_MahmoudAlsous/blob/feature/springboot/springboot/assets/Swagger1.png)
  ![Image](https://github.com/jarviscanada/jarvis_data_eng_MahmoudAlsous/blob/feature/springboot/springboot/assets/Swagger2.png)
  
# Implementation
## Architecture
![Image](https://github.com/jarviscanada/jarvis_data_eng_MahmoudAlsous/blob/feature/springboot/springboot/assets/SpringBoot%20Architecture.drawio.png)

### Controller Layer
The controller layer handles the user requests. In this application, TraderAccountController and QuoteController are utilized. These two controllers deal with the request made by the user and call the service layer that corresponds to that controller class.

### Service Layer
The service layer mainly deals with the business logic of the application. As seen in the diagram above, there are two service classes: TraderAccountService and QuoteService. These two service components receive and manage the input from the controller layer, and then calls the corresponding DAO layer methods. 

### DAO Layer
The Data Access Object layer (DAO) manages the interaction with the PostgreSQL database. The DAO objects in the application use SQL queries to persist and retrieve data from the database using CRUD methodology by turning SQL objects into Java objects. As seen in the diagram above, there is a total of six DAO layer objects in the application that are utilized by the service layer: TraderDao, AccountDao, SecurityOrderDao, PositionDao, QuoteDao, and MarketDataDao.

### SpringBoot: WebServlet/Tomcat and IoC
The SpringBoot part of the application uses Tomcat as its web servlet, which allows the system to work as a web application would. This means that the application can be accessed through a URI instead of executing a command. Along with the SpringBoot web functionality, it also provides the Spring IoC container which allows the application to setup its components as beans using annotations. These annotations guide the IoC container with the proper instructions to setup the beans.

### PSQL and IEX
A PSQL database is used to host the application's data. Initially, the trading-psql Docker image is used to create the test database using an SQL script called init_db.sql. Another SQL script called schema.sql is executed, which creates four tables and one view. The four tables are trader, account, quote, and security_order. The view is called position view, which showcases data from the security_order table.

IEX Cloud is a REST API that provides real-world market data. The data that is updated in the IEX Cloud is retrieved when updaing tickers in the quote table. Then it is saved as an IexQuote, which is then converted to a Quote object. That Quote object is what replaces the current existing Quote object in the quote table.

## REST API Usage
### Swagger
Swagger is an online tool that allows users to interact with API. It is used in this project as a way to test the endpoints of the application.
### Quote Controller
The Quote controller manages user requests that are related to the quote table in the database. The data for the IexQuote is taken from the IEX Cloud and then converted to a Quote object. This Quote object replaces the current Quote that is in the Quote table.

Endpoints:
  - GET `/quote/dailyList`: Show all tickers that are available to be traded on this platform
  - GET `/quote/iex/ticker/{ticker}`: Show iexQuote
  - POST `/quote/tickerId/{tickerId}`: Add a new ticker to the dailyList
  - PUT `/quote/`: Update a quote in the quote table
  - PUT `/quote/iexMarketData`: Update quotes in the quote table using IEX data
### Trader Controller
The Trader controller manages user requests that are related to the trader table in the PSQL database. It can add or delete traders and make deposits and withdrawals from the trader's account.

Endpoints:
- POST `/trader/`: Create a trader and an account.
- PUT `/trader/deposit/*`: Deposit a fund.
- PUT `/trader/withdraw/*`: Withdraw a fund.
- DELETE `/trader/traderId/{traderId}`: Delete a trader from the trader table.
### Order Controller
The Order controller handles user requests that are related to the market security orders. It can validate and submit market orders by checking whether it is a buy or a sell order and then updating the security order status and returning it.

Endpoint:
- POST `/order/marketOrder`: Submit a market order
### App controller
The App controller mainly deals with user requests related to the health of the app.

Endpoint:
- GET `/health`: Obtain health status of the application.
### Dashboard controller
The Dashboard controller handles user requests that relate to the portfolio/profile of the trader. It can be used to view the portfolio or the profile of the trader using their trader ID.

Endpoints:
- GET `/dashboard/portfolio/traderId/{traderId}` Show portfolio by trader ID
- GET `/dashboard/profile/traderId/{traderId}` Show trader profile by trader ID
# Test 
The application's components were tested using JUnit by creating integration tests for every major class of every layer. These tests evaluate the performance of every method in each class to ensure they work as intended. These tests were created in IntelliJ and were executed using the command line. SpringBoot testing libraries were used for the tests using annotations to provide instructions to the IoC. Finally, Swagger was used to test every endpoint that was implemented for the application.

The following is the code line coverage for the project:

![Image](https://github.com/jarviscanada/jarvis_data_eng_MahmoudAlsous/blob/feature/springboot/springboot/assets/Coverage.png)

# Deployment
![Image](https://github.com/jarviscanada/jarvis_data_eng_MahmoudAlsous/blob/feature/springboot/springboot/assets/Docker%20Diagram.drawio.png)

The diagram above shows two images that are used by the application: trading-psql and trading-app. First off, the trading-psql image creates the PSQL database by executing two SQL scripts that setup the database and create four tables. The openjdk:8-alpine image is used as a base image for the trading-app image. The trading-app image is used to package the code using maven dependencies in order to start the application appropriately.

# Improvements
- Convert the returned JSON output into a more organized structure for viewing quotes and other data
- Add the capability for traders to have two or more accounts
- Add more operations that allow users to update traders or delete quotes
