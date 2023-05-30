CREATE DATABASE  IF NOT EXISTS `fog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fog`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 164.92.165.237    Database: fog
-- ------------------------------------------------------
-- Server version	8.0.33-0ubuntu0.20.04.2

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
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(200) NOT NULL,
  `price_per_unit` float NOT NULL,
  `unit_id` int NOT NULL,
  `material_type_id` int NOT NULL,
  `material_length_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_material_unit1_idx` (`unit_id`),
  KEY `fk_material_material_type1_idx` (`material_type_id`),
  KEY `new_index_name` (`material_length_id`),
  CONSTRAINT `fk_material_material_length_id` FOREIGN KEY (`material_length_id`) REFERENCES `material_length` (`id`),
  CONSTRAINT `fk_material_material_type1` FOREIGN KEY (`material_type_id`) REFERENCES `material_type` (`id`),
  CONSTRAINT `fk_material_unit1` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (10,'45x195 mm. spærtræ. ubh.',39,1,1,1),(11,'45x195 mm. spærtræ. ubh.',35,1,1,2),(12,'45x195 mm. spærtræ. ubh.',35,1,1,3),(13,'45x195 mm. spærtræ. ubh.',35,1,1,4),(14,'45x195 mm. spærtræ. ubh.',35,1,1,5),(15,'45x195 mm. spærtræ. ubh.',35,1,1,6),(16,'45x195 mm. spærtræ. ubh.',69,1,1,7),(17,'45x95 mm. spærtræ. ubh',17.48,1,2,1),(18,'45x95 mm. spærtræ. ubh',17.48,1,2,2),(19,'45x95 mm. spærtræ. ubh',17.48,1,2,3),(20,'45x95 mm. spærtræ. ubh',17.48,1,2,4),(21,'45x95 mm. spærtræ. ubh',17.48,1,2,5),(22,'45x95 mm. spærtræ. ubh',17.48,1,2,6),(23,'45x95 mm. spærtræ. ubh',17.48,1,2,7),(24,'97x97 mm. trykimp. Stolpe',54,1,3,1),(27,'4.5x60 mm. skruer 200 stk',49.95,2,5,8),(28,'Plastmo Ecolite blåtonet',110,1,6,1),(29,'Plastmo Ecolite blåtonet',110,1,6,2),(30,'Plastmo Ecolite blåtonet',110,1,6,3),(31,'Plastmo Ecolite blåtonet',110,1,6,4),(32,'Plastmo Ecolite blåtonet',110,1,6,5),(33,'Plastmo Ecolite blåtonet',110,1,6,6),(34,'Plastmo Ecolite blåtonet',110,1,6,7),(35,'Hulbånd 1x20 mm.',10,3,7,9),(36,'Universalbeslag 50',250,2,8,8),(37,'25x200 mm. trykimp. Bræt',49.95,1,11,3),(38,'25x200 mm. trykimp. Bræt',49.95,1,11,5),(39,'25x200 mm. trykimp. Bræt',49.95,1,11,7),(40,'25x200 mm. trykimp. Bræt',49.95,1,12,3),(41,'25x200 mm. trykimp. Bræt',49.95,1,12,5),(42,'25x200 mm. trykimp. Bræt',49.95,1,12,7),(43,'25x200 mm. trykimp. Bræt',49.95,1,12,1),(44,'25x125 mm. trykimp. Bræt',21.95,1,9,1),(45,'25x125 mm. trykimp. Bræt',21.95,1,9,3),(46,'25x125 mm. trykimp. Bræt',21.95,1,9,5),(47,'25x125 mm. trykimp. Bræt',21.95,1,9,7),(48,'25x125 mm. trykimp. Bræt',21.95,1,10,1),(49,'25x125 mm. trykimp. Bræt',21.95,1,10,3),(50,'25x125 mm. trykimp. Bræt',21.95,1,10,5),(51,'25x125 mm. trykimp. Bræt',21.95,1,10,7),(56,'Plastmo tagrende',161.5,1,13,1),(57,'Plastmo tagrende',161.5,1,13,2),(58,'Plastmo tagrende',161.5,1,13,3),(59,'Plastmo tagrende',161.5,1,13,4),(60,'Plastmo tagrende',161.5,1,13,6),(61,'Plastmo tagrende',161.5,1,13,7);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_length`
--

DROP TABLE IF EXISTS `material_length`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_length` (
  `id` int NOT NULL AUTO_INCREMENT,
  `length` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_length`
--

LOCK TABLES `material_length` WRITE;
/*!40000 ALTER TABLE `material_length` DISABLE KEYS */;
INSERT INTO `material_length` VALUES (1,2.4),(2,3),(3,3.5),(4,4),(5,4.7),(6,5.3),(7,6),(8,1),(9,10);
/*!40000 ALTER TABLE `material_length` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_list`
--

DROP TABLE IF EXISTS `material_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(200) NOT NULL,
  `material_id` int NOT NULL,
  `order_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_material_list_order1_idx` (`order_id`),
  KEY `fk_material_list_material1_idx` (`material_id`),
  CONSTRAINT `fk_material_list_material1` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`),
  CONSTRAINT `fk_material_list_order1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=447 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_list`
--

LOCK TABLES `material_list` WRITE;
/*!40000 ALTER TABLE `material_list` DISABLE KEYS */;
INSERT INTO `material_list` VALUES (435,'25x200 mm. trykimp. Bræt',40,63,4),(436,'97x97 mm. trykimp. Stolpe',24,63,4),(437,'25x125 mm. trykimp. Bræt',44,63,4),(438,'25x125 mm. trykimp. Bræt',48,63,3),(439,'4.5x60 mm. skruer 200 stk',27,63,1),(440,'Hulbånd 1x20 mm.',35,63,2),(441,'25x200 mm. trykimp. Bræt',37,63,4),(442,'45x95 mm. spærtræ. ubh',20,63,2),(443,'Universalbeslag 50',36,63,1),(444,'Plastmo tagrende',56,63,3),(445,'45x195 mm. spærtræ. ubh.',10,63,24),(446,'Plastmo Ecolite blåtonet',31,63,5);
/*!40000 ALTER TABLE `material_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_type`
--

DROP TABLE IF EXISTS `material_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_type`
--

LOCK TABLES `material_type` WRITE;
/*!40000 ALTER TABLE `material_type` DISABLE KEYS */;
INSERT INTO `material_type` VALUES (1,'Spær til tag'),(2,'Rem til at stå på stolpe, og støtte tag'),(3,'Stolpe bruges som søjler til carport. graves 90 cm under jorden'),(5,'Skruer til montering'),(6,'Tagplader monteres på spær'),(7,'Hulbånd Til vindkryds på spær'),(8,'Universal beslag'),(9,'Oversternbrædde til siderne'),(10,'Oversternsbrædde til forenden'),(11,'Understernsbrædde til for & bagende'),(12,'Understernsbrædde til siderne'),(13,'Tagrende til bortledning af vand');
/*!40000 ALTER TABLE `material_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `totalPrice` float NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_user_idx` (`username`),
  CONSTRAINT `fk_order_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (63,'Turan','Pending',12904.5,'52187496');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxes`
--

DROP TABLE IF EXISTS `taxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taxes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tax_name` varchar(45) NOT NULL,
  `tax_value` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxes`
--

LOCK TABLES `taxes` WRITE;
/*!40000 ALTER TABLE `taxes` DISABLE KEYS */;
INSERT INTO `taxes` VALUES (1,'sales_tax',0.2),(2,'moms',0.25);
/*!40000 ALTER TABLE `taxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `unit` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'Meter'),(2,'Box'),(3,'Rulle');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('','','customer',''),('admin','1234','admin','47160800'),('citychai','cityboy','customer','59874582'),('Elias','Turan','customer','69854712'),('Elias Yasin','1234','customer','30305252'),('jill','1234','user','12258569'),('meti100','1234','customer','sasdsadsad'),('Metin','1234','customer','65478596'),('Nikolaj','1234','customer','96412878'),('test','test1234','customer','84858769'),('testuser','1234','customer','36295874'),('Turan','1234','Customer','52187496'),('tuser','123','customer','48796138'),('v','v','customer','25298564'),('Vivek','1234','customer','96471542');
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

-- Dump completed on 2023-05-30 14:12:34
