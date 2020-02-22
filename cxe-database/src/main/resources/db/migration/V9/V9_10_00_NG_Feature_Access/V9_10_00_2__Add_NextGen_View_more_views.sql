SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;

drop view if exists v_p40_service_agencies;

CREATE ALGORITHM=UNDEFINED
VIEW `v_p40_service_agencies` AS
SELECT distinct `sa`.`BUDGES_SERV_AGY_ID`, `sa`.`BUDGES_SERV_AGY_CODE`, `sa`.`SERV_AGY_NAME`
FROM `SERVICE_AGENCY` `sa`, `service_agency_acct` `saa` 
where `sa`.`BUDGES_SERV_AGY_ID` = `saa`.`sa_ID`
and `saa`.`saa_budget_tag` = 'P40' and `sa`.`sa_status_flag` = 'A'
order by `sa`.`SERV_AGY_NAME`;

drop view if exists v_service_agency_appropriation_types;

CREATE ALGORITHM=UNDEFINED
VIEW `v_service_agency_appropriation_types` AS
select `saa`.`saa_ID`, 
`saa`.`sa_ID`, 
`saa`.`aa_ID`, 
`sa`.`BUDGES_SERV_AGY_CODE`, 
`sa`.`SERV_AGY_NAME`,
`aa`.`BUDGES_APPROP_ACCT_CODE`, 
`aa`.`BUDGES_APPROP_ACCT_NAME`,
`saa`.`saa_budget_tag`
from `SERVICE_AGENCY` `sa`, `service_agency_acct` `saa`, `APPROP_ACCOUNT` `aa`
where `sa`.`budges_serv_agy_id` = `saa`.`sa_Id`
and `aa`.`budges_approp_acct_id` = `saa`.`aa_Id`
and `sa`.`sa_status_flag` != 'I'
and `aa`.`aa_status_flag` != 'I'
order by `BUDGES_SERV_AGY_CODE`;



