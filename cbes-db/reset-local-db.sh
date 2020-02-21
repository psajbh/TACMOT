#! /bin/bash

source user_mapping.sh

echo
echo "WARNING:"
echo "  This will wipe out your local developer database and rebuild it"
echo "  from scratch.  You will lose all exhibits, etc."
echo
echo "  Are you sure you want to proceed?"
echo
read -p "Enter YES to proceed: " yesOrNo

if [ "$yesOrNo" = "YES" ]; then
  echo
  PARAMS=""

  if [ "`whoami`" == "jamesabarth-werb" ]; then
      PARAMS="-Duser.name=james" 
  fi
  mvn $PARAMS clean compile install flyway:clean flyway:migrate sql:execute \
        $FLYWAY_USERNAME -Dflyway.validateOnMigrate=true -P local,load-developer-configuration $*
else
  echo
  echo ABORTED
fi
