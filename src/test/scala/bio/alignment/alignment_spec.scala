import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  import bio.attribute._

  class AlignmentSpec extends FlatSpec with ShouldMatchers {
    "An Alignment" should "instantiate from a list" in {
      val s1 = new DNA.GappedSequence("agc--taacg---")
      val s2 = new DNA.GappedSequence("agc---aaca---")
      val a = new Alignment(List(s1.toList,s2.toList))
      a.toList.head.mkString should equal ("agc--taacg---")
      a.toList(1).mkString should equal ("agc---aaca---")
    }
/*
    "An Alignment" should "take attributes" in {
      val s1 = new DNA.GappedSequence("agc--taacg---")
      val s2 = new DNA.GappedSequence("agc---aaca---")
      val a = new Alignment(List(s1,s2))
      // val d1 = new AlignmentColumn("Domain",0,3)
      // val d2 = new AlignmentColumn("Domain",6,9)
      // a.addAttr(d1)
      // a.addAttr(d2)
      a.toList.head.toString should equal ("agc--taacg---")
    }
*/
    "An Alignment" should "return columns" in {
      val s1 = new DNA.GappedSequence("ag---ctaacaa")
      val s2 = new DNA.GappedSequence("ag---caaacag")
      val s3 = new DNA.GappedSequence("ag--ccaaacgg")
      val a = new Alignment(List(s1.toList,s2.toList,s3.toList)) 
      val t = a.transpose(a.toList)
      t.toList.head.mkString should equal ("aaa")
      t.toList(1).mkString should equal ("ggg")
    }
  }
}
