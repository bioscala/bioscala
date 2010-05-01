#! /bin/sh

classpath=~/.scala/scalatest-1.0.jar
echo "Compile tests"
fsc -deprecation -cp $classpath test/chemistry/nucleotide_spec.scala 
fsc -deprecation -cp $classpath test/sequence/ntsequence_spec.scala 

scala -cp $classpath org.scalatest.tools.Runner -p . -o -s bio.test.DNANucleotideSpec
scala -cp $classpath org.scalatest.tools.Runner -p . -o -s bio.test.RNANucleotideSpec
scala -cp $classpath org.scalatest.tools.Runner -p . -o -s bio.test.DNASequenceSpec
scala -cp $classpath org.scalatest.tools.Runner -p . -o -s bio.test.RNASequenceSpec
