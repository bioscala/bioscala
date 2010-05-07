/**
 * Sequence transcription. Delegated by the Sequence object.
 * Note we take a functional approach (no reference to self).
 */

import org.biojava.bio.symbol._
import org.biojava.bio.seq._
import bio._

package bio {

  object SequenceTranscription {
    /**
     * Convert DNA to RNA - replacing DNA.T with RNA.U. The 5'-3' order
     * is maintained
     */
    def toRNA(nucleotides: List[Nucleotide]): List[Nucleotide] = {
      nucleotides.map { nt =>
        nt match {
          case DNA.A => RNA.A
          case DNA.C => RNA.C
          case DNA.G => RNA.G
          case DNA.T => RNA.U
          case  _  => throw new IllegalArgumentException("non DNA nucleotide: "+nt)
        }
      }
    }
    /**
     * Transcribe DNA to RNA, the 5'-3' order is maintained (unlike BioJAVA)
     */
    def transcribe = toRNA(_)

    def complement(nucleotides: List[Nucleotide]) = {
      nucleotides.map {
        _ match {
            case DNA.A => DNA.T
            case DNA.T => DNA.A
            case DNA.C => DNA.G
            case DNA.G => DNA.C
            case  _  => throw new IllegalArgumentException("non DNA nucleotide")
        }
      }
    }
  }

}
