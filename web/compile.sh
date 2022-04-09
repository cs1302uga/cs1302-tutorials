#!/bin/bash -e

function maven() {
    local args=$@
    ( # begin subshell
        set -x
        mvn -q -e "$@"
    ) # end subshell
} # maven

function mvn_clean() {
    maven clean "$@"
} # clean

function mvn_compile() {
    maven compile "$@"
} # compile

function mvn_exec_java() {
    if [[ $# -gt 0 ]]; then
        MAIN_CLASS=-Dexec.mainClass="$1";
        ARGS=-Dexec.args=$*
        maven -Dprism.order=sw exec:java "${MAIN_CLASS}" "${ARGS}"
    else
        maven -Dprism.order=sw exec:java
    fi
} # exec

mvn_clean
mvn_compile
mvn_exec_java
