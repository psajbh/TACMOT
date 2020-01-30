delimiter $$

CREATE TRIGGER `insert_budget_file_upload_dc` BEFORE INSERT ON `budget_file_upload`
FOR EACH ROW begin
    set new.date_created = now();
    set new.created_by_user = user();
  end
$$