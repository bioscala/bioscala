package bio.nucleotide.RNA

/** Nucleotides (abbreviated nt) are molecules that, when joined together, make
 * up the structural units of RNA and DNA. In addition, nucleotides play
 * central roles in metabolism.
 * <p>
 * The purines are adenine (A) and guanine (G).  The (complementary)
 * pyrimidines are thymine (T) and cytosine (C).  In RNA uracil (U) is the
 * complementary pyrimidine of adenine.
 * <p>
 * In the bio::DNA and bio::RNA packages special objects of individual
 * nucleotides are represented, to enforce strong type checking. These objects
 * can be converted, back and forth, to the character representation.
 */
abstract class Nucleotide private[RNA] extends NTSymbol
