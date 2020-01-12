SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
insert into config(`cf_name`, `cf_value`, `cf_type`, `cf_desc`, `cf_env`, `cf_cycle`, `cf_year`, `cf_read_only`) 
values 	('jb.draftStamp', 'No display', 'String', 'Draft message stamp on JB', 'default','xxxxx',0,0),
		('jb.reviewStamp', 'Ready for Review', 'String', 'Review message stamp on JB', 'default','xxxxx',0,0),
		('jb.reviewers', 'dtic.belvoir.pm.list.r2-support@mail.mil', 'String', 'comma list of reviewer emails', 'default','xxxxx',0,0);
