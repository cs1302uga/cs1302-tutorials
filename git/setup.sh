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
  touch sherlock.txt
  git add sherlock.txt
  git commit -m "empty sherlock story"
  git checkout -b sherlock
  echo "SHERLOCK HOLMES" >> sherlock.txt
  echo "" >> sherlock.txt
  git commit -am "started writing sherlock holmes"
  git checkout master
  echo "started some writing" >> notes.md
  git add notes.md
  git commit -m "added some notes"
  git checkout sherlock
  wget -q https://www.gutenberg.org/cache/epub/1661/pg1661.txt
  split -d -l 100 pg1661.txt
  rm -f pg1661.txt
  for FILE in x*; do
    cat $FILE >> sherlock.txt
    git commit -am "added sherlock part $FILE"
  done
  rm -f x*
  git checkout master
  git merge sherlock
  git log --all --decorate --oneline --graph  
  echo "subdirectory $DIR successfully created"
else
  >&2 echo "subdirectory $DIR already exists"
fi  
