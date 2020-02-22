SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

INSERT INTO `config`
(`cf_name`,`cf_value`,`cf_type`,`cf_desc`,`cf_env`,`cf_cycle`,`cf_year`,`cf_read_only`)
VALUES
('jbwiz.showGenerateP1R1','true','STRING',NULL,'default','BES',2015,1),
('jbwiz.showGenerateP1R1','false','STRING',NULL,'default','PB',2015,1),
('jbwiz.showAttachP1R1','false','STRING',NULL,'default','BES',2015,1),
('jbwiz.showAttachP1R1','true','STRING',NULL,'default','PB',2015,1);