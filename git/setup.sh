#!/bin/bash

TUTNAME="git"
DIR="cs1302-$TUTNAME"
if [ ! -d $DIR ]; then
  git clone --depth 1 --no-checkout https://github.com/cs1302uga/cs1302-tutorials.git $DIR
  cd $DIR
  git checkout master -- $TUTNAME
  rm -f $TUTNAME/setup.sh
  mv $TUTNAME/* ./
  rm -rf $TUTNAME
  rm -rf .git
  git init
  git add .
  git commit -m "initial commit with starter files"
  git log --all --decorate --oneline --graph
  git status
  echo "subdirectory $DIR successfully created"
else
  >&2 echo "subdirectory $DIR already exists"
fi  
