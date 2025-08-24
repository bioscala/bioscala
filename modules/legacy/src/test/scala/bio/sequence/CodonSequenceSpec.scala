package bio.sequence

import bio._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import bio.Protein._

class CodonSequenceSpec extends AnyFlatSpec with Matchers {
  "A CodonSequence" should "instantiate from a DNA String" in {
    val s = new CodonSequence("agctaacgt")
    s.toString should equal("S*R")
  }
  "A CodonSequence" should "instantiate from a non-3-multiple DNA String" in {
    val s = new CodonSequence("agctaacg")
    s.toString should equal("S*")
  }
  "A CodonSequence" should "show DNA codons" in {
    val s = new CodonSequence("agctaacgt")
    s.toDNA.mkString should equal("agctaacgt")
  }
  "A CodonSequence" should "instantiate from an RNA String" in {
    val s = new CodonSequence("agcuaacgu")
    s.toString should equal("S*R")
  }
  "A CodonSequence" should "show RNA codons" in {
    val s = new CodonSequence("agctaacgt")
    s.toRNA.mkString should equal("agcuaacgu")
  }
  "A CodonSequence amino acid" should "return Codon" in {
    val s = new CodonSequence("agctaacgt")
    s(2).getCodon should equal(List(DNA.C, DNA.G, DNA.T))
  }
  "A CodonSequence" should "return Codon" in {
    val s = new CodonSequence("agctaacgt")
    s.getCodon(2) should equal(List(DNA.C, DNA.G, DNA.T))
  }
  "A CodonSequence" should "instantiate with an ID" in {
    val s = new CodonSequence("ID456", "agctaacgt")
    s.id should equal("ID456")
  }
  "A CodonSequence" should "instantiate with an ID+Description" in {
    val s = new CodonSequence("ID456", "Gene 456", "agctaacgt")
    s.description should equal("Gene 456")
  }
  "A CodonSequence" should "delete amino acid" in {
    val s = new CodonSequence("agctaacgt")
    s.delete(1, 1).toString should equal("SR")
  }
  "A CodonSequence" should "delete codon sequences" in {
    val s = new CodonSequence("agctaacgt")
    s.delete(1, 1).toDNA.mkString should equal("agccgt")
  }
}
