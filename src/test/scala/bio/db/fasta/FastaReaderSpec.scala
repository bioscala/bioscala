package bio.db.fasta

import bio._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FastaReaderSpec extends AnyFlatSpec with Matchers {
  "FastaReader" should "read from NT file" in {
    val f = new FastaReader("./test/data/fasta/nt.fa")
    val ids = f.map { res =>
      val (id, tag, dna) = res
      id
    }.toList
    ids.head should equal("PUT-157a-Arabidopsis_thaliana-1")
  }
  "FastaReader" should "balk on nucleotide N with standard Sequence" in {
    val f = new FastaReader("./test/data/fasta/nt.fa")
    an[IllegalArgumentException] should be thrownBy {
      val seqs = f.map { res =>
        val (id, tag, dna) = res
        new DNA.DNASequence(id, tag, dna)
      }.toList
    }
  }
  "FastaReader" should "be able to convert NT to IUPACSequence" in {
    val f = new FastaReader("./test/data/fasta/nt.fa")
    val seqs = f.map { res =>
      val (id, tag, dna) = res
      new DNA.IUPACSequence(id, tag, dna)
    }.toList
    seqs.head.id.toString should equal("PUT-157a-Arabidopsis_thaliana-1")
  }
  // ---- AminoAcid's
  "FastaReader" should "read from AA file" in {
    val f = new FastaReader("./test/data/fasta/aa.fa")
    val ids = f.map { res =>
      val (id, tag, dna) = res
      id
    }.toList
    ids.head should equal("BAHG_VITSP")
  }
  "FastaReader" should "balk on standard Sequence: Unexpected value for AminoAcid X" in {
    val f = new FastaReader("./test/data/fasta/aa.fa")
    an[IllegalArgumentException] should be thrownBy {
      val seqs = f.map { res =>
        val (id, tag, dna) = res
        new Protein.Sequence(id, tag, dna)
      }.toList
    }
  }
  "FastaReader" should "be able to convert AA to IUPACSequence" in {
    val f = new FastaReader("./test/data/fasta/aa.fa")
    val seqs = f.map { res =>
      val (id, tag, dna) = res
      new Protein.IUPACSequence(id, tag, dna)
    }.toList
    seqs.head.id.toString should equal("BAHG_VITSP")
  }
  // ---- Codons
  "FastaReader" should "be able to convert NT to CodonSequence" in {
    val f = new FastaReader("./test/data/fasta/nt.fa")
    val seqs = f.map { res =>
      val (id, tag, dna) = res
      new Protein.CodonSequence(id, tag, dna)
    }.toList
    seqs.head.toDNA.take(5).mkString should equal("aggtt")
  }
}

