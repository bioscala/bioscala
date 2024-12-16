package bio.chemistry.RNA

import bio.chemistry.Protein.StringConverter
import bio.nucleotide.RNA._

object IUPACNucleotideConvert extends StringConverter[NTSymbol] {
  /**
   * Create a IUPAC object from its character representation.
   */
  def fromChar(c: Char): NTSymbol = {
    c.toLower match {
      case 'm' => M
      case 'r' => R
      case 'w' => W
      case 's' => S
      case 'y' => Y
      case 'k' => K
      case 'v' => V
      case 'h' => H
      case 'd' => D
      case 'b' => B
      case 'x' => N
      case 'n' => N
      case _ =>
        NucleotideConvert.fromChar(c)
    }
  }

  def fromItem(i: NTSymbol): NTSymbol = {
    i match {
      case A => A
      case C => C
      case G => G
      case U => U
      case M => M
      case R => R
      case W => W
      case S => S
      case Y => Y
      case K => K
      case V => V
      case H => H
      case D => D
      case B => B
      case N => N
      case _ => throw new IllegalArgumentException("Unexpected type " + i + " type " + i.getClass.getName)
    }
  }
}
