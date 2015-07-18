DROP TABLE IF EXISTS Greeting;
CREATE TABLE `Greeting` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(64) NOT NULL DEFAULT '' COMMENT 'content',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'update time',
  `isDeleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'whether it''s deleted',
  PRIMARY KEY (`id`),
  KEY `IX_UpdateTime` (`updateTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;