#!/bin/bash

if [ ! -d "cs1302-javadoc" ]; then
  git clone --depth 1 --no-checkout https://github.com/cs1302uga/cs1302-tutorials.git cs1302-javadoc
  cd cs1302-javadoc
  git checkout master -- javadoc
  mv javadoc/* ./
  rm -rf javadoc
  rm -rf .git
fi  
