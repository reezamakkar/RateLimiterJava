#!/bin/bash
mvn clean package -DskipTests=true && java -jar target/ratelimiter.jar server
