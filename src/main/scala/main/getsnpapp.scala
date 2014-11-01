/**
 * Simple SNP caller
 *
 */

import bio._
import bio.Protein._
import bio.io.Control._
import java.io._

object GetSNPApp {
  val version = "0.01"
  val cname1 = this.getClass.getName
  val cname = cname1.slice(0, cname1.length - 1)

  val usage = 
"""

Call SNPs from an alignment.

  Usage:

    bioscala """+cname+""" infile

  Where:

    infile          Input file (FASTA alignment)
    -v              Verbose

  Examples:

    ./bin/bioscala """+cname+""" test/data/fasta/nt_aln.fa

"""

  def main(args: Array[String]) {
    val arglist = args.toList
    if (args.length == 0) {
      println(cname+" "+version)
      println(usage)
      sys.exit(1)
    }

    type OptionMap = Map[scala.Symbol, Any]
    
    def infileOption(xmap: OptionMap, s: String) : OptionMap = {
      val infiles = xmap.get( 'infiles ) match {
        case Some(l : List[String]) => s :: l
        case None => List(s)
      } 
      Map('infiles -> infiles)
    }
    def nextOption(map : OptionMap, list: List[String]) : OptionMap = {
      def switch(s : String) = (s(0) == '-')
      list match {
        case Nil => map
        case "--help" :: tail    => nextOption(map, tail)
        case "-v" :: tail    => nextOption(map ++ Map('verbose -> true), tail)
        case string :: opt2 :: tail if switch(opt2) => 
                                nextOption(map ++ infileOption(map, string), list.tail)
        case string :: Nil   => nextOption(map ++ infileOption(map, string), list.tail)
        case option :: tail if switch(option) => println("Unknown option "+option)
          sys.exit(1)
        case string :: tail  => nextOption(map ++ infileOption(map, string), tail)
      }
      // Map('type -> false)
    }
    val options = nextOption(Map(),arglist)

    def getInt(name : scala.Symbol, default : Int) : Int = 
      options.get( name ) match {
        case Some(v) => v.toString.toInt
        case None    => default
      }
    def getBool(name : scala.Symbol) : Boolean = 
      options.get( name ) match {
        case Some(_) => true
        case None    => false
      }
   
    val verbose = getBool('verbose)
    println(cname+" "+version)

    // ==== Start processing
    options.get('infiles) match {
      case Some(l : List[String]) => 
                   l.reverse.foreach { infilen =>
                     if (verbose) println("Reading " + infilen)
                     val f = new FastaReader(infilen)
                     val seqs = f.map { case(id, tag, symbols) =>
                       new DNA.GappedSequence(id, tag, symbols)
                     }.toList
                     val ids = seqs.map { _.id }
                     val slist = seqs.map { _.toList }
                     val a = new Alignment(slist)
                     val t = a.transpose(a.toList)
                     // and look for SNPs
                     def hasMultipleNucleotides(col: List[Symbol]) = {
                       val uniquelist = col.toSet.filter { _ != DNA.Gap }
                       uniquelist.size>1
                     }
                     val emptyColumn = List.tabulate(ids.size)(i=>'.')
                     val colkeep = t map {  hasMultipleNucleotides }
                     val list = colkeep.zipWithIndex.map { 
                                  case(true, i) => t(i)
                                  case(false, _) => emptyColumn
                                }
                     // println(list)
                     val m1 = new Alignment(list).transpose(list)
                     m1.toList.zip(ids).foreach { case(s, id) =>
                       println('>'+id)
                       println(s.mkString.toUpperCase)
                     }
                     true 
                   }
      case None => 
                   println(usage)
                   println("No input files")
                   sys.exit(1)
    }
    // if (verbose) println("Writing file")
    0
  } // main
} // object


