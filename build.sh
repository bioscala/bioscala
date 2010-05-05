#! /bin/sh
#
#  Compile script (temporary)

classpath=~/.scala/scalatest-1.0.jar:/usr/share/java/jruby.jar:~/.scala/jruby-complete-1.5.0.RC1.jar:~/.scala/biojava.jar

echo "Compile Rubyadapters"
mkdir -p bio/ruby
cd src/bio/ruby
jrubyc --java rbsequence.rb
echo "package bio.ruby;"|cat - RbSequence.java > x.java && mv x.java RbSequence.java
javac -cp $classpath RbSequence.java
mv RbSequence.class ../../../bio/ruby/

echo "Compile Scala library"
fsc -deprecation src/bio/chemistry/nucleotide.scala src/bio/sequence/*.scala

