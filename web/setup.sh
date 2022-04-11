#!/bin/bash

if [ ! -d "cs1302-web" ]; then
  git clone --depth 1 --no-checkout https://github.com/cs1302uga/cs1302-tutorials.git cs1302-web
  cd cs1302-web
  git checkout master -- web
  mv web/* ./
  rm -f setup.sh
  rm -rf web
  rm -rf .git
  git init
  git add .
  git commit -m "initial commit"
  echo "subdirectory cs1302-web successfully created"
else
  >&2 echo "subdirectory cs1302-web already exists"
fi
