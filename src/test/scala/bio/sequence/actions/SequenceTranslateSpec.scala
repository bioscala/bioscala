package bio.sequence.actions

import bio.db.fasta.FastaReader
import bio.sequence.{DNA, RNA}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SequenceTranslateSpec extends AnyFlatSpec with Matchers {

  "Translate DNA sequence" should "translate to protein" in {
    DNA.DNASequence("agctaacga").translate().mkString should equal("S*R")
  }

  "Translate RNA sequence" should "translate to protein" in {
    RNA.RNASequence("agcuaacga").translate().mkString should equal("S*R")
  }

  "Translate ambiguous DNA" should "translate to AA with X" in {
    DNA.IUPACSequence("agctaacgn").translate().mkString should equal("S*R")
  }

  "Translate ambiguous RNA" should "translate to AA with X" in {
    RNA.IUPACSequence("ancuaacgn").translate().mkString should equal("X*R")
  }

  "Translate ambiguous DNA sequences from nt.fa" should "succeed" in {
    val f = new FastaReader("src/test/resources/fasta/nt.fa")
    val seqs = f.map { res =>
      val (id, tag, dna) = res
      DNA.IUPACSequence(id, tag, dna).translate()
    }.toList
    seqs.head.take(14).mkString should equal("RFXRSSXXVLXIVI")
  }
}
