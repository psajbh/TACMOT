INSERT IGNORE INTO `service_agency_acct`(`aa_ID`, `sa_ID`, `saa_budget_tag`, `saa_status_flag`)
VALUES (
  (SELECT `BUDGES_APPROP_ACCT_ID` FROM `APPROP_ACCOUNT` WHERE `BUDGES_APPROP_ACCT_CODE` = '0400'),
  (SELECT `BUDGES_SERV_AGY_ID` FROM `SERVICE_AGENCY` WHERE `BUDGES_SERV_AGY_CODE` = 'DCAA'), 
  'R2', 'A'
);

UPDATE `SERVICE_AGENCY`
SET `BUDGES_APPROP_ACCT_ID`=
  (SELECT `BUDGES_APPROP_ACCT_ID` FROM `APPROP_ACCOUNT` WHERE `BUDGES_APPROP_ACCT_CODE` = '0400')
WHERE `BUDGES_SERV_AGY_CODE` = 'DCAA';