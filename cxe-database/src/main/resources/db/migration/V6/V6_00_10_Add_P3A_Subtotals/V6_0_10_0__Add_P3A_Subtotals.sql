ALTER TABLE `proc_mod_grp` 
 ADD COLUMN `mg_proc_qty_byd_ID` int(10) unsigned DEFAULT NULL AFTER `mg_type`,  
 ADD COLUMN `mg_proc_UC_byd_ID` int(10) unsigned DEFAULT NULL AFTER `mg_proc_qty_byd_ID`,  
 ADD COLUMN `mg_proc_subtot_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `mg_proc_UC_byd_ID`, 
 
 ADD COLUMN `mg_support_qty_byd_ID` int(10) unsigned DEFAULT NULL AFTER `mg_proc_subtot_byd_ID`,  
 ADD COLUMN `mg_support_UC_byd_ID` int(10) unsigned DEFAULT NULL AFTER `mg_support_qty_byd_ID`,  
 ADD COLUMN `mg_support_subtot_byd_ID` INT(10) UNSIGNED DEFAULT NULL AFTER `mg_support_UC_byd_ID`,
 
 ADD COLUMN `mg_proc_support_qty_byd_ID` int(10) unsigned DEFAULT NULL AFTER `mg_support_subtot_byd_ID`,  
 ADD COLUMN `mg_proc_support_UC_byd_ID` int(10) unsigned DEFAULT NULL AFTER `mg_proc_support_qty_byd_ID`,  
 ADD COLUMN `mg_proc_support_subtot_byd_ID` INTEGER(10) UNSIGNED AFTER `mg_proc_support_UC_byd_ID`,  
 
 ADD COLUMN `mg_install_qty_byd_ID` int(10) unsigned DEFAULT NULL AFTER `mg_proc_support_subtot_byd_ID`,  
 ADD COLUMN `mg_install_UC_byd_ID` int(10) unsigned DEFAULT NULL AFTER `mg_install_qty_byd_ID`,  
 ADD COLUMN `mg_install_subtot_byd_ID` INTEGER(10) UNSIGNED AFTER `mg_install_UC_byd_ID`, 
 
 ADD COLUMN `mg_total_cost_qty_byd_ID` int(10) unsigned DEFAULT NULL AFTER `mg_install_subtot_byd_ID`,
 ADD COLUMN `mg_total_cost_UC_byd_ID` int(10) unsigned DEFAULT NULL AFTER `mg_total_cost_qty_byd_ID`,
 ADD COLUMN `mg_total_cost_byd_ID` INTEGER(10) UNSIGNED AFTER `mg_total_cost_UC_byd_ID`;
 