ALTER TABLE `proc_mod_item_install_in_out`
  ADD COLUMN `imi_ID` INT(10) UNSIGNED NULL,
  ADD INDEX `FK_miiio_imi_ID_idx` USING BTREE (`imi_ID`),
  ADD CONSTRAINT `FK_miiio_imi_ID` FOREIGN KEY (`imi_ID`) REFERENCES `proc_mod_item_individual_install` (`imi_ID`) ON DELETE CASCADE ON UPDATE CASCADE;