SET NAMES utf8;
SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;
SET SQL_SAFE_UPDATES=0;

delete from `USER_SERVICE_AGENCY` where `BUDGES_USER_ID` is null;

delete from `USER_SERVICE_AGENCY` where `BUDGES_USER_ID` IN (
select `BUDGES_USER_ID` from `USER` where `USER_LDAP_ID` IN (
	'johnsonm0000','gentrym0000','ibrahima0519','tenpasj0001','trans0000','westa0000','ahmedn0000',
	'danielse0000','zumkhawalaa0000','trowj0000','sondereggere0000''jacobsw0000','baslerc0000',
	'millerc0000','westburyc0000','woode0000','hartj0000','oneilll0000','timpsonm0000'
));

insert into USER_SERVICE_AGENCY (BUDGES_USER_ID, BUDGES_SERV_AGY_ID) 
select u.BUDGES_USER_ID, sa.BUDGES_SERV_AGY_ID  
from USER u, SERVICE_AGENCY sa
where u.ROLE != 'R2AppMgr'
and sa.BUDGES_SERV_AGY_CODE = 'OSD';

insert into USER_SERVICE_AGENCY (BUDGES_USER_ID, BUDGES_SERV_AGY_ID) 
select u.BUDGES_USER_ID, sa.BUDGES_SERV_AGY_ID  
from USER u,  SERVICE_AGENCY sa
where u.ROLE != 'R2AppMgr'
and sa.BUDGES_SERV_AGY_CODE = 'SOCOM';

insert into USER_SERVICE_AGENCY (BUDGES_USER_ID, BUDGES_SERV_AGY_ID)
select BUDGES_USER_ID, sa.BUDGES_SERV_AGY_ID
from USER u, SERVICE_AGENCY sa
where u.USER_LDAP_ID = 'timpsonm0000'
and sa.BUDGES_SERV_AGY_CODE = 'NAVY';

delete from feature_access  where feature_id >= 0;

insert into feature_access(POINT_CUT, FEATURE_QUAL, EQUAL_LOGIC) values
	("execution(CxeSecurityController.getUser(..))",0,0),
    ("execution(ManageUsersController.deleteManagedUser(..))",5,0),
    ("execution(ManageUsersController.addManagedUser(..))",5,0),
    ("execution(ManageUsersController.updateManagedUser(..))",5,0),
    ("execution(ManageUsersController.getManagedUsers())",5,0),
    ("execution(UserProfileController.getProfile())",1,0),
    ("execution(AnnouncementController.getAnnouncement())",1,0),
    ("execution(DownloadsController.getDownloadsList(..))",2,0),
    ("execution(DownloadsController.downloadFile(..))",2,0),
    ("execution(DownloadsController.deleteFile(..))",2,0),
    ("execution(DownloadsController.uploadFile(..))",2,0),
    ("execution(UserGuideController.getUserGuideHTML())",1,0);



