ALTER TABLE `proc_ships_contracts` 
ADD COLUMN `sco_post_shake_avail_start` DATE NULL DEFAULT NULL COMMENT 'P-29/P-30 Post Shakedown Availability Start date' AFTER `sco_is_significant`,
ADD COLUMN `sco_post_shake_avail_start_footnote` TEXT NULL DEFAULT NULL COMMENT 'P-29/P-30 Post Shakedown Availability Start date footnote' AFTER `sco_post_shake_avail_start`,
ADD COLUMN `sco_post_shake_avail_finish` DATE NULL DEFAULT NULL COMMENT 'P-29/P-30 Post Shakedown Availability Finish date' AFTER `sco_post_shake_avail_start_footnote`,
ADD COLUMN `sco_post_shake_avail_finish_footnote` TEXT NULL DEFAULT NULL COMMENT 'P-29/P-30 Post Shakedown Availability Finish date footnote' AFTER `sco_post_shake_avail_finish`;

