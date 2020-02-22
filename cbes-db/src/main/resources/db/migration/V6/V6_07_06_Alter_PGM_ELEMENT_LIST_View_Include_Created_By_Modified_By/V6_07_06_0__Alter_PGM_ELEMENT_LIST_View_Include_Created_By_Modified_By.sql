CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
VIEW `PGM_ELEMENTS_LIST` AS
    select 
        `PE`.`BUDGES_PGM_ELEM_ID` AS `BUDGES_PGM_ELEM_ID`,
        `PE`.`PE_R1_NUM` AS `PE_R1_NUM`,
        `PE`.`BUDGES_PGM_ELEM_NUM` AS `BUDGES_PGM_ELEM_NUM`,
        `PE`.`PE_BUDGET_CYCLE` AS `PE_BUDGET_CYCLE`,
        `PE`.`PE_BUDGET_YEAR` AS `PE_BUDGET_YEAR`,
        fn_budget_act_number_proc(`PE`.`BUDGES_BUDGET_ACTIVITY_ID`) AS `BUDGES_BA_NUMBER`,
        `PE`.`PE_FORMAT` AS `PE_FORMAT`,
        `PE`.`PE_SUBM_DATE` AS `PE_SUBM_DATE`,
        `PE`.`PE_TITLE` AS `PE_TITLE`,
        `PE`.`PE_STATUS_SUBM` AS `PE_STATUS_SUBM`,
        `PE`.`PE_STATE` AS `PE_STATE`,
        `PE`.`PE_INIT_SRC` AS `PE_INIT_SRC`,
        `PE`.`PE_TEST` AS `PE_TEST`,
        `PE`.`PE_TAG` AS `PE_TAG`,
        `UC`.`FULL_NAME` AS `FULL_NAME`,
        `UC`.`FIRST_NAME` AS `FIRST_NAME`,
        `UC`.`MIDDLE_INITIAL` AS `MIDDLE_INITIAL`,
        `UC`.`LAST_NAME` AS `LAST_NAME`,
        `UC`.`USER_LDAP_ID` AS `USER_LDAP_ID`,
        `PE`.`DATE_CREATED_R2` AS `DATE_CREATED`,
        `PE`.`BUDGES_SERV_AGY_ID` AS `BUDGES_SERV_AGY_ID`,
        `SA`.`SERV_AGY_NAME` AS `SERV_AGY_NAME`,
		`PE`.`CREATED_BY_USER_ID_R2` AS `CREATED_BY_USER_ID_R2`,
		`PE`.`MODIFIED_BY_USER_ID_R2` AS `MODIFIED_BY_USER_ID_R2`,
        fn_serv_agy_code(`PE`.`BUDGES_SERV_AGY_ID`) AS `SERV_AGY_CODE`,
        count(`PT`.`BUDGES_PROJ_ID`) AS `NUM_PROJECTS`,
        (select 
                `U`.`FULL_NAME`
            from
                `USER` `U`
            where
                (`U`.`BUDGES_USER_ID` = `PE`.`MODIFIED_BY_USER_ID_OVERALL`)) AS `MOD_FULL_NAME`,
        (select 
                `U`.`FIRST_NAME`
            from
                `USER` `U`
            where
                (`U`.`BUDGES_USER_ID` = `PE`.`MODIFIED_BY_USER_ID_OVERALL`)) AS `MOD_FIRST_NAME`,
        (select 
                `U`.`MIDDLE_INITIAL`
            from
                `USER` `U`
            where
                (`U`.`BUDGES_USER_ID` = `PE`.`MODIFIED_BY_USER_ID_OVERALL`)) AS `MOD_MIDDLE_INITIAL`,
        (select 
                `U`.`LAST_NAME`
            from
                `USER` `U`
            where
                (`U`.`BUDGES_USER_ID` = `PE`.`MODIFIED_BY_USER_ID_OVERALL`)) AS `MOD_LAST_NAME`,
        (select 
                `U`.`USER_LDAP_ID`
            from
                `USER` `U`
            where
                (`U`.`BUDGES_USER_ID` = `PE`.`MODIFIED_BY_USER_ID_OVERALL`)) AS `MOD_USER_LDAP_ID`,
        `PE`.`DATE_MODIFIED_OVERALL` AS `DATE_MODIFIED`,
        if((count(`PT`.`BUDGES_PROJ_ID`) = 0),
            'N',
            if((min(`PT`.`DATE_creatED_R3`) is not null),
                'Y',
                'N')) AS `R3_EXISTS`,
        if((count(`PT`.`BUDGES_PROJ_ID`) = 0),
            'N',
            if((min(`PT`.`DATE_creatED_R4`) is not null),
                'Y',
                'N')) AS `R4_EXISTS`,
        if((count(`PT`.`BUDGES_PROJ_ID`) = 0),
            'N',
            if((min(`PT`.`DATE_creatED_R4A`) is not null),
                'Y',
                'N')) AS `R4A_EXISTS`,
        if((count(`PT`.`BUDGES_PROJ_ID`) = 0),
            'N',
            if((min(`PT`.`DATE_creatED_R5`) is not null),
                'Y',
                'N')) AS `R5_EXISTS`,
        if((`PE`.`EDIT_LOCK_ID_ALL` is not null),
            'Y',
            'N') AS `EDIT_LOCK_ALL`,
        if((`PE`.`EDIT_LOCK_ID_PE_ONLY` is not null),
            'Y',
            'N') AS `EDIT_LOCK_PE_ONLY`,
        count(`PT`.`EDIT_LOCK_ID_PJ`) AS `EDIT_LOCK_ANY_PJ`
    from
        ((((`PGM_ELEMENT` `PE`
        left join `USER` `UC` ON ((`UC`.`BUDGES_USER_ID` = `PE`.`CREATED_BY_USER_ID_R2`)))
        left join `PE_LIST_PROJECT_TMP` `PT` ON ((`PT`.`BUDGES_PGM_ELEM_ID` = `PE`.`BUDGES_PGM_ELEM_ID`)))
        left join `USER` `UMP` ON ((`UMP`.`BUDGES_USER_ID` = `PE`.`MODIFIED_BY_USER_ID_R2`)))
        join `SERVICE_AGENCY` `SA` ON ((`SA`.`BUDGES_SERV_AGY_ID` = `PE`.`BUDGES_SERV_AGY_ID`)))
    group by `PE`.`BUDGES_PGM_ELEM_ID`;
