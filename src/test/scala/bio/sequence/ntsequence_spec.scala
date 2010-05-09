import bio._

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

package bio.test {

  import bio.attribute._

  class SequenceSpec extends FlatSpec with ShouldMatchers {
    import bio.DNA._
    "A Sequence" should "instantiate from a String" in {
      val s = new Sequence("agctaacg")
      s.toString should equal ("agctaacg")
    }
    "A Sequence" should "generate a list of Nucleotides" in {
      val list = new Sequence("agctaacg").toList
      list.first should equal (A)
    }
    "A Sequence" should "instantiate from a List[Nucleotide]" in {
      val list = new Sequence("agctaacg").toList
      val s = new Sequence(list)
      s.toString should equal ("agctaacg")
    }
    "A Sequence" should "instantiate from a Sequence" in {
      val s1 = new Sequence("agctaacg")
      val s2 = new Sequence(s1)
      s2.toString should equal ("agctaacg")
    }
    "A Sequence" should "instantiate with an ID" in {
      val s = new Sequence("ID456","agctaacg")
      s.id should equal ("ID456")
    }
    "A Sequence" should "instantiate with an ID+Description" in {
      val s = new Sequence("ID456","Gene 456","agctaacg")
      s.description should equal ("Gene 456")
    }
  }

  class DNASequenceSpec extends FlatSpec with ShouldMatchers {
    import bio.DNA._

    "DNA sequences" should "print as characters" in {
      val s = new Sequence("agctaacg")
      s.toString should equal ("agctaacg")
      // Mixed case
      new Sequence("agTA").toString should equal ("agta")
    }

    "DNA Sequence" should "not accept strange input" in {
      evaluating { 
        val s = new Sequence("acgtz") // fails
        s.toString
      } should produce [IllegalArgumentException]
      evaluating { 
        val s = new Sequence("acgu") // RNA fails
        s.toString
      } should produce [IllegalArgumentException]
      // Just for fun, using a standard list with uppercase is not safe
      val l = "acgtz".toList.map { nt => nt.toUpperCase }
      l.mkString should equal ("ACGTZ")
    }

    "DNA Sequence" should "not be instantiated from RNA" in {
      val rna = new RNA.Sequence("agucc")
      evaluating {
        val s = new Sequence(rna.toList)
        true
      } should produce [IllegalArgumentException]
    }
    "DNA Sequence" should "instantiate from a Sequence" in {
      val s1 = new Sequence("agctaacg")
      val s2 = new Sequence(s1)
      s2.toString should equal ("agctaacg")
    }
    "A DNA Sequence" should "allow two IDs" in {
      val s = new DNA.Sequence("ID456","Gene 456","agctaacg")
      val s2 = s.attrAdd(Id("Pubmed:456"))
      s2.idList === (List("ID456","Pubmed:456"))
    }
    "A DNA Sequence" should "allow multiple IDs" in {
      val s = new DNA.Sequence("ID456","Gene 456","agctaacg")
      val s2 = s.attrAdd(List(Id("GEO:456"),Id("Pubmed:456")))
      s2.idList === (List("ID456","Geo:456","Pubmed:456"))
    }
    "A DNA Sequence" should "allow multiple Descriptions" in {
      val s = new DNA.Sequence("ID456","Gene 456","agctaacg")
      val s2 = s.attrAdd(Description("Pubmed description"))
      s2.descriptionList === (List("Gene 456","Pubmed description"))
    }
  }

  class RNASequenceSpec extends FlatSpec with ShouldMatchers {
    import bio.RNA._
    "RNA sequences" should "print as characters" in {
      val s = new Sequence("agcuaacg")
      s.toString should equal ("agcuaacg")
      new Sequence("agUA").toString should equal ("agua")
    }

    "RNA sequence" should "not accept strange input" in {
      evaluating { 
        val s = new Sequence("acgut") // fails on t
        s.toString
      } should produce [IllegalArgumentException]
      // Just for fun, using a standard list with uppercase is not safe
      val l = "acgtz".toList.map { nt => nt.toUpperCase }
      l.mkString should equal ("ACGTZ")
    }
    "RNA Sequence" should "not be instantiated from DNA" in {
      val dna = new DNA.Sequence("agtcc")
      evaluating {
        val s = new Sequence(dna.toList)
        true
      } should produce [IllegalArgumentException]
    }
    "RNA Sequence" should "instantiate from a Sequence" in {
      val s1 = new Sequence("agcuaacg")
      val s2 = new Sequence(s1)
      s2.toString should equal ("agcuaacg")
    }
    "An RNA Sequence" should "instantiate with an ID+Description" in {
      val s = new RNA.Sequence("ID456","Gene 456","agcuaacg")
      s.description should equal ("Gene 456")
    }
      
    "A RNA Sequence" should "allow multiple IDs" in {
      val s = new RNA.Sequence("ID456","Gene 456","agcuaacg")
      val s2 = s.attrAdd(Id("Pubmed:456"))
      s2.idList === (List("ID456","Pubmed:456"))
    }
    "A RNA Sequence" should "allow multiple Descriptions" in {
      val s = new RNA.Sequence("ID456","Gene 456","agcuaacg")
      val s2 = s.attrAdd(List(Description("Pubmed description")))
      s2.descriptionList === (List("Gene 456","Pubmed description"))
    }
  }    
}
