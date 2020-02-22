SET NAMES utf8;
SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;
SET SQL_SAFE_UPDATES=0;

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

-- insert feature data
insert into FEATURE_ACCESS(POINT_CUT, FEATURE_QUAL, EQUAL_LOGIC) values
	('execution(CxeSecurityController.getUser(..))',0,0),
    ('execution(ManageUsersController.deleteManagedUser(..))',5,0),
    ('execution(ManageUsersController.addManagedUser(..))',5,0),
    ('execution(ManageUsersController.updateManagedUser(..))',5,0),
    ('execution(ManageUsersController.getManagedUsers())',5,0),
    ('execution(UserProfileController.getProfile())',1,0),
    ('execution(AnnouncementController.getAnnouncement())',1,0),
    ('execution(DownloadsController.getDownloadsList(..))',2,0),
    ('execution(DownloadsController.downloadFile(..))',2,0),
    ('execution(DownloadsController.deleteFile(..))',2,0),
    ('execution(DownloadsController.uploadFile(..))',2,0),
    ('execution(UserGuideController.getUserGuideHTML())',1,0),
    ('execution(PrcpDataController.getR1Data())',5,0),
	('execution(PrcpDataController.getP1Data())',5,0),
	('execution(PrcpDataController.getR1fileData())',5,0),
	('execution(PrcpDataController.getP1fileData())',5,0),
	('execution(PrcpDataController.addPRCPFile(..))',5,0),
	('execution(PrcpDataController.deleteR1data())',5,0),
	('execution(PrcpDataController.deleteP1data())',5,0),
	('execution(PrcpDataController.getPrcpR1file())',5,0),
	('execution(PrcpDataController.getPrcpP1file())',5,0),
	('execution(ExhibitInitializerController.getR2ExhibitInitDto())',2,0)
	;



