-- This is convoluted due to a broken previous migration.

DELIMITER $$

CREATE PROCEDURE temporary_drop_p23_contraint_if_exists ()
BEGIN

IF EXISTS(SELECT table_name 
     FROM INFORMATION_SCHEMA.TABLES
    WHERE table_schema = database()
      AND table_name = 'proc_rqmts_sched_install_agent')
THEN
    ALTER TABLE proc_rqmts_sched_install_agent
    DROP FOREIGN KEY FK_rsia_rs_ID;
END IF;

IF EXISTS(SELECT table_name 
     FROM INFORMATION_SCHEMA.TABLES
    WHERE table_schema = database()
      AND table_name = 'proc_rqmts_sched_install_EIF')
THEN
    ALTER TABLE proc_rqmts_sched_install_EIF
    DROP FOREIGN KEY FK_rsie_rsia_ID;
END IF;

END$$

DELIMITER ;

CALL temporary_drop_p23_contraint_if_exists();

DROP PROCEDURE temporary_drop_p23_contraint_if_exists;
