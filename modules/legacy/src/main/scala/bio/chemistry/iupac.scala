/**
 * IUPAC supports ambiguous data
 *
 * Symbol       Meaning      Nucleic Acid
 * ------------------------------------------
 * A            A            Adenine
 * C            C            Cytosine
 * G            G            Guanine
 * T            T            Thymine
 * U            U            Uracil
 * M          A or C
 * R          A or G
 * W          A or T
 * S          C or G
 * Y          C or T
 * K          G or T
 * V        A or C or G
 * H        A or C or T
 * D        A or G or T
 * B        C or G or T
 * X      G or A or T or C (is really unknown AA, so convert to N)
 * N      G or A or T or C
 *
 * For Amino Acids IUPAC adds to the base types (see aminoacid.scala):
 *
 * B.................Asx.................Aspartic acid or Asparagine
 * Z.................Glx.................Glutamine or Glutamic acid
 * X.................Xaa.................Any amino acid
 *
 *
 *
 */

package bio

package DNA {
  sealed abstract class IUPAC extends NTSymbol

  case object M extends IUPAC {
    override def toString = "m"
  }

  case object R extends IUPAC {
    override def toString = "r"
  }

  case object W extends IUPAC {
    override def toString = "w"
  }

  case object S extends IUPAC {
    override def toString = "s"
  }

  case object Y extends IUPAC {
    override def toString = "y"
  }

  case object K extends IUPAC {
    override def toString = "k"
  }

  case object V extends IUPAC {
    override def toString = "v"
  }

  case object H extends IUPAC {
    override def toString = "h"
  }

  case object D extends IUPAC {
    override def toString = "d"
  }

  case object B extends IUPAC {
    override def toString = "b"
  }

  case object N extends IUPAC {
    override def toString = "n"
  }

  object IUPACNucleotideConvert extends StringConverter[NTSymbol] {
    /**
     * Create a IUPAC object from its character representation.
     */
    def fromChar(c: Char): NTSymbol = {
      c.toLower match {
        // case '-' => EmptyIUPAC
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
        case 'x' => N // convert to N
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
        case T => T
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
  } // Converter
} // DNA

package RNA {
  sealed abstract class IUPAC extends NTSymbol

  case object M extends IUPAC {
    override def toString = "m"
  }

  case object R extends IUPAC {
    override def toString = "r"
  }

  case object W extends IUPAC {
    override def toString = "w"
  }

  case object S extends IUPAC {
    override def toString = "s"
  }

  case object Y extends IUPAC {
    override def toString = "y"
  }

  case object K extends IUPAC {
    override def toString = "k"
  }

  case object V extends IUPAC {
    override def toString = "v"
  }

  case object H extends IUPAC {
    override def toString = "h"
  }

  case object D extends IUPAC {
    override def toString = "d"
  }

  case object B extends IUPAC {
    override def toString = "b"
  }

  case object N extends IUPAC {
    override def toString = "n"
  }

  object IUPACNucleotideConvert extends StringConverter[NTSymbol] {
    /**
     * Create a IUPAC object from its character representation.
     */
    def fromChar(c: Char): NTSymbol = {
      c.toLower match {
        // case '-' => EmptyIUPAC
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
        case 'x' => N // convert to N
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
  } // IPACNucleotideConverter
} // RNA
