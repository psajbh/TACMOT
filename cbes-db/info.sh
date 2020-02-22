#! /bin/bash

source user_mapping.sh

mvn clean compile flyway:info $FLYWAY_USERNAME $*
