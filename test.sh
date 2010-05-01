#! /bin/sh

echo "Compile tests"
fsc -cp ~/.scala/scalatest-1.0.jar test/chemistry/nucleotide_spec.scala 

scala -cp ~/.scala/scalatest-1.0.jar org.scalatest.tools.Runner -p . -o -s DNANucleotideSpec
scala -cp ~/.scala/scalatest-1.0.jar org.scalatest.tools.Runner -p . -o -s RNANucleotideSpec
