package bio.sequence.Protein

import bio.attribute.{Attribute, Description, Id}
import bio.chemistry.Protein.{AminoAcid, AminoAcidConvert}
import bio.sequence

/** The (immutable) Amino acid sequence class represents a list of Protiein
  * amino acids. Each sequence can carry a list of attribute, including the
  * ID(s) and description(s).
  * <p>
  * Contrasting with most Bio* implementations, the Sequence class represents
  * the contained amino acids as a List, not as a String. Also a Sequence may be
  * represented by multiple ID's and descriptions in the form of flexible
  * attribute.
  * <p>
  * As each amino acid is a real object, it may contain additional information.
  * <p>
  * For gapped and ambiguous sequences see the relevant traits.
  *
  * @see GappedSequence
  * @see IUPACSequence
  */
class Sequence(aaList: List[AminoAcid], attributeList: List[Attribute])
    extends sequence.Sequence[AminoAcid](aaList, attributeList) {

  type SequenceType = Sequence

  def create(seqList: List[AminoAcid], attributeList: List[Attribute]) =
    new Sequence(seqList, attributeList)
}

object Sequence {
  def apply(list: List[AminoAcid]) =
    new Sequence(AminoAcidConvert.fromList(list), Nil)

  def apply(str: String) = new Sequence(AminoAcidConvert.fromString(str), Nil)

  def apply(id: String, str: String) =
    new Sequence(AminoAcidConvert.fromString(str), List(Id(id)))

  def apply(id: String, descr: String, str: String) =
    new Sequence(AminoAcidConvert.fromString(str), List(Id(id), Description(descr)))

  def apply(sequence: Sequence) = new Sequence(sequence.seq, Nil)
}
