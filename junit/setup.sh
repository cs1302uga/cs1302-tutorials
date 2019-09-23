#!/bin/bash

TUTNAME="junit"
DIR="cs1302-$TUTNAME"
if [ ! -d $DIR ]; then
  mkdir $DIR
  cd $DIR
  mkdir -p src bin doc test lib
  cd lib
  curl -O https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.5.2/junit-platform-console-standalone-1.5.2.jar
  cd ..
  
  echo "subdirectory $DIR successfully created"
else
  >&2 echo "subdirectory $DIR already exists"
fi  
