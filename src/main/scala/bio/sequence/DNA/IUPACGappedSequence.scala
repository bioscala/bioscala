package bio.sequence.DNA

import bio.Attribute
import bio.DNA.NTSymbol
import bio.RNA.SymbolConvert
import bio.attribute.{Description, Id}
import bio.DNA.IUPACGappedConvert
import bio.Protein.AASymbol
import bio.sequence.{RNA, Sequence}
import bio.sequence.actions.SymbolSequenceTranscription

class IUPACGappedSequence(seqList: List[NTSymbol], attributeList: List[Attribute])
    extends Sequence[NTSymbol](seqList, attributeList) {

  type SequenceType = IUPACGappedSequence

  def create(seqList: List[NTSymbol], attributeList: List[Attribute]) =
    new IUPACGappedSequence(seqList, attributeList)

  def translate(): List[AASymbol] = SymbolSequenceTranslation.translate(transcribe.seq)

  /** @return transcribed DNA.Sequence as RNA.Sequence
    */
  def transcribe: RNA.GappedSequence = {
    val transcribed = SymbolSequenceTranscription.transcribe(seq)
    val list = SymbolConvert.fromList(transcribed)
    RNA.GappedSequence(list)
  }
}

object IUPACGappedSequence {
  def apply(id: String, list: List[NTSymbol]) =
    new IUPACGappedSequence(IUPACGappedConvert.fromList(list), List(Id(id)))

  def apply(list: List[NTSymbol]) = new IUPACGappedSequence(IUPACGappedConvert.fromList(list), Nil)

  def apply(str: String) = new IUPACGappedSequence(IUPACGappedConvert.fromString(str), Nil)

  def apply(id: String, str: String) =
    new IUPACGappedSequence(IUPACGappedConvert.fromString(str), List(Id(id)))

  def apply(id: String, descr: String, str: String) =
    new IUPACGappedSequence(IUPACGappedConvert.fromString(str), List(Id(id), Description(descr)))

  def apply(sequence: DNASequence) = new IUPACGappedSequence(sequence.seq, Nil)
}
