ALTER TABLE `proc_spare_part_rqmt`
 ADD COLUMN `spr_wcf_subtot_byd_fk` int(10) UNSIGNED DEFAULT NULL AFTER `spr_display_order`,
 ADD COLUMN `spr_vd_subtot_byd_fk` int(10) UNSIGNED DEFAULT NULL AFTER `spr_wcf_subtot_byd_fk`;