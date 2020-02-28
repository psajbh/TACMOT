CREATE TABLE `ApplicationSettings`(
	`application_setting_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
	`setting_name` varchar(255) NOT NULL,
	`setting_value` text,
	`setting_description` varchar(1000),
	`created_by_user` varchar(255) DEFAULT NULL,
	`date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`modified_by_user` varchar(255) DEFAULT NULL,
	`date_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`application_setting_id`),
	UNIQUE KEY (`setting_name`)
) 	ENGINE=InnoDB DEFAULT CHARSET=utf8;

