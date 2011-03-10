#! /usr/bin/ruby

# Update the main repository
raise "Only from doc repository" if !File.directory?('output')
# Kernel.system('git pull')

print "Update BioScala pages"

print `rsync -va output/* #{ENV['THEBIRD_HTML']}/bioscala/`


