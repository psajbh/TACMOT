-- Changing the MDAP code to only allow for one code, not a list of codes for Items and Spare Items.

-- Scrap some of the production/staging MDAP codes that are too long (from testing, will not change actual data).
update proc_line_item set li_MDAP = null where length(li_MDAP) > 3;

alter table proc_line_item           modify column li_MDAP   varchar(4); 
alter table proc_mod_grp             modify column mg_MDAP   varchar(4);
alter table proc_spare_part_end_item modify column spei_MDAP varchar(4);
alter table proc_item                modify column pi_MDAP   varchar(4);
