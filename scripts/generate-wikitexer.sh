#! /bin/sh

mkdir -p output
rm output/*
mkwiki -o output/bioscala-design.html --indent-env scala design.txt
mkwiki -o output/bioscala-tutorial.html --indent-env scala tutorial.txt
