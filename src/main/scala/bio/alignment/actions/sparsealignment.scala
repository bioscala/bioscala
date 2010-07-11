/**
 * Trait contains methods for modifying sparse alignments
 */

import bio._
import bio.attribute._

package bio {

  abstract trait SparseAlignment[T] {
    type GapType  // Abstract type
    val gap : GapType

    type Alignment = List[List[T]]  // where T is a bio.Symbol

    def transpose(m: Alignment) : Alignment = {
      if (m == Nil)
        Nil
      else
        if(m.head.isEmpty) Nil else m.map(_.head) :: transpose(m.map(_.tail))
    }

    /** 
     * Remove sparse rows, i.e. rows that contain less than min_symbols
     */
    def removeSparseRows(in: Alignment, min_symbols: Int) : (Alignment, List[Int]) = {
       // add an index to the list
       val indexed = in.zipWithIndex
       // filter on symbols
       val filtered = indexed.filter { case (list, index) => 
         val numgaps = list.count{ i => i == gap }
         val numsymbols = list.length - numgaps
         numsymbols >= min_symbols
       }
       // and put it all back together again
       val (ret , idx ) = List.unzip(filtered)
       val origidx = (0 to in.length-1).toList
       (ret, origidx -- idx)
    }

    /** 
     * Remove columns, i.e. columns that contain less than min_symbols
     */
    def removeSparseColumns(in: Alignment, min_symbols: Int) : (Alignment, List[Int]) = {
      // transpose matrix for optimal list performance
      val (tm, log) = removeSparseRows(transpose(in),min_symbols)
      (transpose(tm), log)
    }
  } // SparseAlignment

} // bio
