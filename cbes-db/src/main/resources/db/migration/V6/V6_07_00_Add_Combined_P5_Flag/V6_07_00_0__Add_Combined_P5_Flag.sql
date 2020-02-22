ALTER TABLE `proc_line_item` 
ADD COLUMN `li_combined_P5_flag` TINYINT(1) NOT NULL DEFAULT '0' AFTER `li_adv_proc_justification`;