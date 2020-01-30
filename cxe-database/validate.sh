#! /bin/bash

source user_mapping.sh

mvn clean compile flyway:validate $FLYWAY_USERNAME $*
