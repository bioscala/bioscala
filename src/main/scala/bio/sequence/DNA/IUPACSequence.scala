package bio.sequence.DNA

import bio.nucleotide.DNA.NTSymbol
import bio.attribute.{Attribute, Description, Id}
import bio.chemistry.DNA.IUPACNucleotideConvert
import bio.chemistry.Protein.AASymbol
import bio.sequence.{RNA, Sequence}
import bio.sequence.actions.SymbolSequenceTranscription

class IUPACSequence(seqList: List[NTSymbol], attributeList: List[Attribute])
    extends Sequence[NTSymbol](seqList, attributeList) {

  type SequenceType = IUPACSequence

  def create(seqList: List[NTSymbol], attributeList: List[Attribute]) =
    new IUPACSequence(seqList, attributeList)

  /** @return transcribed DNA.Sequence as RNA.Sequence
    */
  def transcribe: RNA.IUPACSequence =
    RNA.IUPACSequence(SymbolSequenceTranscription.transcribe(seq))

  def translate(): List[AASymbol] = SymbolSequenceTranslation.translate(transcribe.seq)
}

object IUPACSequence {
  def apply(list: List[NTSymbol]) = new IUPACSequence(IUPACNucleotideConvert.fromList(list), Nil)

  def apply(str: String) = new IUPACSequence(IUPACNucleotideConvert.fromString(str), Nil)

  def apply(id: String, str: String) =
    new IUPACSequence(IUPACNucleotideConvert.fromString(str), List(Id(id)))

  def apply(id: String, descr: String, str: String) =
    new IUPACSequence(IUPACNucleotideConvert.fromString(str), List(Id(id), Description(descr)))

  def apply(sequence: DNASequence) = new IUPACSequence(sequence.seq, Nil)
}
