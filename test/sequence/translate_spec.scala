import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class SequenceTranslateSpec extends FlatSpec with ShouldMatchers {
    import bio.DNA._
    
    "DNA sequence" should "translate to protein" in {
      new Sequence("agctaacga").translate should equal ("S*R")
    }

    "RNA sequence" should "translate to protein" in {
      new Sequence("agcuaacga").translate should equal ("CGT")
    }
  }    
}
