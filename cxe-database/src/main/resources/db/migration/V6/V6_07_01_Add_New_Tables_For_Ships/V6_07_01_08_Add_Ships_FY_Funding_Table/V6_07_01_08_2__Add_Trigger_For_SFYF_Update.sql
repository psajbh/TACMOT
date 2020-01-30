delimiter $$

CREATE
TRIGGER `update_SFYF_fy_funding_data`
BEFORE UPDATE ON `proc_ships_fy_funding`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$