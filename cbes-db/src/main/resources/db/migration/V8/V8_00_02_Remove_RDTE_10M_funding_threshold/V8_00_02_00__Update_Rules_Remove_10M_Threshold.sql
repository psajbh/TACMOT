-- re-enable rules for  RDTE#R2a-590 and RDTE#R2a-600
UPDATE `RDTE_RULE_STATUS`  set `RULE_STATUS` = 0  WHERE `RULE_NUMBER` IN ('RDTE#R2a-590', 'RDTE#R2a-600');

-- disable rules for RDTE#THRRa-650, RDTE#THRRa-660, RDTE#THRRa-670
INSERT INTO `RDTE_RULE_STATUS` (`RULE_NUMBER`, `RULE_STATUS`, `RULE_ACTIVE_DATE`, `RULE_INACTIVE_DATE`)
VALUES
('RDTE#THRR2a-650', 1, NULL, CURRENT_TIMESTAMP),
('RDTE#THRR2a-660', 1, NULL, CURRENT_TIMESTAMP),
('RDTE#THRR2a-670', 1, NULL, CURRENT_TIMESTAMP)
ON DUPLICATE KEY UPDATE `RULE_STATUS`=1, `RULE_ACTIVE_DATE`= NULL, `RULE_INACTIVE_DATE` = CURRENT_TIMESTAMP, `MODIFIED_BY_USER_ID` = NULL;


