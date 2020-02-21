insert into service_agency_acct (saa_ID, aa_ID, sa_ID, saa_budget_tag, saa_status_flag) values ('226', (SELECT BUDGES_APPROP_ACCT_ID FROM APPROP_ACCOUNT where BUDGES_APPROP_ACCT_CODE = '0300D' AND BUDGES_APPROP_ACCT_NAME = 'Procurement, Defense-Wide'), (SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'CIFA'), 'P40', 'A');

insert into service_agency_acct (saa_ID, aa_ID, sa_ID, saa_budget_tag, saa_status_flag) values ('227', (SELECT BUDGES_APPROP_ACCT_ID FROM APPROP_ACCOUNT where BUDGES_APPROP_ACCT_CODE = '0300D' AND BUDGES_APPROP_ACCT_NAME = 'Procurement, Defense-Wide'), (SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DEFW'), 'P40', 'A');

insert into service_agency_acct (saa_ID, aa_ID, sa_ID, saa_budget_tag, saa_status_flag) values ('228', (SELECT BUDGES_APPROP_ACCT_ID FROM APPROP_ACCOUNT where BUDGES_APPROP_ACCT_CODE = '0303D' AND BUDGES_APPROP_ACCT_NAME = 'Joint Urgent Operational Needs Fund'), (SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DEFW'), 'P40', 'A');

insert into service_agency_acct (saa_ID, aa_ID, sa_ID, saa_budget_tag, saa_status_flag) values ('229', (SELECT BUDGES_APPROP_ACCT_ID FROM APPROP_ACCOUNT where BUDGES_APPROP_ACCT_CODE = '0350D' AND BUDGES_APPROP_ACCT_NAME = 'National Guard and Reserve Equipment'), (SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DEFW'), 'P40', 'A');

insert into service_agency_acct (saa_ID, aa_ID, sa_ID, saa_budget_tag, saa_status_flag) values ('230', (SELECT BUDGES_APPROP_ACCT_ID FROM APPROP_ACCOUNT where BUDGES_APPROP_ACCT_CODE = '0300D' AND BUDGES_APPROP_ACCT_NAME = 'Procurement, Defense-Wide'), (SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DEFR'), 'P40', 'A');

insert into service_agency_acct (saa_ID, aa_ID, sa_ID, saa_budget_tag, saa_status_flag) values ('231', (SELECT BUDGES_APPROP_ACCT_ID FROM APPROP_ACCOUNT where BUDGES_APPROP_ACCT_CODE = '0300D' AND BUDGES_APPROP_ACCT_NAME = 'Procurement, Defense-Wide'), (SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'NDU'), 'P40', 'A');

insert into service_agency_acct (saa_ID, aa_ID, sa_ID, saa_budget_tag, saa_status_flag) values ('232', (SELECT BUDGES_APPROP_ACCT_ID FROM APPROP_ACCOUNT where BUDGES_APPROP_ACCT_CODE = '0300D' AND BUDGES_APPROP_ACCT_NAME = 'Procurement, Defense-Wide'), (SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'NGA'), 'P40', 'A');

insert into service_agency_acct (saa_ID, aa_ID, sa_ID, saa_budget_tag, saa_status_flag) values ('233', (SELECT BUDGES_APPROP_ACCT_ID FROM APPROP_ACCOUNT where BUDGES_APPROP_ACCT_CODE = '0390A' AND BUDGES_APPROP_ACCT_NAME = 'Chem Agents & Munitions Ds, Army'), (SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'ARMY'), 'P40', 'A');

insert into service_agency_acct (saa_ID, aa_ID, sa_ID, saa_budget_tag, saa_status_flag) values ('234', (SELECT BUDGES_APPROP_ACCT_ID FROM APPROP_ACCOUNT where BUDGES_APPROP_ACCT_CODE = '0390D' AND BUDGES_APPROP_ACCT_NAME = 'Chem Agents & Munitions Destruction'), (SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'OSD'), 'P40', 'A');
