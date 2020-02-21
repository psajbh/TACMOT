SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
INSERT INTO `config` (`cf_name`, `cf_value`, `cf_type`, `cf_desc`, `cf_env`, `cf_cycle`, `cf_year`, `cf_read_only`) 
VALUES 
('xslt.jb.pbLabel', 'Budget Amendment', 'STRING', 'Long name (label) for the PB cycle, used in all exhibit PDFs, Excel spreadsheets and various sections of the JBook.', 'default', 'PB', 2019, 0), 
('r2.jbPdfPassword', 'ocapkwqabt', 'STRING', 'Password to unlock the JB PDFs - sync-ed with prod.', 'default', 'PB', 2019, 0),
('r2.jbPdfPasswordEnable', 'true', 'BOOLEAN', 'Indicates if Password should be applied to JBook', 'default', 'PB', 2019, 1),
('xslt.pbLabel', 'PB Amended', 'STRING', 'Short name (label) for the PB cycle, used in all exhibit PDFs and Excel spreadsheets.', 'default', 'PB', 2019, 0),
('jbwiz.showAttachP1R1', 'true', 'BOOLEAN', '', 'default', 'PB', 2019, 0),
('jbwiz.showGenerateP1R1', 'false', 'BOOLEAN', '', 'default', 'PB', 2019, 0),
('jbwiz.attachP1R1Required', 'true', 'BOOLEAN', '', 'default', 'PB', 2019, 0),
('jbwiz.generateP1R1Required', 'false', 'BOOLEAN', '', 'default', 'PB', 2019, 0)
ON DUPLICATE KEY UPDATE `cf_value`=VALUES(`cf_value`), `cf_type`=VALUES(`cf_type`), `cf_desc`=VALUES(`cf_desc`), `cf_read_only`=VALUES(`cf_read_only`);