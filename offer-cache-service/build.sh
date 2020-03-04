#!/bin/bash

echo "Building allezon/offer-cache-service:build container"

docker build -t allezon/offer-cache-service:build -f Dockerfile.build .

docker container create --name builder allezon/offer-cache-service:build
docker container cp builder:/tmp/target/ ./target/
docker container rm -f builder

echo "Building allezon/offer-cache-service:$1 container"
docker build --no-cache -t allezon/offer-cache-service:$1 .
