/**
 * The (immutable) Amino acid sequence class represents a list of Protiein
 * amino acids. Each sequence can carry a list of attributes, including the
 * ID(s) and description(s).
 * <p>
 * Contrasting with most Bio* implementations, the Sequence class represents
 * the contained amino acids as a List, not as a String. Also a Sequence may be
 * represented by multiple ID's and descriptions in the form of flexible
 * attributes.
 * <p>
 * As each amino acid is a real object, it may contain additional information.
 * <p>
 * For gapped and ambiguous sequences see the relevant traits.
 *
 * @see GappedSequence
 * @see IUPACSequence
 */

package bio

import attribute._

package Protein {

  class Sequence(aalist: List[AminoAcid], attributelist: List[Attribute]) extends bio.Sequence[AminoAcid](aalist, attributelist) {
    type SequenceType = Sequence
    def create(seqlist: List[AminoAcid], attributelist: List[Attribute]) = new Sequence(seqlist, attributelist)

    def this(list: List[AminoAcid]) = this(AminoAcidConvert.fromList(list), Nil)
    def this(str: String) = this(AminoAcidConvert.fromString(str), Nil)
    def this(id: String, str: String) = this(AminoAcidConvert.fromString(str), List(Id(id)))
    def this(id: String, descr: String, str: String) = this(AminoAcidConvert.fromString(str), List(Id(id), Description(descr)))
    def this(sequence: Sequence) = this(sequence.seq, Nil)
  } // Sequence
} // Protein
