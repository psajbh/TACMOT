
-- TRS 5/20/13 - Rename table proc_spare_part_end_item to proc_spare_part_item and its columns accordingly

ALTER TABLE `proc_spare_part_end_item` RENAME TO `proc_spare_part_item`,
 CHANGE COLUMN `spei_ID` `spi_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
 CHANGE COLUMN `spei_name` `spi_name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
 CHANGE COLUMN `spei_display_order` `spi_display_order` SMALLINT(5) UNSIGNED NOT NULL,
 CHANGE COLUMN `spei_VDR_byd_ID` `spi_VDR_byd_ID` INT(10) UNSIGNED DEFAULT NULL,
 CHANGE COLUMN `spei_WCF_byd_ID` `spi_WCF_byd_ID` INT(10) UNSIGNED DEFAULT NULL,
 CHANGE COLUMN `spei_byd_ID` `spi_byd_ID` INT(10) UNSIGNED DEFAULT NULL,
 CHANGE COLUMN `spei_P1_line_num` `spi_P1_line_num` VARCHAR(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
 CHANGE COLUMN `spei_MDAP` `spi_MDAP` VARCHAR(4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
 CHANGE COLUMN `spei_elem_num` `spi_elem_num` VARCHAR(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 0,
 CHANGE COLUMN `spei_remarks` `spi_remarks` TEXT CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
 DROP PRIMARY KEY,
 ADD PRIMARY KEY  USING BTREE(`spi_ID`)
, DROP INDEX `FK_spei_spba_ID`
, DROP INDEX `FK_spei_VDR_byd_ID`
, DROP INDEX `FK_spei_WCF_byd_ID`,
 ADD INDEX `FK_spi_spba_ID` USING BTREE(`spba_ID`),
 ADD INDEX `FK_spi_VDR_byd_ID` USING BTREE(`spi_VDR_byd_ID`),
 ADD INDEX `FK_spi_WCF_byd_ID` USING BTREE(`spi_WCF_byd_ID`),
 DROP FOREIGN KEY `FK_spei_spba_ID`,
 DROP FOREIGN KEY `FK_spei_VDR_byd_ID`,
 DROP FOREIGN KEY `FK_spei_WCF_byd_ID`;