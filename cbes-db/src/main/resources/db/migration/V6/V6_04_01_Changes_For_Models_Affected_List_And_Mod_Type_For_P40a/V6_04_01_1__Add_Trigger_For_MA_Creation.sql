delimiter $$

CREATE
TRIGGER `insert_MA_create_n_mod_or_item_data`
BEFORE INSERT ON `proc_models_affected`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$