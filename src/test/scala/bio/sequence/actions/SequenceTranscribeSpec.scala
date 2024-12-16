package bio.sequence.actions

import bio.sequence.{DNA, RNA}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SequenceTranscribeSpec extends AnyFlatSpec with Matchers {

  "Transcribe DNA sequence" should "become RNA" in {
    DNA.DNASequence("agctaacga").transcribe.toString should equal(RNA.RNASequence("agcuaacga").toString)
  }

  "Transcribe RNA sequence" should "do nothing" in {
    RNA.RNASequence("agcuaacga").transcribe.toString should equal(RNA.RNASequence("agcuaacga").toString)
  }

  "Complement DNA sequence" should "give complement" in {
    DNA.DNASequence("agct").complement.mkString should equal("tcga")
  }

  "Complement RNA sequence" should "give complement" in {
    DNA.DNASequence("agct").complement.mkString should equal("tcga")
  }
}

