#!/bin/bash
docker stop car-rpc
docker stop hotel-rpc
docker stop tcs-cars
docker stop tcs-hotel-rpc
sleep 10
docker start car-rpc
docker start tcs-cars
docker start hotel-rpc
docker start tcs-hotel-rpc
