Delete from Mapping;

INSERT INTO `Mapping`
(`mapping_id`,`raw_column`,`valid_column`,`type_id`)
VALUES
(1,'Account','account',1),
(2,'Account Title','accountTitle',1),
(3,'Organization','organization',1),
(4,'Budget Activity','budgetActivity',1),
(5,'Budget Activity Title','budgetActivityTitle',1),
(6,'Line Number','lineNumber',1),
(7,'BSA','budgetSubActivity',1),
(8,'Budget Sub Activity (BSA) Title','budgetSubActivityTitle',1),
(9,'Line Item','liNumber',1),
(10,'Line Item Title','liTitle',1),
(11,'Cost Type','costType',1),
(12,'Cost Type Title','costTypeTitle',1),
(13,'Add/ Non-Add','addNonAdd',1),
(14,'FY 2017 Quantity','pyQuantity',1),
(15,'FY 2017 Amount','pyAmount',1),
(16,'FY 2018 Quantity','cyQuantity',1),
(17,'FY 2018 Amount','cyAmount',1),
(18,'FY 2019 Base','by1BaseAmount',1),
(19,'FY 2019 OCO','by1OCOAmount',1),
(20,'FY 2019 Quantity','by1Quantity',1),
(21,'FY 2019 Amount','by1Amount',1),
(22,'FY 2020 Quantity','by2Quantity',1),
(23,'FY 2020 Amount','by2Amount',1),
(24,'FY 2021 Quantity','by3Quantity',1),
(25,'FY 2021 Amount','by3Amount',1),
(26,'FY 2022 Quantity','by4Quantity',1),
(27,'FY 2022 Amount','by4Amount',1),
(28,'FY 2023 Quantity','by5Quantity',1),
(29,'FY 2023 Amount','by5Amount',1),
(30,'Classification','classification',1),
(31,'Account','account',2),
(32,'Account Title','accountTitle',2),
(33,'Organization','organization',2),
(34,'Budget Activity','budgetActivity',2),
(35,'Budget Activity Title','budgetActivityTitle',2),
(36,'Line Number','lineNumber',2),
(37,'PE / BLI','peNumber',2),
(38,'Program Element / Budget Line Item (BLI) Title','peTitle',2),
(39,'RDT&E Project','projectNumber',2),
(40,'RDT&E Project Title','projectTitle',2),
(41,'Fund Category','fundCategory',2),
(42,'FY 2017 Amount','pyAmount',2),
(43,'FY 2018 Amount','cyAmount',2),
(44,'FY 2019 Base','by1BaseAmount',2),
(45,'FY 2019 OCO','by1OCOAmount',2),
(46,'FY 2019 Amount','by1Amount',2),
(47,'FY 2020 Amount','by2Amount',2),
(48,'FY 2021 Amount','by3Amount',2),
(49,'FY 2022 Amount','by4Amount',2),
(50,'FY 2023 Amount','by5Amount',2),
(51,'Classification','classification',2);

ALTER TABLE `P1Data` 
ADD COLUMN `by1BaseAmount` DECIMAL(12,3) NULL COMMENT '' AFTER `cyAmount`,
ADD COLUMN `by1OCOAmount` DECIMAL(12,3) NULL COMMENT '' AFTER `by1BaseAmount`;

ALTER TABLE `R1Data` 
ADD COLUMN `by1BaseAmount` DECIMAL(12,3) NULL COMMENT '' AFTER `cyAmount`,
ADD COLUMN `by1OCOAmount` DECIMAL(12,3) NULL COMMENT '' AFTER `by1BaseAmount`;


