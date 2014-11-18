#!/bin/sh

mvn install:install-file -Dfile=target/test.war -DgroupId=dk.test \
    -DartifactId=test -Dversion=1.0 -Dpackaging=war