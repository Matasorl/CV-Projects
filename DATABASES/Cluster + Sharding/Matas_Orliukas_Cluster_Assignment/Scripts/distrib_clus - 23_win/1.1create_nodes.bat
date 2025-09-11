REM #-------------------------------------------------#
REM #						      #
REM # 0. Set myName to the name of the computer       #
REM #						      #
REM #-------------------------------------------------#
REM #	
SET myName=Matas
REM #
REM #-------------------------------------------------#
REM #						       #
REM # 1.                                             #
REM #						       #
REM #-------------------------------------------------#
REM #	
REM # 1.1. 
REM #	

mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\cfg0"
mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\cfg1"
mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\cfg2"

REM #	
REM # 1.2. 
REM #	


start /b mongod --configsvr --replSet configServers --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\cfg0" --port 4000 --logpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\cfg0.log"

start /b mongod --configsvr --replSet configServers --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\cfg1" --port 4001 --logpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\cfg1.log" 

start /b mongod --configsvr --replSet configServers --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\cfg2" --port 4002 --logpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\cfg2.log"

REM #	
REM #-------------------------------------------------#
REM #						      #
REM # 2.                         		      #
REM #						      #
REM #-------------------------------------------------#
REM #	
REM # 2.1. 
REM #	
 

start /b mongos -- configdb configServers/ %myName%.local:4000,%myName%.local:4001,%myName%.local:4002 --port 5010
start /b mongos --configdb configServers/%myName%.local:4000,%myName%.local:4001,%myName%.local:4002 --port 5020
start /b mongos --configdb configServers/%myName%.local:4000,%myName%.local:4001,%myName%.local:4002 --port 5030
	

REM #	
REM #-------------------------------------------------#
REM #						      #
REM # 3.                              	      #
REM #						      #
REM #-------------------------------------------------#
REM #	
REM # 3.1. 
REM #	

mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\north0"
mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\north1"
mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\north2"
mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\east0"
mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\east1"
mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\east2"
mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\south0"
mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\south1"
mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\south2"
mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\west0"
mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\west1"
mkdir "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\west2"

REM #	
REM # 3.2. 
REM #	

start /b mongod --shardsvr --replSet northReplSet --oplogSize 1 --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\north0" --port 4100
start /b mongod --shardsvr --replSet northReplSet --oplogSize 1 --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\north1" --port 4101
start /b mongod --shardsvr --replSet northReplSet --oplogSize 1 --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\north2" --port 4102

start /b mongod --shardsvr --replSet eastReplSet --oplogSize 1 --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\east0" --port 4200
start /b mongod --shardsvr --replSet eastReplSet --oplogSize 1 --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\east1" --port 4201
start /b mongod --shardsvr --replSet eastReplSet --oplogSize 1 --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\east2" --port 4202

start /b mongod --shardsvr --replSet southReplSet --oplogSize 1 --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\south0" --port 4300
start /b mongod --shardsvr --replSet southReplSet --oplogSize 1 --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\south1" --port 4301
start /b mongod --shardsvr --replSet southReplSet --oplogSize 1 --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\south2" --port 4302

start /b mongod --shardsvr --replSet westReplSet --oplogSize 1 --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\west0" --port 4400
start /b mongod --shardsvr --replSet westReplSet --oplogSize 1 --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\west1" --port 4401
start /b mongod --shardsvr --replSet westReplSet --oplogSize 1 --dbpath "C:\Visual Studio Coding\NoSql\Assignment\Assignment 5\distribc\west2" --port 4402

start /b mongos --configdb configReplSet/ %myName%.local:4000,%myName%.local:4001,%myName%.local:4002 --port 5000

REM #	
