CREATE DATABASE  IF NOT EXISTS `carport` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `carport`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: carport
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `carport`
--

DROP TABLE IF EXISTS `carport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carport` (
  `carport_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `length` double NOT NULL,
  `width` double NOT NULL,
  PRIMARY KEY (`carport_id`),
  KEY `fk_carport_order1_idx` (`order_id`),
  CONSTRAINT `fk_carport_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `carport_has_material_list`
--

DROP TABLE IF EXISTS `carport_has_material_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carport_has_material_list` (
  `carport_id` int NOT NULL,
  `material_id` int NOT NULL,
  `quantity` float NOT NULL,
  PRIMARY KEY (`carport_id`,`material_id`),
  KEY `fk_carport_has_material_list_carport1_idx` (`carport_id`,`material_id`),
  KEY `fk_carport_has_material_list_material1_idx` (`material_id`),
  CONSTRAINT `fk_carport_has_material_list_carport1` FOREIGN KEY (`carport_id`) REFERENCES `carport` (`carport_id`),
  CONSTRAINT `fk_carport_has_material_list_material1` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `length` double DEFAULT NULL,
  `width` double DEFAULT NULL,
  `height` double DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'Høvlet Mahogni',300,200,NULL,3100),(2,'Klink',350,250,NULL,250),(3,'Plastik - Hvidt',100,120,NULL,250),(4,'Carbon fiber',240,240,NULL,2000),(5,'SPÆRTRÆ C24 HØVLET',300,4.5,145,206.85),(6,'SPÆRTRÆ C24 HØVLET',360,4.5,145,248.23),(7,'SPÆRTRÆ C24 HØVLET',420,4.5,145,289.59),(8,'SPÆRTRÆ C24 HØVLET',480,4.5,145,330.96),(9,'SPÆRTRÆ C24 HØVLET',540,4.5,145,372.33),(10,'SPÆRTRÆ C24 HØVLET',600,4.5,145,371.7),(11,'SPÆRTRÆ C24 HØVLET',660,4.5,145,408.88),(12,'SPÆRTRÆ C24 HØVLET',780,4.5,145,446.04),(13,'STOLPE Trykimp',300,15,15,80),(14,'REMTRÆ C18 HØVLET',240,4.5,145,83.88),(15,'REMTRÆ C18 HØVLET',270,4.5,145,94.36),(16,'REMTRÆ C18 HØVLET',300,4.5,145,107.85),(17,'REMTRÆ C18 HØVLET',330,4.5,145,118.63),(18,'REMTRÆ C18 HØVLET',360,4.5,145,129.41),(19,'REMTRÆ C18 HØVLET',390,4.5,145,140.2),(20,'REMTRÆ C18 HØVLET',420,4.5,145,150.99),(21,'REMTRÆ C18 HØVLET',450,4.5,145,161.78),(22,'REMTRÆ C18 HØVLET',480,4.5,145,172.55),(23,'REMTRÆ C18 HØVLET',510,4.5,145,183.34),(24,'REMTRÆ C18 HØVLET',540,4.5,145,194.13),(25,'REMTRÆ C18 HØVLET',570,4.5,145,203.25),(26,'REMTRÆ C18 HØVLET',600,4.5,145,214.43),(27,'REMTRÆ C18 HØVLET',630,4.5,145,223.65),(28,'REMTRÆ C18 HØVLET',660,4.5,145,245.54),(29,'REMTRÆ C18 HØVLET',690,4.5,145,268.98),(30,'REMTRÆ C18 HØVLET',720,4.5,145,298.76),(31,'REMTRÆ C18 HØVLET',750,4.5,145,315.86),(32,'REMTRÆ C18 HØVLET',780,4.5,145,360.22);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_category`
--

DROP TABLE IF EXISTS `material_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_category` (
  `material_category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`material_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_category`
--

LOCK TABLES `material_category` WRITE;
/*!40000 ALTER TABLE `material_category` DISABLE KEYS */;
INSERT INTO `material_category` VALUES (1,'Redskabsrum Beklædning'),(2,'Carport Beklædning'),(3,'Spær'),(4,'Remme'),(5,'Stolper');
/*!40000 ALTER TABLE `material_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_has_material_category`
--

DROP TABLE IF EXISTS `material_has_material_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_has_material_category` (
  `material_id` int NOT NULL,
  `material_category_id` int NOT NULL,
  PRIMARY KEY (`material_id`,`material_category_id`),
  KEY `fk_material_has_material_category_material_category1_idx` (`material_category_id`),
  CONSTRAINT `fk_material_has_material_category_material1` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`),
  CONSTRAINT `fk_material_has_material_category_material_category1` FOREIGN KEY (`material_category_id`) REFERENCES `material_category` (`material_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_has_material_category`
--

LOCK TABLES `material_has_material_category` WRITE;
/*!40000 ALTER TABLE `material_has_material_category` DISABLE KEYS */;
INSERT INTO `material_has_material_category` VALUES (1,1),(2,1),(3,2),(4,2),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3),(12,3),(14,4),(15,4),(16,4),(17,4),(18,4),(19,4),(20,4),(21,4),(22,4),(23,4),(24,4),(25,4),(26,4),(27,4),(28,4),(29,4),(30,4),(31,4),(32,4),(33,4),(13,5);
/*!40000 ALTER TABLE `material_has_material_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `orderdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_state` tinyint DEFAULT '0',
  `saleprice` double DEFAULT NULL,
  `costprice` double DEFAULT NULL,
  PRIMARY KEY (`order_id`,`user_id`),
  KEY `fk_order_user_idx` (`user_id`),
  CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `shed`
--

DROP TABLE IF EXISTS `shed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shed` (
  `shed_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `length` double DEFAULT NULL,
  `width` double DEFAULT NULL,
  PRIMARY KEY (`shed_id`),
  KEY `fk_shed_order1_idx` (`order_id`),
  CONSTRAINT `fk_shed_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `shed_has_material_list`
--

DROP TABLE IF EXISTS `shed_has_material_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shed_has_material_list` (
  `shed_id` int NOT NULL,
  `material_id` int NOT NULL,
  `quantity` float NOT NULL,
  PRIMARY KEY (`material_id`,`shed_id`),
  KEY `fk_shed_has_material_list_material1_idx` (`material_id`),
  KEY `fk_shed_has_material_list_shed1_idx` (`shed_id`),
  CONSTRAINT `fk_shed_has_material_list_material1` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`),
  CONSTRAINT `fk_shed_has_material_list_shed1` FOREIGN KEY (`shed_id`) REFERENCES `shed` (`shed_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(16) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user','1','customer'),(2,'admin','1','employee'),(3,'','','customer');
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

-- Dump completed on 2021-05-19 12:03:12
