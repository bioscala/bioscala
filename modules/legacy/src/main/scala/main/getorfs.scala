/**
 * Called from ./bin/getorfs
 *
 */

import bio._
import bio.Protein._
import bio.io.Control._
import java.io._

object GetORFApp {
  val version = "0.01"

  def main(args: Array[String]) {
    val arglist = args.toList
    if (args.length == 0) {
      println("GetORFs " + version)
      println(
        """

Fetch ORFs from a sequence. Sorry, this script is non-functional at the moment.

  Usage:

    ./bin/getorfs infile

  Where:

    infile          Input file (FASTA)
    -v              Verbose

  Examples:

      """)
      sys.exit(1)
    }

    type OptionMap = Map[scala.Symbol, Any]

    def infileOption(xmap: OptionMap, s: String): OptionMap = {
      val infiles = xmap.get('infiles) match {
        case Some(l: List[String]) => s :: l
        case _ => List(s)
      }
      Map('infiles -> infiles)
    }

    def nextOption(map: OptionMap, list: List[String]): OptionMap = {
      def switch(s: String) = (s(0) == '-')

      list match {
        case Nil => map
        case "-v" :: tail => nextOption(map ++ Map('verbose -> true), tail)
        case string :: opt2 :: tail if switch(opt2) =>
          nextOption(map ++ infileOption(map, string), list.tail)
        case string :: Nil => nextOption(map ++ infileOption(map, string), list.tail)
        case option :: tail if switch(option) =>
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

    val verbose = getBool('verbose)
    println("GetORFs " + version)

    options.get('infiles) match {
      case Some(l: List[String]) =>
        l.reverse.map { infilen =>
          if (verbose) println("Reading file ", infilen)
          //    File file = new File("tmp");
          //    if(!file.exists()) {
          //      file.mkdirs();
          //    }
          val f = new FastaReader(infilen)
          f.map {
            case (id, tag, dna) =>
              println(id)
          }
        }
      case _ =>
        println("No input files")
        sys.exit(1)
    }
    if (verbose) println("Writing file")
  } // main
} // object
