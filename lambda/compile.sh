#!/bin/bash -e

BIN="bin"

set -x

javac -d ${BIN} src/cs1302/lambda/Pipeline.java
javac -d ${BIN} -cp ${BIN} src/cs1302/lambda/Driver.java
java -cp ${BIN} cs1302.lambda.Driver
