-- Give ROLE_P40_MANAGE_DELETE to Site Admin
insert into `role_permission` (`role_id`, `perm_id`) select 4 as `role_id`, `perm_id` from `permission` where `perm_name`='ROLE_P40_MANAGE_DELETE';
