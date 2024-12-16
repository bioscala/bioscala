package bio.chemistry

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.language.postfixOps

class DNANucleotideSpec extends AnyFlatSpec with Matchers {
  import bio.nucleotide.DNA._

  "DNA nucleotides" should "print as characters" in {
    val a = A
    val c = C
    val g = G
    val t = T

    A.toString should equal ("a")
    a.toString should equal ("a")
    c.toString should equal ("c")
    g.toString should equal ("g")
    t.toString should equal ("t")
  }

  "Nucleotides" should "match cleanly" in {
    val a = A
    a should equal (A)
    (a == A) should equal (true)
    (a.toString == C.toString) should be(false)
    val m = a match {
       case A => true
       case _ => false
    }
    m should equal (true)
  }

  "Nucleotides" should "be listable" in {
    val seq = List[Nucleotide](A,G,C,T,T,A)
    seq.head should equal (A)
    (seq mkString) should equal("agctta")
    val seq2 = A :: seq
    (seq2 mkString) should equal("aagctta")
    // Introducing a widened type
    val seq3 = "z" :: seq
    (seq3 mkString) should equal("zagctta")
  }
}
