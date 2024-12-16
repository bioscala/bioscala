package bio.sequence.RNA

import bio.nucleotide.RNA.NTSymbol
import bio.attribute.{Attribute, Description, Id}
import bio.chemistry.Protein.AASymbol
import bio.chemistry.RNA.IUPACNucleotideConvert
import bio.sequence.Sequence

class IUPACSequence(seqList: List[NTSymbol], attributeList: List[Attribute])
    extends Sequence[NTSymbol](seqList, attributeList) {

  type SequenceType = IUPACSequence

  def create(seqList: List[NTSymbol], attributeList: List[Attribute]) =
    new IUPACSequence(seqList, attributeList)

  /** @return transcribed DNA.Sequence as RNA.Sequence
    */
  def transcribe: IUPACSequence = this

  def translate(): List[AASymbol] = SymbolSequenceTranslation.translate(transcribe.seq)
}

object IUPACSequence {
  def apply(list: List[NTSymbol]) = new IUPACSequence(IUPACNucleotideConvert.fromList(list), Nil)

  def apply(str: String) = new IUPACSequence(IUPACNucleotideConvert.fromString(str), Nil)

  def apply(id: String, str: String) =
    new IUPACSequence(IUPACNucleotideConvert.fromString(str), List(Id(id)))

  def apply(id: String, descr: String, str: String) =
    new IUPACSequence(IUPACNucleotideConvert.fromString(str), List(Id(id), Description(descr)))

  def apply(sequence: RNASequence) = new IUPACSequence(sequence.seq, Nil)
}
