# Widening this column from 32 to 56 due to AWS having longer hostnames.

alter table asynch_job
change column run_instance_name run_instance_name varchar(56) null default null comment 'Contains hostname of JM node running the job.' ;
