create table submission_date (
    ID int(10) unsigned NOT NULL AUTO_INCREMENT,
    val varchar(255),
    label varchar(255),
    PRIMARY KEY (`ID`)
);

create table budget_cycle_config (
    ID int(10) unsigned NOT NULL AUTO_INCREMENT,
    CREATED timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    CREATED_BY int(10) unsigned NOT NULL,
    CYCLE varchar(6),
    AMENDED int(1),
    BUDGET_YEAR int(4),
    PRIMARY KEY (`ID`)
);

create table budget_cycle_submission_date (
    ID int(10) unsigned NOT NULL AUTO_INCREMENT,
    submission_date_id int(10) unsigned NOT NULL,
    budget_cycle_config_id int(10) unsigned NOT NULL,
    display_order int(2),
    PRIMARY KEY (`ID`)
);