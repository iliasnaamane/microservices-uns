#!/bin/sh


build() { # $1: directory, $2: image_name
  
  cd $1
  docker build -t $2 . 
  cd ..
}



cd integration
mvn -q clean package
cd esb
#docker build -t petitroll/esb . --no-cache
cd ..
docker build -t petitroll/tcs-bus . --no-cache
cd ..
