/**
 * An Alignment has an (immutable) list of GappedSequences. A Sequence can
 * consist of nucleotides, amino acids and/or gaps. Each of these can have
 * attributes, sequences can have attributes and the Alignment can have
 * attributes.
 */

import bio._
import bio.attribute._

package bio {

  class Alignment[T](sequencelist: List[List[T]]) {
    val list = sequencelist
    // val attributes = attributelist

    type Aln = List[List[T]]
    // def this(list: Ali) = this(list,Nil)
    def toList : Aln = list

    def transpose(m: Aln) : Aln = {
      if (m == Nil)
        Nil
      else
        if(m.head.isEmpty) Nil else m.map(_.head) :: transpose(m.map(_.tail))
    }

    def getColumns(m: Aln) = transpose(m)
  }


  /**
   * Alignment container for Sequences. Each sequence has an 
   * ID that can not be lost. The reason for this class are
   * the functions that update columns. (NYI)
   */
  class SequenceAlignment[Sequence](sequencelist: List[Sequence]) {
    val list = sequencelist

    type Aln = List[Sequence]
    def toList : Aln = list

    // def blankColumnIf(f : (_) => _ ) = { f(_) }

  }

  package DNA {
    object SparseAlignment extends SparseAlignment[NTSymbol] 
    { 
      type GapType = Gap
      val gap = Gap
    } 
  } // DNA
}
