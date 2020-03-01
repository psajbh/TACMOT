SET NAMES utf8;
SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;
SET SQL_SAFE_UPDATES=0;

delete from FEATURE_ACCESS;

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
	('execution(ExhibitInitializerController.getR2ExhibitInitDto())',2,0),
	('execution(ExhibitInitializerController.getAppropriationBudgetActivity(..))',2,0),
	('execution(ExhibitInitializerController.r2CreatePeFormat())',2,0),
	('execution(ProgramElementController.createProgramElement(..))',2,0)
	;
