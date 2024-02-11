/**
 * Simple SNP caller
 *
 */

import bio._

import scala.annotation.tailrec

//noinspection ScalaWeakerAccess
object GetSNPApp {
  val version = "0.01"
  val cname1: String = this.getClass.getName
  val cname: String = cname1.slice(0, cname1.length - 1)

  val usage: String =
    """

Call SNPs from an alignment.

  Usage:

    bioscala """ + cname +
      """ infile

  Where:

    infile          Input file (FASTA alignment)
    -v              Verbose

  Examples:

    ./bin/bioscala """ + cname +
      """ test/data/fasta/nt_aln.fa

"""

  def main(args: Array[String]): Unit = {
    val arglist = args.toList
    if (args.length == 0) {
      println(cname + " " + version)
      println(usage)
      sys.exit(1)
    }

    type OptionMap = Map[scala.Symbol, Any]

    def infileOption(xmap: OptionMap, s: String): OptionMap = {
      val infiles = xmap.get(Symbol("infiles")) match {
        case Some(l: List[String]) => s :: l
        case _ => List(s)
      }
      Map(Symbol("infiles") -> infiles)
    }

    @tailrec
    def nextOption(map: OptionMap, list: List[String]): OptionMap = {
      def switch(s: String): Boolean = s(0) == '-'

      list match {
        case Nil => map
        case "--help" :: tail => nextOption(map, tail)
        case "-v" :: tail => nextOption(map ++ Map(Symbol("verbose") -> true), tail)
        case string :: opt2 :: _ if switch(opt2) =>
          nextOption(map ++ infileOption(map, string), list.tail)
        case string :: Nil => nextOption(map ++ infileOption(map, string), list.tail)
        case option :: _ if switch(option) =>
          println("Unknown option " + option)
          sys.exit(1)
        case string :: tail => nextOption(map ++ infileOption(map, string), tail)
      }
      // Map('type -> false)
    }

    val options = nextOption(Map(), arglist)

    def getInt(name: scala.Symbol, default: Int): Int =
      options.get(name) match {
        case Some(v) => v.toString.toInt
        case None => default
      }

    def getBool(name: scala.Symbol): Boolean =
      options.get(name) match {
        case Some(_) => true
        case None => false
      }

    val verbose = getBool(Symbol("verbose"))
    println(cname + " " + version)

    // ==== Start processing
    options.get(Symbol("infiles")) match {
      case Some(l: List[String]) =>
        l.reverse.foreach { infilen =>
          if (verbose) println("Reading " + infilen)
          val f = new FastaReader(infilen)
          val seqs: List[DNA.GappedSequence] = f.map {
            case (id, tag, symbols) =>
              new DNA.GappedSequence(id, tag, symbols)
          }.toList
          val ids = seqs.map(_.id)
          val slist: List[List[Symbol]] = seqs.map(_.toList.asInstanceOf[List[Symbol]])
          val a = new Alignment[Symbol](slist)
          val t: List[List[Symbol]] = a.transpose(a.toList)

          // and look for SNPs
          def hasMultipleNucleotides(col: List[Symbol]) = col.toSet.count(_ != DNA.Gap) > 1

          val emptyColumn = List.tabulate(ids.size)(_ => '.')
          val colkeep = t map hasMultipleNucleotides
          val list = colkeep.zipWithIndex.map {
            case (true, i) => t(i)
            case (false, _) => emptyColumn
          }
          val m1 = new Alignment(list).transpose(list)
          m1.zip(ids).foreach {
            case (s, id) =>
              println('>' + id)
              println(s.mkString.toUpperCase)
          }
          true
        }
      case _ =>
        println(usage)
        println("No input files")
        sys.exit(1)
    }
    // if (verbose) println("Writing file")
  } // main
} // object
