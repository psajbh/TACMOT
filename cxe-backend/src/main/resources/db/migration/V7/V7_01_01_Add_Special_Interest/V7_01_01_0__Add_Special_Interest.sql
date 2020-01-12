ALTER TABLE `proc_line_item` 
ADD COLUMN `li_special_interest` TINYINT(1) NULL DEFAULT NULL COMMENT '' AFTER `li_tracking_note`;
