#!/bin/sh

build() { # $1: directory, $2: image_name
  
  cd $1
  docker build -t $2 . 
  cd ..
}

# Compile services code
mvn -q clean package


cd integration
cd esb
#docker build -t petitroll/esb . --no-cache
cd ..
docker build -t petitroll/tcs-bus . --no-cache
cd ..



