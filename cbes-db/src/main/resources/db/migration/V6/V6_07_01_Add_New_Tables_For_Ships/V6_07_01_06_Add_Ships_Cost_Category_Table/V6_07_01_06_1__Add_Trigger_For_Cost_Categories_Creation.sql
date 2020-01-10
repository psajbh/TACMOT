delimiter $$

CREATE
TRIGGER `insert_SCC_create_n_cost_cat_data`
BEFORE INSERT ON `proc_ships_cost_cat`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$