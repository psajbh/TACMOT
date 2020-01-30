SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;


drop view if exists v_user_credentials;

CREATE ALGORITHM=UNDEFINED
VIEW v_user_credentials AS
SELECT DISTINCT 
	u.budges_user_id, 
	u.user_ldap_id, 
	u.role as user_role, 
	u.create_pe_priv, 
	u.create_li_priv, 
	IF (r.role_id=1, "ALL", concat(sa.budges_serv_agy_code, '-', sa.budges_serv_agy_id)) agencies,
	r.role_id, r.role_name
	from USER u, USER_SERVICE_AGENCY usa, SERVICE_AGENCY sa , user_role ur, role r
	where usa.budges_user_id = u.budges_user_id
	and usa.BUDGES_SERV_AGY_ID = sa.BUDGES_SERV_AGY_ID
	and ur.user_id = u.budges_user_id
	and ur.role_id = r.role_id
	and u.status_flag = 'A';


