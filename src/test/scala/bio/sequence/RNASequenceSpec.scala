package bio.sequence

import bio.attribute.{Description, Id}
import bio.sequence.DNA.DNASequence
import bio.sequence.RNA.RNASequence
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RNASequenceSpec extends AnyFlatSpec with Matchers {
  "RNA sequences" should "print as characters" in {
    val s = RNASequence("agcuaacg")
    s.toString should equal("agcuaacg")
    RNASequence("agUA").toString should equal("agua")
  }

  "RNA sequence" should "not accept strange input" in {
    an[IllegalArgumentException] should be thrownBy {
      val s = RNASequence("acgut") // fails on t
      s.toString
    }

    // Just for fun, using a standard list with uppercase is not safe
    val l = "acgtz".toList.map { nt => nt.toUpper }
    l.mkString should equal("ACGTZ")
  }

  "RNA Sequence" should "not be instantiated from DNA" in {
    val dna = DNASequence("agtcc")
    an[IllegalArgumentException] should be thrownBy {
      RNASequence(dna.toList.mkString)
    }
  }

  "RNA Sequence" should "instantiate from a Sequence" in {
    val s1 = RNASequence("agcuaacg")
    val s2 = RNASequence(s1)
    s2.toString should equal("agcuaacg")
  }

  "An RNA Sequence" should "instantiate with an ID+Description" in {
    val s = RNASequence("ID456", "Gene 456", "agcuaacg")
    s.description should equal("Gene 456")
  }

  "A RNA Sequence" should "allow multiple IDs" in {
    val s = RNASequence("ID456", "Gene 456", "agcuaacg")
    val s2 = s.attrAdd(Id("Pubmed:456"))
    s2.idList === List("ID456", "Pubmed:456")
  }

  "A RNA Sequence" should "allow multiple Descriptions" in {
    val s = RNASequence("ID456", "Gene 456", "agcuaacg")
    val s2 = s.attrAdd(List(Description("Pubmed description")))
    s2.descriptionList === List("Gene 456", "Pubmed description")
  }
}
