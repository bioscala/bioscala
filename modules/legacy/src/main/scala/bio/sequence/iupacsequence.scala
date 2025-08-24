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

package bio

import bio.attribute._

import scala.language.postfixOps

package DNA {

  import bio.Protein.AASymbol

  class IUPACSequence(seqlist: List[NTSymbol], attributelist: List[Attribute]) extends bio.Sequence[NTSymbol](seqlist, attributelist) {
    type SequenceType = IUPACSequence

    def create(seqlist: List[NTSymbol], attributelist: List[Attribute]) = new IUPACSequence(seqlist, attributelist)

    def this(list: List[NTSymbol]) = this(IUPACNucleotideConvert.fromList(list), Nil)

    def this(str: String) = this(IUPACNucleotideConvert.fromString(str), Nil)

    def this(id: String, str: String) = this(IUPACNucleotideConvert.fromString(str), List(Id(id)))

    def this(id: String, descr: String, str: String) = this(IUPACNucleotideConvert.fromString(str), List(Id(id), Description(descr)))

    def this(sequence: DNASequence) = this(sequence.seq, Nil)

    /**
     * @return transcribed DNA.Sequence as RNA.Sequence
     */
    def transcribe: RNA.IUPACSequence = {
      val transcribed = SymbolSequenceTranscription.transcribe(seq)
      new RNA.IUPACSequence(transcribed)
    }

    def translate(): List[AASymbol] = {
      SymbolSequenceTranslation.translate(transcribe.seq)
    }

  }
}

package RNA {

  import bio.Protein.AASymbol

  class IUPACSequence(seqlist: List[NTSymbol], attributelist: List[Attribute]) extends bio.Sequence[NTSymbol](seqlist, attributelist) {
    type SequenceType = IUPACSequence

    def create(seqlist: List[NTSymbol], attributelist: List[Attribute]) = new IUPACSequence(seqlist, attributelist)

    def this(list: List[NTSymbol]) = this(IUPACNucleotideConvert.fromList(list), Nil)

    def this(str: String) = this(IUPACNucleotideConvert.fromString(str), Nil)

    def this(id: String, str: String) = this(IUPACNucleotideConvert.fromString(str), List(Id(id)))

    def this(id: String, descr: String, str: String) = this(IUPACNucleotideConvert.fromString(str), List(Id(id), Description(descr)))

    def this(sequence: RNASequence) = this(sequence.seq, Nil)

    /**
     * @return transcribed DNA.Sequence as RNA.Sequence
     */
    def transcribe: IUPACSequence = {
      this
    }

    def translate(): List[AASymbol] = {
      SymbolSequenceTranslation.translate(transcribe.seq)
    }

  }
}

package Protein {
  /*
     * AminoAcid Sequence supporting ambiguous IUPAC symbols
     */
  class IUPACSequence(seqlist: List[AminoAcid], attributelist: List[Attribute]) extends bio.Sequence[AminoAcid](seqlist, attributelist) {
    type SequenceType = IUPACSequence

    def create(seqlist: List[AminoAcid], attributelist: List[Attribute]) = new IUPACSequence(seqlist, attributelist)

    def this(list: List[AminoAcid]) = this(IUPACAminoAcidConvert.fromList(list), Nil)

    def this(str: String) = this(IUPACAminoAcidConvert.fromString(str), Nil)

    def this(id: String, str: String) = this(IUPACAminoAcidConvert.fromString(str), List(Id(id)))

    def this(id: String, descr: String, str: String) = this(IUPACAminoAcidConvert.fromString(str), List(Id(id), Description(descr)))

    def this(sequence: Sequence) = this(sequence.seq, Nil)
  }

  class IUPACGappedSequence(seqlist: List[AASymbol], attributelist: List[Attribute]) extends bio.Sequence[AASymbol](seqlist, attributelist) {
    type SequenceType = IUPACGappedSequence

    def create(seqlist: List[AASymbol], attributelist: List[Attribute]) = new IUPACGappedSequence(seqlist, attributelist)

    def this(list: List[AASymbol]) = this(IUPACGappedAminoAcidConvert.fromList(list), Nil)

    def this(str: String) = this(IUPACGappedAminoAcidConvert.fromString(str), Nil)

    def this(id: String, str: String) = this(IUPACGappedAminoAcidConvert.fromString(str), List(Id(id)))

    def this(id: String, descr: String, str: String) = this(IUPACGappedAminoAcidConvert.fromString(str), List(Id(id), Description(descr)))

    def this(sequence: Sequence) = this(sequence.seq, Nil)
  }
}
