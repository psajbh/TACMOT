-- Prefix all Roles with ROLE_  so that spring security can check roles
UPDATE `role` SET `role_name`='ROLE_Site_Admin' WHERE `role_id`='4';