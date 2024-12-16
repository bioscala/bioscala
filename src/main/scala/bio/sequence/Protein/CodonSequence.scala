package bio.sequence.Protein

import bio.attribute.{Attribute, Description, Id}
import bio.chemistry.Protein.{AminoAcid, Codon}
import bio.nucleotide.{DNA, RNA}
import bio.sequence.Sequence

/** Store a list of Codon AminoAcids with their Codon sequences. The sequence is
  * initialized from the DNA sequence, before translation. For example
  *
  * val s = new CodonSequence("agctaacgt")
  * s(2) should equal (R)
  * s(2).getCodon should equal (List(C,G,T))
  */
class CodonSequence(codonList: List[Codon], attributeList: List[Attribute])
    extends bio.sequence.Sequence[Codon](codonList, attributeList) {

  type SequenceType = CodonSequence

  def create(seqList: List[Codon], attributeList: List[Attribute]) =
    new CodonSequence(seqList, attributeList)

  def getCodon(n: Int): List[DNA.NTSymbol] = seq(n).getCodon

  def toAminoAcid: List[AminoAcid] = seq.map { codon => codon.aa }

  def toDNA: List[DNA.NTSymbol] = seq.flatMap { codon => codon.getCodon }

  def toRNA: List[RNA.NTSymbol] = bio.sequence.DNA.IUPACSequence(toDNA).transcribe.toList

  override def toString: String = toAminoAcid.mkString
}

object CodonSequence {
  def apply(str: String) = new CodonSequence(DNAtoCodon(str), Nil)

  def apply(id: String, str: String) =
    new CodonSequence(DNAtoCodon(str), List(Id(id)))

  def apply(id: String, descr: String, str: String) =
    new CodonSequence(DNAtoCodon(str), List(Id(id), Description(descr)))
}
