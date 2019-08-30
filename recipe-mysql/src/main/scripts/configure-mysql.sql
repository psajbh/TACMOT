## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases

#To see all users
#select USER from mysql.user;

drop database if exists sfg_dev;
CREATE DATABASE sfg_dev;

drop database if exists sfg_prod;
CREATE DATABASE sfg_prod;

#Create database service accounts
drop user if exists 'sfg_dev_user'@'localhost';
CREATE USER 'sfg_dev_user'@'localhost' IDENTIFIED BY 'guru';

drop user if exists 'sfg_prod_user'@'localhost';
CREATE USER 'sfg_prod_user'@'localhost' IDENTIFIED BY 'guru';
#CREATE USER 'sfg_dev_user'@'%' IDENTIFIED BY 'guru';
#CREATE USER 'sfg_prod_user'@'%' IDENTIFIED BY 'guru';

#Database grants
GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'localhost';
GRANT SELECT ON sfg_prod.* to 'sfg_prod_user'@'localhost';
GRANT INSERT ON sfg_prod.* to 'sfg_prod_user'@'localhost';
GRANT DELETE ON sfg_prod.* to 'sfg_prod_user'@'localhost';
GRANT UPDATE ON sfg_prod.* to 'sfg_prod_user'@'localhost';

#GRANT SELECT ON sfg_dev.* to 'sfg_dev_user'@'%';  -- % is wildcard if using docker container.
#GRANT INSERT ON sfg_dev.* to 'sfg_dev_user'@'%';
#GRANT DELETE ON sfg_dev.* to 'sfg_dev_user'@'%';
#GRANT UPDATE ON sfg_dev.* to 'sfg_dev_user'@'%';
#GRANT SELECT ON sfg_prod.* to 'sfg_prod_user'@'%';
#GRANT INSERT ON sfg_prod.* to 'sfg_prod_user'@'%';
#GRANT DELETE ON sfg_prod.* to 'sfg_prod_user'@'%';
#GRANT UPDATE ON sfg_prod.* to 'sfg_prod_user'@'%';