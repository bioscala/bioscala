/*
 * Nucleotides (abreviated nt) are molecules that, when joined together, make
 * up the structural units of RNA and DNA. In addition, nucleotides play
 * central roles in metabolism.
 *
 * The purines are adenine (A) and guanine (G).
 * The (complementary) pyrimidines are thymine (T) and cytosine (C).
 * In RNA uracil (U) is the complementary pyrimidine of adenine.
 *
 */

package bio {

  sealed abstract class Nucleotide {
    def fromChar(c : Char) : Char = {
      c
    }
  }

  package DNA {
    case object A extends Nucleotide {
      override def toString() = "a"
    }
    case object G extends Nucleotide {
      override def toString() = "g"
    }
    case object T extends Nucleotide {
      override def toString() = "t"
    }
    case object C extends Nucleotide {
      override def toString() = "c"
    }

    object NucleotideConvert {
      /** 
       * Nucleotide factory: create a Nucleotide object from its
       * character representation. For example:
       *
       * <pre>
       *   import bio.DNA._
       *   val nt = NucleotideConvert.fromChar('a')
       * </pre>
       */
      def fromChar(c: Char): Nucleotide = { 
        c.toLowerCase match {
          case 'a' => A
          case 'c' => C
          case 'g' => G
          case 't' => T
          case  _  => throw new IllegalArgumentException
        }
      }
    }
  }

  package RNA {
    case object A extends Nucleotide {
      override def toString() = "a"
    }
    case object G extends Nucleotide {
      override def toString() = "g"
    }
    case object U extends Nucleotide {
      override def toString() = "u"
    }
    case object C extends Nucleotide {
      override def toString() = "c"
    }

    object NucleotideConvert {
      /** 
       * Nucleotide factory: create a Nucleotide object from its
       * character representation. For example:
       *
       * <pre>
       *   import bio.RNA._
       *   val nt = NucleotideConvert.fromChar('a')
       * </pre>
       */
      def fromChar(c: Char): Nucleotide = { 
        c.toLowerCase match {
          case 'a' => A
          case 'c' => C
          case 'g' => G
          case 'u' => U
          case  _  => throw new IllegalArgumentException
        }
      }
    }
  }

}
