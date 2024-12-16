package bio.sequence.Protein

import bio.nucleotide.DNA.NTSymbol
import bio.attribute.{Attribute, Description, Id}
import bio.chemistry.Protein.{AASymbol, CodonSymbol}
import bio.nucleotide.{DNA, RNA}

class GappedCodonSequence(codonList: List[CodonSymbol], attributeList: List[Attribute])
  extends bio.sequence.Sequence[CodonSymbol](codonList, attributeList) {

  type SequenceType = GappedCodonSequence

  def create(seqList: List[CodonSymbol], attributeList: List[Attribute]) =
    new GappedCodonSequence(seqList, attributeList)

  def getCodon(n: Int): List[NTSymbol] = seq(n).getCodon

  def toAminoAcid: List[AASymbol] = seq.map { codon => codon.getAminoAcid }

  def toDNA: List[DNA.NTSymbol] = seq.flatMap { codon => codon.getCodon }

  def toRNA: List[RNA.NTSymbol] = bio.sequence.DNA.IUPACGappedSequence(toDNA).transcribe.toList

  override def toString: String = toAminoAcid.mkString
}

object GappedCodonSequence {
  def apply(id: String, list: List[CodonSymbol]) = new GappedCodonSequence(list, List(Id(id)))

  def apply(str: String) = new GappedCodonSequence(GappedDNAtoCodon(str), Nil)

  def apply(id: String, str: String) = new GappedCodonSequence(GappedDNAtoCodon(str), List(Id(id)))

  def apply(id: String, descr: String, str: String) =
    new GappedCodonSequence(GappedDNAtoCodon(str), List(Id(id), Description(descr)))
}
