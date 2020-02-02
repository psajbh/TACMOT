SET NAMES utf8;
SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;
SET SQL_SAFE_UPDATES=0;

delete from user_service_agency where budges_user_id is null;

delete from user_service_agency where budges_user_id IN (
select budges_user_id from USER where user_ldap_id IN (
	'johnsonm0000','gentrym0000','ibrahima0519','tenpasj0001','trans0000','westa0000','ahmedn0000',
	'danielse0000','zumkhawalaa0000','trowj0000','sondereggere0000''jacobsw0000','baslerc0000',
	'millerc0000','westburyc0000','woode0000','hartj0000','oneilll0000','timpsonm0000'
));

insert into user_service_agency (budges_user_id, budges_serv_agy_id) 
select u.budges_user_id, sa.budges_serv_agy_id  
from user u, service_agency sa
where u.role != 'R2AppMgr'
and sa.budges_serv_agy_code = 'OSD';

insert into user_service_agency (budges_user_id, budges_serv_agy_id) 
select u.budges_user_id, sa.budges_serv_agy_id  
from user u, service_agency sa
where u.role != 'R2AppMgr'
and sa.budges_serv_agy_code = 'SOCOM';
