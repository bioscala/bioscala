/**
 * Called from ./bin/getorfs
 *
 */

import bio._

object GetORFApp {
  val version = "0.01"

  def main(args: Array[String]): Unit = {
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
      val infiles = xmap.get(Symbol("infiles")) match {
        case Some(l: List[String]) => s :: l
        case _ => List(s)
      }
      Map(Symbol("infiles") -> infiles)
    }

    def nextOption(map: OptionMap, list: List[String]): OptionMap = {
      def switch(s: String): Boolean = s(0) == '-'

      val result: OptionMap = list match {
        case Nil => return map
        case "-v" :: tail => nextOption(map ++ Map(Symbol("verbose") -> true), tail)
        case string :: opt2 :: _ if switch(opt2) =>
          nextOption(map ++ infileOption(map, string), list.tail)
        case string :: Nil => nextOption(map ++ infileOption(map, string), list.tail)
        case option :: _ if switch(option) =>
          println("Unknown option " + option)
          sys.exit(1)
        case string :: tail => nextOption(map ++ infileOption(map, string), tail)
      }

      val options = nextOption(Map(), arglist)

      def getInt(name: scala.Symbol, default: Int): Int = options.get(name) match {
        case Some(v) => v.toString.toInt
        case None => default
      }

      def getBool(name: scala.Symbol): Boolean = options.get(name) match {
        case Some(_) => true
        case None => false
      }

      val verbose = getBool(Symbol("verbose"))
      println("GetORFs " + version)

      options.get(Symbol("infiles")) match {
        case Some(l: List[String]) =>
          l.reverse.map { infilen =>
            if (verbose) println("Reading file ", infilen)
            val f = new FastaReader(infilen)
            f.map {
              case (id, _, _) =>
                println(id)
            }
          }
        case _ =>
          println("No input files")
          sys.exit(1)
      }

      if (verbose) println("Writing file")
      else ()

      result
    }
  }
}
