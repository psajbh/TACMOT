delimiter $$

CREATE
TRIGGER `insert_SODT_costs_data`
BEFORE INSERT ON `proc_ships_outfitting_delivery_cost_type`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$