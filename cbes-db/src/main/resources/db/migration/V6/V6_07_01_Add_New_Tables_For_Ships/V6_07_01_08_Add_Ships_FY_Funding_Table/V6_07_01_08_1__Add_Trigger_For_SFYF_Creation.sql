delimiter $$

CREATE
TRIGGER `insert_SFYF_create_n_fy_funding_data`
BEFORE INSERT ON `proc_ships_fy_funding`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$