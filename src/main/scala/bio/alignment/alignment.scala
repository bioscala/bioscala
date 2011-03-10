/**
 * An Alignment has an (immutable) list of GappedSequences. A Sequence can
 * consist of nucleotides, amino acids and/or gaps. Each of these can have
 * attributes, sequences can have attributes and the Alignment can have
 * attributes.
 */

import bio._
import bio.attribute._

package bio {

  // Seq is a Sequence type
  class Alignment[T](sequencelist: List[List[T]]) {
    val list = sequencelist
    // val attributes = attributelist

    type Ali = List[List[T]]
    // def this(list: Ali) = this(list,Nil)
    def toList : Ali = list

    def transpose(m: Ali) : Ali = {
      if (m == Nil)
        Nil
      else
        if(m.head.isEmpty) Nil else m.map(_.head) :: transpose(m.map(_.tail))
    }

    def getColumns(m: Ali) = transpose(m)

  }

  package DNA {
    object SparseAlignment extends SparseAlignment[NTSymbol] 
    { 
      type GapType = Gap
      val gap = Gap
    } 
  } // DNA
}
