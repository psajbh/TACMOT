INSERT INTO `proc_budget_activity` (`aa_ID`, `ba_num`, `ba_title`, `ba_status_flag`, `ba_RDTE_flag`, `ba_PROC_flag`) VALUES (
  (SELECT `BUDGES_APPROP_ACCT_ID` FROM `APPROP_ACCOUNT` WHERE `BUDGES_APPROP_ACCT_CODE` = '2093D'), 
  '1', 'Rapid Acquisition and Threat Response', 'A', '0', '1'
);