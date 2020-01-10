SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
INSERT INTO `config` (`cf_name`, `cf_value`, `cf_type`, `cf_desc`, `cf_env`, `cf_cycle`, `cf_year`, `cf_read_only`) VALUES ('xslt.jb.pbLabel', 'Amended Budget Estimates', 'STRING', 'Long name (label) for the PB cycle, used in all exhibit PDF\'s, Excel spreadsheets and various sections of the JBook.', 'default', 'PBAmended', 2017, 0), 
                                                                                                                           ('r2.jbPdfPassword', 'imktvmnyhp', 'STRING', 'Password to unlock the JB PDFs - sync-ed with prod.', 'default', 'PBAmended', 2017, 0),
                                                                                                                           ('r2.jbPdfPasswordEnable', 'true', 'BOOLEAN', 'Indicates if Password should be applied to JBook', 'default', 'PBAmended', 2017, 1),
                                                                                                                           ('xslt.pbLabel', 'PB Amended', 'STRING', 'Short name (label) for the PB cycle, used in all exhibit PDF\'s and Excel spreadsheets.', 'default', 'PBAmended', 2017, 0);
