delimiter $$

CREATE
TRIGGER `update_MA_mod_or_item_data`
BEFORE UPDATE ON `proc_models_affected`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$