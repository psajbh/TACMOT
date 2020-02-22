Notes for running commands on Windows
- When using powershell, you have to use quotation marks for the flags that have periods in them, otherwise it won't work
  For example, use mvn flyway:migrate "-Dflyway.validateOnMigrate=true" instead of mvn flyway:migrate -Dflyway.validateOnMigrate=true
   or for the load-validation-rules script, mvn sql:execute "-Pproduction,load-validation-rules" instead of mvn sql:execute -Pproduction,load-validation-rules
   