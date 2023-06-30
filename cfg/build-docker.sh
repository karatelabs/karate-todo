#!/bin/bash

./mvnw clean package

docker build -t karate-todo -f cfg/Dockerfile-app .
