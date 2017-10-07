#!/bin/sh

#PUSH=true
PUSH=false

build() { # $1: directory, $2: image_name
  cd $1
  docker build -t $2 .
  cd ..
}

# Compile services code
mvn -q clean package

# Build docker images
build hotelRPC result/hotel-rpc
build carRPC result/car-rpc
build vols result/vols-doc
# build resource  petitroll/tcs-rest
# build document  petitroll/tcs-doc
