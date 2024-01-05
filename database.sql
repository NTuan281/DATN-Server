-- --------------------------------------------------------
-- Máy chủ:                      127.0.0.1
-- Server version:               10.4.28-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Phiên bản:           12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for erd
CREATE DATABASE IF NOT EXISTS `erd` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `erd`;

-- Dumping structure for table erd.problem
CREATE TABLE IF NOT EXISTS `problem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_at` date DEFAULT NULL,
  `desciption` varchar(255) DEFAULT NULL,
  `difficulty` varchar(255) DEFAULT NULL,
  `guide` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qg87jy9l8hwkmuc1e8e4nvuo` (`name`),
  KEY `FKj6t2lpcskqxjdjr5j4txsxxdp` (`user_id`),
  CONSTRAINT `FKj6t2lpcskqxjdjr5j4txsxxdp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table erd.problem: ~0 rows (approximately)

-- Dumping structure for table erd.problem_tags
CREATE TABLE IF NOT EXISTS `problem_tags` (
  `problem_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`problem_id`,`tag_id`),
  KEY `FKqx6quba5l2i2nq4p864d8cn6p` (`tag_id`),
  CONSTRAINT `FKiss624kebh6xf9q88ifretjy7` FOREIGN KEY (`problem_id`) REFERENCES `problem` (`id`),
  CONSTRAINT `FKqx6quba5l2i2nq4p864d8cn6p` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table erd.problem_tags: ~0 rows (approximately)

-- Dumping structure for table erd.submission
CREATE TABLE IF NOT EXISTS `submission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rusult` varchar(255) NOT NULL,
  `source` varchar(255) NOT NULL,
  `submission_time` date DEFAULT NULL,
  `problem_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKptcopqggpsnejq7vcvpxtvl9j` (`problem_id`),
  KEY `FK1qgnfmncpuladr2896scialg0` (`user_id`),
  CONSTRAINT `FK1qgnfmncpuladr2896scialg0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKptcopqggpsnejq7vcvpxtvl9j` FOREIGN KEY (`problem_id`) REFERENCES `problem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table erd.submission: ~0 rows (approximately)

-- Dumping structure for table erd.tag
CREATE TABLE IF NOT EXISTS `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1wdpsed5kna2y38hnbgrnhi5b` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table erd.tag: ~0 rows (approximately)

-- Dumping structure for table erd.testcase
CREATE TABLE IF NOT EXISTS `testcase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inputs` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL CHECK (json_valid(`inputs`)),
  `output` varchar(255) NOT NULL,
  `problem_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK75jw84bkakgr5i4lq7yqavho9` (`problem_id`),
  CONSTRAINT `FK75jw84bkakgr5i4lq7yqavho9` FOREIGN KEY (`problem_id`) REFERENCES `problem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table erd.testcase: ~0 rows (approximately)

-- Dumping structure for table erd.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_at` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table erd.user: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
