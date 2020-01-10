SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
INSERT INTO `config` (`cf_name`, `cf_value`, `cf_type`, `cf_desc`, `cf_env`, `cf_cycle`, `cf_year`, `cf_read_only`) 
VALUES 
('jb.workFlowStatusDraftDisabled', 'FALSE', 'BOOLEAN', 'Boolean value used to toggle DEFAULT workflow status on and off', 'default', 'xxxxx', 0, 0),
('jb.workFlowStatusPrePRCPDisabled', 'TRUE', 'BOOLEAN', 'Boolean value used to toggle PRE_PRCP workflow status on and off', 'default', 'xxxxx', 0, 0),
('jb.workFlowStatusRFRDisabled', 'TRUE', 'BOOLEAN', 'Boolean value used to toggle RFR workflow status on and off', 'default', 'xxxxx', 0, 0),
('jb.workFlowStatusFinalDisabled', 'TRUE', 'BOOLEAN', 'Boolean value used to toggle FINAL workflow status on and off', 'default', 'xxxxx', 0, 0);

commit;