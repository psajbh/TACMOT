ALTER TABLE `proc_ships_fy_funding` CHANGE COLUMN `sfyf_fiscal_year` `sfyf_fiscal_year` 
ENUM('PY20','PY19','PY18','PY17','PY16','PY15','PY14','PY13','PY12','PY11','PY10','PY9','PY8','PY7','PY6','PY5','PY4','PY3','PY2','PY','CY','BY1','BY2','BY3','BY4','BY5') NOT NULL;

