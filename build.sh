#!/bin/bash
mvn clean
mvn dependency:copy-dependencies
mvn package