CREATE TABLE `qp_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(50) NOT NULL,
  `title` varchar(50) NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_unique` (`link`,`title`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

