-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: cashsystem
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Current Database: `cashsystem`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `cashsystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `cashsystem`;

--
-- Temporary view structure for view `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!50001 DROP VIEW IF EXISTS `bill`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `bill` AS SELECT 
 1 AS `lid`,
 1 AS `cid`,
 1 AS `gid`,
 1 AS `buy_num`,
 1 AS `price`,
 1 AS `ptime`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `gid` char(30) NOT NULL,
  `gname` char(30) NOT NULL,
  `price` double NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES ('11110','啤酒',7,500),('11111','维达纸巾',2,4),('11112','农夫山泉',5,30),('11113','索尼电视',3005,85),('11114','热带冰红茶',3,1447),('11115','方便面',5.5,97),('11116','显示器',999.9,32),('11117','百乐钢笔',99.8,100),('11119','水果',10,21),('v000','会员卡',50,9999999);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `income` (
  `lid` char(30) NOT NULL,
  `cid` char(30) DEFAULT NULL,
  `s_pay` double NOT NULL,
  `r_pay` double NOT NULL,
  `vid` char(10) DEFAULT NULL,
  `ptime` datetime NOT NULL,
  PRIMARY KEY (`lid`),
  KEY `cid` (`cid`),
  KEY `vid` (`vid`),
  CONSTRAINT `income_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `user` (`cid`),
  CONSTRAINT `income_ibfk_2` FOREIGN KEY (`vid`) REFERENCES `vip` (`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income`
--

LOCK TABLES `income` WRITE;
/*!40000 ALTER TABLE `income` DISABLE KEYS */;
INSERT INTO `income` VALUES ('b20210622183707175','cashier1',6010,5409,'112','2021-06-22 18:37:42'),('b20210622225809477','cashier2',9,9,NULL,'2021-06-22 22:58:20'),('b20210622230942387','cashier2',4,4,NULL,'2021-06-22 23:09:48'),('b20210622231253285','cashier2',5,5,NULL,'2021-06-22 23:12:59'),('b20210622231412503','cashier2',4,4,NULL,'2021-06-22 23:14:16'),('b20210622231914427','cashier2',12,12,NULL,'2021-06-22 23:19:30'),('b20210622231941831','cashier2',18,18,NULL,'2021-06-22 23:20:03'),('b20210624231647550','cashier1',4,4,NULL,'2021-06-24 23:17:20'),('b20210628154427216','cashier1',8,8,NULL,'2021-06-28 15:45:10'),('b20210628154728154','cashier1',7,7,NULL,'2021-06-28 15:47:36'),('b20210628155334466','cashier1',4,4,NULL,'2021-06-28 15:53:39'),('b20210628155640368','cashier1',4,4,NULL,'2021-06-28 15:56:44'),('b20210628160053405','cashier1',4,4,NULL,'2021-06-28 16:00:57'),('b20210628160414354','cashier1',4,4,NULL,'2021-06-28 16:04:24'),('b20210628160500799','cashier1',4,4,NULL,'2021-06-28 16:05:05'),('b20210628160747332','cashier1',15,15,NULL,'2021-06-28 16:07:52'),('b20210628160823408','cashier1',10,10,NULL,'2021-06-28 16:08:29'),('b20210628160922563','cashier1',4,3.6,NULL,'2021-06-28 16:09:48'),('b20210628161406163','cashier1',4,3.6,NULL,'2021-06-28 16:14:23'),('b20210628161540764','cashier1',4,3.6,NULL,'2021-06-28 16:15:48'),('b20210628170916012','cashier1',50,50,NULL,'2021-06-28 17:09:15'),('b20210628171046884','cashier1',50,50,NULL,'2021-06-28 17:10:46'),('b20210628174718314','cashier1',50,50,NULL,'2021-06-28 17:47:18'),('b20210628175207913','cashier2',6010,6010,NULL,'2021-06-28 17:52:17'),('b20210628210858051','cashier1',4,3.6,NULL,'2021-06-28 21:09:33'),('b20210628210937791','cashier1',6024,6024,NULL,'2021-06-28 21:10:30'),('b20210629135004562','cashier1',999.9,899.91,'113','2021-06-29 13:50:37'),('b20210629184742580','cashier3',3016.7,2715.0299999999997,'111','2021-06-29 19:17:38'),('b20210629185622035','cashier3',50,50,NULL,'2021-06-29 18:56:21'),('b20210629191953011','cashier3',26.5,23.85,'113','2021-06-29 19:20:40'),('b20210629192044310','cashier3',3009.7,2708.73,'113','2021-06-29 19:21:34'),('b20210629192138797','cashier3',1009.9,1009.9,NULL,'2021-06-29 19:25:50'),('b20210630153942617','cashier3',6014,5412.6,'111','2021-06-30 15:40:44'),('b20210630154047190','cashier3',5,5,NULL,'2021-06-30 15:40:57'),('b20210630154058721','cashier3',3005,3005,NULL,'2021-06-30 15:41:27'),('b20210630154111604','cashier3',50,50,NULL,'2021-06-30 15:41:11');
/*!40000 ALTER TABLE `income` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `t1` AFTER INSERT ON `income` FOR EACH ROW begin
    update vip set amount = amount + new.r_pay
    where vip.vid = new.vid;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `sells`
--

DROP TABLE IF EXISTS `sells`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sells` (
  `gid` char(30) NOT NULL,
  `lid` char(30) NOT NULL,
  `buy_num` int NOT NULL,
  PRIMARY KEY (`gid`,`lid`),
  KEY `lid` (`lid`),
  CONSTRAINT `sells_ibfk_1` FOREIGN KEY (`gid`) REFERENCES `goods` (`gid`),
  CONSTRAINT `sells_ibfk_2` FOREIGN KEY (`lid`) REFERENCES `income` (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sells`
--

LOCK TABLES `sells` WRITE;
/*!40000 ALTER TABLE `sells` DISABLE KEYS */;
INSERT INTO `sells` VALUES ('11111','b20210622225809477',2),('11111','b20210622230942387',2),('11111','b20210622231412503',2),('11111','b20210622231914427',1),('11111','b20210622231941831',4),('11111','b20210624231647550',2),('11111','b20210628154427216',4),('11111','b20210628154728154',1),('11111','b20210628155334466',2),('11111','b20210628155640368',2),('11111','b20210628160053405',2),('11111','b20210628160414354',2),('11111','b20210628160500799',2),('11111','b20210628160922563',2),('11111','b20210628161406163',2),('11111','b20210628161540764',2),('11111','b20210628210858051',2),('11111','b20210628210937791',2),('11111','b20210629184742580',3),('11111','b20210629191953011',2),('11111','b20210629192044310',2),('11111','b20210629192138797',2),('11111','b20210630153942617',2),('11112','b20210622225809477',1),('11112','b20210622231253285',1),('11112','b20210622231914427',2),('11112','b20210622231941831',2),('11112','b20210628154728154',1),('11112','b20210628160747332',3),('11112','b20210628160823408',2),('11112','b20210628210937791',2),('11112','b20210629184742580',1),('11112','b20210630154047190',1),('11113','b20210622183707175',2),('11113','b20210628175207913',2),('11113','b20210628210937791',2),('11113','b20210630153942617',2),('11113','b20210630154058721',1),('11114','b20210629184742580',2),('11114','b20210629191953011',2),('11114','b20210629192044310',2),('11114','b20210629192138797',2),('11115','b20210629191953011',3),('11116','b20210629135004562',1),('11116','b20210629184742580',3),('11116','b20210629192044310',3),('11116','b20210629192138797',1),('v000','b20210628170916012',1),('v000','b20210628171046884',1),('v000','b20210628174718314',1),('v000','b20210629185622035',1),('v000','b20210630154111604',1);
/*!40000 ALTER TABLE `sells` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `cid` char(30) NOT NULL,
  `pwd` char(30) NOT NULL,
  `cname` char(30) NOT NULL,
  `gender` char(2) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `flag` int NOT NULL,
  PRIMARY KEY (`cid`),
  CONSTRAINT `user_chk_1` CHECK ((`gender` in (_utf8mb4'男',_utf8mb4'女'))),
  CONSTRAINT `user_chk_2` CHECK (((`flag` = 1) or (`flag` = 2)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','123','王五','女',NULL,2),('cashier1','123','王五',NULL,NULL,1),('cashier2','123','李四','男',45,1),('cashier3','124','李六',NULL,NULL,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip`
--

DROP TABLE IF EXISTS `vip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vip` (
  `vid` char(30) NOT NULL,
  `vname` char(30) DEFAULT NULL,
  `telephone` char(11) NOT NULL,
  `etime` datetime NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip`
--

LOCK TABLES `vip` WRITE;
/*!40000 ALTER TABLE `vip` DISABLE KEYS */;
INSERT INTO `vip` VALUES ('111',NULL,'12345678','2022-06-14 23:59:59',12512.83),('112','李山','1234568','2022-04-05 23:59:59',110.79999999999998),('113',NULL,'12343','2022-05-23 17:34:23',3732.58),('114',NULL,'1233454','2022-05-20 12:34:56',3000),('v20210622183724853','lisa','11234','2022-06-22 18:37:24',0),('v20210628170915923','lisa','12345','2022-06-28 17:09:15',0),('v20210628171046779','mary','123455','2022-06-28 17:10:46',0),('v20210628211102155','lyh','12334','2022-06-28 21:11:02',0),('v20210629185621904','xiayuan','17673898837','2022-06-29 18:56:21',0),('v20210629192659356','lsz','113234','2022-06-29 19:26:59',0),('v20210630154111526','123123','123','2022-06-30 15:41:11',0),('v20210630154140315','111','22','2022-06-30 15:41:40',0);
/*!40000 ALTER TABLE `vip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `cashsystem`
--

USE `cashsystem`;

--
-- Final view structure for view `bill`
--

/*!50001 DROP VIEW IF EXISTS `bill`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `bill` (`lid`,`cid`,`gid`,`buy_num`,`price`,`ptime`) AS select `income`.`lid` AS `lid`,`income`.`cid` AS `cid`,`sells`.`gid` AS `gid`,`sells`.`buy_num` AS `buy_num`,`goods`.`price` AS `price`,`income`.`ptime` AS `ptime` from ((`income` join `goods`) join `sells`) where ((`income`.`lid` = `sells`.`lid`) and (`sells`.`gid` = `goods`.`gid`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-07 16:12:20
