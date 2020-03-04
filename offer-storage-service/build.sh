#!/bin/bash

echo "Building allezon/offer-storage-service:build container"

docker build -t allezon/offer-storage-service:build -f Dockerfile.build .

docker container create --name builder allezon/offer-storage-service:build
docker container cp builder:/tmp/target/offerstorage-0.1-SNAPSHOT.jar ./app.jar
docker container rm -f builder

echo "Building allezon/offer-storage-service:$1 container"
docker build --no-cache -t allezon/offer-storage-service:$1 .
