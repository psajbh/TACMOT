delimiter $$

CREATE
TRIGGER `update_SODC_costs_data`
BEFORE UPDATE ON `proc_ships_outfitting_delivery_cost_elem`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$