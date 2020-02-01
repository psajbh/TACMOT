SET NAMES utf8;
SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;


-- Configuration for local developer databases.
-- Note: CXE-6006 sm_user failure was caused between mismatch of cf_env. Win10 need to suffix system name with 'dtic.mil'.

-- Bootstrap local user's configuration:
INSERT INTO `config`
(`cf_name`,`cf_value`,`cf_type`,`cf_desc`,`cf_env`,`cf_cycle`,`cf_year`,`cf_read_only`)
VALUES
('user.localUserId','johnsonm0000','STRING','marlin ldap for local','1509934521@mil','xxxxx',0,0),
('user.localUserId','ibrahima0519','STRING','aziz ldap for local','aibrahim','xxxxx',0,0),
('user.localUserId','westa0000','STRING','ldap user id for fakeSiteminderFilter','awest','xxxxx',0,0),
('user.localUserId','tenpasj0001','STRING','LDAP ID for Jen','jtenpas','xxxxx',0,0),
('user.localUserId','curtism0001','STRING','ldap user id for fakeSiteminderFilter','mcurtis','xxxxx',0,0),
('user.localUserId','gentrym0000','STRING','mrg ldap for local','mac-09pc4-75032','xxxxx',0,0),
('user.localUserId','gentrym0000','STRING','mrg ldap for local','mgentry','xxxxx',0,0),
('user.localUserId','trans0000','STRING','sy local ldap user','W7473.dtic.mil','xxxxx',0,0),
('user.localUserId','ahmedn0000','STRING','nabil local ldap user','1463943539@mil','xxxxx',0,0),
('user.localUserId','danielse0000','STRING','eric local ldap user','W7493.dtic.mil','xxxxx',0,0),
('user.localUserId','zumkhawalaa0000','STRING','amar local ldap user','W7472.dtic.mil','xxxxx',0,0),
('user.localUserId','trowj0000','STRING','jen local ldap user','jtrow','xxxxx',0,0),
('user.localUserId','sondereggere0000','STRING','evan local ldap user','1291150019@mil','xxxxx',0,0),
('user.localUserId','jacobsw0000','STRING','will local ldap user','W7490.dtic.mil','xxxxx',0,0),
('user.localUserId', 'baslerc0000','STRING','charles local ldap user','L-09LA3-74026.dtic.mil', 'xxxxx',0,0),
('user.localUserId','millerc0000','STRING','chris local ldap user','mac-09pe2-75030.dtic.mil','xxxxx',0,0),
('user.localUserId', 'westburyc0000','STRING','cameron local ldap user','1521930647@mil', 'xxxxx',0,0),
('user.localUserId', 'woode0000','STRING','edward local ldap user','L-09PE2-51017', 'xxxxx',0,0),
('user.localUserId', 'hartj0000','STRING','jbh local ldap user','L-09PC3-74056.dtic.mil', 'xxxxx',0,0),
('user.localUserId', 'oneilll0000','STRING','llo local ldap user','DT11668.dtic.mil', 'xxxxx',0,0),
('user.localUserId', 'timpsonm0000', 'STRING', 'michael local ldap user', 'M7539.dtic.mil', 'xxxxx', 0,0);


INSERT INTO `config` (`cf_name`,`cf_value`,`cf_type`,`cf_desc`,`cf_env`,`cf_cycle`,`cf_year`,`cf_read_only`)
VALUES
('email.to','marlin.d.johnson8.ctr@mail.mil','EMAIL','marlin email','1509934521@mil','xxxxx',0,0),
('email.to','aibrahim.ctr@dtic.mil','EMAIL','System email messages will be sent to this email address.','aibrahim','xxxxx',0,1),
('email.to','andrew.j.west.ctr@dtic.mil','STRING',NULL,'awest','xxxxx',0,0),
('email.to','jan.i.tenpas.ctr@dtic.mil','EMAIL','System email messages will be sent to this email address.','jtenpas','xxxxx',0,1),
('email.to','michael.r.gentry20.ctr@dtic.mil','STRING',NULL,'mgentry','xxxxx',0,0),
('email.to','mark.s.curtis.ctr@dtic.mil','EMAIL','System email messages will be sent to this email address.','mcurtis','xxxxx',0,1),
('email.to','stran.ctr@dtic.mil','EMAIL','sy email','W7473.dtic.mil','xxxxx',0,0),
('email.to','Nabil.Ahmed.ctr@dtic.mil','EMAIL','nabil email','1463943539@mil','xxxxx',0,0),
('email.to','Eric.Daniels.ctr@dtic.mil','EMAIL','eric email','W7493.dtic.mil','xxxxx',0,0),
('email.to','amar.zumkhawala.ctr@dtic.mil','EMAIL','amar email','W7472.dtic.mil','xxxxx',0,0),
('email.to','Jennifer.M.Trow.ctr@mail.mil','EMAIL','jen email','jtrow','xxxxx',0,0),
('email.to','evan.b.sonderegger.ctr@dtic.mil','EMAIL','evan email','1291150019@mil','xxxxx',0,0),
('email.to','William.H.Jacobs.ctr@dtic.mil','EMAIL','will email','W7490.dtic.mil','xxxxx',0,0),
('email.to','Charles.p.basler.ctr@mail.mil','EMAIL','charles email','L-09LA3-74026.dtic.mil','xxxxx',0,0),
('email.to','christopher.w.miller96.ctr@mail.mil','EMAIL','chris email','1385369596@mil','xxxxx',0,0),
('email.to','cameron.h.westbury.ctr@mail.mil','EMAIL','cameron email','1521930647@mil','xxxxx',0,0),
('email.to','edward.d.wood7.ctr@mail.mil','EMAIL','edward email','L-09PE2-51017','xxxxx',0,0),
('email.to','john.b.hart.ctr@mail.mil','EMAIL','edward email','L-09PC3-74056.dtic.mil','xxxxx',0,0),
('email.to','lynn.l.oneill.ctr@mail.mil','EMAIL','lynn email','DT11668.dtic.mil','xxxxx',0,0),
('email.to','michael.d.timpson.ctr@mail.mil','EMAIL','michael email','M7539.dtic.mil','xxxxx',0,0);

INSERT INTO `config` (`cf_name`,`cf_value`,`cf_type`,`cf_desc`,`cf_env`,`cf_cycle`,`cf_year`,`cf_read_only`)
VALUES
('disable.rmi','true','BOOLEAN','Disable RMI invocations','default','xxxxx',0,0);



-- Bootstrap local users:
INSERT INTO `USER`
(`VERSION`,`USER_LDAP_ID`,`STATUS_FLAG`,`FULL_NAME`,`FIRST_NAME`,`MIDDLE_INITIAL`,`LAST_NAME`,`PHONE_NUM`,`EMAIL`,`LAST_VISIT_DATE`,`CREATE_PE_PRIV`,`ROLE`)
VALUES
(0,'johnsonm0000','A','Marlin Johnson','Marlin',NULL,'Johnson','7037678028','marlin.d.johnson8.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'gentrym0000','A','Michael Gentry','Michael','R','Gentry','703.767.9045','michael.r.gentry20.ctr@dtic.mil','2013-05-02 08:50:54','Y','R2AppMgr'),
(0,'ibrahima0519','A','Abdulaziz Ibrahim','Abdulaziz',NULL,'Ibrahim','703-767-9135','abdulaziz.m.ibrahim.ctr@dtic.mil','2013-03-08 08:00:24','Y','R2AppMgr'),
(0,'tenpasj0001','A','Jan tenPas','Jan','','tenPas','703-767-8016','jtenpas.ctr@dtic.mil','2013-05-02 07:27:56','Y','R2AppMgr'),
(0,'trans0000','A','Sy Tran','Sy',NULL,'Tran','703-338-8359','stran.ctr@dtic.mil','2013-05-02 10:07:27','Y','R2AppMgr'),
(0,'westa0000','A','Andy West','Andy',NULL,'West','7037679006','awest.ctr@dtic.mil','2013-04-11 14:12:08','Y','R2AppMgr'),
(0,'ahmedn0000','A','Nabil Ahmed','Nabil',NULL,'Ahmed','7037679025','Nabil.Ahmed.ctr@dtic.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'danielse0000','A','Eric Daniels','Eric','J','Daniels','703-767-8028','Eric.Daniels.ctr@dtic.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'zumkhawalaa0000','A','Amar Zumkhawala','Amar',NULL,'Zumkhawala','703-767-9036','Amar.Zumkhawala.ctr@dtic.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'trowj0000','A','Jennifer Trow','Jennifer','M','Trow','703-767-8001','Jennifer.M.Trow.ctr@mail.mil','2015-09-02 13:52:08','Y','R2AppMgr'),
(0,'sondereggere0000','A','Evan Sonderegger','Evan','B','Sonderegger','703-767-8028','evan.b.sonderegger.ctr@dtic.mil','2015-01-09 13:52:08','Y','R2AppMgr'),
(0,'jacobsw0000','A','Will Jacobs','William','H','Jacobs','703-767-9060','William.H.Jacobs.ctr@dtic.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'baslerc0000','A','Charles Basler','Charles','P','Basler','703-767-9144','charles.p.basler.ctr@dtic.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'millerc0000','A','Christopher Miller','Christopher','W','Miller','703-767-9036','christopher.w.miller96.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'westburyc0000','A','Cameron Westbury','Cameron','H','Westbury','703-767-8007','cameron.h.westbury.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'woode0000','A','Edward Wood','Edward','D','Wood','703-767-9036','edward.d.wood7.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'hartj0000','A','John Hart','John','B','Hart','703-767-8007','john.b.hart.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'oneilll0000','A','Lynn ONeill','Lynn','L','ONeill','703-767-9045','lynn.l.oneill.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr'),
(0,'timpsonm0000','A','Michael Timpson','Michael','D','Timpson','571-448-9833','michael.d.timpson.ctr@mail.mil','2013-05-30 13:52:08','Y','R2AppMgr');


INSERT INTO `BUDGES_USERS`
(`VERSION`,`USER_LDAP_ID`)
VALUES
(0,'johnsonm0000'),
(0,'curtism0001'),
(0,'gentrym0000'),
(0,'ibrahima0519'),
(0,'tenpasj0001'),
(0,'trans0000'),
(0,'westa0000'),
(0,'ahmedn0000'),
(0,'danielse0000'),
(0,'zumkhawalaa0000'),
(0,'trowj0000'),
(0,'sondereggere0000'),
(0,'jacobsw0000'),
(0,'baslerc0000'),
(0,'millerc0000'),
(0,'westburyc0000'),
(0,'woode0000'),
(0,'hartj0000'),
(0,'oneilll0000'),
(0, 'timpsonm0000');

SET SQL_SAFE_UPDATES=0;

-- Update any values which might have the old e-mail address.
UPDATE config
SET cf_value = 'dtic.belvoir.pm.list.r2-support@mail.mil'
WHERE cf_value = 'R2Support@dtic.mil' OR cf_value = 'dtic.belvoir.pm.list.r2-masslight-contractors@mail.mil';

--- update user_role
insert into user_role (user_id, role_id)
select budges_user_id, 1 from user where role = 'R2AppMgr';

insert into user_role (user_id, role_id)
select budges_user_id, 2 from user where role = 'R2User';



