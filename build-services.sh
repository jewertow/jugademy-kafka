#!/bin/bash

echo "Building offer-storage-service"
cd offer-storage-service
./mvnw clean package
cd ..

echo "Building offer-cache-service"
cd offer-cache-service
sbt clean assembly
cd ..

echo "Building offer-search-service"
cd offer-search-service
./gradlew clean build
cd ..

echo "Done"

