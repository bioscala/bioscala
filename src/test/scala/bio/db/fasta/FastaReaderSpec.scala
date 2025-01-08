package bio.db.fasta

import bio.FastaReader
import bio.sequence.DNA.{DNASequence, IUPACSequence => DNAIUPACSequence}
import bio.sequence.Protein.{CodonSequence, Sequence, IUPACSequence => ProteinIUPACSequence}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FastaReaderSpec extends AnyFlatSpec with Matchers {
  "FastaReader" should "read from NT file" in {
    val f = new FastaReader("./test/data/fasta/nt.fa")
    val ids = f.map { res =>
      val (id, _, _) = res
      id
    }.toList
    ids.head should equal("PUT-157a-Arabidopsis_thaliana-1")
  }

  "FastaReader" should "balk on nucleotide N with standard Sequence" in {
    val f = new FastaReader("./test/data/fasta/nt.fa")
    an[IllegalArgumentException] should be thrownBy {
      f.map { res =>
        val (id, tag, dna) = res
        DNASequence(id, tag, dna)
      }.toList
    }
  }

  "FastaReader" should "be able to convert NT to IUPACSequence" in {
    val f = new FastaReader("./test/data/fasta/nt.fa")
    val seqs = f.map { res =>
      val (id, tag, dna) = res
      DNAIUPACSequence(id, tag, dna)
    }.toList
    seqs.head.id should equal("PUT-157a-Arabidopsis_thaliana-1")
  }

  // ---- AminoAcid's
  "FastaReader" should "read from AA file" in {
    val f = new FastaReader("./test/data/fasta/aa.fa")
    val ids = f.map { res =>
      val (id, _, _) = res
      id
    }.toList
    ids.head should equal("BAHG_VITSP")
  }

  "FastaReader" should "balk on standard Sequence: Unexpected value for AminoAcid X" in {
    val f = new FastaReader("./test/data/fasta/aa.fa")
    an[IllegalArgumentException] should be thrownBy {
      f.map { res =>
        val (id, tag, dna) = res
        Sequence(id, tag, dna)
      }.toList
    }
  }

  "FastaReader" should "be able to convert AA to IUPACSequence" in {
    val f = new FastaReader("./test/data/fasta/aa.fa")
    val seqs = f.map { res =>
      val (id, tag, dna) = res
      ProteinIUPACSequence(id, tag, dna)
    }.toList
    seqs.head.id should equal("BAHG_VITSP")
  }

  // ---- Codons
  "FastaReader" should "be able to convert NT to CodonSequence" in {
    val f = new FastaReader("./test/data/fasta/nt.fa")
    val seqs = f.map { res =>
      val (id, tag, dna) = res
      CodonSequence(id, tag, dna)
    }.toList
    seqs.head.toDNA.take(5).mkString should equal("aggtt")
  }
}

