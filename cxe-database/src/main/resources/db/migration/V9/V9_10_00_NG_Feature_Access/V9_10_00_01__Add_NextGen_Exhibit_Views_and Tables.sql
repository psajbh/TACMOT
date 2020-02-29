SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;

DROP VIEW IF EXISTS v_service_agency_appropriation_types;

CREATE ALGORITHM=UNDEFINED
VIEW `v_service_agency_appropriation_types` AS
SELECT `saa`.`saa_ID`, `saa`.`sa_ID`, 
	`sa`.`BUDGES_SERV_AGY_CODE`,`sa`.`SERV_AGY_NAME`,
	`saa`.`aa_ID`,`aa`.`BUDGES_APPROP_ACCT_CODE`,`aa`.`BUDGES_APPROP_ACCT_NAME`,
	`saa`.`saa_budget_tag`
FROM `SERVICE_AGENCY` `sa`
	JOIN `service_agency_acct` `saa` ON `sa`.`budges_serv_agy_id` = `saa`.`sa_Id`
    JOIN `APPROP_ACCOUNT` `aa` ON  `aa`.`budges_approp_acct_id` = `saa`.`aa_Id`
WHERE `sa`.`sa_status_flag` != 'I'
	AND `aa`.`aa_status_flag` != 'I'
ORDER BY `BUDGES_SERV_AGY_CODE`;

DROP VIEW IF EXISTS v_budget_activity_types;

CREATE ALGORITHM=UNDEFINED
VIEW `v_budget_activity_types` AS
SELECT pba.ba_ID, pba.aa_ID, saapt.sa_ID,
		saapt.BUDGES_SERV_AGY_CODE as sa_code, saapt.SERV_AGY_NAME as sa_name, 
        saapt.BUDGES_APPROP_ACCT_CODE as appn_code, 
        saapt.BUDGES_APPROP_ACCT_NAME as appn_name,
		pba.ba_num, pba.ba_title, ba_RDTE_flag AS isR2
FROM proc_budget_activity AS pba
	JOIN v_service_agency_appropriation_types AS saapt ON saapt.aa_ID = pba.aa_ID
WHERE pba.ba_status_flag = 'A'
ORDER BY saapt.aa_ID, pba.ba_num, pba.ba_title;

DROP VIEW IF EXISTS v_r2_service_agencies;

CREATE ALGORITHM=UNDEFINED
VIEW `v_r2_service_agencies` AS
SELECT DISTINCT sa_ID, sa_code, sa_name 
FROM v_budget_activity_types 
WHERE isR2 = 1
ORDER BY sa_code;

DROP VIEW IF EXISTS v_p40_service_agencies;

CREATE ALGORITHM=UNDEFINED
VIEW `v_p40_service_agencies` AS
SELECT DISTINCT sa_ID, sa_code, sa_name 
FROM v_budget_activity_types 
WHERE isR2 = 0
ORDER BY sa_code;

DROP VIEW IF EXISTS v_r2_appn_ba;

CREATE ALGORITHM=UNDEFINED
VIEW `v_r2_appn_ba` AS
SELECT DISTINCT `vb`.`sa_ID`, 
	`vb`.`aa_ID`, `vb`.`appn_code`, `vb`.`appn_name`, 
	`vb`.`ba_ID`, `vb`.`ba_num`, `vb`.`ba_title`,
	 `pbsa`.`bsa_ID`, `pbsa`.`bsa_num`, `pbsa`.`bsa_title` 
FROM `v_budget_activity_types` vb 
	 LEFT JOIN `proc_budget_sub_activity` as pbsa USING (ba_ID)
WHERE isR2 = 1
ORDER BY sa_ID, APPN_CODE, BA_NUM, BSA_NUM; 


drop table if exists R2_SERV_AGY_APPN_BA_BSA;

CREATE TABLE `R2_SERV_AGY_APPN_BA_BSA` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT '0',
  `SERVICE_AGENCY_ID` int(10) unsigned NOT NULL,
  `APPN_ID` int(10) unsigned NOT NULL,
  `BUDGET_ACTIVITY_ID` int(10) unsigned NOT NULL,
  `BUDGET_SUB_ACTIVITY_ID` int(10),
  `APP_CODE` varchar(10),
  `APP_NAME` varchar(75),
  `BA_NUM` int(3),
  `BA_TITLE` varchar(255),
  `BSA_NUM` int(4),
  `BSA_TITLE` varchar(255),
   PRIMARY KEY (`ID`)
)
ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO `R2_SERV_AGY_APPN_BA_BSA` (`SERVICE_AGENCY_ID`,`APPN_ID`,`BUDGET_ACTIVITY_ID`,
  `BUDGET_SUB_ACTIVITY_ID`, `APP_CODE`,`APP_NAME`,`BA_NUM`,`BA_TITLE`,`BSA_NUM`,`BSA_TITLE`) 
  select sa_ID, aa_ID, ba_ID, bsa_ID, appn_code, appn_name, ba_num, ba_title, bsa_num, bsa_title 
  from v_r2_appn_ba;

DROP VIEW IF EXISTS v_p40_appn_ba;

CREATE ALGORITHM=UNDEFINED
VIEW `v_p40_appn_ba` AS
SELECT DISTINCT `vb`.`sa_ID`, 
	`vb`.`aa_ID`, `vb`.`appn_code`, `vb`.`appn_name`, 
	`vb`.`ba_ID`, `vb`.`ba_num`, `vb`.`ba_title`,
	 `pbsa`.`bsa_ID`, `pbsa`.`bsa_num`, `pbsa`.`bsa_title` 
FROM `v_budget_activity_types` vb 
	 LEFT JOIN `proc_budget_sub_activity` as pbsa USING (ba_ID)
WHERE isR2 = 0
ORDER BY sa_ID, APPN_CODE, BA_NUM, BSA_NUM; 


drop table if exists `P40_SERV_AGY_APPN_BA_BSA`;

CREATE TABLE `P40_SERV_AGY_APPN_BA_BSA` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT '0',
  `SERVICE_AGENCY_ID` int(10) unsigned NOT NULL,
  `APPN_ID` int(10) unsigned NOT NULL,
  `BUDGET_ACTIVITY_ID` int(10) unsigned NOT NULL,
  `BUDGET_SUB_ACTIVITY_ID` int(10),
  `APP_CODE` varchar(10),
  `APP_NAME` varchar(75),
  `BA_NUM` int(3),
  `BA_TITLE` varchar(255),
  `BSA_NUM` int(4),
  `BSA_TITLE` varchar(255),
   PRIMARY KEY (`ID`)
)
ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO `P40_SERV_AGY_APPN_BA_BSA` (`SERVICE_AGENCY_ID`,`APPN_ID`,`BUDGET_ACTIVITY_ID`,
  `BUDGET_SUB_ACTIVITY_ID`, `APP_CODE`,`APP_NAME`,`BA_NUM`,`BA_TITLE`,`BSA_NUM`,`BSA_TITLE`) 
  select sa_ID, aa_ID, ba_ID, bsa_ID, appn_code, appn_name, ba_num, ba_title, bsa_num, bsa_title 
  from v_p40_appn_ba;

