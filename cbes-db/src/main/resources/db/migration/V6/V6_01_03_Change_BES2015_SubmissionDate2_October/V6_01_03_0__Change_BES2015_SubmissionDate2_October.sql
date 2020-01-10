update PGM_ELEMENT set PE_SUBM_DATE = '2013-10-01'
where PE_BUDGET_YEAR = '2015' and PE_BUDGET_CYCLE='BES';
update proc_line_item set li_submit_date = '2013-10-01' 
where li_bdgt_yr = '2015' and li_bdgt_cycle='BES';
