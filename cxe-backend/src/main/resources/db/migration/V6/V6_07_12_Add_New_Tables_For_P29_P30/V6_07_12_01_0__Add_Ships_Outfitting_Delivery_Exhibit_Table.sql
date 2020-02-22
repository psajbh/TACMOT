CREATE TABLE `proc_ships_outfitting_delivery_exhibit` (
  `sod_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Primary key.',
  `lie_ID` INT(10) UNSIGNED NOT NULL COMMENT 'Foreign key link back to the parent line item for this exhibit.',
  `toa_outfitting_total_byd_ID` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT 'Foreign key to a standard costs entry to represent Full Funding TOA outfitting total costs.',
  `toa_delivery_total_byd_ID` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT 'Foreign key to a standard costs entry to represent Full Funding TOA delivery total costs.',
  `toa_first_destination_total_byd_ID` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT 'Foreign key to a standard costs entry to represent Full Funding TOA first destination total costs.',
  `date_created` TIMESTAMP NULL DEFAULT NULL,
  `created_by_user` VARCHAR(255) NULL DEFAULT NULL,
  `modified_by_user` VARCHAR(255) NULL DEFAULT NULL,
  `date_modified` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`sod_ID`),
  INDEX `FK_lie_ID_idx` (`lie_ID` ASC),
  CONSTRAINT `FK_lie_ID`
    FOREIGN KEY (`lie_ID`)
    REFERENCES `proc_li_exhibit` (`lie_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
COMMENT = 'Table which holds a outfitting and delivery exhbit: the overal totals for each primary category (Outfitting, Delivery, First Destination) and the cost list of each class of ship present in the exhibit.';
