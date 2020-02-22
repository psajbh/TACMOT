SET SQL_SAFE_UPDATES=0;

-- Update any values which might have the old e-mail address.
UPDATE config
SET cf_value = 'dtic.belvoir.pm.list.r2-support@mail.mil'
WHERE cf_value = 'R2Support@dtic.mil' OR cf_value = 'dtic.belvoir.pm.list.r2-masslight-contractors@mail.mil';