#!/bin/bash

if [ ! -d "cs1302-interfaces" ]; then
  git clone --depth 1 --no-checkout https://github.com/cs1302uga/cs1302-tutorials.git cs1302-interfaces
  cd cs1302-interfaces
  git checkout master -- interfaces
  rm -f interfaces/setup.sh
  mv interfaces/* ./
  rm -rf interfaces
  rm -rf .git
  mkdir bin doc
  echo "subdirectory cs1302-interfaces successfully created"
else
  >&2 echo "subdirectory cs1302-interfaces already exists"
fi  
