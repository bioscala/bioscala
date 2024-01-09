/**
 * The (immutable) Nucleotide sequence class represents a list of DNA, or RNA,
 * nucleotides. Each sequence can carry a list of attributes, including the
 * ID(s) and description(s).
 * <p>
 * Contrasting with most Bio* implementations, the Sequence class represents
 * the contained nucleotides as a List, not as a String. Also a Sequence may be
 * represented by multiple ID's and descriptions in the form of flexible
 * attributes.
 * <p>
 * As each nucleotide is a real object, it may contain additional information.
 * <p>
 * For gapped and ambiguous sequences see the relevant traits.
 *
 * @see GappedSequence
 * @see IUPACSequence
 */

package bio

import attribute._
import scala.language.postfixOps

package DNA {
  /**
   * A DNA Sequence contains a List of Nucleotide.
   */
  class DNASequence(nucleotidelist: List[Nucleotide], attributelist: List[Attribute]) extends bio.Sequence[Nucleotide](nucleotidelist, attributelist) {
    type SequenceType = DNASequence
    def create(seqlist: List[Nucleotide], attributelist: List[Attribute]) = new DNASequence(seqlist, attributelist)

    def this(list: List[Nucleotide]) = this(NucleotideConvert.fromList(list), Nil)
    def this(str: String) = this(NucleotideConvert.fromString(str), Nil)
    def this(id: String, str: String) = this(NucleotideConvert.fromString(str), List(Id(id)))
    def this(id: String, descr: String, str: String) = this(NucleotideConvert.fromString(str), List(Id(id), Description(descr)))
    def this(sequence: DNASequence) = this(sequence.seq, Nil)

    def translate() = { SequenceTranslation.translate(transcribe seq) }

    /**
     * @return transcribed DNA.Sequence as RNA.Sequence
     */
    def transcribe = {
      val transcribed = SequenceTranscription.transcribe(seq)
      val list = RNA.NucleotideConvert.fromList(transcribed)
      new RNA.RNASequence(list)
    }
    /**
     * @return complementary DNA.Sequence
     */
    def complement = SequenceTranscription.complement(seq)
  }
}

package RNA {
  class RNASequence(nucleotidelist: List[Nucleotide], attributelist: List[Attribute]) extends bio.Sequence[Nucleotide](nucleotidelist, attributelist) {

    type SequenceType = RNASequence
    def create(seqlist: List[Nucleotide], attributelist: List[Attribute]) = new RNASequence(seqlist, attributelist)

    def this(list: List[Nucleotide]) = this(NucleotideConvert.fromList(list), Nil)
    // def this(list: List[NTSymbol]) = this(NucleotideConvert.fromList(list),Nil)
    def this(str: String) = this(NucleotideConvert.fromString(str), Nil)
    def this(sequence: RNASequence) = this(sequence.seq, Nil)
    def this(id: String, str: String) = this(NucleotideConvert.fromString(str), List(Id(id)))
    def this(id: String, descr: String, str: String) = this(NucleotideConvert.fromString(str), List(Id(id), Description(descr)))

    def translate() = { SequenceTranslation.translate(transcribe seq) }

    /**
     * @return itself (source is immutable)
     */
    def transcribe = { this }

  }
}
