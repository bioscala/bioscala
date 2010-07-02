
import sbt._

class BioScalaProject(info: ProjectInfo) extends DefaultProject(info)
{
  override def unmanagedClasspath = super.unmanagedClasspath +++ 
  ("lib" / "scala" / "biojava") +++
  ("lib" / "scala" / "biojava-1.7.1.jar" ) 
  // ("lib" / "scala" / "biojava" / "core-3.0-SNAPSHOT.jar") +++ ("lib" / "scala" / "biojava" / "alignment-3.0-SNAPSHOT.jar") +++ ("lib" / "scala" / "biojava" / "biojava3-phylo-3.0-SNAPSHOT.jar")
                                                                                                                                                     val scalaToolsSnapshots = "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots"                                                
  val scalatest = "org.scalatest" % "scalatest" % "1.0.1"                                               
  override def testOptions = ExcludeTests("bio.test.FullBioTestSuite" :: Nil) :: super.testOptions.toList
  // Modify this line to run single tests
  // override def includeTest(s: String) = { s == "bio.test.BioRubySpec" }
  val properties = System.getProperties()


  // This user only runs a subset of tests:
  val user = properties.get("user.name")
  override def includeTest(s: String) = { 
    if (user == "wrk") {
      s.indexOf("Sequence")>0 || s.indexOf("Writer")>0 || s.indexOf("Reader")>0 || s.indexOf("Alignment")>0 || s.indexOf("Segment") > 0
      // true
    }
    else
      true
    }
}


