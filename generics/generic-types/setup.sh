#!/bin/bash

# MODIFIED FOR GENERICS DUE TO EXTRA DIRECTORY

TUTNAME="generic-types"
DIR="cs1302-$TUTNAME"
if [ ! -d $DIR ]; then
  git clone --depth 1 --no-checkout https://github.com/cs1302uga/cs1302-tutorials.git $DIR
  cd $DIR
  git checkout master -- generics/$TUTNAME
  rm -f generics/$TUTNAME/setup.sh
  mv generics/$TUTNAME/* ./
  mv generics/$TUTNAME/.gitignore ./
  rm -rf generics
  rm -rf .git
  git init
  git add .gitignore
  git commit -m "added .gitignore"
  git add .
  git commit -m "added the rest of the tutorial material"
  mkdir -p bin doc
  echo "subdirectory $DIR successfully created"
else
  >&2 echo "subdirectory $DIR already exists"
fi
