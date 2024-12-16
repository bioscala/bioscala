package bio.db.phylip

import org.biojavax.bio.phylo.io.phylip.PHYLIPFileFormat

import java.io.{BufferedReader, FileReader}

/** PHYLIPReader opens a file and parses the PHYLIP contents using
  * an iterator. This implementation is not ready - it may use the
  * BioJava PHYLIPFileListener as below, but one problem is that
  * ID names are restricted to 9 characters.
  *
  * Note: this is a hack. I am trying to get to grips with the BioJava
  * way of doing this - and it does not map easily to an iterator.
  */
class PHYLIPReader(val filename: String) extends Iterator[(String, String)] {
  private lazy val reader = new BufferedReader(new FileReader(filename))

  private lazy val listener = new PHYLIPListener(List.empty, List.empty)
  PHYLIPFileFormat.parse(listener, reader)
  val list: List[(String, String)] = listener.id_list.zip(listener.seq_list).reverse
  var pos = 0

  def hasNext(): Boolean = pos < list.length

  def next(): (String, String) = {
    val retval = list(pos)
    pos += 1
    retval
  }
}
