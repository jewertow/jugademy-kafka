#!/bin/bash

curl --request POST \
    --data '{ "id": "1", "name": "Rower górski", "description": "Rower górski wyłącznie dla dorosłych." }' \
    --header 'Content-Type: application/json' \
    http://localhost:8080/offers

curl --request POST \
    --data '{ "id": "2", "name": "Rower turystyczny", "description": "Rower turystyczny dla dzieci i młodziezy." }' \
    --header 'Content-Type: application/json' \
    http://localhost:8080/offers

curl --request POST \
    --data '{ "id": "3", "name": "Rower miejski", "description": "Rower przeznaczony do jazdy w warunkach miejskich" }' \
    --header 'Content-Type: application/json' \
    http://localhost:8080/offers
    
curl --request POST \
    --data '{ "id": "4", "name": "Buty do rzutu młotem Adidas 42", "description": "Buty sportowe damskie do rzutu młotem" }' \
    --header 'Content-Type: application/json' \
    http://localhost:8080/offers

curl --request POST \
    --data '{ "id": "5", "name": "Buty do rzutu młotem Adidas 47", "description": "Buty sportowe meskie do rzutu młotem" }' \
    --header 'Content-Type: application/json' \
    http://localhost:8080/offers

curl --request POST \
    --data '{ "id": "6", "name": "Buty do rzutu młotem Nike 42", "description": "Buty sportowe meskie do rzutu młotem" }' \
    --header 'Content-Type: application/json' \
    http://localhost:8080/offers

curl --request POST \
    --data '{ "id": "7", "name": "MacBook Air", "description": "Komputer dla ludzi pijących kawę w starbucks" }' \
    --header 'Content-Type: application/json' \
    http://localhost:8080/offers
