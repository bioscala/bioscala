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
      println("GetORFs "+version)
      println("""

Fetch ORFs from a sequence.

  Usage:

    ./bin/getorfs infile

  Where:

    infile          Input file (FASTA)
    -v              Verbose

  Examples:

      """)
      exit(1)
    }

    type OptionMap = Map[scala.Symbol, Any]
    
    def strOption(s: String) = {
      val regex = """(\d+)""".r
      s match {
        case regex(rule) => Map('rules -> rule)
        case filename => Map('infile -> filename)
      }
    }
    def nextOption(map : OptionMap, list: List[String]) : OptionMap = {
      def switch(s : String) = (s(0) == '-')
      list match {
        case Nil => map
        case "-v" :: tail =>
                               nextOption(map ++ Map('verbose -> true), tail)
        case string :: opt2 :: tail if switch(opt2) => 
                               nextOption(map ++ strOption(string), list.tail)
        case string :: Nil =>  nextOption(map ++ strOption(string), list.tail)
        case option :: tail => println("Unknown option "+option) 
                               exit(1) 
      }
      // Map('type -> false)
    }
    val options = nextOption(Map(),arglist)

    options.get( 'outfile ) match { 
      case Some(fn) => println("getorfs "+version)
                       println(options)
      case _ => 
    }
    def getBool(name : scala.Symbol) : Boolean = 
      options.get( name ) match {
        case Some(_) => true
        case None    => false
      }
   
    def getInt(name : scala.Symbol, default : Int) : Int = 
      options.get( name ) match {
        case Some(v) => v.toString.toInt
        case None    => default
      }
    val verbose = getBool('verbose)

    if (verbose) println("Writing file")
    0
  } // main
} // object


