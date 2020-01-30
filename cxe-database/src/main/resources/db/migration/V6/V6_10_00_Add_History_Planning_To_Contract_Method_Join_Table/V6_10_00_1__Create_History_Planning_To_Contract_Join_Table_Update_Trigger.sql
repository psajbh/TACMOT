delimiter $$

CREATE trigger update_hp_to_cmtf before update on hp_to_cmtf
  for each row begin
    set new.date_modified = now();
    set new.modified_by_user = user();
  end
$$