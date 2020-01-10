-- Give ROLE_P40_EDIT_LINE_ITEM to Site Admin
INSERT INTO `role_permission` (`role_id`, `perm_id`) select 4 as `role_id`, `perm_id` from `permission` where `perm_name`='ROLE_P40_EDIT_LINE_ITEM';