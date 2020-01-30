delimiter $$

CREATE
TRIGGER `update_SH_hulls_data`
BEFORE UPDATE ON `proc_ships_hulls`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$