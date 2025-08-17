organization := "bioscala"

name := "bioscala"

version := "0.2"

scalaVersion := "2.12.18"

lazy val common = Seq(
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.2.17" % "test",
    "org.biojava" % "biojava3-core" % "3.0",
    "org.biojava" % "phylo" % "1.9.7",
    "org.biojava" % "sequencing" % "1.9.7",
    "org.biojava" % "blast" % "1.9.7"
  )
)

lazy val root = (project in file("."))
  .aggregate(legacy, core, chemistry, sequence, ioFasta, ioPaml, algorithms, examples, testkit)
  .settings(name := "bioscala-root", publish / skip := true)

lazy val legacy =
  (project in file("modules/legacy"))
    .settings(
      name := "bioscala-legacy",
      Test / scalaSource := baseDirectory.value / "src" / "test" / "scala",
      Test / resourceDirectory := baseDirectory.value / "src" / "test" / "resources"
    )
    .settings(common)

lazy val core =
  (project in file("modules/core"))
    .settings(common)
    .settings(name := "bioscala-core")

lazy val chemistry =
  (project in file("modules/chemistry"))
    .settings(common)
    .settings(name := "bioscala-chemistry")
    .dependsOn(core)

lazy val sequence =
  (project in file("modules/sequence"))
    .settings(common)
    .settings(name := "bioscala-sequence")
    .dependsOn(core, chemistry)

lazy val ioFasta =
  (project in file("modules/io-fasta"))
    .settings(common)
    .settings(name := "bioscala-io-fasta")
    .dependsOn(core, sequence)

lazy val ioPaml =
  (project in file("modules/io-paml"))
    .settings(common)
    .settings(name := "bioscala-io-paml")
    .dependsOn(core, sequence)

lazy val algorithms =
  (project in file("modules/algorithms"))
    .settings(common)
    .settings(name := "bioscala-algorithms")
    .dependsOn(core, sequence, chemistry)

lazy val examples =
  (project in file("modules/examples"))
    .settings(common)
    .settings(name := "bioscala-examples", publish / skip := true)
    .dependsOn(core, sequence, chemistry, ioFasta, ioPaml, algorithms)

lazy val testkit =
  (project in file("modules/testkit"))
    .settings(common)
    .settings(name := "bioscala-testkit", publish / skip := true)
    .dependsOn(core, sequence)

parallelExecution := false

Test / fork := true

Test / javaOptions ++= Seq(
  "-Xms1g",
  "-Xmx4g",
  "-XX:+CMSClassUnloadingEnabled"
)
