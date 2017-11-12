#!/bin/bash

for((i=0;i<=(5*60);i++))
do
	cp vol.csv integration/input
	cp hotels.csv integration/input
	cp cars.csv integration/input
	sleep 10    
done