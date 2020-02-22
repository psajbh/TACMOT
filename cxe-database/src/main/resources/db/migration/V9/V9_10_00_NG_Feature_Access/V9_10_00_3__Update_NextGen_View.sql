SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;

drop view if exists v_r2_service_agencies;
drop view if exists v_p40_service_agencies;
drop view if exists v_service_agency_appropriation_types;

CREATE ALGORITHM=UNDEFINED
VIEW `v_service_agency_appropriation_types` AS
SELECT `saa`.`saa_ID`, 
`saa`.`sa_ID`, 
`sa`.`BUDGES_SERV_AGY_CODE`, 
`sa`.`SERV_AGY_NAME`,
`saa`.`aa_ID`, 
`aa`.`BUDGES_APPROP_ACCT_CODE`, 
`aa`.`BUDGES_APPROP_ACCT_NAME`,
`saa`.`saa_budget_tag`
FROM `SERVICE_AGENCY` `sa`
	JOIN `service_agency_acct` `saa` ON `sa`.`budges_serv_agy_id` = `saa`.`sa_Id`
    JOIN `APPROP_ACCOUNT` `aa` ON  `aa`.`budges_approp_acct_id` = `saa`.`aa_Id`
    WHERE `sa`.`sa_status_flag` != 'I'
	AND `aa`.`aa_status_flag` != 'I'
	ORDER BY `BUDGES_SERV_AGY_CODE`;
    
drop view if exists v_budget_activity_types;

CREATE ALGORITHM=UNDEFINED
VIEW `v_budget_activity_types` AS
SELECT pba.ba_ID, pba.aa_ID, saapt.sa_ID,
		saapt.BUDGES_SERV_AGY_CODE as sa_code, saapt.SERV_AGY_NAME as sa_name, 
        saapt.BUDGES_APPROP_ACCT_CODE as appn_code, saapt.BUDGES_APPROP_ACCT_NAME as appn_name,
		pba.ba_num, pba.ba_title, ba_RDTE_flag AS isR2
	FROM proc_budget_activity AS pba
	JOIN v_service_agency_appropriation_types AS saapt ON saapt.aa_ID = pba.aa_ID
	WHERE pba.ba_status_flag = 'A'
		ORDER BY saapt.aa_ID, pba.ba_num, pba.ba_title;

-- view for r2 service agencies
CREATE ALGORITHM=UNDEFINED
VIEW `v_r2_service_agencies` AS
select distinct sa_ID, sa_code, sa_name from v_budget_activity_types where isR2 = 1;

-- view for p40 service agencies
CREATE ALGORITHM=UNDEFINED
VIEW `v_p40_service_agencies` AS
select distinct sa_ID, sa_code, sa_name from v_budget_activity_types where isR2 = 0;

CREATE ALGORITHM=UNDEFINED
VIEW `v_r2_appn_ba_bsa` AS
select distinct vb.sa_ID, vb.aa_ID, vb.appn_code, vb.ba_ID, vb.ba_num, vb.ba_title,
pbsa.bsa_num, pbsa.bsa_title 
from v_budget_activity_types vb 
LEFT JOIN proc_budget_sub_activity as pbsa USING (ba_ID)
where isR2 = 1
order by appn_code, ba_id, ba_num; 

CREATE ALGORITHM=UNDEFINED
VIEW `v_p40_appn_ba_bsa` AS
select distinct vb.sa_ID, vb.aa_ID, vb.appn_code, vb.ba_ID, vb.ba_num, vb.ba_title,
pbsa.bsa_num, pbsa.bsa_title 
from v_budget_activity_types vb 
LEFT JOIN proc_budget_sub_activity as pbsa USING (ba_ID)
where isR2 = 0
order by appn_code, ba_id, ba_num; 
