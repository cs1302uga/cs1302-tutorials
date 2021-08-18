#!/bin/bash

TUTNAME="unix"
DIR="cs1302-$TUTNAME"
if [ ! -d $DIR ]; then
  mkdir $DIR
  cd $DIR
  mkdir -p notes/cs1302
  echo "# Unix Notes" > notes/cs1302/unix.md
  echo "# Java Notes" > notes/cs1302/java.md
  mkdir -p notes/cs1730
  echo "# C/C++ Notes" > notes/cs1302/cpp.md
  mkdir -p books
  wget -qO books/pride_and_prejudice.txt https://www.gutenberg.org/files/1342/1342-0.txt
  wget -qO books/sherlock_holmes.txt https://www.gutenberg.org/files/1661/1661-0.txt
  wget -qO books/moby_dick.txt https://www.gutenberg.org/files/2701/2701-0.txt
  echo "# README" > README.md
  echo "spooky file contents" > .spooky
  echo "some secret here" > .secret
  chmod 600 .secret
  echo "subdirectory $DIR successfully created"
else
  >&2 echo "subdirectory $DIR already exists"
fi
