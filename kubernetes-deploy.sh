#!/bin/sh

kubectl create deployment productservice --image=jiayinzhuo/productservice:1.0
kubectl expose deployment productservice --type="LoadBalancer" --port 8080
