organization := "bioscala"

name := "bioscala"

version := "0.2"

scalaVersion := "2.11.7"

// Dependency at compilation-time only (not at runtime).
libraryDependencies += "com.nativelibs4java" %% "scalaxy-streams" % "0.3.2" % "provided"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"

libraryDependencies += "org.biojava" % "biojava3-core" % "3.0"

libraryDependencies += "org.biojava" % "phylo" % "1.9.1"

libraryDependencies += "org.biojava" % "sequencing" % "1.9.1"

libraryDependencies += "org.biojava" % "blast" % "1.9.1"

parallelExecution := false
