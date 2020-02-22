delimiter $$

CREATE
TRIGGER `insert_SC_create_n_class_data`
BEFORE INSERT ON `proc_ships_class`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$