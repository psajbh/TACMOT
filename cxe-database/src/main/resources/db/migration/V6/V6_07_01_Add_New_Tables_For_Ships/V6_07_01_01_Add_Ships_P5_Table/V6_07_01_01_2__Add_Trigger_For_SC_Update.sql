delimiter $$

CREATE
TRIGGER `update_SC_class_data`
BEFORE UPDATE ON `proc_ships_class`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$