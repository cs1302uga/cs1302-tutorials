#!/bin/bash

# setup info
REPO=https://github.com/cs1302uga/cs1302-tutorials.git
BRANCH=alsi
FROMDIR=javadoc
TODIR=cs1302-javadoc

FILESTOREMOVE=".git javadoc-figure.png javadoc-figure.pptx javadoc.md" # do not leave empty
DIRSTOMAKE="bin doc" # do not leave empty

if [ ! -d "${TODIR}" ]; then
  # get tutorial starter code
  git clone --depth 1 --no-checkout --branch ${BRANCH} ${REPO} ${TODIR}
  pushd ${TODIR} &>/dev/null
  git checkout ${BRANCH} -- ${FROMDIR}
  # clean up what we got
  rm -f ${FROMDIR}/setup.sh
  mv ${FROMDIR}/* ./
  rm -rf ${FROMDIR}
  rm -rf ${FILESTOREMOVE}
  # create bin and doc
  mkdir -p ${DIRSTOMAKE}
  popd &>/dev/null
  # if tree is available, show the directory
  if command -v tree &>/dev/null; then 
    tree ${TODIR}
  fi
  echo "subdirectory ${TODIR} successfully created"
else
  >&2 echo "subdirectory ${TODIR} already exists"
  >&2 echo "if you want to start over, delete or rename the existing directory"
fi  
