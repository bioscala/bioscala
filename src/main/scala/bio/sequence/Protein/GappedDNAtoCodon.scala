package bio.sequence.Protein

import bio.nucleotide.DNA
import bio.sequence.DNA.{ToGappedDNA, ToGappedSequence}
import bio.chemistry.Protein.Gap
import bio.chemistry.Protein.{AminoAcid, Codon, CodonGap, CodonSymbol}

object GappedDNAtoCodon {
  /** Return the Codons */
  def apply(str: String): List[CodonSymbol] = {
    /** Helper method, takes the NT list and splits it into
     * a list of codons - gaps (triple dashes) are merely passed
     * on as codons
     */
    def codons(seq: List[DNA.NTSymbol]): List[List[DNA.NTSymbol]] = {
      val (codon, rest) = seq.splitAt(3)
      codon match {
        case Nil => Nil
        case _   => List(codon) ::: codons(rest)
      }
    }

    // Amino acids and nucleotides
    val aas = ToGappedSequence(str).translate() // IUPAC Sequence
    val nts = ToGappedDNA(str)
    // split into codons and zip them with AA's
    val codons2 = codons(nts)
    val zipped = aas.zip(codons2)
    // Return a list of Codon objects
    zipped.map { z =>
      val (aa, seq3) = z
      // println(seq3)
      aa match {
        case Gap           => CodonGap
        case aa: AminoAcid => Codon(aa, seq3)
        case _             => throw new IllegalArgumentException("Unexpected value " + aa)
      }
    }
  }
}
