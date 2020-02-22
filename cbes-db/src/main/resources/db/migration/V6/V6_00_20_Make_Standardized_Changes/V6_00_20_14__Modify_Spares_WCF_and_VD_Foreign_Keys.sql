ALTER TABLE `proc_spare_part_rqmt`
  CHANGE COLUMN `spr_wcf_subtot_byd_fk` `spr_wcf_subtot_byd_ID` INT(10) UNSIGNED DEFAULT NULL,
  CHANGE COLUMN `spr_vd_subtot_byd_fk` `spr_vd_subtot_byd_ID` INT(10) UNSIGNED DEFAULT NULL,
  ADD CONSTRAINT `FK_spr_wcf_subtot_byd_ID` FOREIGN KEY `FK_spr_wcf_subtot_byd_ID` (`spr_wcf_subtot_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_spr_vd_subtot_byd_ID` FOREIGN KEY `FK_spr_vd_subtot_byd_ID` (`spr_vd_subtot_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;