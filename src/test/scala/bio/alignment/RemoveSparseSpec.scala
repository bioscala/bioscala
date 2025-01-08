package bio.alignment

import org.scalatest.flatspec.AnyFlatSpec
import bio.DNA.SparseAlignment
import bio.sequence.DNA.GappedSequence
import org.scalatest.matchers.should.Matchers


class RemoveSparseSpec extends AnyFlatSpec with Matchers {
  "An alignment" should "remove sparse columns" in {
    // this also tests removeSparseRows and transpose
    val s1 = GappedSequence("agc--taacg---").seq
    val s2 = GappedSequence("agc---aaca---").seq
    val s3 = GappedSequence("agc----aca---").seq
    val m = List(s1,s2,s3)
    val (m1,log1) = SparseAlignment.removeSparseColumns(m,1)
    log1 should equal (List(3,4,10,11,12))
    m1.head.mkString should equal ("agctaacg")
    val (m2,log2) = SparseAlignment.removeSparseColumns(m,2)
    log2 should equal (List(3,4,5,10,11,12))
    m2.head.mkString should equal ("agcaacg")
    m2(1).mkString should equal ("agcaaca")
  }
}

