delimiter $$

CREATE trigger insert_hp_to_cmtf before insert on hp_to_cmtf
  for each row begin
    set new.date_created = now();
    set new.created_by_user = user();
    set new.date_modified = now();
    set new.modified_by_user = user();
  end
$$