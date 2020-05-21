#!/bin/sh

kubectl create deployment productservice --image=gcr.io/gcp-demo-277801/productservice:latest
kubectl expose deployment productservice --type="LoadBalancer" --port 8080
