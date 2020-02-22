delimiter $$

CREATE
TRIGGER `update_SACE_cost_analysis_data`
BEFORE UPDATE ON `proc_ships_analysis_cost_est`
FOR EACH ROW
begin
    set new.modified_by_user = user();
    set new.date_modified = now();
  end
$$