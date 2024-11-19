-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: wallet
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `transaction`
--
CREATE SCHEMA `wallet` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
use wallet;
DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` timestamp NULL DEFAULT NULL,
  `updated_date` timestamp NULL DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `trx_amount` decimal(19,2) DEFAULT NULL,
  `trx_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'FUND TRANSFER',123.00,'2019-09-12 11:11:11'),(2,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'ATM WITHDRWAL',1123.00,'2019-09-11 11:11:11'),(3,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'FUND TRANSFER',1223.00,'2019-10-11 11:11:11'),(4,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'3rd Party FUND TRANSFER',1233.00,'2019-11-11 11:11:11'),(5,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'3rd Party FUND TRANSFER',1243.00,'2019-08-11 11:11:11'),(6,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'3rd Party FUND TRANSFER',12553.00,'2019-07-11 11:11:11'),(7,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'BILL PAYMENT',12113.00,'2019-08-11 11:11:11'),(8,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'BILL PAYMENT',1222.00,'2019-09-11 11:11:11'),(9,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'FUND TRANSFER',2123.00,'2019-09-11 11:11:11'),(10,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'FUND TRANSFER',1323.00,'2019-09-11 11:11:11'),(11,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'FUND TRANSFER',12443.00,'2019-09-11 11:11:11'),(12,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'FUND TRANSFER',125553.00,'2019-09-11 11:11:11'),(13,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'FUND TRANSFER',126663.00,'2019-09-11 11:11:11'),(14,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838283',222,'FUND TRANSFER',1266663.00,'2019-09-11 11:11:11'),(15,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'FUND TRANSFER',1121223.00,'2019-09-11 11:11:11'),(16,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'FUND TRANSFER',12231233.00,'2019-09-11 11:11:11'),(17,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'FUND TRANSFER',111123.00,'2019-09-11 11:11:11'),(18,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'BILL PAYMENT',12223.00,'2019-09-11 11:11:11'),(19,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'BILL PAYMENT',1223233.00,'2019-09-11 11:11:11'),(20,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'ATM WITHDRWAL',1223233.00,'2019-09-11 11:11:11'),(21,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'ATM WITHDRWAL',1223123233.00,'2019-09-11 11:11:11'),(22,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'3rd Party FUND TRANSFER',1223233.00,'2019-09-11 11:11:11'),(23,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'3rd Party FUND TRANSFER',12232133.00,'2019-09-11 11:11:11'),(24,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'3rd Party FUND TRANSFER',1223233.00,'2019-09-11 11:11:11'),(25,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'BILL PAYMENT',1111123.00,'2019-09-11 11:11:11'),(26,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'BILL PAYMENT',122223.00,'2019-09-11 11:11:11'),(27,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'BILL PAYMENT',1222223.00,'2019-09-11 11:11:11'),(28,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'FUND TRANSFER',1223233.00,'2019-09-11 11:11:11'),(29,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'FUND TRANSFER',11123.00,'2019-09-11 11:11:11'),(30,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'FUND TRANSFER',1223233.00,'2019-09-11 11:11:11'),(31,'2024-11-19 20:01:46','2024-11-19 20:01:46','8872838299',222,'FUND TRANSFER',123666.00,'2019-09-11 11:11:11'),(32,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'BILL PAYMENT',1.00,'2019-09-11 11:11:11'),(33,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'BILL PAYMENT',1223.00,'2019-09-12 11:11:11'),(34,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'BILL PAYMENT',12323.00,'2019-09-13 11:11:11'),(35,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'BILL PAYMENT',12323.00,'2019-09-11 11:11:11'),(36,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'BILL PAYMENT',121233.00,'2019-09-11 11:11:11'),(37,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'BILL PAYMENT',19923.00,'2019-09-11 11:11:11'),(38,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'BILL PAYMENT',12893.00,'2019-09-13 11:11:11'),(39,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'FUND TRANSFER',99123.00,'2019-09-14 11:11:11'),(40,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'FUND TRANSFER',19923.00,'2019-09-11 11:11:11'),(41,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'FUND TRANSFER',12993.00,'2019-09-11 11:11:11'),(42,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'FUND TRANSFER',12993.00,'2019-09-15 11:11:11'),(43,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'FUND TRANSFER',12993.00,'2019-09-11 11:11:11'),(44,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'FUND TRANSFER',12993.00,'2019-09-16 11:11:11'),(45,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'FUND TRANSFER',12993.00,'2019-09-17 11:11:11'),(46,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'FUND TRANSFER',9123.00,'2019-09-11 11:11:11'),(47,'2024-11-19 20:01:46','2024-11-19 20:01:46','6872838260',333,'FUND TRANSFER',1923.00,'2019-09-11 11:11:11');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` timestamp NULL DEFAULT NULL,
  `updated_date` timestamp NULL DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_verified` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lqjrcobrh9jc8wpcar64q1bfh` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2024-11-18 16:32:19','2024-11-18 16:32:19','aung@gmail.com',_binary '','$2a$10$z5yx4hNqJpztgJ0vZUy3X.V/x5TmHQjs4ZCGmVotxTXl0vGGvssHO','NORMAL','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'wallet'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-20  3:15:07
