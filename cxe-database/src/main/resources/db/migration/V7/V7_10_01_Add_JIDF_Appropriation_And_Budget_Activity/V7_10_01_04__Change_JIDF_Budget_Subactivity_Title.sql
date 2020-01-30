UPDATE `proc_budget_sub_activity`
SET `bsa_title` = 'Rapid Acquisition and Threat Response - Defense Threat Reduction Agency'
WHERE `ba_ID` = (SELECT `ba_ID` FROM `proc_budget_activity` WHERE `ba_title` = 'Rapid Acquisition and Threat Response');