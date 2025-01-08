package bio.sequence.actions

import bio.FastaReader
import bio.sequence.DNA.{DNASequence, IUPACSequence => DNAIUPACSequence}
import bio.sequence.RNA.{RNASequence, IUPACSequence => RNAIUPACSequence}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SequenceTranslateSpec extends AnyFlatSpec with Matchers {
  "Translate DNA sequence" should "translate to protein" in {
    DNASequence("agctaacga").translate().mkString should equal("S*R")
  }

  "Translate RNA sequence" should "translate to protein" in {
    RNASequence("agcuaacga").translate().mkString should equal("S*R")
  }

  "Translate ambiguous DNA" should "translate to AA with X" in {
    DNAIUPACSequence("agctaacgn").translate().mkString should equal("S*R")
  }

  "Translate ambiguous RNA" should "translate to AA with X" in {
    RNAIUPACSequence("ancuaacgn").translate().mkString should equal("X*R")
  }

  "Translate ambiguous DNA sequences from nt.fa" should "succeed" in {
    val f = new FastaReader("./test/data/fasta/nt.fa")
    val seqs = f.map { res =>
      val (id, tag, dna) = res
      DNAIUPACSequence(id, tag, dna).translate()
    }.toList
    seqs.head.take(14).mkString should equal("RFXRSSXXVLXIVI")
  }
}
