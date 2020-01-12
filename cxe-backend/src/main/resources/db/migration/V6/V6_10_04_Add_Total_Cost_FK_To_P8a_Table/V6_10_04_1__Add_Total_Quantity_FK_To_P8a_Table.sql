ALTER TABLE `proc_ships_cost_cat` ADD COLUMN `p8a_qty_byd_ID` INT(10) UNSIGNED NULL DEFAULT NULL AFTER `p8a_tot_byd_ID` , 
  ADD CONSTRAINT `FK_scc_p8a_qty_byd_ID`
  FOREIGN KEY (`p8a_qty_byd_ID` )
  REFERENCES `proc_bdgt_yrs_data` (`byd_ID` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `FK_scc_p8a_qty_byd_ID_idx` (`p8a_qty_byd_ID` ASC) ;
