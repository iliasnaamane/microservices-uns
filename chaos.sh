#!/bin/bash
r=$(( ( RANDOM % 7 )  + 1 ))

case $r in
	1)
		docker stop tcs-cars
		sleep 10
		docker start tcs-cars
		;;
	2)
		docker stop hotel-rpc
		sleep 10
		docker start hotel-rpc
		;;
	3)
		docker stop tcs-hotel-rpc
		sleep 10
		docker start tcs-hotel-rpc
		;;
	4)
		dcoker stop car-rpc
		sleep 10
		docker start car-rpc
		;;
	5)
		docker stop vols-document
		sleep 10
		docker start vols-document
		;;
	6)
		docker stop flightreservation
		sleep 10
		docker start flightreservation
		;;
	7)
		docker stop businesstravel-resource
		sleep 10
		docker start businesstravel-resource
		;;
esac