package bio.chemistry.Protein.aminoacids

import bio.chemistry.Protein.AminoAcid

sealed abstract class NegativeAminoAcid(name: String, fullname: String) extends AminoAcid(name, fullname)

case object D extends NegativeAminoAcid("Asp", "Aspartic Acid")

case object E extends NegativeAminoAcid("Glu", "Glutamic Acid")
