package bio.sequence
package DNA

import bio.nucleotide.DNA
import bio.sequence.actions.{SequenceTranscription, SequenceTranslation}
import bio.attribute.{Description, Id}
import bio.sequence.RNA.RNASequence
import bio.attribute.Attribute
import bio.nucleotide.RNA
import bio.chemistry.Protein.AASymbol

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
class DNASequence(nucleotideList: List[DNA.Nucleotide],
                  attributeList: List[Attribute])
    extends Sequence[DNA.Nucleotide](nucleotideList, attributeList) {

  type SequenceType = DNASequence

  def create(seqList: List[DNA.Nucleotide], attributeList: List[Attribute]) =
    new DNASequence(seqList, attributeList)

  def translate(): List[AASymbol] =
    new SequenceTranslation[RNA.Nucleotide].translate(transcribe.seq)

  /**
    * @return transcribed DNA.Sequence as RNA.Sequence
    */
  def transcribe: RNASequence = {
    val transcribed = SequenceTranscription.transcribe(seq)
    val list = RNA.NucleotideConvert.fromList(transcribed)
    RNASequence(list)
  }

  /**
    * @return complementary DNA.Sequence
    */
  def complement: List[DNA.Nucleotide] = SequenceTranscription.complement(seq)
}

object DNASequence {
  def apply(list: List[DNA.Nucleotide]) =
    new DNASequence(DNA.NucleotideConvert.fromList(list), Nil)

  def apply(str: String) =
    new DNASequence(DNA.NucleotideConvert.fromString(str), Nil)

  def apply(id: String, str: String) =
    new DNASequence(DNA.NucleotideConvert.fromString(str), List(Id(id)))

  def apply(id: String, descr: String, str: String) =
    new DNASequence(DNA.NucleotideConvert.fromString(str),
                    List(Id(id), Description(descr)))

  def apply(sequence: DNASequence) = new DNASequence(sequence.seq, Nil)
}
