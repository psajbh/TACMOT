INSERT INTO proc_budget_sub_activity (ba_ID, bsa_num, bsa_title, bsa_status_flag) 
      VALUES ((SELECT ba_ID FROM proc_budget_activity ba, APPROP_ACCOUNT a 
        WHERE ba.ba_num = 1 AND ba.ba_title = 'Space Procurement, Air Force' AND a.BUDGES_APPROP_ACCT_CODE = '3021F' AND a.BUDGES_APPROP_ACCT_NAME = 'Space Procurement, Air Force' AND a.BUDGES_APPROP_ACCT_ID = ba.aa_id), '1' ,'Space Programs', 'A');
