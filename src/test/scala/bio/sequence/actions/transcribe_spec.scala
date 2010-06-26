import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class SequenceTranscribeSpec extends FlatSpec with ShouldMatchers {
    
    "Transcribe DNA sequence" should "become RNA" in {
      new DNA.Sequence("agctaacga").transcribe.toString should equal (new RNA.Sequence("agcuaacga").toString)
    }

    "Transcribe RNA sequence" should "do nothing" in {
      new RNA.Sequence("agcuaacga").transcribe.toString should equal (new RNA.Sequence("agcuaacga").toString)
    }

    "Complement DNA sequence" should "give complement" in {
      new DNA.Sequence("agct").complement.mkString should equal ("tcga")
    }
    "Complement RNA sequence" should "give complement" in {
      new DNA.Sequence("agct").complement.mkString should equal ("tcga")
    }
  }    
}
