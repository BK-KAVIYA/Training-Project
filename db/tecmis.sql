-- MySQL dump 10.13  Distrib 8.3.0, for Win64 (x86_64)
--
-- Host: localhost    Database: tecmis
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `blogpost`
--

DROP TABLE IF EXISTS `blogpost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogpost` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) NOT NULL,
  `Author` int DEFAULT NULL,
  `Content` text NOT NULL,
  `PublishedDate` varchar(50) NOT NULL,
  `Category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_author` (`Author`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogpost`
--

LOCK TABLES `blogpost` WRITE;
/*!40000 ALTER TABLE `blogpost` DISABLE KEYS */;
INSERT INTO `blogpost` VALUES (1,'Introduction to SQL',0,'SQL (Structured Query Language) is a programming language...','2024-06-01','Technology'),(2,'10 Tips for Better Time Management',0,'Time management is crucial for productivity and success...','2024-06-02','Self Improvement'),(3,'The Art of Cooking Pasta',0,'Pasta is a versatile dish that can be prepared in many ways...','2024-06-03','Food'),(4,'The Importance of Regular Exercise',0,'Regular exercise has numerous benefits for both physical and mental health...','2024-06-04','Health'),(5,'Traveling on a Budget',0,'Traveling doesn have to break the bank. With careful planning and budgeting...','2024-06-05','Travel');
/*!40000 ALTER TABLE `blogpost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'John Doe','john.doe@example.com',20,'123 Main St','555-1234','A',NULL),(2,'Jane Smith','jane.smith@example.com',22,'456 Elm St','555-5678','B',NULL),(3,'Robert Brown','robert.brown@example.com',21,'789 Oak St','555-8765','C',NULL),(4,'Emily Davis','emily.davis@example.com',19,'321 Maple St','555-4321','B',NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'USER',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `contact_number` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$12$FXmG8y8wak0GFyDMsFFMu.2ZJFZlM.3p6sOXYnYSP0QWhRa4XU3FK','ADMIN','user@gmail.com','0774586986'),(2,'user','$2a$12$ZeGmyWvI1CyCClrCBM6PX.aFyA.I2klyBvQcQSjlzicwfOi3ZeAJy','USER','admin@gmail.com','0775424061'),(3,'Kavinda','$2a$10$W/g33AQVjvKptorHopP/O.NcMP1OcKCbKi2PhyUT1SedQ3FzZztiy','User','kavinda@gmail.com','0788311883'),(4,'saman','$2a$10$d9.WZzYaJ7yj5KMoQvxlVeBQFQoA3px5wIc3bmkeR11.l63aU2tFe','','saman@gmail.com','0788311883'),(5,'kamal','$2a$10$jTUBJUPg3YcHkYT8wkP73OGLrYQQI30W1RLfpRYrS/OcqlZTEu.3a','','kamal@gmail.com','0755485666'),(6,'nimesh','$2a$10$PfokzHPmQKl76y6goHkmnu4IQe1hNzZ0CHtnTFgoKfDL2n0qTrqhK','USER','nimesh@gmail.com','0788311883');
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

-- Dump completed on 2024-06-19 10:20:43
