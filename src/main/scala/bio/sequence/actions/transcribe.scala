/**
 * Sequence transcription and complement. Delegated by the Sequence object.
 * Note we take a functional approach (no reference to self).
 */

package bio
import org.biojava.bio.symbol._
import org.biojava.bio.seq._

package DNA {

  object SequenceTranscription {
    /**
     * Convert DNA to RNA - replacing DNA.T with RNA.U. The 5'-3' order
     * is maintained
     */
    def toRNA(nucleotides: List[Nucleotide]): List[RNA.Nucleotide] = {
      nucleotides.map { nt =>
        nt match {
          case A => RNA.A
          case C => RNA.C
          case G => RNA.G
          case T => RNA.U
          case _ => throw new IllegalArgumentException("non DNA nucleotide " + nt + " type " + nt.getClass.getName)
        }
      }
    }
    /**
     * Transcribe DNA to RNA, the 5'-3' order is maintained (unlike BioJAVA)
     */
    def transcribe(nucleotides: List[Nucleotide]): List[RNA.Nucleotide] = toRNA(nucleotides)

    /**
     * Complement nucleotides - note: no support for Ambiguous symbols.
     */
    def complement(nucleotides: List[Nucleotide]): List[Nucleotide] = {
      nucleotides.map { nt =>
        nt match {
          case A => T
          case T => A
          case C => G
          case G => C
          case _ => throw new IllegalArgumentException("non DNA nucleotide " + nt + " type " + nt.getClass.getName)
        }
      }
    }
  }

  object SymbolSequenceTranscription {
    /**
     * Convert DNA to RNA - replacing DNA.T with RNA.U. The 5'-3' order
     * is maintained
     */
    /**
     * Convert DNA to RNA - replacing DNA.T with RNA.U. The 5'-3' order
     * is maintained
     */
    def toRNA(nucleotides: List[NTSymbol]): List[RNA.NTSymbol] = {
      nucleotides.map { nt =>
        nt match {
          case A   => RNA.A
          case C   => RNA.C
          case G   => RNA.G
          case T   => RNA.U
          // Ambiguous code:
          case M   => RNA.M
          case R   => RNA.R
          case W   => RNA.W
          case S   => RNA.S
          case Y   => RNA.Y
          case K   => RNA.K
          case V   => RNA.V
          case H   => RNA.H
          case D   => RNA.D
          case B   => RNA.B
          case N   => RNA.N
          case Gap => RNA.Gap
          case _   => throw new IllegalArgumentException("non DNA nucleotide " + nt + " type " + nt.getClass.getName)
        }
      }
    }
    /**
     * Transcribe DNA to RNA, the 5'-3' order is maintained (unlike BioJAVA)
     */
    def transcribe(nucleotides: List[NTSymbol]): List[RNA.NTSymbol] = toRNA(nucleotides)
    /**
     * Complement nucleotides - note: no support for Ambiguous symbols.
     */
    def complement(nucleotides: List[NTSymbol]): List[NTSymbol] = {
      nucleotides.map { nt =>
        nt match {
          case A => T
          case T => A
          case C => G
          case G => C
          case _ => throw new IllegalArgumentException("non DNA nucleotide " + nt + " type " + nt.getClass.getName)
        }
      }
    }
  }
}
