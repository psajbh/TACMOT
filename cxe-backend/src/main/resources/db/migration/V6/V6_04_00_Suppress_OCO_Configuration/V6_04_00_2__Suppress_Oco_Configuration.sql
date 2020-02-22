SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

DELETE FROM `config` WHERE `cf_name` = 'oco.enabled';

INSERT INTO `config`
(`cf_name`,`cf_value`,`cf_type`,`cf_desc`,`cf_env`,`cf_cycle`,`cf_year`,`cf_read_only`)
VALUES
('oco.suppressed', 'false', 'BOOLEAN', NULL, 'default', 'xxxxx', '0', 0);
