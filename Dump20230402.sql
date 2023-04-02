CREATE DATABASE  IF NOT EXISTS `myshop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `myshop`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: myshop
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `trade_coupon`
--

DROP TABLE IF EXISTS `trade_coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_coupon` (
  `coupon_id` bigint NOT NULL,
  `coupon_price` decimal(10,2) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `is_used` int DEFAULT NULL,
  `used_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_coupon`
--

LOCK TABLES `trade_coupon` WRITE;
/*!40000 ALTER TABLE `trade_coupon` DISABLE KEYS */;
INSERT INTO `trade_coupon` VALUES (1,20.00,2,839836658445717504,1,'2023-04-02 01:33:40'),(2,20.00,2,839840213214826496,1,'2023-04-02 01:46:16'),(3,20.00,2,839841661851930624,1,'2023-04-02 01:52:03'),(4,20.00,2,839851147635331072,1,'2023-04-02 02:29:43');
/*!40000 ALTER TABLE `trade_coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_goods`
--

DROP TABLE IF EXISTS `trade_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_goods` (
  `goods_id` bigint NOT NULL,
  `goods_name` varchar(255) DEFAULT NULL,
  `goods_number` int DEFAULT NULL,
  `goods_price` decimal(10,2) DEFAULT NULL,
  `goods_desc` varchar(255) DEFAULT NULL,
  `add_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_goods`
--

LOCK TABLES `trade_goods` WRITE;
/*!40000 ALTER TABLE `trade_goods` DISABLE KEYS */;
INSERT INTO `trade_goods` VALUES (1,'MacBook Pro',96,8999.00,'The ultimate pro notebook','2023-03-30 20:47:00'),(2,'iPhone 13 Pro Max',50,6999.00,'The most advanced iPhone ever','2023-03-30 20:48:00'),(3,'iPad Air',70,3999.00,'Powerful. Colorful. Wonderful.','2023-03-30 20:49:00'),(4,'Samsung Galaxy S21 Ultra',90,5999.00,'The ultimate smartphone experience','2023-03-30 20:50:00'),(5,'Dell XPS 13',80,7999.00,'The smallest 13-inch laptop on the planet','2023-03-30 20:51:00'),(6,'LG UltraFine Display',30,2399.00,'A display as true to your vision as your imagination','2023-03-30 20:52:00'),(7,'Sony WH-1000XM4 Wireless Headphones',120,1999.00,'Industry-leading noise cancelation','2023-03-30 20:53:00'),(8,'Nintendo Switch',60,3299.00,'Play your way with Nintendo Switch','2023-03-30 20:54:00'),(9,'Bose SoundLink Revolve+ Portable Bluetooth Speaker',40,1499.00,'Deep. Loud. And immersive','2023-03-30 20:55:00'),(10,'Canon EOS R5 Full-Frame Mirrorless Camera',20,29999.00,'Raise your photography to the next level','2023-03-30 20:56:00');
/*!40000 ALTER TABLE `trade_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_goods_number_log`
--

DROP TABLE IF EXISTS `trade_goods_number_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_goods_number_log` (
  `goods_id` int NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `goods_number` int DEFAULT NULL,
  `log_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_goods_number_log`
--

LOCK TABLES `trade_goods_number_log` WRITE;
/*!40000 ALTER TABLE `trade_goods_number_log` DISABLE KEYS */;
INSERT INTO `trade_goods_number_log` VALUES (1,'839836658445717504',-1,'2023-04-02 09:33:39'),(1,'839840213214826496',-1,'2023-04-02 09:46:16'),(1,'839841661851930624',-1,'2023-04-02 09:52:02'),(1,'839850475502309376',-1,'2023-04-02 10:27:04'),(1,'839851147635331072',-1,'2023-04-02 10:29:43');
/*!40000 ALTER TABLE `trade_goods_number_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_mq_consumer_log`
--

DROP TABLE IF EXISTS `trade_mq_consumer_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_mq_consumer_log` (
  `msg_id` varchar(50) DEFAULT NULL,
  `group_name` varchar(100) NOT NULL,
  `msg_tag` varchar(100) NOT NULL,
  `msg_key` varchar(100) NOT NULL,
  `msg_body` varchar(500) DEFAULT NULL,
  `consumer_status` int DEFAULT NULL,
  `consumer_times` int DEFAULT NULL,
  `consumer_timestamp` timestamp NULL DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_mq_consumer_log`
--

LOCK TABLES `trade_mq_consumer_log` WRITE;
/*!40000 ALTER TABLE `trade_mq_consumer_log` DISABLE KEYS */;
INSERT INTO `trade_mq_consumer_log` VALUES ('0A04D2FE111818B4AAC207649D590000','order_orderTopic_cancel_group','order_cancel','839850475502309376','{\"couponId\":4,\"goodsId\":1,\"goodsNum\":1,\"orderId\":839850475502309376,\"userId\":2,\"userMoney\":100}',1,0,'2023-04-02 02:27:15',NULL),('AC11000200016B884D5707261C840006','order_orderTopic_cancel_group','tag','key','messageBody',0,1,NULL,NULL);
/*!40000 ALTER TABLE `trade_mq_consumer_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_mq_producer_temp`
--

DROP TABLE IF EXISTS `trade_mq_producer_temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_mq_producer_temp` (
  `id` varchar(100) NOT NULL,
  `group_name` varchar(100) DEFAULT NULL,
  `msg_topic` varchar(100) DEFAULT NULL,
  `msg_tag` varchar(100) DEFAULT NULL,
  `msg_key` varchar(100) DEFAULT NULL,
  `msg_body` varchar(500) DEFAULT NULL,
  `msg_status` int DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_mq_producer_temp`
--

LOCK TABLES `trade_mq_producer_temp` WRITE;
/*!40000 ALTER TABLE `trade_mq_producer_temp` DISABLE KEYS */;
INSERT INTO `trade_mq_producer_temp` VALUES ('839945995272986624','payProducerGroup','payTopic','paid','839943432024760320','{\"isPaid\":2,\"orderId\":352537369385242624,\"payId\":839943432024760320}',NULL,'2023-04-02 08:46:43'),('839945995277180928','payProducerGroup','payTopic','paid','839943432024760320','{\"isPaid\":2,\"orderId\":352537369385242624,\"payId\":839943432024760320}',NULL,'2023-04-02 08:46:43'),('839946010875797504','payProducerGroup','payTopic','paid','839943432024760320','{\"isPaid\":2,\"orderId\":352537369385242624,\"payId\":839943432024760320}',NULL,'2023-04-02 08:46:44');
/*!40000 ALTER TABLE `trade_mq_producer_temp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_order`
--

DROP TABLE IF EXISTS `trade_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_order` (
  `order_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `order_status` int DEFAULT NULL,
  `pay_status` int DEFAULT NULL,
  `shipping_status` int DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `consignee` varchar(255) DEFAULT NULL,
  `goods_id` bigint DEFAULT NULL,
  `goods_number` int DEFAULT NULL,
  `goods_price` decimal(10,2) DEFAULT NULL,
  `goods_amount` decimal(10,0) DEFAULT NULL,
  `shipping_fee` decimal(10,2) DEFAULT NULL,
  `order_amount` decimal(10,2) DEFAULT NULL,
  `coupon_id` bigint DEFAULT NULL,
  `coupon_paid` decimal(10,2) DEFAULT NULL,
  `money_paid` decimal(10,2) DEFAULT NULL,
  `pay_amount` decimal(10,2) DEFAULT NULL,
  `add_time` timestamp NULL DEFAULT NULL,
  `confirm_time` timestamp NULL DEFAULT NULL,
  `pay_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_order`
--

LOCK TABLES `trade_order` WRITE;
/*!40000 ALTER TABLE `trade_order` DISABLE KEYS */;
INSERT INTO `trade_order` VALUES (1,1,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(352537369385242624,2,1,2,NULL,'北京',NULL,1,1,8999.00,NULL,0.00,8999.00,4,20.00,100.00,8879.00,'2023-04-02 02:29:43',NULL,NULL),(839836658445717504,2,0,NULL,NULL,'北京',NULL,1,1,8999.00,NULL,0.00,8999.00,1,20.00,100.00,8879.00,'2023-04-02 01:33:32',NULL,NULL),(839840213214826496,2,0,NULL,NULL,'北京',NULL,1,1,8999.00,NULL,0.00,8999.00,2,20.00,100.00,8879.00,'2023-04-02 01:46:16',NULL,NULL),(839841661851930624,2,0,NULL,NULL,'北京',NULL,1,1,8999.00,NULL,0.00,8999.00,3,20.00,100.00,8879.00,'2023-04-02 01:52:01',NULL,NULL),(839850475502309376,2,0,NULL,NULL,'北京',NULL,1,1,8999.00,NULL,0.00,8999.00,4,20.00,100.00,8879.00,'2023-04-02 02:27:03',NULL,NULL),(839851147635331072,2,1,0,NULL,'北京',NULL,1,1,8999.00,NULL,0.00,8999.00,4,20.00,100.00,8879.00,'2023-04-02 02:29:43','2023-04-02 02:29:43',NULL);
/*!40000 ALTER TABLE `trade_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_pay`
--

DROP TABLE IF EXISTS `trade_pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_pay` (
  `pay_id` bigint NOT NULL,
  `order_id` bigint DEFAULT NULL,
  `pay_amount` decimal(10,2) DEFAULT NULL,
  `is_paid` int DEFAULT NULL,
  PRIMARY KEY (`pay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_pay`
--

LOCK TABLES `trade_pay` WRITE;
/*!40000 ALTER TABLE `trade_pay` DISABLE KEYS */;
INSERT INTO `trade_pay` VALUES (839943432024760320,352537369385242624,880.00,2);
/*!40000 ALTER TABLE `trade_pay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_user`
--

DROP TABLE IF EXISTS `trade_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_user` (
  `user_id` bigint NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_mobile` varchar(255) DEFAULT NULL,
  `user_score` int DEFAULT NULL,
  `user_reg_time` timestamp NULL DEFAULT NULL,
  `user_money` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_user`
--

LOCK TABLES `trade_user` WRITE;
/*!40000 ALTER TABLE `trade_user` DISABLE KEYS */;
INSERT INTO `trade_user` VALUES (1,'张三','123456','13800000001',100,'2023-03-30 21:00:00',100000),(2,'李四','123456','13800000002',90,'2023-03-30 21:01:00',49600),(3,'王五','123456','13800000003',80,'2023-03-30 21:02:00',30000),(4,'赵六','123456','13800000004',70,'2023-03-30 21:03:00',20000),(5,'孙七','123456','13800000005',60,'2023-03-30 21:04:00',15000),(6,'钱八','123456','13800000006',50,'2023-03-30 21:05:00',10000),(7,'周九','123456','13800000007',40,'2023-03-30 21:06:00',8000),(8,'吴十','123456','13800000008',30,'2023-03-30 21:07:00',6000),(9,'陈华','123456','13800000009',20,'2023-03-30 21:08:00',4000),(10,'林梦','123456','13800000010',10,'2023-03-30 21:09:00',2000);
/*!40000 ALTER TABLE `trade_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade_user_money_log`
--

DROP TABLE IF EXISTS `trade_user_money_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade_user_money_log` (
  `user_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  `money_log_type` int NOT NULL,
  `use_money` decimal(10,2) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade_user_money_log`
--

LOCK TABLES `trade_user_money_log` WRITE;
/*!40000 ALTER TABLE `trade_user_money_log` DISABLE KEYS */;
INSERT INTO `trade_user_money_log` VALUES (2,839836658445717504,1,100.00,'2023-04-02 01:33:43'),(2,839840213214826496,1,100.00,'2023-04-02 01:46:16'),(2,839841661851930624,1,100.00,'2023-04-02 01:52:04'),(2,839850475502309376,1,100.00,'2023-04-02 02:27:06'),(2,839850475502309376,2,100.00,'2023-04-02 02:27:15'),(2,839851147635331072,1,100.00,'2023-04-02 02:29:43');
/*!40000 ALTER TABLE `trade_user_money_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-02 17:28:17
