
import sbt._

class BioScalaProject(info: ProjectInfo) extends DefaultProject(info)
{
  override def unmanagedClasspath = super.unmanagedClasspath +++ ("lib" / "scala" / "biojava" / "core" / "3.0-SNAPSHOT" / "core-3.0-SNAPSHOT.jar") 

  val scalaToolsSnapshots = "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots"                                                
  val scalatest = "org.scalatest" % "scalatest" % "1.0"                                               
  override def testOptions = ExcludeTests("bio.test.FullBioTestSuite" :: Nil) :: super.testOptions.toList
  // Modify this line to run single tests
  // override def includeTest(s: String) = { s == "bio.test.BioRubySpec" }
  val properties = System.getProperties()


  // This user only runs a subset of tests:
  val user = properties.get("user.name")
  override def includeTest(s: String) = { 
    if (user == "wrk") {
      s.indexOf("Sequence")>0 || s.indexOf("Reader")>0 || s.indexOf("Alignment")>0
    }
    else
      true
    }
}


