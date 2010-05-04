# BioRuby to BioScala adapter
#

$: << ENV['HOME']+'/izip/git/opensource/bioruby/lib'

require 'java'
require 'bio'

class RbSequence

  def translate(sequence, frame, codon_table)
    seq = Bio::Sequence::NA.new(sequence)
    seq.translate(frame,codon_table)
  end

end

