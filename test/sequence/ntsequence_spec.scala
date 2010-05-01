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

    "DNA sequence" should "not accept strange input" in {
      evaluating { 
        val s = new Sequence("acgtz") // fails
        s.mkString
      } should produce [IllegalArgumentException]
      // Just for fun, using a standard list with uppercase is not safe
      val l = "acgtz".toList.map { nt => nt.toUpperCase }
      l.mkString should equal ("ACGTZ")
    }

  }

  // class RNASequenceSpec extends FlatSpec with ShouldMatchers {
  //   import bio.RNA._
  // }    
}
