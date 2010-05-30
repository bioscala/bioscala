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

  package Protein {

    /**
     * Store a list of AminoAcids with their Codon sequences
     */
    class CodonSequence(aalist: List[AminoAcid], ntlist: List[Nucleotide], attributelist: List[Attribute]) extends Sequence(aalist, attributelist) {
      val nucleotidelist = ntlist

      // def this(list: List[AminoAcid]) = this(AminoAcidConvert.fromList(list),Nil)
      def this(str: String) = {
        this((new DNA.Sequence(str)).translate,new DNA.Sequence(str).toList,Nil)
      }
      // def this(id: String, str: String) = this(AminoAcidConvert.fromString(str), List(Id(id)))
      // def this(id: String, descr: String, str: String) = this(AminoAcidConvert.fromString(str),List(Id(id),Description(descr)))
      // def this(sequence: Sequence) = this(sequence.seq, Nil)
      def toDNA = nucleotidelist
      def toRNA = nucleotidelist
    } // CodonSequence
  } // Protein
} // bio
