SET FOREIGN_KEY_CHECKS=0;
SET UNIQUE_CHECKS=0;
SET SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
INSERT INTO `config` 
(`cf_name`, `cf_value`, `cf_type`, `cf_desc`, `cf_env`, `cf_cycle`, `cf_year`, `cf_read_only`) 
VALUES 
('jm.job.FileSystemCleanJob2', 
'{  \"jobBeanClass\" : \"mil.dtic.r2.jobmanager.tasks.jobs.FileSystemCleanJob2\", \"jobGroup\" : \"defaultGroup\", \"locations\" : \"[{\\\"age\\\":\\\"30\\\",\\\"path\\\":\\\"/upload/r2\\\",\\\"extensions\\\":[\\\"\\\"],\\\"whitelist\\\":[\\\"logos\\\",\\\"touchfiles\\\"]}, {\\\"age\\\":\\\"30\\\",\\\"path\\\":\\\"/logs/r2\\\",\\\"extensions\\\":[\\\".xml\\\",\\\".log\\\"],\\\"whitelist\\\":[]}]\", \"enable\" : \"true\",   \"cronTrigger\" : \"0 0 7 * * ?\", \"doc-locations\" : \"json array of path/age/extension objects, with the paths to directories to scan for deletion, minimum file age, and extension list\", \"jobName\" : \"fsclean\" }',
'JSONJOB', 'Routinely delete aged files.', 'default', 'xxxxx', '0', '0');
