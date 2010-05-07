/**
 * The Nucleotide sequence class represents a list of DNA, or RNA,
 * nucleotides. Each sequence can carry a list of attributes, 
 * including the ID(s) and description(s).
 * <p>
 * Contrasting with most Bio* implementations, the Sequence
 * class represents the contained nucleotides as a List, not as a
 * String. Also a Sequence may be represented by multiple ID's and
 * descriptions.
 */

import bio._

package bio {

  abstract class Sequence(nucleotidelist: List[Nucleotide], attributelist: List[Int]) {
    lazy val nucleotides = nucleotidelist
    lazy val attributes  = attributelist

    def transcribe(): Sequence
    def translate() = { SequenceTranslation.translate(
                          transcribe nucleotides) }

    /** @return Nucleotide List as a String */
    override def toString = nucleotides mkString
    /** @return Nucleotide List */
    def toList = nucleotides
  }

  package DNA {
    class Sequence(nucleotidelist: List[Nucleotide], attributelist: List[Int]) extends
      bio.Sequence(nucleotidelist, attributelist) {
      def this(list: List[Nucleotide]) = this(NucleotideConvert.fromList(list),Nil)
      def this(str: String) = this(NucleotideConvert.fromString(str),Nil)
      override def transcribe = { 
        println("Transcribing ",nucleotides)
        val trans = SequenceTranscription.transcribe(nucleotides) 
        val rna = new RNA.Sequence(trans.mkString)
        rna
      }
    }
  }

  package RNA {
    class Sequence(nucleotidelist: List[Nucleotide], attributelist: List[Int]) extends
      bio.Sequence(nucleotidelist, attributelist) {
      def this(list: List[Nucleotide]) = this(NucleotideConvert.fromList(list),Nil)
      def this(str: String) = this(NucleotideConvert.fromString(str),Nil)
      override def transcribe = { new RNA.Sequence(this.toString) }
    }
  }
}
