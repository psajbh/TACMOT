ALTER TABLE `proc_advance_bdgt_just` 
add column `abj_title_footnote` varchar(3000) DEFAULT NULL AFTER `abj_title`, 
add column `abj_when_reqd_footnote` varchar(3000) DEFAULT NULL AFTER `abj_when_reqd`,
add column `abj_qty_per_assem_footnote` varchar(3000) DEFAULT NULL AFTER `abj_qty_per_assem`,
add column `abj_PLT_footnote` varchar(3000) DEFAULT NULL AFTER `abj_PLT`,
add column `proc_termination_liability_footnote` varchar(3000) DEFAULT NULL AFTER `proc_termination_liability_fk`;