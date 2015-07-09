DROP TABLE IF EXISTS Greeting;
CREATE TABLE `Greeting` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `content` varchar(64) COMMENT 'content'
);