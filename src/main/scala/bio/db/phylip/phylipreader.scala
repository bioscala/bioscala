/**
 * PhylipReader writes a list of sequences to a file in Phylip format.
 *
 */

package bio

import java.io._
// import org.biojava.bio.alignment._
// import org.biojava.bio.symbol._
// import org.biojava.bio.seq._
import org.biojavax.bio.phylo.io.phylip._

/**
 * PhylipReader opens a file and parses the Phylip contents using
 * an iterator. This implementation is not ready - it may use the
 * BioJava PHYLIPFileListener as below, but one problem is that
 * ID names are restricted to 9 characters.
 *
 * Note: this is a hack. I am trying to get to grips with the BioJava
 * way of doing this - and it does not map easily to an iterator.
 */
//noinspection ScalaWeakerAccess
class PhylipReader(val filename: String) extends Iterator[(String, String)] {
  private lazy val reader = new BufferedReader(new FileReader(filename))

  class PhylipReaderException(string: String) extends Exception(string)

  var id_list: List[String] = Nil
  var seq_list: List[String] = Nil

  object PhylipListener extends PHYLIPFileListener {
    def receiveSequence(s: String): Unit = seq_list ::= s

    def setCurrentSequenceName(s: String): Unit = id_list ::= s

    def setSitesCount(i: Int): Unit = {}

    def setSequenceCount(i: Int): Unit = {}

    def endFile(): Unit = {}

    def startFile(): Unit = {}
  }

  lazy val listener = PhylipListener
  private val x: Unit = PHYLIPFileFormat.parse(listener, reader)
  val list: List[(String, String)] = id_list.zip(seq_list).reverse
  var pos = 0

  def hasNext(): Boolean = pos < list.length

  def next(): (String, String) = {
    val retval = list(pos)
    pos += 1
    retval
  }
} // PhylipReader
