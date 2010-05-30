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
    class CodonSequence extends AminoAcidSequence {
      // def this(list: List[AminoAcid]) = this(AminoAcidConvert.fromList(list),Nil)
      // def this(str: String) = this(DNA.NucleotideConvert.fromString(str),Nil)
      // def this(id: String, str: String) = this(AminoAcidConvert.fromString(str), List(Id(id)))
      // def this(id: String, descr: String, str: String) = this(AminoAcidConvert.fromString(str),List(Id(id),Description(descr)))
      // def this(sequence: Sequence) = this(sequence.seq, Nil)

  } // Protein
} // bio
