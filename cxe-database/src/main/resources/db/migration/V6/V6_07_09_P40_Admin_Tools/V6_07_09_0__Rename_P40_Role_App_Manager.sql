-- Prefix all Roles with ROLE_  so that spring security can check roles
UPDATE `role` SET `role_name`='ROLE_App_Manager' WHERE `role_id`='1';