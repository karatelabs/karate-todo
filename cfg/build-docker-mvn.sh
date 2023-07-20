#!/bin/bash

# docker run -it --rm -v "$(pwd)":/karate-todo -w /karate-todo -e START_SERVER=true maven:3.9.3-amazoncorretto-20 mvn test -P gatling -Dmaven.repo.local=./target/repository

# export DOCKER_DEFAULT_PLATFORM=linux/amd64

docker build -t karate-mvn -f cfg/Dockerfile-mvn .
