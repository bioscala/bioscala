package bio.chemistry.Protein

sealed abstract class IUPAC(name: String, fullname: String) extends AminoAcid(name, fullname)

case object B extends IUPAC("Asx", "Aspartic acid or Asparagine")

case object Z extends IUPAC("Glx", "Glutamine or Glutamic acid")

case object X extends IUPAC("Xaa", "Any amino acid")
