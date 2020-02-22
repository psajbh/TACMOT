ALTER TABLE `proc_mod_item_individual_install`
  ADD INDEX `FK_imi_mi_ID_idx` USING BTREE (`mi_ID`),
  ADD INDEX `FK_imi_qty_byd_ID_idx` USING BTREE (`imi_qty_byd_ID`),
  ADD INDEX `FK_imi_total_byd_ID_idx` USING BTREE (`imi_total_byd_ID`),
  ADD INDEX `FK_imi_qty_install_byd_ID_idx` USING BTREE (`imi_qty_install_byd_ID`),
  ADD INDEX `FK_imi_tot_install_byd_ID_idx` USING BTREE (`imi_tot_install_byd_ID`);