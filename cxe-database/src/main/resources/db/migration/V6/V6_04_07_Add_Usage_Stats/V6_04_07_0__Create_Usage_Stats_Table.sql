create table usage_stats (
    ID int(10) unsigned NOT NULL AUTO_INCREMENT,
    CREATED timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    USER_ID int(10) unsigned NOT NULL,
    LINEITEM_ID int(10) unsigned NOT NULL,
    EXHIBIT_ID int(10) unsigned NOT NULL,
    ACTION varchar(50),
    PRIMARY KEY (`ID`),
    CONSTRAINT `FK_us_user_ID` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`BUDGES_USER_ID`),
    CONSTRAINT `FK_us_e_ID` FOREIGN KEY (`EXHIBIT_ID`) REFERENCES `proc_item_exhibit` (`ie_ID`),
    CONSTRAINT `FK_us_li_ID` FOREIGN KEY (`LINEITEM_ID`) REFERENCES `proc_line_item` (`li_ID`)
);
