ALTER TABLE `proc_budget_activity` 
DROP INDEX `UN_ba_appn_ba_num` 
, ADD UNIQUE INDEX `UN_ba_appn_ba_num` USING BTREE (`aa_ID` ASC, `ba_num` ASC, `ba_status_flag` ASC, `ba_title` ASC) ;
