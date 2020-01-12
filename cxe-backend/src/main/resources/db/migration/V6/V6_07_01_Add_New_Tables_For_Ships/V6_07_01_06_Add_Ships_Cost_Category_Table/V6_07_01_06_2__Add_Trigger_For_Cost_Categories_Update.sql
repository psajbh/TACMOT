delimiter $$

CREATE
TRIGGER `update_SCC_cost_cat_data`
BEFORE UPDATE ON `proc_ships_cost_cat`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$