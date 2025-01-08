package bio.sequence.DNA

import bio.Attribute
import bio.attribute.{Description, Id}
import bio.DNA.GappedConvert
import bio.Protein.AASymbol
import bio.DNA.NTSymbol
import bio.RNA.SymbolConvert
import bio.sequence.{RNA, Sequence}
import bio.sequence.actions.SymbolSequenceTranscription

class GappedSequence(seqList: List[NTSymbol], attributeList: List[Attribute])
    extends Sequence[NTSymbol](seqList, attributeList) {

  type SequenceType = GappedSequence

  def create(seqList: List[NTSymbol], attributeList: List[Attribute]) =
    new GappedSequence(seqList, attributeList)

  def translate(): List[AASymbol] = SymbolSequenceTranslation.translate(transcribe.seq)

  /** @return transcribed DNA.Sequence as RNA.Sequence
    */
  def transcribe: RNA.GappedSequence = {
    val transcribed = SymbolSequenceTranscription.transcribe(seq)
    val list = SymbolConvert.fromList(transcribed)
    RNA.GappedSequence(list)
  }
}

object GappedSequence {
  def apply(list: List[NTSymbol]) = new GappedSequence(GappedConvert.fromList(list), Nil)

  def apply(str: String) = new GappedSequence(GappedConvert.fromString(str), Nil)

  def apply(id: String, str: String) =
    new GappedSequence(GappedConvert.fromString(str), List(Id(id)))

  def apply(id: String, descr: String, str: String) =
    new GappedSequence(GappedConvert.fromString(str), List(Id(id), Description(descr)))

  def apply(sequence: DNASequence) = new GappedSequence(sequence.seq, Nil)
}
