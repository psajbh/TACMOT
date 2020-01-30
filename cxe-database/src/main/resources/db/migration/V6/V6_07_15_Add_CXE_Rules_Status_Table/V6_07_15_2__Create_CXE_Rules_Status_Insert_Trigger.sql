delimiter $$

CREATE trigger insert_rdte_rule_status before insert on RDTE_RULE_STATUS
  for each row begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.date_modified = now();
    set new.modified_by_user = user();
  end
$$