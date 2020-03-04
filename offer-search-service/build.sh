#!/bin/bash

echo "Building allezon/offer-search-service:build container"

docker build -t allezon/offer-search-service:build -f Dockerfile.build .

docker container create --name builder allezon/offer-search-service:build
docker container cp builder:/tmp/build/libs/offer-search-service.jar ./app.jar
docker container rm -f builder

echo "Building allezon/offer-search-service:$1 container"
docker build --no-cache -t allezon/offer-search-service:$1 .
