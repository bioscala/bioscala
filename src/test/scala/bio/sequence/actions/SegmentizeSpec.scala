package bio.sequence.actions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import bio.DNA.{A, G, Gap, C, T}
import bio.sequence.DNA.SegmentizeGappedSequence

class SegmentizeSpec extends AnyFlatSpec with Matchers {


  "SegmentizeGappedSequence" should "split on Nucleotide" in {
    SegmentizeGappedSequence.split(List(A, G, Gap, Gap, C, T, Gap, T)) should
      equal(List(List(A, G), List(Gap, Gap), List(C, T), List(Gap), List(T)))
  }

  "SegmentizeGappedSequence" should "handle three" in {
    SegmentizeGappedSequence.split(List(A, G, T)) should equal(List(List(A, G, T)))
  }

  "SegmentizeGappedSequence" should "handle two" in {
    SegmentizeGappedSequence.split(List(A, G)) should equal(List(List(A, G)))
  }

  "SegmentizeGappedSequence" should "handle one" in {
    SegmentizeGappedSequence.split(List(A)) should equal(List(List(A)))
  }

  "SegmentizeGappedSequence" should "handle three gaps" in {
    SegmentizeGappedSequence.split(List(Gap, Gap, Gap)) should equal(List(List(Gap, Gap, Gap)))
  }

  "SegmentizeGappedSequence" should "handle two gaps" in {
    SegmentizeGappedSequence.split(List(Gap, Gap)) should equal(List(List(Gap, Gap)))
  }

  "SegmentizeGappedSequence" should "handle one gap" in {
    SegmentizeGappedSequence.split(List(Gap)) should equal(List(List(Gap)))
  }

}