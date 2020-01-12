-- Fixes a previous migration which didn't update the dates correctly.

update RDTE_RULE_STATUS
set rule_active_date = now(), modified_by_user = 'SQL Script', date_modified = now()
where rule_number in ('RDTE#R2a-590', 'RDTE#R2a-600') and
      rule_active_date is null;

update RDTE_RULE_STATUS
set rule_inactive_date = now(), modified_by_user = 'SQL Script', date_modified = now()
where rule_number in ('RDTE#THRR2a-650', 'RDTE#THRR2a-660', 'RDTE#THRR2a-670') and
      rule_inactive_date is null;
