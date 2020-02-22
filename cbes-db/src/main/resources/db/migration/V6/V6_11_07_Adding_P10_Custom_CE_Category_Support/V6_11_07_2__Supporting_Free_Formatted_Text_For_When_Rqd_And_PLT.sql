ALTER TABLE `proc_advance_bdgt_just` 
CHANGE COLUMN `abj_when_reqd` `abj_when_reqd` VARCHAR(45) NULL DEFAULT NULL,
ADD COLUMN `abj_scn_plt` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Production Leadtime Support for SCN P-10s' AFTER `abj_when_reqd_footnote`,
ADD COLUMN `abj_scn_plt_footnote` VARCHAR(3000) NULL DEFAULT NULL COMMENT 'Footnote for SCN P-10 Cost Element Production Leadtimes' AFTER `abj_scn_plt`;
