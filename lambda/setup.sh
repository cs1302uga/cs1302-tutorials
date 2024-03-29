#!/bin/bash

REPO="https://github.com/cs1302uga/cs1302-tutorials.git"
BRANCH="master"
PATHSPEC="lambda"
DIR="cs1302-${PATHSPEC}"

function git-fetch-pathspec() {
    local repository="$1"
    local branch="$2"
    local pathspec="$3"
    local directory="$4"
    (
        set -e
        git clone --depth 1 --no-checkout ${repository} ${directory}
        cd ${directory}
        git checkout ${branch} -- ${pathspec}
        mv ${pathspec}/* ./
        rm -rf .git setup.sh ${pathspec}
        mkdir bin doc
        git init
        git add -f *
        git commit -m "initial commit"
    )
} # git-fetch-pathspec

function compile-starter-code() {
    local directory="$1"
    (
        set -e
        cd ${directory}
        find src -type f -name "*.java" | xargs javac -d bin
        find src -type f -name "*.java" | xargs javadoc -cp bin -d doc
    )
} # compile-starter-code

function publish-doc() {
    local directory="$1"
    local reltarget="$2"
    local rellinkname="$3"
    local target="$(pwd)/${directory}/${reltarget}"
    local linkname="${HOME}/public_html/${rellinkname}"
    local hostname=$(hostname)
    local user="$(whoami)"
    local url="https://webwork.cs.uga.edu/~${user}/${rellinkname}"
    (
        if [[ ${hostname} == csci-odin.cs.uga.edu ]]; then
            if [[ ! -L ${linkname} ]]; then
                ln -s ${target} ${linkname}
                echo "   - doc: ${DIR}/doc"
                echo "     - ${linkname} -> ${target}"
                echo "     - ${url} -> ${linkname}"
            fi
        fi
    )
} # publish doc

if [ ! -d $DIR ]; then
    git-fetch-pathspec ${REPO} ${BRANCH} ${PATHSPEC} ${DIR}
    compile-starter-code ${DIR}
    echo ""
    echo "subdirectory ${DIR} successfully created"
    echo " - starter code:"
    echo "   - src: ${DIR}/src"
    echo "   - bin: ${DIR}/bin"
    echo "   - doc: ${DIR}/doc"
    publish-doc ${DIR} doc ${DIR}
    echo ""
else
  >&2 echo "subdirectory ${DIR} already exists"
fi
