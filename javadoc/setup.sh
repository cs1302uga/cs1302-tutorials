if [[ "${PWD##*/}" == "cs1302-javadoc" ]]; then
  mv javadoc/* ./
  rm -r javadoc
  rm -f setup.sh
fi
