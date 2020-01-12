delimiter $$

CREATE
TRIGGER `insert_SACE_create_n_cost_analysis_data`
BEFORE INSERT ON `proc_ships_analysis_cost_est`
FOR EACH ROW
begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.modified_by_user = user();
  end
$$