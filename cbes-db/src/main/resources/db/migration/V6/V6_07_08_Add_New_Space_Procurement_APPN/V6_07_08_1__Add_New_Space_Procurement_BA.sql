INSERT INTO proc_budget_activity (aa_ID, ba_num, ba_title, ba_status_flag, ba_PROC_flag) 
       VALUES ((SELECT BUDGES_APPROP_ACCT_ID FROM APPROP_ACCOUNT a 
          WHERE a.BUDGES_APPROP_ACCT_CODE = '3021F' AND a.BUDGES_APPROP_ACCT_NAME = 'Space Procurement, Air Force'), '1' ,'Space Procurement, Air Force', 'A', 1);