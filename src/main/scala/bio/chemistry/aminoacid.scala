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

    abstract class AASymbol(attributelist: List[Attribute]) extends Symbol with AttributeAccess {
      lazy val attributes = attributelist
      def setCodon(nts: List[DNA.NTSymbol]) : AminoAcid = {
        X 
      }
      def getCodon = attribFirst(GetCodon,attributes)
    }
    sealed abstract class AminoAcid extends AASymbol(List())
    abstract class PositiveAminoAcid extends AminoAcid
    abstract class NegativeAminoAcid extends AminoAcid
    abstract class UnchargedAminoAcid extends AminoAcid
    abstract class SpecialAminoAcid extends AminoAcid
    abstract class HydrophobicAminoAcid extends AminoAcid

    case object R extends PositiveAminoAcid {
      val name="Arg"
      val fullname="Arginine"
      override def toString() = "R"
    }
    case object H extends PositiveAminoAcid {
      val name="His"
      val fullname="Histidine"
      override def toString() = "H"
    }
    case object K extends PositiveAminoAcid {
      val name="Lys"
      val fullname="Lysine"
      override def toString() = "K"
    }
    case object D extends NegativeAminoAcid {
      val name="Asp"
      val fullname="Aspartic Acid"
      override def toString() = "D"
    }
    case object E extends NegativeAminoAcid {
      val name="Glu"
      val fullname="Glutamic Acid"
      override def toString() = "E"
    }
    case object S extends UnchargedAminoAcid {
      val name="Ser"
      val fullname="Serine"
      override def toString() = "S"
    }
    case object T extends UnchargedAminoAcid {
      val name="Thr"
      val fullname="Threonine"
      override def toString() = "T"
    }
    case object N extends UnchargedAminoAcid {
      val name="Asn"
      val fullname="Asparagine"
      override def toString() = "N"
    }
    case object Q extends UnchargedAminoAcid {
      val name="Gln"
      val fullname="Glutamine"
      override def toString() = "Q"
    }
    case object C extends UnchargedAminoAcid {
      val name="Cys"
      val fullname="Cysteine"
      override def toString() = "C"
    }
    case object U extends UnchargedAminoAcid {
      val name="Sec"
      val fullname="Selenocysteine"
      override def toString() = "U"
    }
    case object G extends UnchargedAminoAcid {
      val name="Gly"
      val fullname="Glycine"
      override def toString() = "G"
    }
    case object P extends UnchargedAminoAcid {
      val name="Pro"
      val fullname="Proline"
      override def toString() = "P"
    }
    case object A extends HydrophobicAminoAcid {
      val name="Ala"
      val fullname="Alanine"
      override def toString() = "A"
    }
    case object I extends HydrophobicAminoAcid {
      val name="Ile"
      val fullname="Isoleucine"
      override def toString() = "I"
    }
    case object L extends HydrophobicAminoAcid {
      val name="Leu"
      val fullname="Leucine"
      override def toString() = "L"
    }
    case object M extends HydrophobicAminoAcid {
      val name="Met"
      val fullname="Methionine"
      override def toString() = "M"
    }
    case object F extends HydrophobicAminoAcid {
      val name="Phe"
      val fullname="Phenylalanine"
      override def toString() = "F"
    }
    case object W extends HydrophobicAminoAcid {
      val name="Trp"
      val fullname="Tryptophan"
      override def toString() = "W"
    }
    case object Y extends HydrophobicAminoAcid {
      val name="Tyr"
      val fullname="Tyrosine"
      override def toString() = "Y"
    }
    case object V extends HydrophobicAminoAcid {
      val name="Val"
      val fullname="Valine"
      override def toString() = "V"
    }
    case object * extends HydrophobicAminoAcid {
      val name="*"
      val fullname="Stop"
      override def toString() = "*"
    }
    object AminoAcidConvert {
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
        c.toUpperCase match {
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
      def fromString(s: String): List[AminoAcid] = s.toList.map { fromChar(_) }
      def fromList(list: List[AminoAcid]): List[AminoAcid] = {
        list.map { c =>
          c match {
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
            case  _  => throw new IllegalArgumentException("Can not construct AminoAcid from unknown "+c+" type (should be AminoAcid) "+c.getClass.getName)
             }
        }
      }
    }

    /* IUPAC */
    sealed abstract class IUPAC extends AminoAcid
    case object B extends IUPAC {
      val name="Asx"
      val fullname="Aspartic acid or Asparagine"
      override def toString() = "B"
    }
    case object Z extends IUPAC {
      val name="Glx"
      val fullname="Glutamine or Glutamic acid"
      override def toString() = "Z"
    }
    case object X extends IUPAC {
      val name="Xaa"
      val fullname="Any amino acid"
      override def toString() = "X"
    }

    object IUPACAminoAcidConvert {
      /** 
       * Create a IUPAC object from its character representation.
       */
      def fromChar(c: Char): AminoAcid = { 
        c.toUpperCase match {
          // case '-' => EmptyIUPAC
          case 'B' => B
          case 'Z' => Z
          case 'X' => X
          case  _  => 
            AminoAcidConvert.fromChar(c)
        }
      }
      def fromString(s: String): List[AminoAcid] = s.toList.map { fromChar(_) }
      def fromList(list: List[AminoAcid]): List[AminoAcid] = {
        list.map { aa =>
          aa match {
            case B => B
            case Z => Z
            case X => X
            case  _  => throw new IllegalArgumentException("Unexpected type "+aa+" type "+aa.getClass.getName)
            }
          }
        }
    }
  }
}

