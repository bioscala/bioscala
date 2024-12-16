package bio.alignment

import bio.chemistry.DNA.Gap
import bio.nucleotide.DNA.NTSymbol
import bio.sequence.DNA.GappedSequence
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should._

class AlignmentSpec extends AnyFlatSpec with Matchers {
  "An Alignment" should "instantiate from a list" in {
    val s1 = GappedSequence("agc--taacg---")
    val s2 = GappedSequence("agc---aaca---")
    val a = new Alignment(List(s1.toList, s2.toList))
    a.toList.head.mkString should equal("agc--taacg---")
    a.toList(1).mkString should equal("agc---aaca---")
  }

  "An Alignment" should "return columns" in {
    val s1 = GappedSequence("ag---ctaacaac")
    val s2 = GappedSequence("ag---caaacagt")
    val s3 = GappedSequence("ag--ccaaacgga")
    val a: Alignment[NTSymbol] = new Alignment(List(s1.toList, s2.toList, s3.toList))
    val t1 = a.transpose(a.toList)
    t1.head.mkString should equal("aaa")
    val t2 = a.getColumns(a.toList)
    t2(1).mkString should equal("ggg")

    // and look for SNPs
    def hasMultipleNucleotides(col: List[NTSymbol]) = col.toSet.count(_ != Gap) > 1

    val bools = t1 map hasMultipleNucleotides

    // functional approach
    val list = bools.zipWithIndex.map {
      case (true, i) => t1(i)
      case (false, _) => Nil
    }.filter(_ != Nil)

    list.head.mkString should equal("taa")
    list(3).mkString should equal("cta")
    val m1 = new Alignment(list).transpose(list)
    m1.head.mkString should equal("taac")
    m1(2).mkString should equal("agga")
  }

  "A SequenceAlignment" should "return columns" in {
    val s1 = GappedSequence("ag---ctaacaac")
    val s2 = GappedSequence("ag---caaacagt")
    val s3 = GappedSequence("ag--ccaaacgga")
    new SequenceAlignment(List(s1, s2, s3))
  }
}
