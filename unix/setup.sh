#!/bin/bash

TUTNAME="unix"
DIR="cs1302-$TUTNAME"
if [ ! -d $DIR ]; then
  mkdir $DIR
  cd $DIR
  mkdir -p notes/cs1302
  mkdir -p notes/cs1302/ce00
  mkdir -p notes/cs1302/ce01
  mkdir -p notes/cs1730
  echo "# ce00 NOTES" > notes/cs1302/ce00/NOTES.md
  echo "# ce01 NOTES" > notes/cs1302/ce01/NOTES.md
  echo "# README" > README.md
  echo "subdirectory $DIR successfully created"
else
  >&2 echo "subdirectory $DIR already exists"
fi  
