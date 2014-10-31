/**
 * Nucleotides (abreviated nt) are molecules that, when joined together, make
 * up the structural units of RNA and DNA. In addition, nucleotides play
 * central roles in metabolism.
 * <p>
 * The purines are adenine (A) and guanine (G).  The (complementary)
 * pyrimidines are thymine (T) and cytosine (C).  In RNA uracil (U) is the
 * complementary pyrimidine of adenine.
 * <p>
 * In the bio::DNA and bio::RNA packages special objects of individual
 * nucleotides are represented, to enforce strong type checking. These objects
 * can be converted, back and forth, to the character representation.
 */

package bio {

  /** Base class for all (one letter) symbols used in BioScala.
   *
   */
  abstract class Symbol {
    /** Get the Symbol name by parsing the class name, which looks 
     *  like 'bio.Protein.X$'. The symbol is the character before last.
     */
    override def toString : String = {
      getClass.getName.toList.takeRight(2).head.toString
    }
    lazy val toChar = toString.last
  }

  package DNA {

    abstract class NTSymbol extends Symbol
    sealed abstract class Nucleotide extends NTSymbol

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

    object NucleotideConvert extends StringConverter[Nucleotide] {
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
        c.toLower match {
          case 'a' => A
          case 'c' => C
          case 'g' => G
          case 't' => T
          case  _  => throw new IllegalArgumentException("Unexpected value for nucleotide "+c)
        }
      }
      def fromItem(i: Nucleotide): Nucleotide = {
        i match {
          case A => A
          case C => C
          case G => G
          case T => T 
          case  _  => throw new IllegalArgumentException("Can not construct DNA from unknown "+i+" type (should be DNA) "+i.getClass.getName)
        }
      }
    } // NucleotideConvert

    object SymbolConvert extends StringConverter[NTSymbol] {
      /** 
       * Nucleotide factory: create a Nucleotide object from its
       * character representation. For example:
       *
       * <pre>
       *   import bio.DNA._
       *   val nt = NucleotideConvert.fromChar('a')
       * </pre>
       */
      def fromChar(c: Char): NTSymbol = { 
        c.toLower match {
          case 'a' => A
          case 'c' => C
          case 'g' => G
          case 't' => T
          case 'n' => N
          case '-' => Gap
          case  _  => throw new IllegalArgumentException("Unexpected value for nucleotide "+c)
        }
      }
      def fromItem(i: NTSymbol): NTSymbol = i
    } // NucleotideConvert

  } // DNA

  package RNA {
    abstract class NTSymbol extends Symbol
    sealed abstract class Nucleotide extends NTSymbol

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

    object NucleotideConvert extends StringConverter[Nucleotide] {
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
        c.toLower match {
          case 'a' => A
          case 'c' => C
          case 'g' => G
          case 'u' => U
          case  _  => throw new IllegalArgumentException("Unexpected value for nucleotide "+c)
        }
      }
      def fromItem(i: Nucleotide): Nucleotide = {
        i match {
          case A => A
          case C => C
          case G => G
          case U => U
          case  _  => throw new IllegalArgumentException("Can not construct DNA from unknown "+i+" type (should be DNA) "+i.getClass.getName)
        }
      }
    } // NucleotideConvert
    object SymbolConvert extends StringConverter[NTSymbol] {
      /** 
       * Nucleotide factory: create a Nucleotide object from its
       * character representation. For example:
       *
       * <pre>
       *   import bio.DNA._
       *   val nt = NucleotideConvert.fromChar('a')
       * </pre>
       */
      def fromChar(c: Char): NTSymbol = { 
        c.toLower match {
          case 'a' => A
          case 'c' => C
          case 'g' => G
          case 'u' => U 
          case 'n' => N
          case '-' => Gap
          case  _  => throw new IllegalArgumentException("Unexpected value for nucleotide "+c)
        }
      }
      def fromItem(i: NTSymbol): NTSymbol = i
    } // NucleotideConvert

  } // RNA
} // bio
