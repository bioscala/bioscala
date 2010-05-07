import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class SequenceTranslateSpec extends FlatSpec with ShouldMatchers {
    
    "Translate DNA sequence" should "translate to protein" in {
      new DNA.Sequence("agctaacga").translate should equal ("S*R")
    }

    "Translate RNA sequence" should "translate to protein" in {
      new RNA.Sequence("agcuaacga").translate should equal ("S*R")
    }
  }    
}
