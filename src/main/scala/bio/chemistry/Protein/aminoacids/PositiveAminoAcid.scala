package bio.chemistry.Protein.aminoacids

import bio.chemistry.Protein.AminoAcid

sealed abstract class PositiveAminoAcid(name: String, fullname: String) extends AminoAcid(name, fullname)

case object R extends PositiveAminoAcid("Arg", "Arginine")

case object H extends PositiveAminoAcid("His", "Histidine")

case object K extends PositiveAminoAcid("Lys", "Lysine")
