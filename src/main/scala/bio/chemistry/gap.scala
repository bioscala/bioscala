/** 
 * A gap represents a gap in a GappedSequence, as used in an alignment. Gaps
 * can contain state - e.g. quality - through the attributes list.
 */

package bio {

  package DNA {

    sealed abstract class Gap extends NTSymbol
    case object Gap extends Gap {
      override def toString = "-"
    }

    object GappedNucleotideConvert {
    /** 
     * Create a Gap object from its character representation.
     */
    def fromChar(c: Char): NTSymbol = { 
      c.toLowerCase match {
        case '-' => Gap
        case  _  => 
          NucleotideConvert.fromChar(c)
      }
    }
    def fromString(s: String): List[NTSymbol] = s.toList.map { fromChar(_) }
    def fromList(list: List[NTSymbol]): List[NTSymbol] = {
      list.map { 
        _ match {
          case Gap => Gap
          case  _  => throw new IllegalArgumentException("Unexpected type")
          }
        }
      }
    }
  }

  class GappedConvert[T](Gap : T, char_converter: Char => T) {

  /** 
   * Create a Gap object from its character representation.
   */
  def fromChar(c: Char): T = { 
    c.toLowerCase match {
      case '-' => Gap
      case  _  => 
        // Protein.AminoAcidConvert.fromChar(c)
        char_converter(c)
    }
  }
  def fromString(s: String): List[T] = s.toList.map { fromChar(_) }
  def fromList(list: List[T]): List[T] = {
    list.map { 
      _ match {
        case Gap => Gap
        case  _  => throw new IllegalArgumentException("Unexpected type")
        }
      }
    }
  }
  package Protein {
    sealed abstract class Gap extends AASymbol
    case object Gap extends Gap {
      override def toString = "-"
    }

    object GappedConvert extends bio.GappedConvert[AASymbol](Gap,
        AminoAcidConvert.fromChar)
  }
}
