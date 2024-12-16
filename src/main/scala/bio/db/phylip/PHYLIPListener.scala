package bio.db.phylip

import org.biojavax.bio.phylo.io.phylip.PHYLIPFileListener

class PHYLIPListener(var id_list: List[String], var seq_list: List[String]) extends PHYLIPFileListener {
  def receiveSequence(s: String): Unit = seq_list ::= s

  def setCurrentSequenceName(s: String): Unit = id_list ::= s

  def setSitesCount(i: Int): Unit = {}

  def setSequenceCount(i: Int): Unit = {}

  def endFile(): Unit = {}

  def startFile(): Unit = {}
}
