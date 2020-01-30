-- Assign Switch User permission to App Manager if not already assigned 
INSERT INTO `role_permission` (role_id, perm_id)
SELECT * FROM (SELECT role_id from role where role_name='ROLE_APP_MANAGER') AS r, (SELECT perm_id from permission where perm_name='ROLE_P40_SWITCH_USER') AS p  
WHERE NOT EXISTS (
    SELECT role_id, perm_id FROM `role_permission` WHERE perm_id =(SELECT perm_id FROM `permission` where perm_name='ROLE_P40_SWITCH_USER') and role_id=(SELECT role_id from role where role_name='ROLE_APP_MANAGER')
) LIMIT 1;