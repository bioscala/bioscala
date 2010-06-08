/**
 * Codon is an AminoAcid with its Codon information (DNA sequence)
 * Both the AminoAcid and the DNA sequence can be amibiguous (IUPAC)
 */

package bio {

  package Protein {

    case class Codon(val aa: AminoAcid, val codon: List[DNA.NTSymbol]) {
      def getCodon = codon
    }
  }

} // bio
