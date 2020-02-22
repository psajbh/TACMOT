--Removing unused columns from the P10 Cost Element table
alter table proc_advance_bdgt_just drop column abj_parent_abj_ID;
alter table proc_advance_bdgt_just drop column cec_ID;
alter table proc_advance_bdgt_just drop column abj_date_by1_cntrct_forecast;
alter table proc_advance_bdgt_just drop column abj_date_by1_forecast_var;
alter table proc_advance_bdgt_just drop column abj_date_by2_cntrct_forecast;
alter table proc_advance_bdgt_just drop column abj_date_by2_forecast_var;
