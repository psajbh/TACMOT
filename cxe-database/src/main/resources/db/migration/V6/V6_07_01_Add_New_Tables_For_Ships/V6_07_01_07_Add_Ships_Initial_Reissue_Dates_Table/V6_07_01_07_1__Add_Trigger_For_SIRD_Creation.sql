delimiter $$

CREATE
TRIGGER `insert_SIRD_create_n_init_reissue_data`
BEFORE INSERT ON `proc_ships_initial_reissue_dates`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$