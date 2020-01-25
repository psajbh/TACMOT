delimiter $$

CREATE
TRIGGER `update_SCH_characteristics_data`
BEFORE UPDATE ON `proc_ships_characteristics`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$