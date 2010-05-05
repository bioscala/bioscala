#! /bin/sh

# scalac_options="-deprecation -verbose"
scalac_options="-deprecation"
classpath=~/.scala/scalatest-1.0.jar:/usr/share/java/jruby.jar:~/.scala/jruby-complete-1.5.0.RC1.jar

echo "Compile tests"
fsc $scalac_options -cp $classpath test/ruby/bioruby_spec.scala 
fsc $scalac_options -cp $classpath test/chemistry/nucleotide_spec.scala 
fsc $scalac_options -cp $classpath test/sequence/*_spec.scala 
fsc $scalac_options -cp $classpath test/suite.scala 

# Run the tests. The -o switch sends output to stdout.
echo "Run tests"
scala -cp $classpath org.scalatest.tools.Runner -p . -o -s bio.test.FullBioTestSuite

