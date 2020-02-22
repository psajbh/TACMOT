INSERT INTO `proc_budget_sub_activity` (`ba_ID`, `bsa_num`, `bsa_title`) VALUES (
  (SELECT `ba_ID` FROM `proc_budget_activity` WHERE `ba_title` = 'Rapid Acquisition and Threat Response'),
  '1', 'Network Attack'
);