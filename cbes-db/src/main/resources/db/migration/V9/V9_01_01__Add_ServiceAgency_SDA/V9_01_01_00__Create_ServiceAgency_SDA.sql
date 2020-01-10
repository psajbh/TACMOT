INSERT INTO SERVICE_AGENCY (BUDGES_SERV_AGY_ID, BUDGES_SERV_AGY_CODE, SERV_AGY_NAME, BUDGES_APPROP_ACCT_ID, sa_status_flag, sa_SMCA_tag, LOGO_FILE_NAME, NO_WARNING) 
VALUES ('150', 'SDA', 'Space Development Agency', '4', 'A', '0', 'TEST.png', 'N');

INSERT INTO service_agency_acct(aa_ID, sa_ID, saa_budget_tag, saa_status_flag)
VALUES (
(SELECT BUDGES_APPROP_ACCT_ID FROM APPROP_ACCOUNT WHERE BUDGES_APPROP_ACCT_CODE = '0400'),
(SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'SDA'), 
  'R2', 'A');

INSERT INTO PE_SUFFIX(PS_SERV_AGY_ID, PS_SUFFIX, PS_DATE_ACTIVE)
VALUES (
  (SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'SDA'),
  'SDA', CURRENT_TIMESTAMP);

INSERT INTO jb_volume (
  agencyVolume, bookDescription, bookGroup, bookLabel, bookNumber,
  generateLineItemTocByBA, generateLineItemTocByTitle, generateP1,
  generateProgramElementTocByBA, generateProgramElementTocByTitle, generateR1, 
  generateR1Summary, generateR1c, generateR1d,
  overrideDocumentAssemblyOptionsInXml, p40Volume, serviceAgencyId
) VALUES (1, 'Space Development Agency', '5', 'Volume', '5',  0, 0, 0, 1, 1, 0,  0, 0, 0,
  1, 0, (SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE='SDA'));
