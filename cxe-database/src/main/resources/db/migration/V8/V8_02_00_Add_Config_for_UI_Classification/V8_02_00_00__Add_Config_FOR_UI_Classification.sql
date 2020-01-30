SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
insert into config(`cf_name`, `cf_value`, `cf_type`, `cf_desc`, `cf_env`, `cf_cycle`, `cf_year`, `cf_read_only`) 
values  ('classification.banner', 'THIS PAGE CONTAINS DYNAMIC CONTENT. HIGHEST POSSIBLE CLASSIFICATION IS UNCLASSIFIED', 'String', 'Banner Text for either NIPR or SIPR', 'default','xxxxx',0,0),
        ('classification.css', ' ', 'String', ' css file supporting banner', 'default','xxxxx',0,0),
        ('classification.style', ' ', 'String', 'Arbitrary CSS we can apply to the classification banner deiv element i.e color:blue;text-align:center', 'default','xxxxx',0,0);
