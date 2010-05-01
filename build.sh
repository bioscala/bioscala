#! /bin/sh
#
#  Compile script (temporary)

echo "Compile library"
fsc -deprecation src/bio/chemistry/nucleotide.scala 

# echo "Compile tests"
# fsc -cp ~/.scala/scalatest-1.0.jar test/chemistry/nucleotide_spec.scala 


