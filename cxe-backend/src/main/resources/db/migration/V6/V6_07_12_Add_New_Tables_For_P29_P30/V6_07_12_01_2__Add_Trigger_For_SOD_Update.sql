delimiter $$

CREATE
TRIGGER `update_SOD_exhibit_data`
BEFORE UPDATE ON `proc_ships_outfitting_delivery_exhibit`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$