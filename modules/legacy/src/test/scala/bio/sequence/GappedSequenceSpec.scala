package bio.sequence

import bio._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GappedSequenceSpec extends AnyFlatSpec with Matchers {

  import bio.DNA._

  "A DNA GappedSequence" should "instantiate from a String" in {
    val s = new GappedSequence("agc--taacg---")
    s.toString should equal("agc--taacg---")
  }

  "A DNA GappedSequence" should "instantiate with an ID" in {
    val s = new GappedSequence("ID456", "agctaacg")
    s.id should equal("ID456")
  }

  "A DNA GappedSequence" should "instantiate with an ID+Description" in {
    val s = new GappedSequence("ID456", "Gene 456", "agctaacg")
    s.description should equal("Gene 456")
  }

  "An AA GappedSequence" should "instantiate from a String" in {
    val s = new Protein.GappedSequence("SSI-ISNS---FSRP")
    s.toString should equal("SSI-ISNS---FSRP")
  }

  "An AA GappedSequence" should "instantiate with an ID" in {
    val s = new Protein.GappedSequence("ID456", "SSI-ISNS---FSRP")
    s.id should equal("ID456")
  }

  "An AA GappedSequence" should "instantiate with an ID+Description" in {
    val s = new Protein.GappedSequence("ID456", "Gene 456", "SSI-ISNS---FSRP")
    s.description should equal("Gene 456")
  }

  "A GappedCodonSequence" should "instantiate from a String" in {
    val s = new Protein.GappedCodonSequence("agc---taacgt")
    s.toString should equal("S-*R")
  }

  "A GappedCodonSequence" should "instantiate with an ID" in {
    val s = new Protein.GappedCodonSequence("ID456", "agctaacg")
    s.id should equal("ID456")
  }

  "A GappedCodonSequence" should "instantiate with an ID+Description" in {
    val s = new Protein.GappedCodonSequence("ID456", "Gene 456", "agctaacg")
    s.description should equal("Gene 456")
  }

  "A misaligned GappedCodonSequence" should "do something" in {
    val s = new Protein.GappedCodonSequence("agc----taacgt")
    s.toString should equal("S--T")
  }
}
