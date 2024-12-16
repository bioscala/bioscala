package bio.chemistry.Protein

import bio.chemistry.Protein.aminoacids.{C, D, E, G, H, K, N, P, Q, R, S, T, U}

object AminoAcidConvert extends StringConverter[AminoAcid] {

  /** AminoAcid factory: create a AminoAcid object from its
   * character representation. For example:
   *
   * <pre>
   * import bio._
   * val aa = AminoAcidConvert.fromChar('a')
   * </pre>
   */
  def fromChar(c: Char): AminoAcid = {
    c.toUpper match {
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
      case _   => throw new IllegalArgumentException("Unexpected value for AminoAcid " + c)
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
      case _ =>
        throw new IllegalArgumentException(
          "Can not construct AminoAcid from unknown " + i + " type (should be AminoAcid) " + i.getClass.getName
        )
    }
  }
}
