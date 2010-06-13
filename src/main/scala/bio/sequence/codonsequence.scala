/**
 * CodonSequence is really an AminoAcidSequence, where every AminoAcid has
 * a CodonAttribute containing the three letter DNA.
 *
 * @see AminoAcidSequence 
 * @see IUPACSequence
 */

import bio._
import bio.attribute._

package bio {

  package DNA {
    /** 
     * Take a DNA or RNA string and convert it to a DNA nucleotide list -
     * allowing for ambiguous codes (IUPAC).
     */
    object ToDNA {
      def apply(str: String): List[NTSymbol] = {
        str.toList map { c =>
          c.toLowerCase match {
            case 'u' => T
            case  _  => IUPACNucleotideConvert.fromChar(c)
          }
        }
      }
    }
    object ToSequence {
      def apply(str: String): IUPACSequence = {
        new IUPACSequence(ToDNA(str))
      }
    }
  } // DNA

  package Protein {

    import bio.DNA._

    object DNAtoCodon {
      /* Return the Codons */
      def apply(str: String): List[Codon] = {
        /* Helper method, takes the NT list and splits it into
         * a list of codons
         */
        def codons(seq : List[DNA.NTSymbol]) : List[List[DNA.NTSymbol]] = {
          val (codon, rest) = seq.splitAt(3)
          codon match {
            case Nil    => Nil
            case _      => List(codon) ::: codons(rest)
          }
        }
        val aas = ToSequence(str).translate
        val nts = ToDNA(str)
        val codons2 = codons(nts)
        val zipped = aas.zip(codons2)
        zipped.map { z => 
          val (aa,seq3) = z
          // println("*****",aa,seq3)
          Codon(aa,seq3)
        }
      }
    }

    /**
     * Store a list of Codon AminoAcids with their Codon sequences. The sequence is
     * initialized from the DNA sequence, before translation. For example
     *
     *   val s = new CodonSequence("agctaacgt")
     *   s(2) should equal (R)
     *   s(2).getCodon should equal (List(C,G,T))
     */
    class CodonSequence(codonlist: List[Codon], attributelist: List[Attribute]) extends bio.Sequence[Codon](codonlist, attributelist) {

      type SequenceType = CodonSequence
      def create(seqlist: List[Codon], attributelist: List[Attribute]) = new CodonSequence(seqlist, attributelist)

      def this(str: String) = { this(DNAtoCodon(str),Nil) }
      def this(id: String, str: String) = this( DNAtoCodon(str), List(Id(id)))
      def this(id: String, descr: String, str: String) = this(DNAtoCodon(str),List(Id(id),Description(descr)))
      def getCodon(n: Int) = seq(n).getCodon
      def toAminoAcid : List[AminoAcid] = seq.map { codon => codon.aa }
      def toDNA: List[DNA.NTSymbol] = seq.map { codon => codon.getCodon }.flatten
      def toRNA: List[RNA.NTSymbol] = (new DNA.IUPACSequence(toDNA)).transcribe.toList
      override def toString : String = toAminoAcid.mkString
    } // CodonSequence

    object GappedDNAtoCodon {
      def apply(str: String): List[Codon] = {
        Nil
      }
      def fromChar(c: Char) : CodonSymbol = CodonGap
    }

    class GappedCodonSequence(codonlist: List[CodonSymbol], attributelist: List[Attribute]) extends bio.Sequence[CodonSymbol](codonlist, attributelist) {

      type SequenceType = GappedCodonSequence
      def create(seqlist: List[CodonSymbol], attributelist: List[Attribute]) = new GappedCodonSequence(seqlist, attributelist)

      def this(str: String) = { this(GappedDNAtoCodon(str),Nil) }
      def this(id: String, str: String) = this( GappedDNAtoCodon(str), List(Id(id)))
      def this(id: String, descr: String, str: String) = this(GappedDNAtoCodon(str),List(Id(id),Description(descr)))
      def getCodon(n: Int) = seq(n).getCodon
      def toAminoAcid : List[AASymbol] = seq.map { codon => codon.getAminoAcid }
      def toDNA: List[DNA.NTSymbol] = seq.map { codon => codon.getCodon }.flatten
      def toRNA: List[RNA.NTSymbol] = (new DNA.IUPACSequence(toDNA)).transcribe.toList
      override def toString : String = toAminoAcid.mkString
    } // CodonSequence
  } // Protein
} // bio
