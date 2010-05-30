import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  import bio.attribute._

  class CodonSequenceSpec extends FlatSpec with ShouldMatchers {
    import bio.Protein._
    "A CodonSequence" should "instantiate from a DNA String" in {
      val s = new CodonSequence("agctaacgt")
      s.toString should equal ("NNN")
    }
    "A CodonSequence" should "show DNA codons" in {
      val s = new CodonSequence("agctaacgt")
      s.toDNA.toString should equal ("agctaacgt")
    }
    "A CodonSequence" should "instantiate from a RNA String" in {
      val s = new CodonSequence("agcuaacgu")
      s.toString should equal ("NNN")
    }
    "A CodonSequence" should "show RNA codons" in {
      val s = new CodonSequence("agctaacgt")
      s.toRNA.toString should equal ("agcuaacgu")
    }
    "A CodonSequence" should "instantiate with an ID" in {
      val s = new Sequence("ID456","agctaacgt")
      s.id should equal ("ID456")
    }
    "A CodonSequence" should "instantiate with an ID+Description" in {
      val s = new Sequence("ID456","Gene 456","agctaacgt")
      s.description should equal ("Gene 456")
    }
  }
}
