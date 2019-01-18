if [[ "${PWD##*/}" == "cs1302-javadoc" ]]; then
  mv -r javadoc/* ./
  rm -f setup.sh
fi
