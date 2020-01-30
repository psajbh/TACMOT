delimiter $$

CREATE
TRIGGER `update_SODT_costs_data`
BEFORE UPDATE ON `proc_ships_outfitting_delivery_cost_type`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$