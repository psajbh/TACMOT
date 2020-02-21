delimiter $$

CREATE
TRIGGER `update_SCO_contract_data`
BEFORE UPDATE ON `proc_ships_contracts`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$