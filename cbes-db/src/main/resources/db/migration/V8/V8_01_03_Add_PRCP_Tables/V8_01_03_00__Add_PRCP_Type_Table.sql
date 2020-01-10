CREATE TABLE `PRCP_TYPE` (
  `type_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type_cd` varchar(2) NOT NULL,
  `type_desc` varchar(50) DEFAULT NULL,
  `created_by_user` varchar(256) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modified_by_user` varchar(256) DEFAULT NULL,
  `date_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `PRCP_TYPE`
(`type_id`,`type_cd`,`type_desc`)
VALUES
(1,1,'P1 Mapping Data'),
(2,2,'R1 Mapping Data');