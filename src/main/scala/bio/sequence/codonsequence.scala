/**
 * CodonSequence is really an AminoAcidSequence, where every AminoAcid has
 * a CodonAttribute containing the three letter DNA.
 *
 * @see AminoAcidSequence 
 * @see IUPACSequence
 */

import bio._
import bio.attribute._

package bio {

  package DNA {
    /** 
     * Take a DNA or RNA string and convert it to a DNA nucleotide list -
     * allowing for ambiguous codes (IUPAC).
     */
    object ToDNA {
      def apply(str: String): List[NTSymbol] = {
        str.toList map { c =>
          c.toLowerCase match {
            case 'u' => T
            case  _  => IUPACNucleotideConvert.fromChar(c)
          }
        }
      }
    }
    object ToSequence {
      def apply(str: String): IUPACSequence = {
        new IUPACSequence(ToDNA(str))
      }
    }
  } // DNA

  package Protein {

    /**
     * Store a list of AminoAcids with their Codon sequences
     */
    class CodonSequence(aalist: List[AminoAcid], ntlist: List[DNA.NTSymbol], attributelist: List[Attribute]) extends Sequence(aalist, attributelist) {
      val nucleotidelist = ntlist

      // def this(list: List[AminoAcid]) = this(AminoAcidConvert.fromList(list),Nil)
      def this(str: String) = { this(DNA.ToSequence(str).translate,DNA.ToDNA(str),Nil)
      }
      def this(id: String, str: String) = this(DNA.ToSequence(str).translate,DNA.ToDNA(str), List(Id(id)))
      def this(id: String, descr: String, str: String) = this(DNA.ToSequence(str).translate,DNA.ToDNA(str),List(Id(id),Description(descr)))
      // def this(sequence: Sequence) = this(sequence.seq, Nil)
      def toDNA: List[DNA.NTSymbol] = nucleotidelist
      def toRNA: List[RNA.NTSymbol] = (new DNA.IUPACSequence(toDNA)).transcribe.toList
    } // CodonSequence
  } // Protein
} // bio
