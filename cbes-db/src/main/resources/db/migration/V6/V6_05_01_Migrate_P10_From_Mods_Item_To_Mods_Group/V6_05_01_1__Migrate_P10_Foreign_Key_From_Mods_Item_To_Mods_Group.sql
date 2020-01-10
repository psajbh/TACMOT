DELIMITER $$

CREATE PROCEDURE migrate_p10_from_mod_item_to_group ()
BEGIN
    DECLARE finished INTEGER DEFAULT 0;
    DECLARE mod_item_p10_fk INTEGER UNSIGNED;
    DECLARE mod_item_mg_id INTEGER UNSIGNED;
    
    DECLARE move_p10_fk CURSOR FOR SELECT mg_ID, proc_advance_rqmt_fk
        FROM proc_mod_item
        WHERE proc_advance_rqmt_fk IS NOT NULL;
    
    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET finished = 1;
    
    SET finished = 0;
    OPEN move_p10_fk;
    
    REPEAT
        FETCH move_p10_fk INTO mod_item_mg_id, mod_item_p10_fk;
        UPDATE proc_mod_grp SET proc_advance_rqmt_fk = mod_item_p10_fk
            WHERE mg_ID = mod_item_mg_id;
        
    UNTIL finished
    END REPEAT;
    
    CLOSE move_p10_fk;
    SELECT mg_ID, proc_advance_rqmt_fk FROM proc_mod_grp;
        
END$$

DELIMITER ;

CALL migrate_p10_from_mod_item_to_group();

DROP PROCEDURE migrate_p10_from_mod_item_to_group;