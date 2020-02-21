--Adding new columns for footnotes and leadtime
alter table proc_advance_rqmt add column ar_sys_interval_footnote varchar(3000);
alter table proc_advance_rqmt add column ar_description text;
alter table proc_advance_rqmt add column ar_production_leadtime decimal(13,3);
alter table proc_advance_rqmt add column ar_production_leadtime_footnote varchar(3000);
alter table proc_advance_rqmt add column ar_date_1st_sys_award_footnote varchar(3000);
alter table proc_advance_rqmt add column ar_date_1st_sys_complete_footnote varchar(3000);
