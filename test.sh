#! /bin/sh

classpath=~/.scala/scalatest-1.0.jar:/usr/share/java/jruby.jar:~/.scala/jruby-complete-1.5.0.RC1.jar

echo "Compile tests"
fsc -deprecation -cp $classpath test/ruby/bioruby_spec.scala 
fsc -deprecation -cp $classpath test/chemistry/nucleotide_spec.scala 
fsc -deprecation -cp $classpath test/sequence/ntsequence_spec.scala 
fsc -deprecation -cp $classpath test/suite.scala 

# Run the tests. The -o switch sends output to stdout.
scala -cp $classpath org.scalatest.tools.Runner -p . -o -s bio.test.FullBioTestSuite

