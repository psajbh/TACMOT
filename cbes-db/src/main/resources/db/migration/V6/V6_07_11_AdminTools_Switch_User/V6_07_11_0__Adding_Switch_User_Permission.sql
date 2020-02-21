-- Insert Switch User Permission if it doesn't already exist 
INSERT INTO `permission` (perm_name, perm_desc, perm_fixed)
SELECT * FROM (SELECT 'ROLE_P40_SWITCH_USER', 'Allows App Manager to log in as another user to validate P-40 application behaviour.', '1') AS tmp
WHERE NOT EXISTS (
    SELECT perm_name FROM `permission` WHERE perm_name = 'ROLE_P40_SWITCH_USER'
) LIMIT 1;