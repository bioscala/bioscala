/**
 * Codon is an AminoAcid with its Codon information (DNA sequence)
 * Both the AminoAcid and the DNA sequence can be amibiguous (IUPAC)
 * 
 * Note: the implementation may change to an Attribute version
 */

package bio {

  package Protein {

    /** The Codon stores an (ambiguous) AminoAcid with the matching
     *  DNA sequence (also ambiguous). Note that this is the most
     *  open implementation with the purpose of storing codons and
     *  their amino acids in matching alignments.
     */
    case class Codon(val aa: AminoAcid, val codon: List[DNA.NTSymbol]) {
      def getAminoAcid = aa
      def getCodon = codon
    }
  }

} // bio
