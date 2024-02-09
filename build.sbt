organization := "bioscala"

name := "bioscala"

version := "0.2"

scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.18" % "test",
  "org.biojava" % "biojava3-core" % "3.0",
  "org.biojava" % "phylo" % "1.9.7",
  "org.biojava" % "sequencing" % "1.9.7",
  "org.biojava" % "blast" % "1.9.7"
)

parallelExecution := false
