#!/bin/sh
if [ -z "$DOCKER_ACCOUNT" ]; then
    DOCKER_ACCOUNT=jiayinzhuo
fi;
docker build -t productservice .
docker tag productservice $DOCKER_ACCOUNT/productservice:latest
docker push $DOCKER_ACCOUNT/productservice