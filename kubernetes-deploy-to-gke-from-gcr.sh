#!/bin/sh

kubectl create deployment productservice --image=gcr.io/cicd-284616/productservice:1.0
kubectl expose deployment productservice --type="LoadBalancer" --port 8080
