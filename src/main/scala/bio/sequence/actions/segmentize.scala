/**
 * Segmentize Sequence
 */

package bio
import org.biojava.bio.symbol._
import org.biojava.bio.seq._

class SegmentizeGappedSequence[T](val gap: T) {
  /**
   * Split a sequence into gapped/non-gapped segments
   */
  def split(seq: List[T]): List[List[T]] = {
    def isGapType[Q >: T](c: Q) = (c == gap)
    def isMatch(c: T) = { if (isGapType(seq(0))) isGapType(c) else !isGapType(c) }
    val (s, tail) = seq.span { isMatch }
    tail match {
      case Nil => s :: Nil
      case _   => s :: split(tail)
    }
  }
} // class

package DNA {
  object SegmentizeGappedSequence extends SegmentizeGappedSequence[NTSymbol](Gap)
}
