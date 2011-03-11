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
      val s1 = new DNA.GappedSequence("ag---ctaacaac")
      val s2 = new DNA.GappedSequence("ag---caaacagt")
      val s3 = new DNA.GappedSequence("ag--ccaaacgga")
      val a = new Alignment(List(s1.toList,s2.toList,s3.toList)) 
      val t = a.transpose(a.toList)
      t.toList.head.mkString should equal ("aaa")
      val t2 = a.getColumns(a.toList)
      t2.toList(1).mkString should equal ("ggg")
      // and look for SNPs
      def hasMultipleNucleotides(col: List[Symbol]) = {
        val uniquelist = col.removeDuplicates.filter { _ != DNA.Gap }
        // println(uniquelist.mkString,uniquelist.size>1)
        uniquelist.size>1
      }
      val bools = t map {  hasMultipleNucleotides }

      /* iterative approach:
      var idx = -1
      val list = bools.map { b =>
        idx += 1
        if (b) { t(idx) }
        else Nil
      }.filter( l =>  l.size > 0 ) 
      */
      // functional approach
      val list = bools.zipWithIndex.map { 
                   case(true, i) => t(i)
                   case(false, _) => Nil
                 }.filter( l =>  l.size > 0 ) 
      println(list)

      list.toList(0).mkString should equal ("taa")
      list.toList(3).mkString should equal ("cta")
      val m1 = new Alignment(list).transpose(list)
      m1.toList(0).mkString should equal ("taac")
      m1.toList(2).mkString should equal ("agga")
    }
  }
}
