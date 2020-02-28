SET NAMES utf8;
SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;
SET SQL_SAFE_UPDATES=0;

-- Configuration for local developer databases.
-- Note: CXE-6006 sm_user failure was caused between mismatch of cf_env. Win10 need to suffix system name with 'dtic.mil'.

DELETE FROM config where cf_name = 'disable.rmi';

INSERT INTO `config` (`cf_name`,`cf_value`,`cf_type`,`cf_desc`,`cf_env`,`cf_cycle`,`cf_year`,`cf_read_only`)
VALUES('disable.rmi','true','BOOLEAN','Disable RMI invocations','default','xxxxx',0,0);

-- Update any values which might have the old e-mail address.
UPDATE config
SET cf_value = 'dtic.belvoir.pm.list.r2-support@mail.mil'
WHERE cf_value = 'R2Support@dtic.mil' OR cf_value = 'dtic.belvoir.pm.list.r2-masslight-contractors@mail.mil';

delete from user_role where user_id in (
select BUDGES_USER_ID from USER where user_ldap_id IN (
	'johnsonm0000','gentrym0000','ibrahima0519','tenpasj0001','trans0000','westa0000','ahmedn0000',
	'danielse0000','zumkhawalaa0000','trowj0000','sondereggere0000','jacobsw0000','baslerc0000',
	'millerc0000','westburyc0000','woode0000','hartj0000','oneilll0000','timpsonm0000'
)); 

DELETE from BUDGES_USERS where user_ldap_id IN (
	'johnsonm0000','gentrym0000','ibrahima0519','tenpasj0001','trans0000','westa0000','ahmedn0000',
	'danielse0000','zumkhawalaa0000','trowj0000','sondereggere0000','jacobsw0000','baslerc0000',
	'millerc0000','westburyc0000','woode0000','hartj0000','oneilll0000','timpsonm0000');
    
DELETE from USER where user_ldap_id IN (
	'johnsonm0000','gentrym0000','ibrahima0519','tenpasj0001','trans0000','westa0000','ahmedn0000',
	'danielse0000','zumkhawalaa0000','trowj0000','sondereggere0000','jacobsw0000','baslerc0000',
	'millerc0000','westburyc0000','woode0000','hartj0000','oneilll0000','timpsonm0000');

INSERT INTO `USER`
(`VERSION`,`USER_LDAP_ID`,`STATUS_FLAG`,`FULL_NAME`,`FIRST_NAME`,`MIDDLE_INITIAL`,`LAST_NAME`,`PHONE_NUM`,`EMAIL`,`LAST_VISIT_DATE`,`CREATE_PE_PRIV`,`ROLE`)
VALUES
(0,'johnsonm0000','A','Marlin Johnson','Marlin',NULL,'Johnson','7037678028','marlin.d.johnson8.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'gentrym0000','A','Michael Gentry','Michael','R','Gentry','703.767.9045','michael.r.gentry20.ctr@dtic.mil','2013-05-02 08:50:54','Y','R2User'),
(0,'ibrahima0519','A','Abdulaziz Ibrahim','Abdulaziz',NULL,'Ibrahim','703-767-9135','abdulaziz.m.ibrahim.ctr@dtic.mil','2013-03-08 08:00:24','Y','R2AppMgr'),
(0,'tenpasj0001','A','Jan tenPas','Jan','','tenPas','703-767-8016','jtenpas.ctr@dtic.mil','2013-05-02 07:27:56','Y','R2AppMgr'),
(0,'trans0000','A','Sy Tran','Sy',NULL,'Tran','703-338-8359','stran.ctr@dtic.mil','2013-05-02 10:07:27','Y','R2AppMgr'),
(0,'westa0000','A','Andy West','Andy',NULL,'West','7037679006','awest.ctr@dtic.mil','2013-04-11 14:12:08','Y','R2AppMgr'),
(0,'ahmedn0000','A','Nabil Ahmed','Nabil',NULL,'Ahmed','7037679025','Nabil.Ahmed.ctr@dtic.mil','2013-05-30 13:52:08','Y','R2User'),
(0,'danielse0000','A','Eric Daniels','Eric','J','Daniels','703-767-8028','Eric.Daniels.ctr@dtic.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'zumkhawalaa0000','A','Amar Zumkhawala','Amar',NULL,'Zumkhawala','703-767-9036','Amar.Zumkhawala.ctr@dtic.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'trowj0000','A','Jennifer Trow','Jennifer','M','Trow','703-767-8001','Jennifer.M.Trow.ctr@mail.mil','2015-09-02 13:52:08','Y','R2AppMgr'),
(0,'sondereggere0000','A','Evan Sonderegger','Evan','B','Sonderegger','703-767-8028','evan.b.sonderegger.ctr@dtic.mil','2015-01-09 13:52:08','Y','R2AppMgr'),
(0,'jacobsw0000','A','Will Jacobs','William','H','Jacobs','703-767-9060','William.H.Jacobs.ctr@dtic.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'baslerc0000','A','Charles Basler','Charles','P','Basler','703-767-9144','charles.p.basler.ctr@dtic.mil','2013-05-30 13:52:08','Y','R2User'),
(0,'millerc0000','A','Christopher Miller','Christopher','W','Miller','703-767-9036','christopher.w.miller96.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'westburyc0000','A','Cameron Westbury','Cameron','H','Westbury','703-767-8007','cameron.h.westbury.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'woode0000','A','Edward Wood','Edward','D','Wood','703-767-9036','edward.d.wood7.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'hartj0000','A','John Hart','John','B','Hart','703-767-8007','john.b.hart.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'oneilll0000','A','Lynn ONeill','Lynn','L','ONeill','703-767-9045','lynn.l.oneill.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'timpsonm0000','A','Michael Timpson','Michael','D','Timpson','571-448-9833','michael.d.timpson.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr');

insert into BUDGES_USERS (version, user_ldap_id)
	select 0, user_ldap_id from USER;

insert into user_role (user_id, role_id)
 select BUDGES_USER_ID, 1 from USER where role = 'R2AppMgr';
 
insert into user_role (user_id, role_id)
 select BUDGES_USER_ID, 2 from USER where role = 'R2User';
 
delete from `USER_SERVICE_AGENCY` where `BUDGES_USER_ID` >= 0;

-- mix in logical user|role|agency data for non app managers
insert into USER_SERVICE_AGENCY (BUDGES_USER_ID, BUDGES_SERV_AGY_ID) 
select u.BUDGES_USER_ID, sa.BUDGES_SERV_AGY_ID  
from USER u, SERVICE_AGENCY sa
where u.ROLE != 'R2AppMgr'
and u.user_ldap_id IN ('johnsonm0000', 'gentrym0000', 'ibrahima0519')
and sa.BUDGES_SERV_AGY_CODE = 'NAVY';

insert into USER_SERVICE_AGENCY (BUDGES_USER_ID, BUDGES_SERV_AGY_ID) 
select u.BUDGES_USER_ID, sa.BUDGES_SERV_AGY_ID  
from USER u, SERVICE_AGENCY sa
where u.ROLE != 'R2AppMgr'
and u.user_ldap_id IN ('johnsonm0000', 'gentrym0000', 'ibrahima0519')
and sa.BUDGES_SERV_AGY_CODE = 'OSD';

insert into USER_SERVICE_AGENCY (BUDGES_USER_ID, BUDGES_SERV_AGY_ID) 
select u.BUDGES_USER_ID, sa.BUDGES_SERV_AGY_ID  
from USER u, SERVICE_AGENCY sa
where u.ROLE != 'R2AppMgr'
and u.user_ldap_id IN ('tenpasj0001', 'trans0000', 'millerc0000')
and sa.BUDGES_SERV_AGY_CODE = 'SOCOM';

insert into USER_SERVICE_AGENCY (BUDGES_USER_ID, BUDGES_SERV_AGY_ID) 
select u.BUDGES_USER_ID, sa.BUDGES_SERV_AGY_ID  
from USER u, SERVICE_AGENCY sa
where u.ROLE != 'R2AppMgr'
and u.user_ldap_id IN ('westburyc0000', 'oneilll0000')
and sa.BUDGES_SERV_AGY_CODE = 'SDA';

insert into USER_SERVICE_AGENCY (BUDGES_USER_ID, BUDGES_SERV_AGY_ID) 
select u.BUDGES_USER_ID, sa.BUDGES_SERV_AGY_ID  
from USER u, SERVICE_AGENCY sa
where u.ROLE != 'R2AppMgr'
and u.user_ldap_id IN ('jacobsw0000', 'westa0000')
and sa.BUDGES_SERV_AGY_CODE = 'OSD';

insert into USER_SERVICE_AGENCY (BUDGES_USER_ID, BUDGES_SERV_AGY_ID) 
select u.BUDGES_USER_ID, sa.BUDGES_SERV_AGY_ID  
from USER u, SERVICE_AGENCY sa
where u.ROLE != 'R2AppMgr'
and u.user_ldap_id IN ('baslerc0000', 'danielse0000')
and sa.BUDGES_SERV_AGY_CODE = 'SOCOM';
delete from FEATURE_ACCESS  where feature_id >= 0;

 
 
 
 
 