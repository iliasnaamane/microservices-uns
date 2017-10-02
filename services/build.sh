#!/bin/sh

PUSH=true
#PUSH=false

build() { # $1: directory, $2: image_name
  cd $1
  cd $2
  docker build -t $3 .
  if [ "$PUSH" = "true" ]; then docker push $3; fi
  cd ..
}

# Build docker images
build resource carrental iliasnaamane/tcs-carrental
