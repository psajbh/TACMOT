-- Dropping the Columns we no longer need in the P10
alter table proc_advance_rqmt drop column ar_item_title;
alter table proc_advance_rqmt drop column ar_item_number;
alter table proc_advance_rqmt drop column ar_end_item_qty_byd_ID;
alter table proc_advance_rqmt drop column ar_end_item_UC_byd_ID;
alter table proc_advance_rqmt drop column ar_end_item_cost_byd_ID;
alter table proc_advance_rqmt drop column ar_date_by1_cntrct_forecast;
alter table proc_advance_rqmt drop column ar_date_by1_forecast_var;
alter table proc_advance_rqmt drop column ar_date_by2_cntrct_forecast;
alter table proc_advance_rqmt drop column ar_date_by2_forecast_var;
alter table proc_advance_rqmt drop column ar_remarks_p2;
