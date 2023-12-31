# cinema_room_rest_services_java
A simple implementation of Virtual Cinema Theater, which is based on Java Spring Boot and built with Gradle 8.4

## Description
Always wanted to have your private movie theater and screen only the movies you like? You can buy a fancy projector and set it up in a garage, 
but how can you sell tickets? The idea of a ticket booth is old-fashioned, so let's create a special service for that! Make good use of Spring 
and write a REST service that can show the available seats, sell and refund tickets, and display the statistics of your venue. Pass me the 
popcorn, please!

## What I used
[Java 17](https://openjdk.org/projects/jdk/17/)  
[Spring Boot 3.1.0](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.1-Release-Notes)  
[Gradle 8.4](https://docs.gradle.org/8.4/release-notes.html)

## What I learned
In this project, I learned how to create a simple Spring REST service that will help you manage a small movie theatre. Handle HTTP requests in 
controllers, create services and respond with JSON objects.

## How to run
1. Open the terminal and clone this repository either via HTTPS or via SSH
2. Navigate to the project's root folder: cd cinema_room_rest_services_java
3. Build the project with `./gradlew build`
4. Run the project with `./gradlew bootRun`
5. Access the available endpoints using a client like Postman or Insomnia.

## Additional notes
1. The cinema room has 9 rows and 9 columns, with a total of 81 seats.
2. The first 4 rows have a price of 10, and the last 5 rows have a price of 8.
3. The ticket token is generated by UUID when a ticket is purchased.
4. The password to get statistics is "super_secret" and it's visible in the CinemaService class. However, it could be moved to a properties
   file or environment variable.
