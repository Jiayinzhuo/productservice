#!/bin/sh

kubectl create deployment productservice --image=jiayinzhuo/productservice
kubectl expose deployment productservice --type="LoadBalancer" --port 8080
