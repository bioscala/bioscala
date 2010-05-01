import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class DNASequenceSpec extends FlatSpec with ShouldMatchers {
    import bio.DNA._
    
    "DNA sequences" should "print as characters" in {
      val s = new DNASequence("agctaacg")
      s.toString should equal ("agctaacg")
      // Mixed case
      new DNASequence("agTA").toString should equal ("agta")
    }

    "DNA sequence" should "not accept strange input" in {
      evaluating { 
        val s = new DNASequence("acgtz") // fails
        s.mkString
      } should produce [IllegalArgumentException]
      evaluating { 
        val s = new DNASequence("acgu") // RNA fails
        s.mkString
      } should produce [IllegalArgumentException]
      // Just for fun, using a standard list with uppercase is not safe
      val l = "acgtz".toList.map { nt => nt.toUpperCase }
      l.mkString should equal ("ACGTZ")
    }

  }

  class RNASequenceSpec extends FlatSpec with ShouldMatchers {
    import bio.RNA._
    "RNA sequences" should "print as characters" in {
      val s = new RNASequence("agcuaacg")
      s.toString should equal ("agcuaacg")
      new RNASequence("agUA").toString should equal ("agua")
    }

    "RNA sequence" should "not accept strange input" in {
      evaluating { 
        val s = new RNASequence("acgut") // fails on t
        s.toString
      } should produce [IllegalArgumentException]
      // Just for fun, using a standard list with uppercase is not safe
      val l = "acgtz".toList.map { nt => nt.toUpperCase }
      l.mkString should equal ("ACGTZ")
    }
  }    
}
