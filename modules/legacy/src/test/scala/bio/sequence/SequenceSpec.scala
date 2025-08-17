package bio.sequence

import bio.DNA.{DNASequence, A}
import bio.attribute._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SequenceSpec extends AnyFlatSpec with Matchers {
  "A Sequence" should "instantiate from a String" in {
    val s = new DNASequence("agctaacg")
    s.toString should equal("agctaacg")
  }

  "A Sequence" should "generate a list of Nucleotides" in {
    val list = new DNASequence("agctaacg").toList
    list.head should equal(A)
  }

  "A Sequence" should "instantiate from a List[Nucleotide]" in {
    val list = new DNASequence("agctaacg").toList
    val s = new DNASequence(list)
    s.toString should equal("agctaacg")
  }

  "A Sequence" should "instantiate from a Sequence" in {
    val s1 = new DNASequence("agctaacg")
    val s2 = new DNASequence(s1)
    s2.toString should equal("agctaacg")
  }

  "A Sequence" should "instantiate with an ID" in {
    val s = new DNASequence("ID456", "agctaacg")
    s.id should equal("ID456")
  }

  "A Sequence" should "instantiate with an ID+Description" in {
    val s = new DNASequence("ID456", "Gene 456", "agctaacg")
    s.description should equal("Gene 456")
  }

  "A Sequence" should "generate XML" in {
    val s = new DNASequence("ID456", "Gene 456", "agctaacg")
    val xml = s.attribValues(GetXML, s.attributes)
    xml.mkString should equal("<Id>ID456</Id><Description>Gene 456</Description>")
  }

  "A Sequence" should "delete from a Sequence" in {
    val s = new DNASequence("agctaacg")
    s.delete(3, 3).toString should equal("agccg")
  }
}
