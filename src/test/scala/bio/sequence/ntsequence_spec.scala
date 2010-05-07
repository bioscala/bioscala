import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  class SequenceSpec extends FlatSpec with ShouldMatchers {
    import bio.DNA._
    "A Sequence" should "instantiate from a String" in {
      val s1 = new Sequence("agctaacg")
      s1.toString should equal ("agctaacg")
    }
    "A Sequence" should "instantiate from a Sequence" in {
      val s1 = new Sequence("agctaacg")
      val s2 = new Sequence(s1)
      s2.toString should equal ("agctaacg")
    }
  }

  class DNASequenceSpec extends FlatSpec with ShouldMatchers {
    import bio.DNA._

    "DNA sequences" should "print as characters" in {
      val s = new Sequence("agctaacg")
      s.toString should equal ("agctaacg")
      // Mixed case
      new Sequence("agTA").toString should equal ("agta")
    }

    "DNA sequence" should "not accept strange input" in {
      evaluating { 
        val s = new Sequence("acgtz") // fails
        s.toString
      } should produce [IllegalArgumentException]
      evaluating { 
        val s = new Sequence("acgu") // RNA fails
        s.toString
      } should produce [IllegalArgumentException]
      // Just for fun, using a standard list with uppercase is not safe
      val l = "acgtz".toList.map { nt => nt.toUpperCase }
      l.mkString should equal ("ACGTZ")
    }

  }

  class RNASequenceSpec extends FlatSpec with ShouldMatchers {
    import bio.RNA._
    "RNA sequences" should "print as characters" in {
      val s = new Sequence("agcuaacg")
      s.toString should equal ("agcuaacg")
      new Sequence("agUA").toString should equal ("agua")
    }

    "RNA sequence" should "not accept strange input" in {
      evaluating { 
        val s = new Sequence("acgut") // fails on t
        s.toString
      } should produce [IllegalArgumentException]
      // Just for fun, using a standard list with uppercase is not safe
      val l = "acgtz".toList.map { nt => nt.toUpperCase }
      l.mkString should equal ("ACGTZ")
    }
  }    
}
