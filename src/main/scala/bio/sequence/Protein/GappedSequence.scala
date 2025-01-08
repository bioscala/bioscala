package bio.sequence.Protein

import bio.Protein.GappedConvert
import bio.attribute.{Description, Id}
import bio.Protein.AASymbol
import bio.{Attribute, sequence}

class GappedSequence(seqList: List[AASymbol], attributeList: List[Attribute])
    extends sequence.Sequence[AASymbol](seqList, attributeList) {

  type SequenceType = GappedSequence

  def create(seqList: List[AASymbol], attributeList: List[Attribute]) =
    new GappedSequence(seqList, attributeList)
}

object GappedSequence {
  def apply(list: List[AASymbol]) = new GappedSequence(GappedConvert.fromList(list), Nil)

  def apply(str: String) = new GappedSequence(GappedConvert.fromString(str), Nil)

  def apply(id: String, str: String) =
    new GappedSequence(GappedConvert.fromString(str), List(Id(id)))

  def apply(id: String, descr: String, str: String) =
    new GappedSequence(GappedConvert.fromString(str), List(Id(id), Description(descr)))

  def apply(sequence: Sequence) = new GappedSequence(sequence.seq, Nil)
}
