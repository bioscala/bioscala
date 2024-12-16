package bio.sequence.Protein

import bio.nucleotide.DNA
import bio.sequence.DNA.{ToDNA, ToSequence}
import bio.chemistry.Protein.{AminoAcid, Codon}

object DNAtoCodon {
  /** Return the Codons */
  def apply(str: String): List[Codon] = {
    /** Helper method, takes the NT list and splits it into
     * a list of codons - gaps (triple dashes) are merely passed
     * on as codons
     */
    def codons(seq: Seq[DNA.NTSymbol]): Seq[Seq[DNA.NTSymbol]] =
      seq.grouped(3).toSeq

    // Amino acids and nucleotides
    val aas = ToSequence(str).translate() // IUPAC Sequence
    val nts = ToDNA(str)
    // split into codons and zip them with AA's
    val codons2 = codons(nts)
    val zipped = aas.zip(codons2)
    // Return a list of Codon objects
    zipped.map { z =>
      val (aa, seq3) = z
      // println(seq3)
      aa match {
        case aa: AminoAcid => Codon(aa, seq3.toList)
        case _             => throw new IllegalArgumentException("Unexpected value " + aa)
      }
    }
  }
}
