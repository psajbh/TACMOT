DELETE FROM `proc_li_exhibit` WHERE `ie_ID` IN (SELECT `ie_ID` FROM `proc_item_exhibit` WHERE `ie_code` = 'P-23');
DELETE FROM `proc_item_group_40A` WHERE `ie_ID` in (SELECT `ie_ID` FROM `proc_item_exhibit` WHERE `ie_code` = 'P-23');