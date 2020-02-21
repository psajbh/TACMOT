ALTER TABLE `proc_ships_contracts` 
DROP FOREIGN KEY `FK_sco_sc_ID`;
ALTER TABLE `proc_ships_contracts` 
CHANGE COLUMN `sc_ID` `sc_ID` INT(10) UNSIGNED NULL ;
ALTER TABLE `proc_ships_contracts` 
ADD CONSTRAINT `FK_sco_sc_ID`
  FOREIGN KEY (`sc_ID`)
  REFERENCES `proc_ships_class` (`sc_ID`);