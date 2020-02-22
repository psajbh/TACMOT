ALTER TABLE `proc_advance_rqmt` 
 ADD COLUMN `ar_CFE_subtot_byd_ID` int(10) unsigned DEFAULT NULL AFTER `ar_date_1st_sys_complete_footnote`,  
 ADD COLUMN `ar_GFE_subtot_byd_ID` int(10) unsigned DEFAULT NULL AFTER `ar_CFE_subtot_byd_ID`,  
 ADD COLUMN `ar_EOQ_subtot_byd_ID` int(10) UNSIGNED DEFAULT NULL AFTER `ar_GFE_subtot_byd_ID`, 
 ADD COLUMN `ar_other_subtot_byd_ID` int(10) UNSIGNED DEFAULT NULL AFTER `ar_EOQ_subtot_byd_ID`;