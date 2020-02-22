ALTER TABLE `proc_line_item` 
DROP INDEX `UN_li_byc_bsa_item_num` 
, ADD UNIQUE INDEX `UN_li_byc_bsa_item_num` USING BTREE (`li_bdgt_yr` ASC, `li_bdgt_cycle` ASC, `bsa_ID` ASC, `sa_ID` ASC, `li_P1_item_num` ASC, `li_cost_type` ASC, `li_status_flag` ASC, `li_status_date` ASC, `li_test` ASC) ;
