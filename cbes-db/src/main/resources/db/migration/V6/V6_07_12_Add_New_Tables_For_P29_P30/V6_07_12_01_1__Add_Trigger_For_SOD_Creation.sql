delimiter $$

CREATE
TRIGGER `insert_SOD_exhibit_data`
BEFORE INSERT ON `proc_ships_outfitting_delivery_exhibit`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$