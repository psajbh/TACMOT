INSERT INTO service_agency_acct (aa_ID, sa_ID, saa_budget_tag, saa_status_flag) 
      VALUES ((SELECT BUDGES_APPROP_ACCT_ID FROM APPROP_ACCOUNT a 
        WHERE a.BUDGES_APPROP_ACCT_CODE = '3021F' AND a.BUDGES_APPROP_ACCT_NAME = 'Space Procurement, Air Force'), 
        (SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY a 
        WHERE a.BUDGES_SERV_AGY_CODE = 'AF' AND a.SERV_AGY_NAME = 'Air Force'),'P40', 'A');