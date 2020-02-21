--SET SQL_SAFE_UPDATES=0;

DELETE FROM `validation_exemption` WHERE `validation_rule_fk` IN (SELECT `Id` FROM `ValidationRule` WHERE `number` LIKE '[PROC#P23-%');
DELETE FROM `ValidationClassValidationRule` WHERE `validationRuleId` IN (SELECT `Id` FROM `ValidationRule` WHERE `number` LIKE '[PROC#P23-%');
DELETE FROM `ValidationRule` WHERE `number` LIKE '[PROC#P23-%';