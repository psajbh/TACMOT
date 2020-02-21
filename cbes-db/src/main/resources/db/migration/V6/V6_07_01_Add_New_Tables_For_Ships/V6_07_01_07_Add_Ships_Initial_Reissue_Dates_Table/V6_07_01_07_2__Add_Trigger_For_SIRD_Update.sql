delimiter $$

CREATE
TRIGGER `update_SIRD_init_reissue_data`
BEFORE UPDATE ON `proc_ships_initial_reissue_dates`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$