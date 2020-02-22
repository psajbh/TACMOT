CREATE TABLE `COMBINED_SPEC_CAT` (  
`spec_cat_id` int(11) unsigned NOT NULL AUTO_INCREMENT,  
`spec_cat_category` varchar(15) NOT NULL,  -- like 'DODSERVICE or DW'  
`spec_cat_code` varchar(15) DEFAULT NULL,  -- like '0200' or 'NAVY'  
`created_by_user` varchar(256) DEFAULT NULL,  
`date_created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',  
`modified_by_user` varchar(256) DEFAULT NULL,  
`date_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  
PRIMARY KEY (`spec_cat_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO COMBINED_SPEC_CAT (spec_cat_category, spec_cat_code)VALUES ('DOD_SERVICE', 'NAVY');  
INSERT INTO COMBINED_SPEC_CAT (spec_cat_category, spec_cat_code)VALUES ('DOD_SERVICE', 'ARMY');  
INSERT INTO COMBINED_SPEC_CAT (spec_cat_category, spec_cat_code)VALUES ('DOD_SERVICE', 'AF');  
INSERT INTO COMBINED_SPEC_CAT (spec_cat_category, spec_cat_code)VALUES ('DOD_BIG_AGENCY', 'DARPA');  
INSERT INTO COMBINED_SPEC_CAT (spec_cat_category, spec_cat_code)VALUES ('DOD_BIG_AGENCY', 'MDA');  
INSERT INTO COMBINED_SPEC_CAT (spec_cat_category, spec_cat_code)VALUES ('DOD_BIG_AGENCY', 'OSD');  
INSERT INTO COMBINED_SPEC_CAT (spec_cat_category, spec_cat_code)VALUES ('DOD_BIG_AGENCY', 'CBDP');  
INSERT INTO COMBINED_SPEC_CAT (spec_cat_category, spec_cat_code)VALUES ('DW', '0400');  
INSERT INTO COMBINED_SPEC_CAT (spec_cat_category, spec_cat_code)VALUES ('DW', '0300D');  