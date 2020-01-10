DELETE FROM `proc_class_item_exhibit` WHERE `vc_ID` IN (SELECT `Id` FROM `ValidationClass` WHERE `name` LIKE '%.P23Validator');
DELETE FROM `ValidationClass` WHERE `name` LIKE '%.P23Validator';