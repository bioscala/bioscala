#! /usr/bin/ruby

# Update the main repository
raise "Only from root repository" if !File.directory?('scripts')
# Kernel.system('git pull')

print "Update BioScala APIDOC pages"

print `rsync -va ./target/scala_2.7.7/doc/main/api #{ENV['THEBIRD_HTML']}/bioscala/`


