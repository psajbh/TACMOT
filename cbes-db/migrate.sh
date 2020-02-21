#! /bin/bash

source user_mapping.sh

mvn clean compile install flyway:migrate $FLYWAY_USERNAME -Dflyway.validateOnMigrate=true -Dflyway.outOfOrder=true $*
