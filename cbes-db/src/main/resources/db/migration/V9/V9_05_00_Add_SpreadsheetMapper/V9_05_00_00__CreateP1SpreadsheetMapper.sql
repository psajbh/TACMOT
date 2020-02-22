CREATE TABLE `P1SpreadsheetColumn` (
  `p1spreadsheetcolumn_id` int(11) NOT NULL AUTO_INCREMENT,
  `columnTitle` varchar(256) NOT NULL,
  `columnLetter` varchar(45) NOT NULL,
  `columnOrder` int(11) NOT NULL,
  PRIMARY KEY (`p1spreadsheetcolumn_id`),
  UNIQUE KEY `p1spreadsheetcolumn_id_UNIQUE` (`p1spreadsheetcolumn_id`),
  UNIQUE KEY `columnTitle_UNIQUE` (`columnTitle`),
  UNIQUE KEY `columnLetter_UNIQUE` (`columnLetter`),
  UNIQUE KEY `columnOrder_UNIQUE` (`columnOrder`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8