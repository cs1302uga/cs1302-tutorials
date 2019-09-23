#!/bin/bash

TUTNAME="junit"
JUNIT_VER="1.5.2"
JUNIT_JAR="junit-platform-console-standalone-$JUNIT_VER.jar"
JUNIT_URL="https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/$JUNIT_VER/$JUNIT_JAR"
DIR="cs1302-$TUTNAME"

if [ ! -d $DIR ]; then
  mkdir $DIR
  cd $DIR
  mkdir -p src bin doc test lib
  cd lib
  echo "Downloading $JUNIT_JAR"
  curl -s -O $JUNIT_URL
  cd ..
  echo "Downloading ConsoleLauncher"
  curl -s -O https://raw.githubusercontent.com/cs1302uga/cs1302-tutorials/master/junit/ConsoleLauncher
  chmod u+x ConsoleLauncher
  echo "subdirectory $DIR successfully created"
  echo "- JUnit $JUNIT_VER dependencies are in $DIR/lib/$JUNIT_JAR"
  echo "- while in $DIR, you can use './ConsoleLauncher' to run JUnit $JUNT_VER"
else
  >&2 echo "subdirectory $DIR already exists"
fi  
