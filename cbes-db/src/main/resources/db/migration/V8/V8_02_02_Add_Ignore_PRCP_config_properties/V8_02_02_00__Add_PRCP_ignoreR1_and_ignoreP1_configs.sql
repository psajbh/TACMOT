SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

DELETE FROM `config` WHERE (`cf_name`='prcp.ignoreR1' OR `cf_name`='prcp.ignoreP1') AND `cf_year` != 0;

INSERT INTO `config`
  (`cf_name`, `cf_value`, `cf_type`, `cf_desc`, `cf_env`, `cf_cycle`, `cf_year`, `cf_read_only`)
VALUES
  ('prcp.ignoreR1', 'false', 'BOOLEAN', 
  'Ignore any R1 data loaded into the app and behave as though we have yet to receive PRCP data for RDTE.', 
  'default', 'xxxxx', 0, 0),
  ('prcp.ignoreP1', 'false', 'BOOLEAN', 
  'Ignore any P1 data loaded into the app and behave as though we have yet to receive PRCP data for Procurement.', 
  'default', 'xxxxx', 0, 0)
ON DUPLICATE KEY UPDATE `cf_value`='false';