DROP TRIGGER insert_budget_file_upload_dc; 

delimiter $$

CREATE TRIGGER `insert_budget_file_upload_dc` BEFORE INSERT ON `budget_file_upload`
FOR EACH ROW begin
    set new.date_created = now();
  end
$$