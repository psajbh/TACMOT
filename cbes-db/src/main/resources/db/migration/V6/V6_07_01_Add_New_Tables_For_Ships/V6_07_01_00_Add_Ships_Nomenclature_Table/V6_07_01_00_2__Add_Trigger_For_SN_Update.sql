delimiter $$

CREATE
TRIGGER `update_SN_nomenclature_data`
BEFORE UPDATE ON `proc_ships_nomenclature`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$