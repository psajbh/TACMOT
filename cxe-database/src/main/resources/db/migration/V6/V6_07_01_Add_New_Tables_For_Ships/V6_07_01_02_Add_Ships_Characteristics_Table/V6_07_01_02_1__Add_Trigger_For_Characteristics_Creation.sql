delimiter $$

CREATE
TRIGGER `insert_SCH_create_n_characteristics_data`
BEFORE INSERT ON `proc_ships_characteristics`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$