package bio.chemistry.Protein.aminoacids

import bio.chemistry.Protein.AminoAcid

abstract class UnchargedAminoAcid(name: String, fullname: String) extends AminoAcid(name, fullname)

case object S extends UnchargedAminoAcid("Ser", "Serine")

case object T extends UnchargedAminoAcid("Thr", "Threonine")

case object N extends UnchargedAminoAcid("Asn", "Asparagine")

case object Q extends UnchargedAminoAcid("Gln", "Glutamine")

case object C extends UnchargedAminoAcid("Cys", "Cysteine")

case object U extends UnchargedAminoAcid("Sec", "Selenocysteine")

case object G extends UnchargedAminoAcid("Gly", "Glycine")

case object P extends UnchargedAminoAcid("Pro", "Proline")
