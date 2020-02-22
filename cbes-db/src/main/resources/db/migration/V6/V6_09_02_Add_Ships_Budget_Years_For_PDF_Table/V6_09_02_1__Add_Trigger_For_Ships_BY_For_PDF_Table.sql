delimiter $$

CREATE
TRIGGER `insert_SBYFP_create_n_by_data`
BEFORE INSERT ON `proc_ships_by_for_pdf`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$