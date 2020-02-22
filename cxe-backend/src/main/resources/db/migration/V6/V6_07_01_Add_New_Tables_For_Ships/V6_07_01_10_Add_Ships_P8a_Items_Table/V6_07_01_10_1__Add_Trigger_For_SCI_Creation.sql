delimiter $$

CREATE
TRIGGER `insert_SCI_create_n_item_data`
BEFORE INSERT ON `proc_ships_category_items`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$