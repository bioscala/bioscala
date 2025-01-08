package bio.sequence.RNA

import bio.Attribute
import bio.RNA.{Nucleotide, NucleotideConvert}
import bio.attribute.{Description, Id}
import bio.Protein.AASymbol

/**
 * The (immutable) Nucleotide sequence class represents a list of DNA, or RNA,
 * nucleotides. Each sequence can carry a list of attribute, including the
 * ID(s) and description(s).
 * <p>
 * Contrasting with most Bio* implementations, the Sequence class represents
 * the contained nucleotides as a List, not as a String. Also a Sequence may be
 * represented by multiple ID's and descriptions in the form of flexible
 * attribute.
 * <p>
 * As each nucleotide is a real object, it may contain additional information.
 * <p>
 * For gapped and ambiguous sequences see the relevant traits.
 *
 * @see GappedSequence
 * @see IUPACSequence
 */
class RNASequence(nucleotideList: List[Nucleotide],
                  attributeList: List[Attribute])
    extends bio.sequence.Sequence[Nucleotide](nucleotideList, attributeList) {

  type SequenceType = RNASequence

  def create(seqList: List[Nucleotide], attributeList: List[Attribute]): RNASequence =
    new RNASequence(seqList, attributeList)

  def translate(): List[AASymbol] = SequenceTranslation.translate(transcribe.seq)

  /**
    * @return itself (source is immutable)
    */
  def transcribe: RNASequence = this
}

object RNASequence {
  def apply(list: List[Nucleotide]) =
    new RNASequence(NucleotideConvert.fromList(list), Nil)

  def apply(str: String) =
    new RNASequence(NucleotideConvert.fromString(str), Nil)

  def apply(sequence: RNASequence) = new RNASequence(sequence.seq, Nil)

  def apply(id: String, str: String) =
    new RNASequence(NucleotideConvert.fromString(str), List(Id(id)))

  def apply(id: String, descr: String, str: String) =
    new RNASequence(NucleotideConvert.fromString(str),
                    List(Id(id), Description(descr)))
}
