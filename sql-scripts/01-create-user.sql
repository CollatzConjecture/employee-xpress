-- Drop user first if they exist
DROP USER if exists 'employeexpress'@'localhost' ;

-- Now create user with prop privileges
CREATE USER 'employeexpress'@'localhost' IDENTIFIED BY 'employeexpress';

GRANT ALL PRIVILEGES ON * . * TO 'employeexpress'@'localhost';