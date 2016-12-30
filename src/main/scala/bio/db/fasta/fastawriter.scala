/**
 * FastaWriter writes a list of sequences to a file in Fasta format.
 *
 */

package bio
import java.io._

class FastaWriter(val fout: FileOutputStream) {
  class FastaWriterException(string: String) extends Exception(string)
  var sequence_width = 70

  def this(f: File) = this(new FileOutputStream(f))
  def this(filen: String) = this(new FileOutputStream(filen))

  /**
   * Write Fasta Fasta format.
   *
   * Example:
   *
   *   import bio.io.Control._
   *   val tmpfn = File.createTempFile("BioScala-Fasta-",".fa")
   *   using(new FileOutputStream(tmpfn)) { stream =>
   *     new FastaWriter(stream).write(seqlist)
   *   }
   */
  def write[T <: AbstractSequence](list: List[T]): Unit = {
    val size = list.head.length
    val writer = new OutputStreamWriter(fout)
    list.foreach { seq =>
      def write_seq(list: List[Any]): Unit = {
        val (w, rest) = list.splitAt(sequence_width)
        writer.write(w.mkString + "\n")
        rest match {
          case Nil =>
          case _ =>
            // writer.write(" "*(seq.id.length+2))
            write_seq(rest)
        }
      }
      if (sequence_width > 0) {
        writer.write(">" + seq.id)
        if (seq.hasDescription) writer.write(" " + seq.description)
        writer.write("\n")
        write_seq(seq.toList)
      } else {
        writer.write(seq.id + "  " + seq.toString + "\n")
      }
    }
    writer.flush // expected before close(!)
  }
} // FastaWriter
