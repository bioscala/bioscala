# BioScala
_Scalable, functional bioinformatics on the JVM — written in Scala, usable from Scala/Java and friends._

> This is a community-driven fork focused on code clarity, small safe refactors, and contributor experience.  
> This a functional bioinformatics library.

---

## ✨ What’s inside (today)
- Strongly-typed **DNA/RNA/Protein** sequences (with IUPAC ambiguity & gapped variants)
- **Transcription** (DNA → RNA)
- **Translation** (RNA → amino acids) with BioJava interop
- Parsers/Writers:
  - Iterator-based **FASTA** reader/writer
  - Iterator-based **PAML (PHY)** reader
  - **Phylip** reader/writer (via BioJava)
- Early alignment utilities and attribute system (immutable, WIP)

> Some APIs reflect “classic BioScala” semantics; modernization happens behind shims first to keep PRs tiny.
### Why BioScala?
**🧬 Functional Design:** Immutable data structures and pure functions for reliable, reproducible analysis.

**🧩 Modular Architecture:** Plug-and-play modules for sequences, alignments, and attributes.

**📊 Extensible:** Easily add custom functionality or integrate with other tools.

**🔬 Research-Ready:** Designed with bioinformatics workflows in mind.

---

### Features
#### Core Functionality

- **DNA/RNA/Protein Sequences:** Immutable, type-safe representations with support for gaps and IUPAC symbols.

- **Sequence Alignment:** Basic pairwise alignment and sparse alignment tools.

- **Transcription & Translation:** Convert DNA to RNA and RNA to protein sequences.

- **Attributes:** Attach metadata (e.g., IDs, descriptions) to sequences and alignments.

### Quick Start

#### Installation

Since BioScala is a **work in progress** and not yet published on Maven Central, you’ll need to clone the repository and publish it locally:

1. **Clone the Repository:**
```bash
Copy
git clone https://github.com/bioscala/bioscala.git
cd bioscala
```

2. **Publish Locally:**
Use sbt to publish the library to your local Ivy repository:
```bash
Copy
sbt publishLocal
```

3. **Add to Your Project:**
Add the dependency to your `build.sbt`:
```scala
libraryDependencies += "org.bioscala" %% "bioscala-core" % "0.2.0"
```

#### Example: DNA to RNA Transcription
```scala
val dnaSequence = new DNASequence("ATGGCCATTGTAATGGGCCGCTGAA")
val rnaSequence = dnaSequence.transcribe()

println(rnaSequence)  // Output: AUGGCCAUUGUAAUGGGCCGCUGAA
```

#### Example: Protein Translation
```scala
val rnaSequence = new RNASequence("AUGGCCAUUGUAAUGGGCCGCUGAA")
val proteinSequence = SequenceTranslation.translate(rnaSequence.seq)

println(proteinSequence)  // Output: MAIVMGR*
```

#### Example: Sparse Alignment
```scala
val alignment: List[List[NTSymbol]] = List(
  List(A, C, G, T, Gap),
  List(A, Gap, G, T, C)
)

val (filteredAlignment, removedColumns) = SparseAlignment.removeSparseRows(alignment, minSymbols = 2)

println(filteredAlignment)  // Output: List(List(A, C, G, T, Gap), List(A, Gap, G, T, C))
```

---

### Documentation

#### Core Concepts
- **Sequences**: Immutable lists of nucleotides or amino acids.

- **Alignments**: Lists of sequences with gap support.

- **Attributes**: Metadata attached to sequences or alignments.

#### API Reference
- **Alignment**: Tools for working with sequence alignments.

- **Attribute**: Managing metadata and properties.

- **Chemistry**: Representing nucleotides, amino acids, and codons.

- **Nucleotide**: Core DNA and RNA sequence handling.

- **Sequence**: High-level sequence abstractions.

Please refer to these links for a richer documentation:

- [The tutorial](http://monomorphic.org/bioscala/bioscala-tutorial.html) 

- [Design docs](http://monomorphic.org/bioscala/bioscala-design.html) 

- [API docs](http://monomorphic.org/bioscala/api/)

---

### Community
BioScala is an open-source project, and we welcome contributions from the community! Here’s how you can get involved:

- 🐛 Report Bugs: [Issue Tracker](https://github.com/bioscala/bioscala/issues)

- 💡 Suggest Features: I will share the Medium post here.

- 👩‍💻 Contribute Code: [Contributing Guide](https://github.com/bioscala/bioscala/blob/master/CONTRIBUTING.md)

- 💬 Join our [Discord](https://discord.gg/RFwVD3FQ) to participate in discussions.

---

### Credits & license
Original author/maintainer: Pjotr Prins

Interop: BioJava for translation and IO helpers

License: BSD (see [LICENSE](https://github.com/bioscala/bioscala/blob/master/LICENSE)).

---

### Citing BioScala
If you use BioScala in your research, please cite:

```bibtex
@software{bioscala,
  author = {BioScala Team},
  title = {BioScala: A Functional Bioinformatics Library},
  year = {2023},
  publisher = {GitHub},
  journal = {GitHub repository},
  howpublished = {\url{https://github.com/bioscala/bioscala}}
}
```

---

### License
BioScala is released under the [BSD 3-Clause License](https://github.com/bioscala/bioscala/blob/master/LICENSE)
, ensuring freedom for academic, commercial, and personal use.

---

### Acknowledgments

BioScala is made possible by the contributions of developers like you. Special thanks to:

- The **Scala community** for building a powerful and expressive language.

- The **open-source bioinformatics community** for inspiring this project.

---

### Join Us
BioScala is more than a library—it’s a community-driven effort to make bioinformatics more accessible and functional. 
Whether you’re a seasoned bioinformatician or a curious beginner, **we welcome you to the BioScala community**.

🌟 **Star this repo** to show your support.
🚀 **Fork and contribute** to shape the future of BioScala.

Let’s build the future of bioinformatics, together.

---

**BioScala Team**

On [GitHub](https://github.com/bioscala) and [Discord](https://discord.gg/RFwVD3FQ)