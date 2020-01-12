SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET SQL_SAFE_UPDATES=0;

INSERT INTO config
(
    cf_name,
    cf_value,
    cf_type,
    cf_desc,
    cf_env,
    cf_cycle,
    cf_year,
    cf_read_only
)
VALUES
(
    'classification.label',
    'UNCLASSIFIED',
    'STRING',
    'The classification label to stamp onto PDFs',
    'default',
    'xxxxx',
    0,
    0
),
(
    'classification.symbol',
    'U',
    'STRING',
    'The classification symbol to stamp onto PDFs',
    'default',
    'xxxxx',
    0,
    0
);

DELETE FROM config
WHERE cf_name = 'classification.setting';
