ALTER TABLE `proc_budget_sub_activity` 
DROP INDEX `UN_bsa_ba_ID_bsa_num` 
, ADD UNIQUE INDEX `UN_bsa_ba_ID_bsa_num` USING BTREE (`ba_ID` ASC, `bsa_num` ASC, `bsa_status_flag` ASC, `bsa_title` ASC) ;
