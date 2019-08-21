## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases

show databases;

drop database if exists sfg_dev;
create database sfg_dev;

drop USER if exists 'sfg_dev_user'@'localhost';
CREATE USER 'sfg_dev_user'@'localhost' IDENTIFIED BY 'guru';

-- use asterist to indicate any table that may not yet be created yet.
GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'localhost';

GRANT ALL ON sfg_dev.* to 'sfg_dev_user'@'localhost';

shocategoryw grants for 'sfg_dev_user'@'localhost';

select user, host, select_priv, insert_priv, update_priv, delete_priv, create_priv, drop_priv from mysql.user;


