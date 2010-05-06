import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class SequenceTranslateSpec extends FlatSpec with ShouldMatchers {
    
    "Transcribe DNA sequence" should "become RNA" in {
      new DNA.Sequence("agctaacga").transcribe.toString should equal (new RNA.Sequence("agcuaacga").toString)
    }

    "Transcribe RNA sequence" should "do nothing" in {
      new RNA.Sequence("agcuaacga").transcribe.toString should equal (new RNA.Sequence("agcuaacga").toString)
    }

    "Translate DNA sequence" should "translate to protein" in {
      new DNA.Sequence("agctaacga").translate should equal ("S*R")
    }

    "Translate RNA sequence" should "translate to protein" in {
      new RNA.Sequence("agcuaacga").translate should equal ("S*R")
    }
  }    
}
