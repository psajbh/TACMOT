INSERT INTO `role_permission` (role_id, perm_id)
SELECT * FROM (SELECT role_id from role where role_name='ROLE_ANALYST') AS r, (SELECT perm_id from permission where perm_name='ROLE_P40_VIEW_ALL_JB_BUILDS') AS p  
WHERE NOT EXISTS (
    SELECT role_id, perm_id FROM `role_permission` WHERE perm_id =(SELECT perm_id FROM `permission` where perm_name='ROLE_P40_VIEW_ALL_JB_BUILDS') 
    and role_id=(SELECT role_id from role where role_name='ROLE_ANALYST')
) LIMIT 1;