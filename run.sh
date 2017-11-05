#!/bin/bash

for((i=0;i<=(5*60);i++))
do
	cp vol.csv integration/input
	cp hotels.csv integration/input
	cp car.csv integration/input
	sleep 1     
done