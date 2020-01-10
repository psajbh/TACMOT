-- Add Audit columns to USER_LINE_ITEM
ALTER TABLE `user_line_item` 
ADD COLUMN `uli_modified_by_user` INT(10) UNSIGNED NOT NULL AFTER `DATE_MODIFIED`,
ADD COLUMN `uli_created_by_user` INT(10) UNSIGNED NOT NULL AFTER `uli_modified_by_user`,
ADD COLUMN `uli_date_created` DATETIME NOT NULL AFTER `uli_created_by_user`,
ADD COLUMN `uli_date_modified` DATETIME NOT NULL AFTER `uli_date_created`,
ADD INDEX `FK_uli_creator_user_id_idx` (`uli_created_by_user` ASC),
ADD INDEX `FK_uli_modifier_user_id_idx` (`uli_modified_by_user` ASC);
ALTER TABLE `user_line_item` 
ADD CONSTRAINT `FK_uli_creator_user_id`
  FOREIGN KEY (`uli_created_by_user`)
  REFERENCES `USER` (`BUDGES_USER_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `FK_uli_modifier_user_id`
  FOREIGN KEY (`uli_modified_by_user`)
  REFERENCES `USER` (`BUDGES_USER_ID`)
  ON DELETE NO ACTION

  ON UPDATE NO ACTION;