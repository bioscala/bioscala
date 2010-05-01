import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class DNASequenceSpec extends FlatSpec with ShouldMatchers {
    import bio.DNA._
    
    "DNA sequences" should "print as characters" in {
      val s = new Sequence("agctaacg")
      s.toString should equal ("agctaacg")
    }

  }

  // class RNASequenceSpec extends FlatSpec with ShouldMatchers {
  //   import bio.RNA._
  // }    
}
