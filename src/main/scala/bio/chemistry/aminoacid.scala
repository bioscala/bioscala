/**
 *
 * Proteinogenic amino acids are 22 amino acids that are found in proteins and
 * require cellular machinery coded for in the genetic code [1] of any organism
 * for their isolated production. Humans can synthesize 10 of them (by
 * interconversions) from each other or from other molecules of intermediary
 * metabolism, but the other 10 (essential amino acids: arginine, histidine,
 * isoleucine, leucine, lysine, methionine, phenylalanine, threonine,
 * tryptophan, and valine) must be consumed in the diet.  Proteinogenic
 * literally means protein building. Proteinogenic amino acids can be assembled
 * into a polypeptide (the subunit of a protein) through a process known as
 * translation (the second stage of protein biosynthesis, part of the overall
 * process of gene expression) (from Wikipedia May 2010).
 * <p>
 * These amino acids belong to PostiveAminoAcid, NegativeAminoAcid,
 * UnchargedAminoAcid, SpecialAminoAcid and HydrophobicAminoAcid.
 * <p>
 * <pre>
 * For Amino Acids IUPAC adds to the base types:
 *
 * B.................Asx.................Aspartic acid or Asparagine
 * Z.................Glx.................Glutamine or Glutamic acid
 * X.................Xaa.................Any amino acid
 * </pre>
 */

import bio.attribute._

package bio {
  package Protein {

    abstract class AASymbol extends Symbol

    sealed abstract class AminoAcid(val name: String, val fullname: String) extends AASymbol
    abstract class PositiveAminoAcid(name: String, fullname: String)
      extends AminoAcid(name, fullname)
    abstract class NegativeAminoAcid(name: String, fullname: String)
      extends AminoAcid(name, fullname)
    abstract class UnchargedAminoAcid(name: String, fullname: String)
      extends AminoAcid(name, fullname)
    abstract class SpecialAminoAcid(name: String, fullname: String)
      extends AminoAcid(name, fullname)
    abstract class HydrophobicAminoAcid(name: String, fullname: String)
      extends AminoAcid(name, fullname)

    case object R extends PositiveAminoAcid("Arg", "Arginine")
    case object H extends PositiveAminoAcid("His", "Histidine")
    case object K extends PositiveAminoAcid("Lys", "Lysine")

    case object D extends NegativeAminoAcid("Asp", "Aspartic Acid")
    case object E extends NegativeAminoAcid("Glu", "Glutamic Acid")

    case object S extends UnchargedAminoAcid("Ser", "Serine")
    case object T extends UnchargedAminoAcid("Thr", "Threonine")
    case object N extends UnchargedAminoAcid("Asn", "Asparagine")
    case object Q extends UnchargedAminoAcid("Gln", "Glutamine")
    case object C extends UnchargedAminoAcid("Cys", "Cysteine")
    case object U extends UnchargedAminoAcid("Sec", "Selenocysteine")
    case object G extends UnchargedAminoAcid("Gly", "Glycine")
    case object P extends UnchargedAminoAcid("Pro", "Proline")

    case object A extends HydrophobicAminoAcid("Ala", "Alanine")
    case object I extends HydrophobicAminoAcid("Ile", "Isoleucine")
    case object L extends HydrophobicAminoAcid("Leu", "Leucine")
    case object M extends HydrophobicAminoAcid("Met", "Methionine")
    case object F extends HydrophobicAminoAcid("Phe", "Phenylalanine")
    case object W extends HydrophobicAminoAcid("Trp", "Tryptophan")
    case object Y extends HydrophobicAminoAcid("Tyr", "Tyrosine")
    case object V extends HydrophobicAminoAcid("Val", "Valine")

    case object * extends AminoAcid("*", "Stop") {
      override def toString = "*"
    }

    object AminoAcidConvert extends StringConverter[AminoAcid] {
      /**
       * AminoAcid factory: create a AminoAcid object from its
       * character representation. For example:
       *
       * <pre>
       *   import bio._
       *   val aa = AminoAcidConvert.fromChar('a')
       * </pre>
       */
      def fromChar(c: Char): AminoAcid = {
        c.toUpper  match {
          case 'R' => R
          case 'H' => H
          case 'K' => K
          case 'D' => D
          case 'E' => E
          case 'S' => S
          case 'T' => T
          case 'N' => N
          case 'Q' => Q
          case 'C' => C
          case 'U' => U
          case 'G' => G
          case 'P' => P
          case 'A' => A
          case 'I' => I
          case 'L' => L
          case 'M' => M
          case 'F' => F
          case 'W' => W
          case 'Y' => Y
          case 'V' => V
          case '*' => *
          case  _  => throw new IllegalArgumentException("Unexpected value for AminoAcid "+c)
        }
      }
      def fromItem(i: AminoAcid): AminoAcid = {
        i match {
          case R => R
          case H => H
          case K => K
          case D => D
          case E => E
          case S => S
          case T => T
          case N => N
          case Q => Q
          case C => C
          case U => U
          case G => G
          case P => P
          case A => A
          case I => I
          case L => L
          case M => M
          case F => F
          case W => W
          case Y => Y
          case V => V
          case * => *
          case  _  => throw new IllegalArgumentException("Can not construct AminoAcid from unknown "+i+" type (should be AminoAcid) "+i.getClass.getName)
        }
      }
    }

    /* IUPAC */
    sealed abstract class IUPAC(name: String, fullname: String) extends AminoAcid(name, fullname)
    case object B extends IUPAC("Asx", "Aspartic acid or Asparagine")
    case object Z extends IUPAC("Glx", "Glutamine or Glutamic acid")
    case object X extends IUPAC("Xaa", "Any amino acid")

    object SymbolConvert extends StringConverter[AASymbol] {
      def fromChar(c: Char): AASymbol = {
        c.toUpper match {
          // case '-' => EmptyIUPAC
          case '-' => Gap
          case  _  =>
            AminoAcidConvert.fromChar(c)
        }
      }
      def fromItem(i: AASymbol): AASymbol = {
        i match {
          case Gap => Gap
          case  _  => throw new IllegalArgumentException("Unexpected type "+i+" type "+i.getClass.getName)
        }
      }
    }

    object IUPACAminoAcidConvert extends StringConverter[AminoAcid] {
      /**
       * Create a IUPAC object from its character representation.
       */
      def fromChar(c: Char): AminoAcid = {
        c.toUpper match {
          case 'B' => B
          case 'Z' => Z
          case 'X' => X
          case  _  =>
            AminoAcidConvert.fromChar(c)
        }
      }
      def fromItem(i: AminoAcid): AminoAcid = {
        i match {
          case B   => B
          case Z   => Z
          case X   => X
          case  _  => throw new IllegalArgumentException("Unexpected type "+i+" type "+i.getClass.getName)
        }
      }
    }
    object IUPACGappedAminoAcidConvert extends StringConverter[AASymbol] {
      /**
       * Create a IUPAC object from its character representation.
       */
      def fromChar(c: Char): AASymbol = {
        c.toUpper match {
          case '-' => Gap
          case 'B' => B
          case 'Z' => Z
          case 'X' => X
          case  _  =>
            AminoAcidConvert.fromChar(c)
        }
      }
      def fromItem(i: AASymbol): AASymbol = {
        i match {
          case Gap => Gap
          case B   => B
          case Z   => Z
          case X   => X
          case  _  => throw new IllegalArgumentException("Unexpected type "+i+" type "+i.getClass.getName)
        }
      }
    }
  }
}
