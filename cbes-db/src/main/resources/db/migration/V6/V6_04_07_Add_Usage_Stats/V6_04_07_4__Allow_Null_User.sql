alter table usage_stats MODIFY COLUMN USER_ID int(10) unsigned;

alter table usage_stats ADD COLUMN SERVICEAGENCY varchar(100);
alter table usage_stats ADD COLUMN LINE_ITEM_NUM varchar(25);
alter table usage_stats ADD COLUMN LINE_NUM varchar(10);
alter table usage_stats ADD COLUMN LINE_ITEM_TITLE varchar(255);
alter table usage_stats ADD COLUMN APPROPRIATION varchar(10);
alter table usage_stats ADD COLUMN BUDGET_ACTIVITY TINYINT(3); 
alter table usage_stats ADD COLUMN BUDGET_SUB_ACTIVITY SMALLINT(4);