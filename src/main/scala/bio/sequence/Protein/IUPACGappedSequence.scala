package bio.sequence.Protein

import bio.attribute.Attribute
import bio.attribute.{Description, Id}
import bio.chemistry.Protein.{AASymbol, IUPACGappedAminoAcidConvert}
import bio.sequence

class IUPACGappedSequence(seqList: List[AASymbol], attributeList: List[Attribute])
    extends sequence.Sequence[AASymbol](seqList, attributeList) {

  type SequenceType = IUPACGappedSequence

  def create(seqList: List[AASymbol], attributeList: List[Attribute]) =
    new IUPACGappedSequence(seqList, attributeList)
}

object IUPACGappedSequence {
  def apply(list: List[AASymbol]) =
    new IUPACGappedSequence(IUPACGappedAminoAcidConvert.fromList(list), Nil)

  def apply(str: String) = new IUPACGappedSequence(IUPACGappedAminoAcidConvert.fromString(str), Nil)

  def apply(id: String, str: String) =
    new IUPACGappedSequence(IUPACGappedAminoAcidConvert.fromString(str), List(Id(id)))

  def apply(id: String, descr: String, str: String) =
    new IUPACGappedSequence(
      IUPACGappedAminoAcidConvert.fromString(str),
      List(Id(id), Description(descr))
    )

  def apply(sequence: Sequence) = new IUPACGappedSequence(sequence.seq, Nil)
}
