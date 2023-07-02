#!/bin/bash

./mvnw clean package

# export DOCKER_DEFAULT_PLATFORM=linux/amd64

docker build -t karate-todo -f cfg/Dockerfile-app .
