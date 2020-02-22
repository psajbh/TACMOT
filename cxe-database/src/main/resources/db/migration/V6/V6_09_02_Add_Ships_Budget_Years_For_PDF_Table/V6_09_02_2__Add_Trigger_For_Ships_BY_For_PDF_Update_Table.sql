delimiter $$

CREATE
TRIGGER `update_SBYFP_by_data`
BEFORE UPDATE ON `proc_ships_by_for_pdf`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$