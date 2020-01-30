delimiter $$

CREATE
TRIGGER `update_SCI_item_data`
BEFORE UPDATE ON `proc_ships_category_items`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$