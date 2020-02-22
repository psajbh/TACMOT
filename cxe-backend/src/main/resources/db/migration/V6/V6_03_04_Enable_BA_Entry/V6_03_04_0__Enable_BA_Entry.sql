-- This entry was inactive in the DB and the name was incorrect.

update proc_budget_activity set ba_title = 'Communications and Electronics Equipment', ba_status_flag='A'
 where ba_title = 'Communications & electronics equipment' and ba_num=4;
