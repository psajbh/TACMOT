SET @DHA_SA_ID = (SELECT BUDGES_SERV_AGY_ID FROM SERVICE_AGENCY WHERE BUDGES_SERV_AGY_CODE = 'DHA');
SET @DHP_AA_ID = (SELECT aa_ID FROM service_agency_acct JOIN SERVICE_AGENCY ON (service_agency_acct.sa_ID = SERVICE_AGENCY.BUDGES_SERV_AGY_ID) WHERE BUDGES_SERV_AGY_CODE = 'DHP');

INSERT into service_agency_acct (aa_ID, sa_ID, saa_budget_tag, saa_status_flag) 
values (@DHP_AA_ID, @DHA_SA_ID, 'R2', 'A');