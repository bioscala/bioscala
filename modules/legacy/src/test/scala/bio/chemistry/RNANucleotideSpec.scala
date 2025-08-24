package bio.chemistry

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RNANucleotideSpec extends AnyFlatSpec with Matchers {
  import bio.RNA._

  "RNA nucleotides" should "act as DNA nucleotides and print as characters" in {
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
