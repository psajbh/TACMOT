SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

INSERT INTO `config`
(`cf_name`,`cf_value`,`cf_type`,`cf_desc`,`cf_env`,`cf_cycle`,`cf_year`,`cf_read_only`)
VALUES
('xslt.pomLabel','POM','STRING',NULL,'default','POM',2015,1);
