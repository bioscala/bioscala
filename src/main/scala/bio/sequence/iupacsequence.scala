/**
 * IUPAC Nucleotide Sequence represents a Sequence that uses the 
 * ambiguous code:
 *
 * Symbol       Meaning      Nucleic Acid
 * ------------------------------------------
 * A            A            Adenine
 * C            C            Cytosine
 * G            G            Guanine
 * T            T            Thymine
 * U            U            Uracil
 * M          A or C
 * R          A or G
 * W          A or T
 * S          C or G
 * Y          C or T
 * K          G or T
 * V        A or C or G
 * H        A or C or T
 * D        A or G or T
 * B        C or G or T
 * X      G or A or T or C
 * N      G or A or T or C
 *
 */

import bio._
import bio.attribute._

package bio {

  class IUPACSequence (seqlist: List[Symbol], attributelist: List[Attribute]) extends bio.Sequence[Symbol](seqlist,attributelist) {
  }

  package DNA {
    class IUPACSequence (seqlist: List[NTSymbol], attributelist: List[Attribute]) extends bio.IUPACSequence(seqlist,attributelist) {
      def this(list: List[NTSymbol]) = this(IUPACNucleotideConvert.fromList(list),Nil)
      def this(str: String) = this(IUPACNucleotideConvert.fromString(str),Nil)
      def this(id: String, str: String) = this(IUPACNucleotideConvert.fromString(str), List(Id(id)))
      def this(id: String, descr: String, str: String) = this(IUPACNucleotideConvert.fromString(str),List(Id(id),Description(descr)))
      def this(sequence: Sequence) = this(sequence.seq, Nil)

      /**
       * @return transcribed DNA.Sequence as RNA.Sequence
       */
      def transcribe = { 
        val transcribed = SequenceTranscription.transcribe(seq) 
        new RNA.IUPACSequence(transcribed)
      }
   
      def translate() = { SymbolSequenceTranslation.translate(transcribe seq) }

    }
  }
  package RNA {
    class IUPACSequence (seqlist: List[NTSymbol], attributelist: List[Attribute]) extends bio.IUPACSequence(seqlist,attributelist) {
      def this(list: List[NTSymbol]) = this(IUPACNucleotideConvert.fromList(list),Nil)
      def this(str: String) = this(IUPACNucleotideConvert.fromString(str),Nil)
      def this(id: String, str: String) = this(IUPACNucleotideConvert.fromString(str), List(Id(id)))
      def this(id: String, descr: String, str: String) = this(IUPACNucleotideConvert.fromString(str),List(Id(id),Description(descr)))
      def this(sequence: Sequence) = this(sequence.seq, Nil)

      /**
       * @return transcribed DNA.Sequence as RNA.Sequence
       */
      def transcribe = { this }
   
      def translate() = { SymbolSequenceTranslation.translate(transcribe seq) }

    }
  }
  package Protein {
    /*
     * AminoAcid Sequence supporting ambiguous IUPAC symbols
     */
    class IUPACSequence (seqlist: List[AminoAcid], attributelist: List[Attribute]) extends bio.IUPACSequence(seqlist,attributelist) {
      def this(list: List[AminoAcid]) = this(IUPACAminoAcidConvert.fromList(list),Nil)
      def this(str: String) = this(IUPACAminoAcidConvert.fromString(str),Nil)
      def this(id: String, str: String) = this(IUPACAminoAcidConvert.fromString(str), List(Id(id)))
      def this(id: String, descr: String, str: String) = this(IUPACAminoAcidConvert.fromString(str),List(Id(id),Description(descr)))
      def this(sequence: Sequence) = this(sequence.seq, Nil)
    }
  }
}

