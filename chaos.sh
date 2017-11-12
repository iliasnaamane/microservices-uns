#!/bin/bash
docker stop car-rpc
docker stop tcs-cars
sleep 100
docker start car-rpc
docker start tcs-cars
