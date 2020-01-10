mvn clean compile install flyway:migrate "-Dflyway.validateOnMigrate=true" "-Dflyway.outOfOrder=false" "-Pproduction,promptUser"
