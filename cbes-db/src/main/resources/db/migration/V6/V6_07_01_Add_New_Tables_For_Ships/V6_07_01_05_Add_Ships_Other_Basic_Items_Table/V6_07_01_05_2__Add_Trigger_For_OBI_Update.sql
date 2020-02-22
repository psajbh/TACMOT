delimiter $$

CREATE
TRIGGER `update_OBI_obi_data`
BEFORE UPDATE ON `proc_ships_other_basic_items`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$