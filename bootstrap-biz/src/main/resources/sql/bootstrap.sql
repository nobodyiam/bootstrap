CREATE DATABASE IF NOT EXISTS bootstrap DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE bootstrap;
DROP TABLE IF EXISTS Greeting;
CREATE TABLE `Greeting` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(64) DEFAULT NULL COMMENT 'content',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;