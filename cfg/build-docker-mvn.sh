#!/bin/bash

export START_SERVER=true

./mvnw test -P gatling -Dmaven.repo.local=./target/repository

# export DOCKER_DEFAULT_PLATFORM=linux/amd64

docker build -t karate-mvn -f cfg/Dockerfile-mvn .
