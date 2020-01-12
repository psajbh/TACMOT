delimiter $$

CREATE
TRIGGER `insert_OBI_create_n_obi_data`
BEFORE INSERT ON `proc_ships_other_basic_items`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$