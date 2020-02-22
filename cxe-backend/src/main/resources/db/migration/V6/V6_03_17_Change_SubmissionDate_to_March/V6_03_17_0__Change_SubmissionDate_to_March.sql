update PGM_ELEMENT set PE_SUBM_DATE = '2014-03-01'
where PE_BUDGET_YEAR = '2015' and PE_BUDGET_CYCLE='PB';
update proc_line_item set li_submit_date = '2014-03-01' 
where li_bdgt_yr = '2015' and li_bdgt_cycle='PB';
