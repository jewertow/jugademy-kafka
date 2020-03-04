#!/bin/bash

echo "Building image jugademy/offer-storage-service:0.1"
docker build -t jugademy/offer-storage-service:0.1 ./offer-storage-service

echo "Building image jugademy/offer-cache-service:0.1"
docker build -t jugademy/offer-cache-service:0.1 ./offer-cache-service

echo "Building image jugademy/offer-search-service:0.1"
docker build -t jugademy/offer-search-service:0.1 ./offer-search-service

