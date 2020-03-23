CREATE DATABASE community

CREATE TABLE USER(
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `account_id` VARCHAR(100),
    `name` VARCHAR(50),
    `token` VARCHAR(36),
    `gmt_create` BIGINT,
    `gmt_modified` BIGINT

)ENGINE=INNODB DEFAULT CHARSET=utf8;

SELECT * FROM USER
