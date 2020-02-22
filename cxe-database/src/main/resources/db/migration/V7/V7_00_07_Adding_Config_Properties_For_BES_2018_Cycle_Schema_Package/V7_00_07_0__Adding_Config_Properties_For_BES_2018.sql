SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
INSERT INTO `config` (`cf_name`, `cf_value`, `cf_type`, `cf_desc`, `cf_env`, `cf_cycle`, `cf_year`, `cf_read_only`) VALUES ('xslt.jb.besLabel', 'Program/Budget Review Submission', 'STRING', 'Long name (label) for the BES cycle, used in all exhibit PDF\'s, Excel spreadsheets and various sections of the JBook.', 'default', 'BES', 2018, 0), 
                                                                                                                           ('r2.jbPdfPassword', 'jdmvuelfnc', 'STRING', 'Password to unlock the JB PDFs - sync-ed with prod.', 'default', 'BES', 2018, 0),
                                                                                                                           ('r2.jbPdfPasswordEnable', 'true', 'BOOLEAN', 'Indicates if Password should be applied to JBook', 'default', 'BES', 2018, 1),
                                                                                                                           ('xslt.besLabel', 'BES', 'STRING', 'Short name (label) for the BES cycle, used in all exhibit PDF\'s and Excel spreadsheets.', 'default', 'BES', 2018, 0);
