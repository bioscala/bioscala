package bio.chemistry.Protein

/** Proteinogenic amino acids are 22 amino acids that are found in proteins and
  * require cellular machinery coded for in the genetic code [1] of any organism
  * for their isolated production. Humans can synthesize 10 of them (by
  * interconversions) from each other or from other molecules of intermediary
  * metabolism, but the other 10 (essential amino acids: arginine, histidine,
  * isoleucine, leucine, lysine, methionine, phenylalanine, threonine,
  * tryptophan, and valine) must be consumed in the diet.  Proteinogenic
  * literally means protein building. Proteinogenic amino acids can be assembled
  * into a polypeptide (the subunit of a protein) through a process known as
  * translation (the second stage of protein biosynthesis, part of the overall
  * process of gene expression) (from Wikipedia May 2010).
  * <p>
  * These amino acids belong to PostiveAminoAcid, NegativeAminoAcid,
  * UnchargedAminoAcid, SpecialAminoAcid and HydrophobicAminoAcid.
  * <p>
  * <pre>
  * For Amino Acids IUPAC adds to the base types:
  *
  * B.................Asx.................Aspartic acid or Asparagine
  * Z.................Glx.................Glutamine or Glutamic acid
  * X.................Xaa.................Any amino acid
  * </pre>
  */
abstract class AminoAcid private[Protein] (val name: String, val fullname: String) extends AASymbol
