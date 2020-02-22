delimiter $$

CREATE
TRIGGER `insert_SN_create_n_nomenclature_data`
BEFORE INSERT ON `proc_ships_nomenclature`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$