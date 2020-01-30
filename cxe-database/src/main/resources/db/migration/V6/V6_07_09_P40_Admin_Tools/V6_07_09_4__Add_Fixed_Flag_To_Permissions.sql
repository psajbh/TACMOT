-- Add flag to Permission table to define which permissions are static
ALTER TABLE `permission` 
ADD COLUMN `perm_fixed` TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'Denotes whether the permission is editable.' AFTER `DATE_MODIFIED`;