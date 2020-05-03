# Photoblog

Demonstrate rest services spring security with jwt

Pre-requisites
1. Java - version 1.8 
2. Apache Maven 3.6 
3. Postgres 
4. ToolSuite for Development IDE

Configuration - Postgres
For postgres installation steps refer to Installation guide.

Once Postgres is up - There is a file <b>schema.sql</b> in resources folder.
You may use any SQL Client or PgAdmin to connect to remote Postgres DB with valid connection string/credentials.

set the url, username and password in properties file
spring.datasource.url=url
spring.datasource.username=username
spring.datasource.password=password

Build Steps

To build the code after checkout/download go to command prompt
Change to the directory root of the code and where pom file resides
Execute <b>mvn clean install -DskipTests</b>
All required dependencies will be downloaded.

Working on Spring-Boot Project

Open ToolSuite for Development IDE

Click File->Import Existing Maven Project and select the project folder with pom file
The project will be imported in to workspace

API Documentation

Can be accessed with link http://localhost:8080/swagger-ui/index.html