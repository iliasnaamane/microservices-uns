#!/bin/bash
cd integration

mvn clean package

cp flows/target/implem-1.0.jar ../../apache-servicemix-7.0.1/deploy/.