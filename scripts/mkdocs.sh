#! /bin/bash

echo Use 'doc' command in sbt
exit 1
if [ ! -d doc ]; then
  echo "Run from root"
  exit 1
fi

src=src/main/scala/bio/
apidoc=doc/apidoc
mkdir -p $apidoc
if [ -d $apidoc/bio ]; then
  rm -rf $apidoc/bio
fi
cp -vau $src $apidoc
cd $apidoc
infiles=`find bio/ -name *.scala |xargs`
echo $infiles
scaladoc $infiles
cd ..
