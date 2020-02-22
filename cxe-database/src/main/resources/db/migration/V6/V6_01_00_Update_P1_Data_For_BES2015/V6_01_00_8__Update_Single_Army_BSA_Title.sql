update proc_budget_sub_activity set bsa_title='Modification of Aircraft'
where (select ba_id from proc_budget_activity ba, APPROP_ACCOUNT a where ba.ba_num=2 and ba.ba_title='Modification of Aircraft' and a.BUDGES_APPROP_ACCT_CODE='2031A' and a.BUDGES_APPROP_ACCT_NAME='Aircraft Procurement, Army' and a.BUDGES_APPROP_ACCT_ID = ba.aa_id)
and bsa_title='Modification Of Aircraft' and bsa_num=10;
