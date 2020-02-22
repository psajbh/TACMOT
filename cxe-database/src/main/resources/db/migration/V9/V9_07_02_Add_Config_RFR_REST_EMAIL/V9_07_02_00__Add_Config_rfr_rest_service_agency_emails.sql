SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

INSERT INTO config (`cf_name`, `cf_value`, `cf_desc`, `cf_env`, `cf_cycle`, `cf_year`, `cf_read_only`) 
values ('jb.rest.rfr.json', 
'{\'serviceAgencyEmails\':[{\'saCode\':\'OSD\',\'emails\':\'jhart@masslight.com,william.b.williams1.ctr@mail.mil,mohammed.m.rahman10.ctr@mail.mil\'},{\'saCode\':\'NAVY\',\'emails\':\'jhart@masslight.com,william.b.williams1.ctr@mail.mil,mohammed.m.rahman10.ctr@mail.mil\'},{\'saCode\':\'AF\',\'emails\':\'jhart@masslight.com,william.b.williams1.ctr@mail.mil,mohammed.m.rahman10.ctr@mail.mil\'}]}',
'json agency jbook rfr email list', default, 'xxxxx',0,0);