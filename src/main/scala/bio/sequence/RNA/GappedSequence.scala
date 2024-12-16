package bio.sequence.RNA

import bio.nucleotide.RNA.NTSymbol
import bio.attribute.{Attribute, Description, Id}
import bio.chemistry.Protein.AASymbol
import bio.chemistry.RNA.GappedConvert
import bio.sequence.Sequence

class GappedSequence(seqList: List[NTSymbol], attributeList: List[Attribute])
    extends Sequence[NTSymbol](seqList, attributeList) {

  type SequenceType = GappedSequence

  def create(seqList: List[NTSymbol], attributeList: List[Attribute]) =
    new GappedSequence(seqList, attributeList)

  def translate(): List[AASymbol] = SymbolSequenceTranslation.translate(transcribe.seq)

  /** @return itself (source is immutable)
    */
  def transcribe: GappedSequence = this
}

object GappedSequence {
  def apply(list: List[NTSymbol]) = new GappedSequence(GappedConvert.fromList(list), Nil)

  def apply(str: String) = new GappedSequence(GappedConvert.fromString(str), Nil)

  def apply(id: String, str: String) =
    new GappedSequence(GappedConvert.fromString(str), List(Id(id)))

  def apply(id: String, descr: String, str: String) =
    new GappedSequence(GappedConvert.fromString(str), List(Id(id), Description(descr)))

  def apply(sequence: RNASequence) = new GappedSequence(sequence.seq, Nil)
}
