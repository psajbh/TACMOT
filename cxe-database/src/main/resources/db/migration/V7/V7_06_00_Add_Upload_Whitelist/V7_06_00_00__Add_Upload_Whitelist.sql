SET NAMES utf8;
SET TIME_ZONE='+00:00';
SET UNIQUE_CHECKS=0;
SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_NOTES=0;

insert into config
(cf_name, cf_value, cf_type, cf_desc, cf_env, cf_cycle, cf_year, cf_read_only)
values
('upload.whitelist', 'xml,pdf,zip,zzz,docx,pptx,xlsx,jpg,jpeg,gif,png', 'STRING', 'Allowed upload file extensions - CSV format.', 'default', 'xxxxx', 0, 0);
