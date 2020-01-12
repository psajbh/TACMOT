ALTER TABLE `proc_mod_grp` 
add column `mg_title_footnote` varchar(3000) DEFAULT NULL AFTER `mg_title`, 
add column `mg_mod_num_footnote` varchar(3000) DEFAULT NULL AFTER `mg_mod_num`,
add column `mg_models_footnote` varchar(3000) DEFAULT NULL AFTER `mg_models`,
add column `mg_type_footnote` varchar(3000) DEFAULT NULL AFTER `mg_type`;
