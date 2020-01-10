INSERT INTO `SERVICE_AGENCY`
	(`BUDGES_SERV_AGY_ID`,`VERSION`,`BUDGES_SERV_AGY_CODE`,`SERV_AGY_NAME`,`BUDGES_APPROP_ACCT_ID`,`sa_status_flag`,`sa_SMCA_tag`,`LOGO_FILE_NAME`,`NO_WARNING`)
	SELECT 89,0,'DPAP','Defense Production Act Purchases',9999,'A',0,'DPAP.png','N' FROM dual
	WHERE NOT EXISTS (SELECT * FROM `SERVICE_AGENCY` WHERE `BUDGES_SERV_AGY_CODE` = 'DPAP');

DELETE FROM `jb_volume`;

/* R2 Service Volumes */
INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES ('Budget Activities 1, 2, and 3', '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, null, false, false);

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES ('Budget Activities 4 and 5', '2', 'Volume', '2', false, false, false, true, true, false, false, false, false, true, null, false, false);

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES ('Budget Activities 6 and 7', '3', 'Volume', '3', false, false, false, true, true, false, false, false, false, true, null, false, false);

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES (null, '4', null, null, false, false, false, true, true, false, false, false, false, true, null, false, false);

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES (null, '5', null, null, false, false, false, true, true, false, false, false, false, true, null, false, false);

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES (null, '6', null, null, false, false, false, true, true, false, false, false, false, true, null, false, false);

/* P40 Service Volumes */
INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES (null, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, null, true, false);

/* R2 Agency volumes */
INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DARPA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '2', 'Volume', '2', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'MDA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '3', 'Volume', '3', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'OSD';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '4', 'Volume', '4', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'CBDP';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '5', 'Volume', '5', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DCMA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '5', 'Volume', '5', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DHRA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '5', 'Volume', '5', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DISA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '5', 'Volume', '5', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DLA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '5', 'Volume', '5', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DSCA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '5', 'Volume', '5', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DSS';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '5', 'Volume', '5', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DTIC';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '5', 'Volume', '5', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DTRA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '5', 'Volume', '5', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'TJS';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '5', 'Volume', '5', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'SOCOM';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '5', 'Volume', '5', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'WHS';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '5', 'Volume', '5', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, false, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'OTE';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES ('Defense Geospatial Intelligence Agency', '5', '', '(see NIP and MIP Justification Books)', false, false, false, false, false, false, false, false, false, false, null, false, true);

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES ('Defense Intelligence Agency', '5', '', '(see NIP and MIP Justification Books)', false, false, false, false, false, false, false, false, false, false, null, false, true);

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES ('National Security Agency', '5', '', '(see NIP and MIP Justification Books)', false, false, false, false, false, false, false, false, false, false, null, false, true);

/* End R2 Agency Volumes */

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'CBDP';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DCAA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DCMA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DHRA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DISA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DLA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DMACT';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DPAP';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DSCA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DSS';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DODEA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'OSD';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'TJS';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'SOCOM';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'WHS';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '1', 'Volume', '1', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'JUON';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
SELECT SERV_AGY_NAME, '2', 'Volume', '2', false, false, false, true, true, false, false, false, false, true, BUDGES_SERV_AGY_ID, true, true FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'MDA';

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES (null, '1', null, null, false, false, false, true, true, false, false, false, false, true, null, true, true);

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES (null, '1', null, null, false, false, false, true, true, false, false, false, false, true, null, true, true);

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES (null, '1', null, null, false, false, false, true, true, false, false, false, false, true, null, true, true);

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES (null, '1', null, null, false, false, false, true, true, false, false, false, false, true, null, true, true);

INSERT INTO `jb_volume`
(`bookDescription`,`bookGroup`,`bookLabel`,`bookNumber`,`generateLineItemTocByBA`,`generateLineItemTocByTitle`,`generateP1`,`generateProgramElementTocByBA`,`generateProgramElementTocByTitle`,`generateR1`,`generateR1Summary`,`generateR1c`,`generateR1d`,`overrideDocumentAssemblyOptionsInXml`,`serviceAgencyId`, `p40volume`, `agencyVolume`)
VALUES (null, '1', null, null, false, false, false, true, true, false, false, false, false, true, null, true, true);

UPDATE `jb_volume` SET `bookDescription` = 'Department of Defense Education Activity (No Dependants)' WHERE bookDescription = 'Department of Defense Education Activity';

