-- Modify indicator that was added to allow out-year data for an item group that consists of mods procurement

ALTER TABLE proc_item_group_40A MODIFY COLUMN `ig_mods_indicator` tinyint(1) DEFAULT 0 AFTER `ig_title_footnote`;