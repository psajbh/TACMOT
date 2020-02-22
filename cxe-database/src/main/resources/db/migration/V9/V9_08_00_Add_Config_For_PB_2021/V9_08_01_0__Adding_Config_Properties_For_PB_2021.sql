SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
INSERT INTO `config` (`cf_name`, `cf_value`, `cf_type`, `cf_desc`, `cf_env`, `cf_cycle`, `cf_year`, `cf_read_only`) 
VALUES 
('xslt.jb.pbLabel', 'Budget Amendment', 'STRING', 'Long name (label) for the PB cycle, used in all exhibit PDF\'s, Excel spreadsheets and various sections of the JBook.', 'default', 'PB', 2021, 0), 
('xslt.pbLabel', 'PB', 'STRING', 'Short name (label) for the PB cycle, used in all exhibit PDF\'s and Excel spreadsheets.', 'default', 'PB', 2021, 0);
