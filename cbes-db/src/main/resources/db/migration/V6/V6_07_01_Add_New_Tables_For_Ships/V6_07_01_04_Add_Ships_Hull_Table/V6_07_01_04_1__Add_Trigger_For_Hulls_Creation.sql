delimiter $$

CREATE
TRIGGER `insert_SH_create_n_hull_data`
BEFORE INSERT ON `proc_ships_hulls`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$