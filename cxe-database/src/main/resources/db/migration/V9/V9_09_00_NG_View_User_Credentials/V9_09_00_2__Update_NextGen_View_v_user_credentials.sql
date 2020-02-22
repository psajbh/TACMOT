SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;

drop view if exists v_user_credentials;

CREATE ALGORITHM=UNDEFINED
VIEW v_user_credentials AS
SELECT DISTINCT 
	`u`.`BUDGES_USER_ID`, 
	`u`.`USER_LDAP_ID`, 
	`u`.`ROLE` as `USER_ROLE`, 
     concat(`sa`.`BUDGES_SERV_AGY_CODE`, '-', `sa`.`BUDGES_SERV_AGY_ID`) `AGENCIES`,
	 `r`.`ROLE_ID`, `r`.`ROLE_NAME`, `u`.`FULL_NAME`, `u`.`FIRST_NAME`, `u`.`LAST_NAME`, `u`.`MIDDLE_INITIAL`, `u`.`EMAIL`
	from `USER` `u`, `USER_SERVICE_AGENCY` `usa`, `SERVICE_AGENCY` `sa` , `user_role` `ur`, `role` `r`
	where `usa`.`BUDGES_USER_ID` = `u`.`BUDGES_USER_ID`
	and `usa`.`BUDGES_SERV_AGY_ID` = `sa`.`BUDGES_SERV_AGY_ID`
	and `ur`.`USER_ID` = `u`.`BUDGES_USER_ID`
	and `ur`.`ROLE_ID` = `r`.`ROLE_ID`
    and `ur`.`ROLE_ID` != 1
	and `u`.`STATUS_FLAG` = 'A'
	UNION ALL
	SELECT DISTINCT 
	`u`.`BUDGES_USER_ID`, 
	`u`.`USER_LDAP_ID`, 
	`u`.`ROLE` as `USER_ROLE`, 
    'ALL' as AGENCIES,
	 `r`.`ROLE_ID`, `r`.`ROLE_NAME`, `u`.`FULL_NAME`, `u`.`FIRST_NAME`, `u`.`LAST_NAME`, `u`.`MIDDLE_INITIAL`, `u`.`EMAIL`
	from `USER` `u`, `user_role` `ur`, `role` `r`
	where r.role_id = 1
	and `ur`.`USER_ID` = `u`.`BUDGES_USER_ID`
	and `ur`.`ROLE_ID` = `r`.`ROLE_ID`
	and `u`.`STATUS_FLAG` = 'A';    
