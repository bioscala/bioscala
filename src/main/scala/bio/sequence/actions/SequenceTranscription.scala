package bio.sequence.actions

import bio.DNA.{A, C, G, T}

/** Sequence transcription and complement. Delegated by the Sequence object.
  * Note we take a functional approach (no reference to self).
  */
object SequenceTranscription {

  /** Convert DNA to RNA - replacing DNA.T with RNA.U. The 5'-3' order
    * is maintained
    */
  def toRNA(nucleotides: List[bio.DNA.Nucleotide]): List[bio.RNA.Nucleotide] = {
    nucleotides.map {
      case A => bio.RNA.A
      case C => bio.RNA.C
      case G => bio.RNA.G
      case T => bio.RNA.U
      case nt =>
        throw new IllegalArgumentException(
          "non DNA nucleotide " + nt + " type " + nt.getClass.getName
        )
    }
  }

  /** Transcribe DNA to RNA, the 5'-3' order is maintained (unlike BioJAVA)
    */
  def transcribe(nucleotides: List[bio.DNA.Nucleotide]): List[bio.RNA.Nucleotide] = toRNA(nucleotides)

  /** Complement nucleotides - note: no support for Ambiguous symbols.
    */
  def complement(nucleotides: List[bio.DNA.Nucleotide]): List[bio.DNA.Nucleotide] = {
    nucleotides.map {
      case A => T
      case T => A
      case C => G
      case G => C
      case nt =>
        throw new IllegalArgumentException(
          "non DNA nucleotide " + nt + " type " + nt.getClass.getName
        )
    }
  }
}
