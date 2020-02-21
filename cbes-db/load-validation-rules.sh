#! /bin/bash

source user_mapping.sh

function printUsageAndExit
{
  echo Usage: load-validation-rules.sh [local,unittest,development,staging,production]
  exit 1
}

function loadValidationRules
{
  mvn clean compile install sql:execute $FLYWAY_USERNAME -P $1,load-validation-rules
}

if [ $# -eq 0 -o $# -gt 1 ]; then
  printUsageAndExit
fi

case "$1" in
  local|unittest|development|staging|production)
	loadValidationRules $1
	;;
    *)
	printUsageAndExit
	;;
esac
