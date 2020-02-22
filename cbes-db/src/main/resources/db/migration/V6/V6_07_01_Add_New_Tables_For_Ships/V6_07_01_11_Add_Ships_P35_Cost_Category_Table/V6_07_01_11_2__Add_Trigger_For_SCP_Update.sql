delimiter $$

CREATE
TRIGGER `update_SCP_category_data`
BEFORE UPDATE ON `proc_ships_p35_categories`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$