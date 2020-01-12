ALTER TABLE `proc_bdgt_yrs_data` 
      ADD COLUMN `byd_py20` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_pyrs_footnote` , 
      ADD COLUMN `byd_py20_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py20` ,
      
      ADD COLUMN `byd_py19` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py20_footnote` , 
      ADD COLUMN `byd_py19_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py19` ,
      
      ADD COLUMN `byd_py18` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py19_footnote` , 
      ADD COLUMN `byd_py18_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py18` ,
      
      ADD COLUMN `byd_py17` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py18_footnote` , 
      ADD COLUMN `byd_py17_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py17` ,
      
      ADD COLUMN `byd_py16` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py17_footnote` , 
      ADD COLUMN `byd_py16_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py16` ,
      
      ADD COLUMN `byd_py15` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py16_footnote` , 
      ADD COLUMN `byd_py15_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py15` ,
      
      ADD COLUMN `byd_py14` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py15_footnote` , 
      ADD COLUMN `byd_py14_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py14` ,
      
      ADD COLUMN `byd_py13` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py14_footnote` , 
      ADD COLUMN `byd_py13_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py13` ,
      
      ADD COLUMN `byd_py12` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py13_footnote` , 
      ADD COLUMN `byd_py12_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py12` ,
      
      ADD COLUMN `byd_py11` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py12_footnote` , 
      ADD COLUMN `byd_py11_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py11` ,
      
      ADD COLUMN `byd_py10` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py11_footnote` , 
      ADD COLUMN `byd_py10_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py10` ,
      
      ADD COLUMN `byd_py9` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py10_footnote` , 
      ADD COLUMN `byd_py9_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py9` ,
      
      ADD COLUMN `byd_py8` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py9_footnote` , 
      ADD COLUMN `byd_py8_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py8` ,
      
      ADD COLUMN `byd_py7` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py8_footnote` , 
      ADD COLUMN `byd_py7_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py7` ,
      
      ADD COLUMN `byd_py6` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py7_footnote` , 
      ADD COLUMN `byd_py6_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py6` ,
      
      ADD COLUMN `byd_py5` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py6_footnote` , 
      ADD COLUMN `byd_py5_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py5` ,
     
      ADD COLUMN `byd_py4` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py5_footnote` , 
      ADD COLUMN `byd_py4_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py4` ,
      
      ADD COLUMN `byd_py3` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py4_footnote` , 
      ADD COLUMN `byd_py3_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py3` ,
      
      ADD COLUMN `byd_py2` DECIMAL(13,3) NULL DEFAULT NULL  AFTER `byd_py3_footnote` , 
      ADD COLUMN `byd_py2_footnote` TEXT NULL DEFAULT NULL  AFTER `byd_py2`;