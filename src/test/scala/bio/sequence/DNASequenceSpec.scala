package bio.sequence

import bio.{DNA, RNA}
import bio.DNA.DNASequence
import bio.attribute.{Description, Id}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DNASequenceSpec extends AnyFlatSpec with Matchers {
  "DNA sequences" should "print as characters" in {
    val s = new DNASequence("agctaacg")
    s.toString should equal("agctaacg")
    // Mixed case
    new DNASequence("agTA").toString should equal("agta")
  }

  "DNA Sequence" should "not accept strange input" in {
    an[IllegalArgumentException] should be thrownBy {
      val s = new DNASequence("acgtz") // fails
      s.toString
    }

    an[IllegalArgumentException] should be thrownBy {
      val s = new DNASequence("acgu") // RNA fails
      s.toString
    }

    // Just for fun, using a standard list with uppercase is not safe
    val l = "acgtz".toList.map { nt => nt.toUpper }
    l.mkString should equal("ACGTZ")
  }

  "DNA Sequence" should "not be instantiated from RNA" in {
    val rna = new RNA.RNASequence("agucc")
    an[IllegalArgumentException] should be thrownBy {
      val s = new DNA.DNASequence(rna.toString)
    }
  }

  "DNA Sequence" should "instantiate from a Sequence" in {
    val s1 = new DNASequence("agctaacg")
    val s2 = new DNASequence(s1)
    s2.toString should equal("agctaacg")
  }

  "A DNA Sequence" should "allow two IDs" in {
    val s = new DNA.DNASequence("ID456", "Gene 456", "agctaacg")
    val s2 = s.attrAdd(Id("Pubmed:456"))
    s2.idList === List("ID456", "Pubmed:456")
  }

  "A DNA Sequence" should "allow multiple IDs" in {
    val s = new DNA.DNASequence("ID456", "Gene 456", "agctaacg")
    val s2 = s.attrAdd(List(Id("GEO:456"), Id("Pubmed:456")))
    s2.idList === List("ID456", "Geo:456", "Pubmed:456")
  }

  "A DNA Sequence" should "allow multiple Descriptions" in {
    val s = new DNA.DNASequence("ID456", "Gene 456", "agctaacg")
    val s2 = s.attrAdd(Description("Pubmed description"))
    s2.descriptionList === (List("Gene 456", "Pubmed description"))
  }
}
