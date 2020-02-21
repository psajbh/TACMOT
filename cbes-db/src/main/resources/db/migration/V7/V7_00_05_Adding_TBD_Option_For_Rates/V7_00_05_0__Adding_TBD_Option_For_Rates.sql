ALTER TABLE `proc_manufacturer` 
            CHANGE COLUMN `date_created` `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '' ,
            ADD    COLUMN `cem_msr_tbd`   TINYINT(1) NULL DEFAULT NULL COMMENT '' AFTER `cem_msr`,
            ADD    COLUMN `cem_1_8_5_tbd` TINYINT(1) NULL DEFAULT NULL COMMENT '' AFTER `cem_1_8_5`,
            ADD    COLUMN `cem_max_tbd`   TINYINT(1) NULL DEFAULT NULL COMMENT '' AFTER `cem_max`;
