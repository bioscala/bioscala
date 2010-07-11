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

    def removeSparseRows(in: Alignment, min_symbols: Int) : (Alignment, List[Int]) = {
       def scanRow(in: Alignment, log: List[Int], rownum : Int) : (Alignment, List[Int]) = {
         println(in, log)
         in match {
           case Nil         => (Nil, Nil)
           case row :: tail =>
             val numgaps = row.count{ i => i == gap }
             val numsymbols = row.length - numgaps
             if (numsymbols >= min_symbols) {
               val (m, log2) = scanRow(tail, log, rownum+1)
               (row :: m, log2)
             }
             else 
               // remove row, return rest
               scanRow(tail, rownum :: log, rownum+1)
         }
       }
       scanRow(in, List(), 0)
    }

    def removeSparseColumns(in: Alignment, min_symbols: Int) : (Alignment, List[Int]) = {
      val (tm, log) = removeSparseRows(transpose(in),min_symbols)
      (transpose(tm), log)
    }
  } // SparseAlignment

} // bio
