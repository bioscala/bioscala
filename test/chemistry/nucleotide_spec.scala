import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class DNANucleotideSpec extends FlatSpec with ShouldMatchers {
  import bio.DNA._
  
  "Nucleotides" should "print as characters" in {
	  val a = A
	  val c = C
	  val g = G
	  val t = T
    a.toString should equal ("a")
    c.toString should equal ("c")
    g.toString should equal ("g")
    t.toString should equal ("t")
  }
}

class RNANucleotideSpec extends FlatSpec with ShouldMatchers {
  import bio.RNA._
  
  "Nucleotides" should "print as characters" in {
	  val a = A
	  val c = C
	  val g = G
	  val u = U
    a.toString should equal ("a")
    c.toString should equal ("c")
    g.toString should equal ("g")
    u.toString should equal ("u")
  }
}
