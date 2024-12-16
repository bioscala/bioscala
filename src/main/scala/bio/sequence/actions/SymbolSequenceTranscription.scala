package bio.sequence.actions

import bio.nucleotide.{DNA, RNA}
import bio.chemistry.{DNA => DNAChemistry, RNA => RNAChemistry}

object SymbolSequenceTranscription {

  /** Convert DNA to RNA - replacing DNA.T with RNA.U. The 5'-3' order
    * is maintained
    */

  /** Convert DNA to RNA - replacing DNA.T with RNA.U. The 5'-3' order
    * is maintained
    */
  def toRNA(nucleotides: List[DNA.NTSymbol]): List[RNA.NTSymbol] = {
    nucleotides.map {
      case DNA.A => RNA.A
      case DNA.C => RNA.C
      case DNA.G => RNA.G
      case DNA.T => RNA.U
      // Ambiguous code:
      case DNAChemistry.M   => RNAChemistry.M
      case DNAChemistry.R   => RNAChemistry.R
      case DNAChemistry.W   => RNAChemistry.W
      case DNAChemistry.S   => RNAChemistry.S
      case DNAChemistry.Y   => RNAChemistry.Y
      case DNAChemistry.K   => RNAChemistry.K
      case DNAChemistry.V   => RNAChemistry.V
      case DNAChemistry.H   => RNAChemistry.H
      case DNAChemistry.D   => RNAChemistry.D
      case DNAChemistry.B   => RNAChemistry.B
      case DNAChemistry.N   => RNAChemistry.N
      case DNAChemistry.Gap => RNAChemistry.Gap
      case nt =>
        throw new IllegalArgumentException(
          "non DNA nucleotide " + nt + " type " + nt.getClass.getName
        )
    }
  }

  /** Transcribe DNA to RNA, the 5'-3' order is maintained (unlike BioJAVA)
    */
  def transcribe(nucleotides: List[DNA.NTSymbol]): List[RNA.NTSymbol] = toRNA(nucleotides)

  /** Complement nucleotides - note: no support for Ambiguous symbols.
    */
  def complement(nucleotides: List[DNA.NTSymbol]): List[DNA.NTSymbol] = {
    nucleotides.map {
      case DNA.A => DNA.T
      case DNA.T => DNA.A
      case DNA.C => DNA.G
      case DNA.G => DNA.C
      case nt =>
        throw new IllegalArgumentException(
          "non DNA nucleotide " + nt + " type " + nt.getClass.getName
        )
    }
  }
}
