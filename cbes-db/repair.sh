#! /bin/bash

source user_mapping.sh

mvn clean compile flyway:repair $FLYWAY_USERNAME $*
