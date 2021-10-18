#!/bin/bash -e

BIN="bin"

set -x

javac -d ${BIN} src/cs1302/lambda/Employee.java
javac -d ${BIN} src/cs1302/lambda/Pipeline.java
javac -d ${BIN} -cp ${BIN} src/cs1302/lambda/Lists.java
javac -d ${BIN} -cp ${BIN} src/cs1302/lambda/Driver.java
java -cp ${BIN} cs1302.lambda.Driver
