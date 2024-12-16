package bio.sequence.actions

import bio.nucleotide.DNA.{A, C, G, T}
import bio.nucleotide.{RNA, DNA}

/** Sequence transcription and complement. Delegated by the Sequence object.
  * Note we take a functional approach (no reference to self).
  */
object SequenceTranscription {

  /** Convert DNA to RNA - replacing DNA.T with RNA.U. The 5'-3' order
    * is maintained
    */
  def toRNA(nucleotides: List[DNA.Nucleotide]): List[RNA.Nucleotide] = {
    nucleotides.map {
      case A => RNA.A
      case C => RNA.C
      case G => RNA.G
      case T => RNA.U
      case nt =>
        throw new IllegalArgumentException(
          "non DNA nucleotide " + nt + " type " + nt.getClass.getName
        )
    }
  }

  /** Transcribe DNA to RNA, the 5'-3' order is maintained (unlike BioJAVA)
    */
  def transcribe(nucleotides: List[DNA.Nucleotide]): List[RNA.Nucleotide] = toRNA(nucleotides)

  /** Complement nucleotides - note: no support for Ambiguous symbols.
    */
  def complement(nucleotides: List[DNA.Nucleotide]): List[DNA.Nucleotide] = {
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
