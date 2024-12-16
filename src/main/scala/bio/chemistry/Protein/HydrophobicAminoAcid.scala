package bio.chemistry.Protein

abstract class HydrophobicAminoAcid(name: String, fullname: String) extends AminoAcid(name, fullname)

case object A extends HydrophobicAminoAcid("Ala", "Alanine")

case object I extends HydrophobicAminoAcid("Ile", "Isoleucine")

case object L extends HydrophobicAminoAcid("Leu", "Leucine")

case object M extends HydrophobicAminoAcid("Met", "Methionine")

case object F extends HydrophobicAminoAcid("Phe", "Phenylalanine")

case object W extends HydrophobicAminoAcid("Trp", "Tryptophan")

case object Y extends HydrophobicAminoAcid("Tyr", "Tyrosine")

case object V extends HydrophobicAminoAcid("Val", "Valine")
