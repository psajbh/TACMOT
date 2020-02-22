-- Give ROLE_P40_EDIT_LINE_ITEM to App Manager
INSERT INTO `role_permission` (`role_id`, `perm_id`) select 1 as `role_id`, `perm_id` from `permission` where `perm_name`='ROLE_P40_EDIT_LINE_ITEM';