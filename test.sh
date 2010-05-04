#! /bin/sh

classpath=~/.scala/scalatest-1.0.jar
echo "Compile tests"
fsc -deprecation -cp $classpath test/suite.scala 
fsc -deprecation -cp $classpath test/chemistry/nucleotide_spec.scala 
fsc -deprecation -cp $classpath test/sequence/ntsequence_spec.scala 

# Run the tests. The -o switch sends output to stdout.
scala -cp $classpath org.scalatest.tools.Runner -p . -o -s bio.test.FullBioTestSuite

