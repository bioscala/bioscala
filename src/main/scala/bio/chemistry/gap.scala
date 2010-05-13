/** 
 * A gap represents a gap in a GappedSequence, as used in an alignment.  For
 * Gaps that have some type of state use DegenerateSequence.
 */

package bio {

  sealed abstract class Gap extends Symbol
  case object EmptyGap extends Gap {
    override def toString = "-"
  }

  object GapConvert {
    /** 
     * Create a Gap object from its character representation.
     */
    def fromChar(c: Char): Gap = { 
      c.toLowerCase match {
        case '-' => EmptyGap
        case  _  => throw new IllegalArgumentException("Unexpected value for Gap "+c)
      }
    }
    def fromString(s: String): List[Gap] = s.toList.map { fromChar(_) }
    def fromList(list: List[Gap]): List[Gap] = {
      list.map { 
        _ match {
          case EmptyGap => EmptyGap
          case  _  => throw new IllegalArgumentException("Unexpected type")
          }
        }
      }
  }
  package DNA {
    object GappedNucleotideConvert {
      /** 
       * Create a Gap object from its character representation.
       */
      def fromChar(c: Char): Symbol = { 
        c.toLowerCase match {
          case '-' => EmptyGap
          case  _  => 
            NucleotideConvert.fromChar(c)
        }
      }
      def fromString(s: String): List[Symbol] = s.toList.map { fromChar(_) }
      def fromList(list: List[Symbol]): List[Symbol] = {
        list.map { 
          _ match {
            case EmptyGap => EmptyGap
            case  _  => throw new IllegalArgumentException("Unexpected type")
            }
          }
        }
    }
  }
}
