-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: testdb
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'USER',
  `status` tinyint(1) DEFAULT '1',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,'user01','pass01','홍길동1','010-0001-0001','user01@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(8,'user03','pass03','홍길동3','010-0003-0003','user03@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(9,'user04','pass04','홍길동4','010-0004-0004','user04@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(10,'user05','pass05','홍길동5','010-0005-0005','user05@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(11,'user06','pass06','홍길동6','010-0006-0006','user06@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(12,'user07','pass07','홍길동7','010-0007-0007','user07@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(13,'user08','pass08','홍길동8','010-0008-0008','user08@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(14,'user09','pass09','홍길동9','010-0009-0009','user09@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(15,'user10','pass10','홍길동10','010-0010-0010','user10@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(16,'user11','pass11','김철수1','010-0011-0011','user11@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(17,'user12','pass12','김철수2','010-0012-0012','user12@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(18,'user13','pass13','김철수3','010-0013-0013','user13@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(19,'user14','pass14','김철수4','010-0014-0014','user14@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(20,'user15','pass15','김철수5','010-0015-0015','user15@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(21,'user16','pass16','이영희1','010-0016-0016','user16@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(22,'user17','pass17','이영희2','010-0017-0017','user17@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(23,'user18','pass18','이영희3','010-0018-0018','user18@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(24,'user19','pass19','이영희4','010-0019-0019','user19@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(25,'user20','pass20','이영희5','010-0020-0020','user20@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(26,'user21','pass21','박민수1','010-0021-0021','user21@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(27,'user22','pass22','박민수2','010-0022-0022','user22@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(28,'user23','pass23','박민수3','010-0023-0023','user23@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(29,'user24','pass24','박민수4','010-0024-0024','user24@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(30,'user25','pass25','박민수5','010-0025-0025','user25@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(31,'user26','pass26','최지훈1','010-0026-0026','user26@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(32,'user27','pass27','최지훈2','010-0027-0027','user27@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(33,'user28','pass28','최지훈3','010-0028-0028','user28@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(34,'user29','pass29','최지훈4','010-0029-0029','user29@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(35,'user30','pass30','최지훈5','010-0030-0030','user30@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(36,'user31','pass31','장미영1','010-0031-0031','user31@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(37,'user32','pass32','장미영2','010-0032-0032','user32@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(38,'user33','pass33','장미영3','010-0033-0033','user33@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(39,'user34','pass34','장미영4','010-0034-0034','user34@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(40,'user35','pass35','장미영5','010-0035-0035','user35@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(41,'user36','pass36','윤성민1','010-0036-0036','user36@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(42,'user37','pass37','윤성민2','010-0037-0037','user37@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(43,'user38','pass38','윤성민3','010-0038-0038','user38@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(44,'user39','pass39','윤성민4','010-0039-0039','user39@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(45,'user40','pass40','윤성민5','010-0040-0040','user40@example.com','USER',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(46,'admin1','adminpass1','관리자1','010-9991-9991','admin1@example.com','ADMIN',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(47,'admin2','adminpass2','관리자2','010-9992-9992','admin2@example.com','ADMIN',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(48,'admin3','adminpass3','관리자3','010-9993-9993','admin3@example.com','ADMIN',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(49,'admin4','adminpass4','관리자4','010-9994-9994','admin4@example.com','ADMIN',1,'2025-11-03 11:18:35','2025-11-03 11:18:35'),(51,'abc','abc123','sample','010-1234-1234','hey@hey.com','USER',1,'2025-11-03 11:19:51','2025-11-03 11:19:51'),(52,'aaa','$2a$10$y5m6xFqa4sTaxqCnjSjKGuPCbzQ0zekAf41K1rO9a7rSGH73777.K','aaa','010-1212-1211','aaa@aaa.com','USER',1,'2025-11-03 11:59:48','2025-11-03 12:35:16'),(53,'bbb','bbb','bbb','010-2121-2122','bbb@bbb.com','USER',1,'2025-11-03 12:00:13','2025-11-03 12:00:13'),(54,'kkk','$2a$10$fow4KBKqgn4CbidzOxg8tOjq4l2z5Odr.VP95LPlf0YS0oGGGDOli','kkk','010-7777-7777','kkk1@kkk.com','USER',1,'2025-11-03 12:25:02','2025-11-03 12:25:02'),(56,'admin','$2a$10$iEta/3PUJyl0kWhCftGxIeCiObYe58Q8.RmYAtUdIqhscEekko17y','관리자','000-0000-0000','admin@system.com','ADMIN',1,'2025-11-03 13:34:39','2025-11-03 13:34:39'),(57,'qwer','$2a$10$Z7RQZILZyhKF9Etz7.862urm0Fdi1dTdArvA3Kq3ExIVyNmU9gfU6','qwer','010-1234-1234','1234@1234.com','USER',1,'2025-11-03 13:36:12','2025-11-03 13:36:41'),(59,'q','$2a$10$NXt.v8uDYoLGJdhP4u9QFeljuDcUWfqR3ZsCturZwVHDzLQMA07SW','q','q','213','USER',1,'2025-11-03 13:38:52','2025-11-03 13:38:52'),(60,'aa','$2a$10$yYSE5r1bOxjatowWc5VfX.a.FgCu9N3h41ldVKe1twfRDiQMaBtly','aa','010-2121-2121','aabb@aabb.com','USER',1,'2025-11-03 13:44:01','2025-11-03 13:44:01'),(61,'top','$2a$10$M84wtuwfWl3D7jPHQ5kYW.GDq4HR0DBMzBFogESCXgycMcRTNP/5i','top','010-2525-2525','1@nnn.cc','USER',1,'2025-11-03 13:46:19','2025-11-03 13:46:19'),(62,'haechan','$2a$10$zI1oJTi8UYLP9OeS1ZpHgeIQW37oNqBc2PuimsM0QVbSk9KiQaDDa','한해찬','010-2222-3333','haec@naver.com','USER',1,'2025-11-03 14:40:18','2025-11-03 14:41:42');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-04  9:12:14
