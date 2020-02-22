-- Remove potential duplicates first
DELETE FROM `jb_volume` WHERE `p40Volume`=0 AND `serviceAgencyId` = 
(SELECT `BUDGES_SERV_AGY_ID` FROM `SERVICE_AGENCY` WHERE `BUDGES_SERV_AGY_CODE`="DCAA");

INSERT INTO `jb_volume` (
  `agencyVolume`, `bookDescription`, `bookGroup`, `bookLabel`, `bookNumber`,
  `generateLineItemTocByBA`, `generateLineItemTocByTitle`, `generateP1`,
  `generateProgramElementTocByBA`, `generateProgramElementTocByTitle`, `generateR1`, 
  `generateR1Summary`, `generateR1c`, `generateR1d`,
  `overrideDocumentAssemblyOptionsInXml`, `p40Volume`, `serviceAgencyId`
) VALUES (
  1, "Defense Contract Audit Agency", "5", "Volume", "5", 
  0, 0, 0,
  1, 1, 0, 
  0, 0, 0,
  1, 0, (SELECT `BUDGES_SERV_AGY_ID` FROM `SERVICE_AGENCY` WHERE `BUDGES_SERV_AGY_CODE`="DCAA")
);