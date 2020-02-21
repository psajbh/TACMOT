ALTER TABLE `proc_mod_grp`
 ADD CONSTRAINT `FK_proc_mg_proc_qty_byd_ID` FOREIGN KEY `FK_proc_mg_proc_qty_byd_ID` (`mg_proc_qty_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_proc_UC_byd_ID` FOREIGN KEY `FK_proc_mg_proc_UC_byd_ID` (`mg_proc_UC_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_proc_byd_ID` FOREIGN KEY `FK_proc_mg_proc_byd_ID` (`mg_proc_subtot_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_support_qty_byd_ID` FOREIGN KEY `FK_proc_mg_support_qty_byd_ID` (`mg_support_qty_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_support_UC_byd_ID` FOREIGN KEY `FK_proc_mg_support_UC_byd_ID` (`mg_support_UC_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_support_byd_ID` FOREIGN KEY `FK_proc_mg_support_byd_ID` (`mg_support_subtot_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_proc_supp_qty_byd_ID` FOREIGN KEY `FK_proc_mg_proc_supp_qty_byd_ID` (`mg_proc_support_qty_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_proc_supp_UC_byd_ID` FOREIGN KEY `FK_proc_mg_proc_supp_UC_byd_ID` (`mg_proc_support_UC_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_proc_supp_byd_ID` FOREIGN KEY `FK_proc_mg_proc_supp_byd_ID` (`mg_proc_support_subtot_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_install_qty_byd_ID` FOREIGN KEY `FK_proc_mg_install_qty_byd_ID` (`mg_install_qty_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_install_UC_byd_ID` FOREIGN KEY `FK_proc_mg_install_UC_byd_ID` (`mg_install_UC_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_install_byd_ID` FOREIGN KEY `FK_proc_mg_install_byd_ID` (`mg_install_subtot_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_total_cost_qty_byd_ID` FOREIGN KEY `FK_proc_mg_total_cost_qty_byd_ID` (`mg_total_cost_qty_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_total_cost_UC_byd_ID` FOREIGN KEY `FK_proc_mg_total_cost_UC_byd_ID` (`mg_total_cost_UC_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
 ADD CONSTRAINT `FK_proc_mg_total_cost_byd_ID` FOREIGN KEY `FK_proc_mg_total_cost_byd` (`mg_total_cost_byd_ID`)
    REFERENCES `proc_bdgt_yrs_data` (`byd_ID`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT;