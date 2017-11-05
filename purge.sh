#!/bin/bash
# Delete all containers
docker stop $(docker ps -a -q)
docker rm -f $(docker ps -a -q)
# Delete all images
docker rmi -f $(docker images -q)
