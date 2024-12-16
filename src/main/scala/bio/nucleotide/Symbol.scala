package bio.nucleotide

/** Base class for all (one letter) symbols used in BioScala.
  */
abstract class Symbol {

  /** Get the Symbol name by parsing the class name, which looks
    * like 'bio.Protein.X$'. The symbol is the character before last.
    */
  override def toString: String = {
    getClass.getName.toList.takeRight(2).head.toString
  }

  lazy val toChar: Char = toString.last
}
