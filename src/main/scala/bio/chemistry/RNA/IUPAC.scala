package bio.chemistry.RNA

import bio.nucleotide.RNA.NTSymbol

/**
 * IUPAC supports ambiguous data
 *
 * Symbol       Meaning      Nucleic Acid
 * ------------------------------------------
 * A            A            Adenine
 * C            C            Cytosine
 * G            G            Guanine
 * T            T            Thymine
 * U            U            Uracil
 * M          A or C
 * R          A or G
 * W          A or T
 * S          C or G
 * Y          C or T
 * K          G or T
 * V        A or C or G
 * H        A or C or T
 * D        A or G or T
 * B        C or G or T
 * X      G or A or T or C (is really unknown AA, so convert to N)
 * N      G or A or T or C
 *
 * For Amino Acids IUPAC adds to the base types (see aminoacid.scala):
 *
 * B.................Asx.................Aspartic acid or Asparagine
 * Z.................Glx.................Glutamine or Glutamic acid
 * X.................Xaa.................Any amino acid
 */
private[chemistry] trait IUPAC extends NTSymbol {
  def toString: String
}
