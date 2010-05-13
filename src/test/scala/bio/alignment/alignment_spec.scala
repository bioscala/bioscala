import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  import bio.attribute._

  class AlignmentSpec extends FlatSpec with ShouldMatchers {
    "An Alignment" should "instantiate from a list" in {
      val s1 = new DNA.GappedSequence("agc--taacg---")
      val s2 = new DNA.GappedSequence("agc---aaca---")
      val a = new Alignment(List(s1,s2))
      true
    }
  }
}
