import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  import bio.DNA._

  class RemoveSparseSpec extends FlatSpec with ShouldMatchers {
    "An alignment" should "remove sparse columns" in {
      // this also tests removeSparseRows and transpose
      val s1 = new GappedSequence("agc--taacg---")
      val s2 = new GappedSequence("agc---aaca---")
      val s3 = new GappedSequence("agc----aca---")
      val m = List(s1.toList,s2.toList,s3.toList)
      val (m1,log1) = SparseAlignment.removeSparseColumns(m,1)
      log1 should equal (List(3,4,10,11,12))
      m1.head.mkString should equal ("agctaacg")
      val (m2,log2) = SparseAlignment.removeSparseColumns(m,2)
      log2 should equal (List(3,4,5,10,11,12))
      m2.head.mkString should equal ("agcaacg")
      m2(1).mkString should equal ("agcaaca")
    }
  }
}
