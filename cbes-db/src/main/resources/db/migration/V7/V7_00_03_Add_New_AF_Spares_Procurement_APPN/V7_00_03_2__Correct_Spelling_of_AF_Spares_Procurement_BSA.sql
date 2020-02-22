UPDATE `proc_budget_sub_activity` 
   SET `bsa_title`='SSpares' 
 WHERE `ba_ID`= (SELECT ba_ID FROM proc_budget_activity ba, APPROP_ACCOUNT a 
                  WHERE ba.ba_num = 2 AND ba.ba_title = 'Spares' AND a.BUDGES_APPROP_ACCT_CODE = '3021F' AND a.BUDGES_APPROP_ACCT_NAME = 'Space Procurement, Air Force' AND a.BUDGES_APPROP_ACCT_ID = ba.aa_id);
