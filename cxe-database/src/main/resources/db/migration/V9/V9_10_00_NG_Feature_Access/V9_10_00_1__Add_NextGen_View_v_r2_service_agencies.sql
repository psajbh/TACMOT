SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;

drop view if exists v_r2_service_agencies;

CREATE ALGORITHM=UNDEFINED
VIEW `v_r2_service_agencies` AS
SELECT distinct `sa`.`BUDGES_SERV_AGY_ID`, `sa`.`BUDGES_SERV_AGY_CODE`, `sa`.`SERV_AGY_NAME`
FROM `SERVICE_AGENCY` `sa`, `service_agency_acct` `saa` 
where `sa`.`BUDGES_SERV_AGY_ID` = `saa`.`sa_ID`
and `saa`.`saa_budget_tag` = 'R2' and `sa`.`sa_status_flag` = 'A'
order by `sa`.`SERV_AGY_NAME`;
