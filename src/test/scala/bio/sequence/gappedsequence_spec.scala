import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  import bio.attribute._

  class GappedSequenceSpec extends FlatSpec with ShouldMatchers {
    import bio.DNA._
    "A GappedSequence" should "instantiate from a String" in {
      val s = new GappedSequence("agc--taacg---")
      s.toString should equal ("agc--taacg---")
    }
    "A GappedSequence" should "instantiate with an ID" in {
      val s = new GappedSequence("ID456","agctaacg")
      s.id should equal ("ID456")
    }
    "A GappedSequence" should "instantiate with an ID+Description" in {
      val s = new GappedSequence("ID456","Gene 456","agctaacg")
      s.description should equal ("Gene 456")
    }
  }
}
