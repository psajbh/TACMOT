SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

insert into config(`cf_name`, `cf_value`, `cf_type`, `cf_desc`, `cf_env`, `cf_cycle`, `cf_year`, `cf_read_only`)
values ('r2.registrationEmailToUser', 'true', 'BOOLEAN', 'Also send registration email to user', 'default','xxxxx',0,0); 