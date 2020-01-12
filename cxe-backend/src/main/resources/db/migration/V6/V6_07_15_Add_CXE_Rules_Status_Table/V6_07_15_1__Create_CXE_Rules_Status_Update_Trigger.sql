delimiter $$

CREATE trigger update_rdte_rule_status before update on RDTE_RULE_STATUS
  for each row begin
    set new.date_modified = now();
    set new.modified_by_user = user();
  end
$$