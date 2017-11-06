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
cd services

#Build docker images
build vols result/document-vol
build hotelRPC result/hotel-rpc
build carRPC result/car-rpc
build resourceBusinessTravel result/businesstravel-resource
build ocr ocr/mock

#cd services
cd externals
cd cars 
#npm i 
npm install
npm run build
cd ..

build cars soa-team-2/cars

build hotels soa-team-1/hotels


cd ..
cd ..


cd integration
cd esb
docker build -t petitroll/esb .
cd ..
docker build -t petitroll/tcs-bus .
cd ..



