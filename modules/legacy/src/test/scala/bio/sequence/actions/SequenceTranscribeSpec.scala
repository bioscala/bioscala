package bio.sequence.actions

import bio._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SequenceTranscribeSpec extends AnyFlatSpec with Matchers {

  "Transcribe DNA sequence" should "become RNA" in {
    new DNA.DNASequence("agctaacga").transcribe.toString should equal(new RNA.RNASequence("agcuaacga").toString)
  }

  "Transcribe RNA sequence" should "do nothing" in {
    new RNA.RNASequence("agcuaacga").transcribe.toString should equal(new RNA.RNASequence("agcuaacga").toString)
  }

  "Complement DNA sequence" should "give complement" in {
    new DNA.DNASequence("agct").complement.mkString should equal("tcga")
  }

  "Complement RNA sequence" should "give complement" in {
    new DNA.DNASequence("agct").complement.mkString should equal("tcga")
  }
}

