#!/bin/sh

#PUSH=true
PUSH=false
cd services

build() { # $1: directory, $2: image_name
  
  cd $1
  docker build -t $2 .
  cd ..
}

# Compile services code
mvn -q clean package

#Build docker images
build vols result/document-vol
build hotelRPC result/hotel-rpc
build carRPC result/car-rpc
build resourceBusinessTravel result/businesstravel-resource

#cd ..
#cd integration
#cd esb
#docker build -t  docker build -t petitroll/esb .
#cd ..
#docker build -t petitroll/tcs-bus .
#cd ..
#cd ..


