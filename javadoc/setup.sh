#!/bin/bash

REPO=https://github.com/cs1302uga/cs1302-tutorials.git
BRANCH=alsi
FROMDIR=javadoc
TODIR=cs1302-javadoc

if [ ! -d "${TODIR}" ]; then
  git clone --depth 1 --no-checkout --branch ${BRANCH} ${REPO} ${TODIR}
  cd ${TODIR}
  git checkout ${BRANCH} -- ${FROMDIR}
  rm -f ${FROMDIR}/setup.sh
  mv ${FROMDIR}/* ./
  rm -rf ${FROMDIR}
  rm -rf .git
  mkdir bin doc
  if command -v tree; then 
    tree ${TODIR}
  fi
  echo "subdirectory ${TODIR} successfully created"
else
  >&2 echo "subdirectory ${TODIR} already exists"
  >&2 echo "if you want to start over, delete or rename the existing directory"
fi  
