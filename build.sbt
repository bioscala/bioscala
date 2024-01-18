organization := "bioscala"

name := "bioscala"

version := "0.2"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  // Dependency at compilation-time only (not at runtime).
  "com.nativelibs4java" %% "scalaxy-streams" % "0.3.2" % "provided",

  "org.scalatest" %% "scalatest" % "3.2.17" % "test",
  "org.biojava" % "biojava3-core" % "3.0",
  "org.biojava" % "phylo" % "1.9.7",
  "org.biojava" % "sequencing" % "1.9.7",
  "org.biojava" % "blast" % "1.9.7"
)

parallelExecution := false
