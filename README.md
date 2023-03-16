# commerce-back
<div>
  <img src="https://seeklogo.com/images/R/revature-logo-59A35B4F75-seeklogo.com.png"
</div>
<br/> <br>
 
E-commerce [Spring Boot](http://projects.spring.io/spring-boot/) web application
  
## Requirements
 
For building and running the application you need:
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Docker](https://www.docker.com/) Use docker container locally or on an EC2 instance remotely

## Runnning the application locally
- Run the Spring Boot application locally by executing the the 'main' method in the ECommerceApplication.java

## Connect your own Database
- In the application.yaml file, make sure the datasource url is correct for your local or remote database

## Build docker image and create container
- Use Dockerfile to build the image
```shell
docker build -t project3 .
docker run -d -p 8083:8083 --name
project 3 project3:latest
```
  

  

