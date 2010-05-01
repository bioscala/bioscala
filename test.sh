#! /bin/sh

classpath=~/.scala/scalatest-1.0.jar
echo "Compile tests"
fsc -cp $classpath test/chemistry/nucleotide_spec.scala 

scala -cp $classpath org.scalatest.tools.Runner -p . -o -s bio.test.DNANucleotideSpec
scala -cp $classpath org.scalatest.tools.Runner -p . -o -s bio.test.RNANucleotideSpec
