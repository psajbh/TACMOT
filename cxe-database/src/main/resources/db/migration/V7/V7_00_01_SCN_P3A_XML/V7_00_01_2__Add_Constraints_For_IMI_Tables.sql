ALTER TABLE `proc_mod_item_individual_install`
  ADD CONSTRAINT `FK_mi_ID` FOREIGN KEY (`mi_ID`) REFERENCES `proc_mod_item` (`mi_ID`) ON DELETE cascade ON UPDATE cascade,
  ADD CONSTRAINT `FK_imi_qty_byd_ID` FOREIGN KEY (`imi_qty_byd_ID`) REFERENCES `proc_bdgt_yrs_data` (`byd_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_imi_total_byd_ID` FOREIGN KEY (`imi_total_byd_ID`) REFERENCES `proc_bdgt_yrs_data` (`byd_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_imi_qty_install_byd_ID` FOREIGN KEY (`imi_qty_install_byd_ID`) REFERENCES `proc_bdgt_yrs_data` (`byd_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_imi_tot_install_byd_ID` FOREIGN KEY (`imi_tot_install_byd_ID`) REFERENCES `proc_bdgt_yrs_data` (`byd_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
