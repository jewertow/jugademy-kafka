#!/bin/bash

curl --request POST \
    --data '{ "id": "8", "name": "Buty do rzutu młotem", "description": "Mi się podobają" }' \
    --header 'Content-Type: application/json' \
    http://localhost:8080/offers

