delimiter $$

CREATE
TRIGGER `insert_SCP_create_n_category_data`
BEFORE INSERT ON `proc_ships_p35_categories`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$