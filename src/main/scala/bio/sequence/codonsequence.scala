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

    object DNAtoCodonAA {
      /* Return the AA list with attached CodonAttributes */
      def apply(str: String): List[AminoAcid] = {
        /* Helper method, takes the NT list and splits it into
         * a list of codons
         */
        def codons(seq : List[DNA.NTSymbol]) : List[List[DNA.NTSymbol]] = {
          val (codon, rest) = seq.splitAt(3)
          codon match {
            case List() => Nil
            case _      => List(codon) ::: codons(rest)
          }
        }
        val aas = ToSequence(str).translate
        val nts = ToDNA(str)
        val codons2 = codons(nts)
        val zipped = aas.zip(codons2)
        zipped.map { z => 
          val (aa,nts) = z
          aa.setCodon(nts) }
      }
    }

    /**
     * Store a list of AminoAcids with their Codon sequences. The sequence is
     * initialized from the DNA sequence, before translation. For example
     *
     *   val s = new CodonSequence("agctaacgt")
     *   s(2) should equal (R)
     *   s(2).getCodon should equal (List(C,G,T))
     */
    // class CodonSequence(aalist: List[AminoAcid], ntlist: List[DNA.NTSymbol], attributelist: List[Attribute]) extends Sequence(aalist.withCodons(ntlist), attributelist) {
    class CodonSequence(aalist: List[AminoAcid], attributelist: List[Attribute]) extends Sequence(aalist, attributelist) {

      val nucleotidelist = List(DNA.A, DNA.C, DNA.G, DNA.T)

      // def this(list: List[AminoAcid]) = this(AminoAcidConvert.fromList(list),Nil)
      def this(str: String) = { this(DNAtoCodonAA(str),Nil) }
      def this(id: String, str: String) = this( DNAtoCodonAA(str), List(Id(id)))
      def this(id: String, descr: String, str: String) = this(DNAtoCodonAA(str),List(Id(id),Description(descr)))
      // def this(sequence: Sequence) = this(sequence.seq, Nil)
      def getCodon(n: Int) = seq(n).getCodon
      def toDNA: List[DNA.NTSymbol] = nucleotidelist
      def toRNA: List[RNA.NTSymbol] = (new DNA.IUPACSequence(toDNA)).transcribe.toList
    } // CodonSequence
  } // Protein
} // bio
