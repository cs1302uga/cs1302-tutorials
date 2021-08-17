#!/bin/bash -e

if [[ -n ${1} ]]; then
    CAST_FILE=${1}
    SVG_FILE=${CAST_FILE/.cast/.svg}
    echo "Converting '${CAST_FILE}' to '${SVG_FILE}'..."
    cat ${CAST_FILE} | svg-term --out=${SVG_FILE} --window
    OLD_HEIGHT=$(xmlstarlet sel -t -v "/_:svg/@height" ${SVG_FILE})
    NEW_HEIGHT=$(echo "${OLD_HEIGHT} + 15.0" | bc)
    echo "Adjusting height to create a 15px bottom margin (${OLD_HEIGHT} => ${NEW_HEIGHT})..."
    xmlstarlet ed --inplace -u "/_:svg/@height" -v '{$NEW_HEIGHT}' ${SVG_FILE}
    echo "Done!"
else
    echo "USAGE: ${0} CAST_FILE"
    exit 1
fi
