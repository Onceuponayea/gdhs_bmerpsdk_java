-- MySQL dump 10.13  Distrib 8.0.30, for Linux (x86_64)
--
-- Host: localhost    Database: banmaerp
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `bmerp_account`
--

DROP TABLE IF EXISTS `bmerp_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_account` (
  `id` bigint NOT NULL COMMENT '用户id',
  `real_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT ' 用户名称',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT ' 用户邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户手机号',
  `department` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '部门',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `state` varchar(100) DEFAULT NULL COMMENT '账户状态',
  `userType` varchar(100) DEFAULT NULL COMMENT '账户类型',
  `banma_master_app_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `banma_data_access_id` varchar(255) DEFAULT NULL COMMENT '账号权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_account`
--

LOCK TABLES `bmerp_account` WRITE;
/*!40000 ALTER TABLE `bmerp_account` DISABLE KEYS */;
INSERT INTO `bmerp_account` VALUES (12345,'test','a@b.com','1500000000','',NULL,NULL,'正常','MasterAccount','1371383319572253423',NULL),(26455,'15060715230','eric.linjialong@gmail.com','15060715230','','2022-10-27 15:21:37','2022-11-10 16:46:41','正常','MasterAccount',NULL,NULL),(26679,'谢晓斌','','13696919609','','2022-11-10 16:44:16','2022-12-20 08:04:06','正常','SubAccount',NULL,NULL),(27213,'小蒋4','13122119606@qq.com','13221119606','采购部','2022-12-12 20:54:57','2022-12-20 11:38:17','正常','SubAccount',NULL,NULL),(27214,'小蒋3','17122919606@qq.com','17221919606','采购部','2022-12-12 20:58:36','2022-12-20 11:38:22','正常','SubAccount',NULL,NULL),(27268,'a13003988209',NULL,'13003988209',NULL,'2022-12-15 08:59:03','2022-12-15 08:59:03','正常','MasterAccount',NULL,NULL),(27349,'lian123','','18596945810','','2022-12-21 03:48:34','2022-12-21 03:48:34','Normal','MasterAccount','1507349451440668672',NULL),(27364,'hqx','','15985852996','','2022-12-22 03:54:51','2022-12-22 03:54:51','Normal','MasterAccount',NULL,NULL),(27407,'a18060575020','','18060575020','','2022-12-23 20:34:49','2022-12-23 20:34:49','Normal','MasterAccount','1508327460637261824',NULL),(27408,'a18065558891','','18065558891','','2022-12-23 20:51:53','2022-12-23 20:51:53','Normal','MasterAccount','1508331755721998336',NULL),(27416,'演示账号','','17353519723','','2022-12-24 02:16:41','2022-12-24 02:16:41','Normal','MasterAccount','1508413493005533184',NULL),(27574,'蒋志鑫','','13799812132','','2023-01-03 17:21:52','2023-01-03 17:21:52','正常','SubAccount',NULL,NULL),(27582,'磊少','','15260926996','','2023-01-04 10:53:36','2023-01-04 10:53:36','正常','SubAccount',NULL,NULL),(27600,'a18300568267','','18300568267','','2023-01-04 21:01:05','2023-01-04 21:01:05','正常','MasterAccount','1512803518950428672',NULL);
/*!40000 ALTER TABLE `bmerp_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_account_data_access`
--

DROP TABLE IF EXISTS `bmerp_account_data_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_account_data_access` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id',
  `data_access_mode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限模式',
  `data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '店铺id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `banma_account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_account_data_access`
--

LOCK TABLES `bmerp_account_data_access` WRITE;
/*!40000 ALTER TABLE `bmerp_account_data_access` DISABLE KEYS */;
INSERT INTO `bmerp_account_data_access` VALUES ('0402b50d-9be7-4386-8ea2-186423b34b88','2','1492814222537527296,1492814222604636160,1492814222633996288,1492814222659162112,1492814222734659584,1492814222759825408,1497119990426374144','2022-12-26 13:57:22','2022-12-28 20:41:34',26679),('0e3811f5-4c79-4cab-87f3-680862396955','2','1492814222604636160,1492814222734659584,1497119990426374144','2022-12-26 13:57:22','2022-12-26 18:14:17',27214),('5103da20-4fed-4588-8eb7-e280ba472ea7','2','1492814222537527296,1492814222604636160,1497119990426374144','2022-12-26 13:57:22','2022-12-28 20:41:24',27213),('61d4fc2d-d6b7-4d76-9269-9ac45d8f6b0b','0','-1','2022-12-27 17:46:08','2022-12-28 10:22:08',27462),('771549db-de30-4f5e-9ebe-27414f4ab2b5','1','-1','2023-01-03 15:25:01',NULL,27268),('777d4a41-8bef-450b-acc6-c3fe540da5e1','0','-1','2023-01-03 15:25:04','2023-01-03 15:25:42',27552),('9b60f7cb-332d-4068-9527-ab6bef800cb7','2','1492814222659162112,1497119990426374144','2022-12-28 10:38:05','2022-12-30 15:56:35',27467),('b6eceb30-b09c-4e3e-9837-e4fd058388ef','0','-1','2022-12-27 17:30:36',NULL,27461),('b8b0bdeb-c99c-4c02-b7f1-98223c682bc4','2','1492814222659162112,1497119990426374144','2022-12-26 13:57:22','2022-12-28 20:41:44',27278),('c11c8d38-ac09-4003-8b63-42669e697068','0','-1','2022-12-27 17:29:07','2022-12-27 17:29:09',27460),('c11c8d38-ac09-4003-8b63-42669e697069','2','1492814222659162112,1497119990426374144','2023-01-03 17:22:42','2023-01-03 17:22:46',27574);
/*!40000 ALTER TABLE `bmerp_account_data_access` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_category`
--

DROP TABLE IF EXISTS `bmerp_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_category` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '类目id',
  `parent_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '类目父级id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '类目名称',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '类目描述',
  `code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '类目简码',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `banma_master_app_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_category`
--

LOCK TABLES `bmerp_category` WRITE;
/*!40000 ALTER TABLE `bmerp_category` DISABLE KEYS */;
INSERT INTO `bmerp_category` VALUES ('32781a0f-50bd-4fd5-b15a-af5500e69a33','00000000-0000-0000-0000-000000000000','男装','','N',10000,'2022-11-22 13:59:35','2022-11-23 14:36:33','1492055167686688768'),('43a0acba-367a-4f00-819d-af5600f0caa3','00000000-0000-0000-0000-000000000000','女装','','N',10001,'2022-11-23 14:36:41','2022-11-23 14:36:41','1492055167686688768'),('bd755fca-0c6f-4727-a39c-af4801414ca2','00000000-0000-0000-0000-000000000000','未分类','系统自动创建','',9999,'2022-11-09 19:29:48','2022-11-09 19:29:48','1492055167686688768');
/*!40000 ALTER TABLE `bmerp_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_order`
--

DROP TABLE IF EXISTS `bmerp_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order` (
  `order_UUID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `order_masterId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `banma_master_app_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `banma_account_id` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_order`
--

LOCK TABLES `bmerp_order` WRITE;
/*!40000 ALTER TABLE `bmerp_order` DISABLE KEYS */;
INSERT INTO `bmerp_order` VALUES ('54476144-d0cb-4cfb-9fae-69055c7c5703','1497122489925373952','1505374058785554432','26679-指定权限,27213-指定权限,27214-指定权限,27278-指定权限,27574-指定权限'),('58fab7a5-c1c5-419d-95a0-f7fe494ccb19','1502207479604322304','1505374058785554432','26679-指定权限,27213-指定权限,27214-指定权限,27278-指定权限,27574-指定权限'),('5cda3048-1410-42f9-a01e-45ed58c99732','1502207029920407552','1505374058785554432','26679-指定权限,27213-指定权限,27214-指定权限,27278-指定权限');
/*!40000 ALTER TABLE `bmerp_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_order_address`
--

DROP TABLE IF EXISTS `bmerp_order_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order_address` (
  `order_address_id` varchar(255) NOT NULL COMMENT '订单地址id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '姓名',
  `name_kana` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '收件人片假名',
  `country` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '国家',
  `country_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '国家代码',
  `province` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '省州',
  `city` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '城市',
  `county` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '县',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'email',
  `postal_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '邮编',
  `addr_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '地址类型',
  `address1` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '地址1',
  `address2` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '地址2',
  `address3` varchar(255) DEFAULT NULL COMMENT '地址3',
  `is_original` bit(1) DEFAULT NULL COMMENT '是否是原始值',
  `tax_no` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '税号',
  `order_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`order_address_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_order_address`
--

LOCK TABLES `bmerp_order_address` WRITE;
/*!40000 ALTER TABLE `bmerp_order_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `bmerp_order_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_order_details`
--

DROP TABLE IF EXISTS `bmerp_order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order_details` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '订单明显id',
  `package_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '包裹id',
  `original_detail_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '平台上的订单明细id',
  `original_sku` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '平台上的sku',
  `spu_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '产品spuid',
  `sku_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '产品skuid',
  `sku_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'sku类型',
  `sku_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '本地skucode',
  `sku_specification` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '订单上的原始规格',
  `price` decimal(10,2) DEFAULT NULL COMMENT '产品单价',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '产品标题',
  `original_image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '产品图片',
  `quantity` int DEFAULT NULL COMMENT '购买数量',
  `original_quantity` int DEFAULT NULL COMMENT '购买数量(订单上写的数量,不能人为修改)',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '子单总金额',
  `status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '订单状态',
  `price_currency` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '计价币种',
  `properties` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '特殊属性，定制类产品的定制信息[json]',
  `original_product_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '平台上的产品id',
  `original_sku_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '平台上的skuid',
  `warehouse_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '库房id',
  `inventory_status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '库存状态',
  `inventory_lock_quantity` int DEFAULT NULL COMMENT '锁定库存的数量',
  `sku_change_status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '包裹变更状态',
  `original_specification` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'sku对应的规则值',
  `sku_image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'sku对应的图片',
  `sort` int DEFAULT NULL COMMENT '排序',
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '产品类型',
  `product_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '产品url',
  `quantity_status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '产品数量是状态',
  `relation_detail_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '关联订单明细id',
  `delivery_instructions` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '	发货说明，如：配送说明，配送时间等',
  `priority` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '优先级（值越大优先级越高,默认 0）',
  `order_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '订单id',
  `original_order_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_order_details`
--

LOCK TABLES `bmerp_order_details` WRITE;
/*!40000 ALTER TABLE `bmerp_order_details` DISABLE KEYS */;
INSERT INTO `bmerp_order_details` VALUES ('1831fc08-918a-47b7-9fc3-af6300fb27a2','0','1502207029928796160','1497490072574889984','1497121263854817280','1497490072574889984','普通','22112200001-White-One-Size','White;One Size',112.00,'重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,1,112.00,'待审核','CNY','','','','00000000-0000-0000-0000-000000000000','未知',0,'未变更','White;One Size','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,'普通','','原始','00000000-0000-0000-0000-000000000000','',00000000000,'54476144-d0cb-4cfb-9fae-69055c7c5703',NULL),('2f0de1b8-907e-4a06-a576-af6300fba55b','1507227076913012736','1502207479675625472','1497488584339361793','1497488584330973184','1497488584339361793','普通','N-22112300001-White-One-Size','White;One Size',111.00,'踏进银河 日系软糯毛茸茸外套小个子甜美加厚保暖羊羔绒棉服秋冬','https://gd4.alicdn.com/imgextra/i1/2601139430/O1CN01xn0Jro2JWzgjULs67_!!2601139430.jpg_400x400.jpg',1,1,111.00,'待发货','CNY','','','','0eeedc05-91aa-4aa5-b5db-af3b00fd2205','未知',0,'未变更','White;One Size','https://gd4.alicdn.com/imgextra/i1/2601139430/O1CN01xn0Jro2JWzgjULs67_!!2601139430.jpg_400x400.jpg',2,'普通','','原始','00000000-0000-0000-0000-000000000000','',00000000000,'54476144-d0cb-4cfb-9fae-69055c7c5703',NULL),('e112b802-8c6d-4953-b32a-af5500ef0793','1497122579410849792','1497122489946345472','1497121263867400193','1497121263854817280','1497121263867400193','普通','22112200001-白色-175','白色;175',11.00,'重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,1,11.00,'待发货','CNY','','','','0eeedc05-91aa-4aa5-b5db-af3b00fd2205','未知',0,'未变更','白色;175','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,'普通','','原始','00000000-0000-0000-0000-000000000000','',00000000000,'58fab7a5-c1c5-419d-95a0-f7fe494ccb19',NULL),('e9578d60-fe02-4abb-a77e-af6300fba55b','1507227076913012736','1502207479637876736','1497490072574889984','1497121263854817280','1497490072574889984','普通','22112200001-White-One-Size','White;One Size',112.00,'重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,1,112.00,'待发货','CNY','','','','0eeedc05-91aa-4aa5-b5db-af3b00fd2205','未知',0,'未变更','White;One Size','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,'普通','','原始','00000000-0000-0000-0000-000000000000','',00000000000,'5cda3048-1410-42f9-a01e-45ed58c99732',NULL);
/*!40000 ALTER TABLE `bmerp_order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_order_details_inventorydata`
--

DROP TABLE IF EXISTS `bmerp_order_details_inventorydata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order_details_inventorydata` (
  `order_inventoryData_id` varchar(255) NOT NULL COMMENT 'id',
  `sku_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'skuid',
  `sku_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'skucode',
  `shortage_quantity` int DEFAULT NULL COMMENT '缺货数量',
  `lock_quantity` int DEFAULT NULL COMMENT '锁定库存数量',
  `spu_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'spuid',
  `detail_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '订单详情id',
  PRIMARY KEY (`order_inventoryData_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_order_details_inventorydata`
--

LOCK TABLES `bmerp_order_details_inventorydata` WRITE;
/*!40000 ALTER TABLE `bmerp_order_details_inventorydata` DISABLE KEYS */;
/*!40000 ALTER TABLE `bmerp_order_details_inventorydata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_order_fulfillment`
--

DROP TABLE IF EXISTS `bmerp_order_fulfillment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order_fulfillment` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id',
  `express_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流公司',
  `express_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流单号',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间，可能为null',
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单id',
  `banma_master_app_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_order_fulfillment`
--

LOCK TABLES `bmerp_order_fulfillment` WRITE;
/*!40000 ALTER TABLE `bmerp_order_fulfillment` DISABLE KEYS */;
/*!40000 ALTER TABLE `bmerp_order_fulfillment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_order_master`
--

DROP TABLE IF EXISTS `bmerp_order_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order_master` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '订单id',
  `store_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '店铺id',
  `platform` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '平台',
  `original_order_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '各平台订单数据查询用ID',
  `display_order_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '各平台订单页面展示的ID',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `pay_channel` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '付款通道',
  `pay_amount` decimal(11,2) DEFAULT NULL COMMENT '付款原始金额',
  `pay_currency` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '付款币种',
  `pay_amount_usd` decimal(11,2) DEFAULT NULL COMMENT '美元付款金额',
  `pay_amount_cny` decimal(11,2) DEFAULT NULL COMMENT '人民币付款金额',
  `pay_freight` decimal(11,2) DEFAULT NULL COMMENT '运费',
  `pay_freight_currency` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '运费币种',
  `pay_freight_usd` decimal(11,2) DEFAULT NULL COMMENT '美元运费',
  `pay_freight_cny` decimal(11,2) DEFAULT NULL COMMENT '人民币运费',
  `refund_amount` decimal(11,2) DEFAULT NULL COMMENT '原始退款金额',
  `refund_currency` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '原始退款币种',
  `refund_amount_usd` decimal(11,2) DEFAULT NULL COMMENT '美元退款金额',
  `refund_amount_cny` decimal(11,2) DEFAULT NULL COMMENT '人民币退款金额',
  `discount_amount` decimal(11,2) DEFAULT NULL COMMENT '折扣金额',
  `discount_currency` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '折扣币种',
  `status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '订单状态',
  `pay_status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '订单支付状态',
  `shipping_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户指定快递方式',
  `country_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '国家代码',
  `hold_status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '搁置状态',
  `refund_status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '退款状态',
  `quantity` int DEFAULT NULL COMMENT '购买总数量',
  `purchase_freight` decimal(11,2) DEFAULT NULL COMMENT '采购运费',
  `user_indentity` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户唯一标识（平台用户ID、邮箱、手机号等）',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `tags` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '订单标签',
  `original_order_status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '原始订单状态',
  `original_order_time` datetime DEFAULT NULL COMMENT '原始下单时间',
  `original_pay_time` datetime DEFAULT NULL COMMENT '原始付款时间',
  `have_message` bit(1) DEFAULT NULL COMMENT '是否有留言',
  `have_remark` bit(1) DEFAULT NULL COMMENT '是否有备注',
  `latest_delivery_time` datetime DEFAULT NULL COMMENT '最晚发货时间(预计交货时间,超过这个可能会被平台罚款)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_cod` bit(1) DEFAULT NULL COMMENT '是否货到付款(COD)',
  `flags` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '订单标记（系统内置），和标签不同',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '客户邮箱（下单邮箱）',
  `inventory_status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '库存状态',
  `inventory_mode` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '库存模式',
  `tx_no` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '交易号',
  `buyer_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '买家名',
  `message` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '买家留言',
  `shipping_time` datetime DEFAULT NULL COMMENT '真实发货时间',
  `original_shipping_time` datetime DEFAULT NULL COMMENT '原始下单时间',
  `original_tags` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '平台标签',
  `buyer_phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '买家电话',
  `requested_delivery_date` date DEFAULT NULL COMMENT '要求交货日期/预计交货日期/期望交货日期',
  `requested_delivery_time` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '要求交货时间/预计交货时间/期望交货时间时间段，如 10:00-16:00,上午/午前=06:00-12:00,下午/午后=12:00-18:00,夜间=18:00-21:00\r\nRiskLevel	enum	风险等级,具体值参见:RiskLevel\r\nUsedPoint	decimal	积分\r\n要求交货时间/预计交货时间/期望交货时间时间段，如 10:00-16:00,上午/午前=06:00-12:00,下午/午后=12:00-18:00,夜间=18:00-21:00\r\nRiskLevel	enum	风险等级,具体值参见:RiskLevel\r\nUsedPoint	decimal	积分\r\n要求交货时间/预计交货时间/期望交货时间时间段，如 10:00-16:00,上午/午前=06:00-12:00,下午/午后=12:00-18:00,夜间=18:00-21:00',
  `risk_level` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '风险等级',
  `used_point` int DEFAULT NULL COMMENT '积分',
  `pay_type` int DEFAULT NULL,
  `order_uuid` varchar(255) DEFAULT NULL,
  `banma_master_app_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_order_master`
--

LOCK TABLES `bmerp_order_master` WRITE;
/*!40000 ALTER TABLE `bmerp_order_master` DISABLE KEYS */;
INSERT INTO `bmerp_order_master` VALUES ('1497122489925373952','1497119990426374144','26','20221122142700000001','20221122142700000001','2022-12-30 07:30:16','',11.00,'CNY',1.54,11.00,0.00,'CNY',0.00,0.00,0.00,'CNY',0.00,0.00,0.00,'CNY','待发货','已付款','','CN','未暂停','无退款',1,0.00,'','2022-11-22 14:30:16','','','2022-11-22 14:30:16','2022-11-22 14:30:16',_binary '\0',_binary '\0',NULL,'2022-11-22 14:30:16','2022-11-22 14:30:38',_binary '\0','无','','未知','无库存发货','','','',NULL,NULL,'','',NULL,'','未知',NULL,NULL,'54476144-d0cb-4cfb-9fae-69055c7c5703','1505374058785554432'),('1502207029920407552','1497119990426374144','26','20221122142700000005','20221122142700000005','2022-12-30 08:14:25','',112.00,'CNY',15.97,112.00,0.00,'CNY',0.00,0.00,0.00,'CNY',0.00,0.00,0.00,'CNY','待审核','已付款','','CN','未暂停','无退款',1,0.00,'','2022-12-06 15:14:25','','','2022-12-06 15:14:25','2022-12-06 15:14:25',_binary '\0',_binary '\0',NULL,'2022-12-06 15:14:25','2022-12-06 15:14:25',_binary '\0','无','','未知','无库存发货','','小蒋','',NULL,NULL,'','',NULL,'','未知',NULL,NULL,'5cda3048-1410-42f9-a01e-45ed58c99732','1505374058785554432'),('1502207479604322304','1497119990426374144','26','20221122142700000006','20221122142700000006','2022-12-29 07:16:12','',223.00,'CNY',31.80,223.00,0.00,'CNY',0.00,0.00,0.00,'CNY',0.00,0.00,0.00,'CNY','待发货','已付款','','CN','未暂停','无退款',1,0.00,'','2022-12-06 15:16:12','','','2022-12-06 15:16:12','2022-12-06 15:16:12',_binary '\0',_binary '\0',NULL,'2022-12-06 15:16:12','2022-12-20 11:42:18',_binary '\0','无','','未知','无库存发货','','小蒋','',NULL,NULL,'','',NULL,'','未知',NULL,NULL,'58fab7a5-c1c5-419d-95a0-f7fe494ccb19','1505374058785554432');
/*!40000 ALTER TABLE `bmerp_order_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_order_refunds`
--

DROP TABLE IF EXISTS `bmerp_order_refunds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order_refunds` (
  `order_refund_id` int NOT NULL COMMENT '退款id',
  `detail_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '详情id',
  `original_refund_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '原始退款id',
  `refund_amount` decimal(10,2) DEFAULT NULL COMMENT '退款金额',
  `refund_currency` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '退款币种',
  `refund_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '退款类型',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `order_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '订单id',
  `banma_master_app_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_refund_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_order_refunds`
--

LOCK TABLES `bmerp_order_refunds` WRITE;
/*!40000 ALTER TABLE `bmerp_order_refunds` DISABLE KEYS */;
/*!40000 ALTER TABLE `bmerp_order_refunds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_order_remarks`
--

DROP TABLE IF EXISTS `bmerp_order_remarks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order_remarks` (
  `id` int NOT NULL COMMENT 'id',
  `category` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注类型',
  `content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '备注创建时间',
  `order_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_order_remarks`
--

LOCK TABLES `bmerp_order_remarks` WRITE;
/*!40000 ALTER TABLE `bmerp_order_remarks` DISABLE KEYS */;
/*!40000 ALTER TABLE `bmerp_order_remarks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_order_tracking`
--

DROP TABLE IF EXISTS `bmerp_order_tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order_tracking` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态',
  `express_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流单号',
  `receipted_time` datetime DEFAULT NULL COMMENT '签收时间，可以为null',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单id',
  `banma_master_app_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_order_tracking`
--

LOCK TABLES `bmerp_order_tracking` WRITE;
/*!40000 ALTER TABLE `bmerp_order_tracking` DISABLE KEYS */;
/*!40000 ALTER TABLE `bmerp_order_tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_order_tracking_info`
--

DROP TABLE IF EXISTS `bmerp_order_tracking_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order_tracking_info` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id',
  `date` datetime DEFAULT NULL COMMENT '时间',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述信息',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '位置信息，可能为空',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原始状态',
  `track_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流跟踪id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_order_tracking_info`
--

LOCK TABLES `bmerp_order_tracking_info` WRITE;
/*!40000 ALTER TABLE `bmerp_order_tracking_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `bmerp_order_tracking_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product`
--

DROP TABLE IF EXISTS `bmerp_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product` (
  `product_spuid` bigint DEFAULT NULL,
  `product_DescriptionId` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `banma_master_app_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`product_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product`
--

LOCK TABLES `bmerp_product` WRITE;
/*!40000 ALTER TABLE `bmerp_product` DISABLE KEYS */;
INSERT INTO `bmerp_product` VALUES (1508324641523908608,'7f360e0d-1e48-40ba-8dd5-0bae6f1b5632','026fddd6-bdc1-4229-8873-d52d61308687',NULL),(1507731996833492992,'e1b53fc8-e6e3-4851-92ab-a873c92e0b8b','11092659-1be1-4b9f-880a-29d1e8919ab3',NULL),(1507311249686282240,'422b8854-9aff-4a02-a5b0-b08a125430cd','1a92cd65-0399-4f5a-8983-ea566d9b8a01','1492055167686688768'),(1507984514062766080,'fc1dbafe-1054-40ac-9650-487614aab1e4','230340f2-36b9-4f46-a01b-a129cd4eceb6','1505374058785554432'),(1509385894233128960,'29d572af-ed61-470a-8271-b7e1905756e3','2e618e43-a348-4bc6-b843-4202688a188c','1505374058785554432'),(1507984497713369088,'43c561be-3164-4b3c-98f6-8eea5fc26ff0','36218519-6a33-4a91-84ff-5809b5e42966','1505374058785554432'),(1508308246606135296,'7f1ae943-a392-430d-a612-2fae3edbadb8','3654b087-0ad9-4e94-a3df-e3269a3a635b','1507713422647631872'),(1508015327919755264,'7df9b411-a62e-4858-9e9e-46cc37b61318','38d6cbd7-ff5a-4f63-8a0a-f11d8291cf78','1505374058785554432'),(1507707914964443136,'3d5428b0-3356-485a-96e2-4d9a5f008779','3ba36bc3-f7e1-40d1-90d6-08c3e6cfeab4','1507668022771339264'),(1507984567175237632,'00a1e53f-77a2-46d1-a63d-2d1d181861a7','4f602538-e29a-4cad-98b7-8bcc1947afef','1505374058785554432'),(1506901405753954304,'dfcc79cf-6f8a-4f43-bf1f-070173f65179','5893ef24-cd82-4394-b515-64d0220efc42',NULL),(1508357887129305088,'b6600e3b-bd41-40e4-b63e-20ae242264bc','5b0aa73f-b81d-4c8b-bb27-66445da7e8da','1505374058785554432'),(1509368362973544448,'b2f27589-d7b2-4afb-a3be-d8a5950c450d','635b4f5a-a0b3-4f59-9b1f-4d8c5a4071c9','1505374058785554432'),(1507983655807504384,'1fa6ad89-a18d-44e1-b0ab-d6196bbc2289','6389d68d-692e-47ea-823e-79441a8219f3','1505374058785554432'),(1497121263854817280,'445ae638-3559-4566-904e-9ed1c6e8cb7e','6a2b7a80-9b92-4f0e-b357-7bc0bdc61674','1492055167686688768'),(1508052856094085120,'a1ddc631-6f50-43c7-b7fa-446eb1c82972','70e485d0-5486-4fd1-990c-733d55cee14f','1505374058785554432'),(1507984595339988992,'6df4db20-9158-489f-8534-8cdb488f89db','712c5a28-8883-4a86-be87-4f252440940d','1505374058785554432'),(1510110376157528064,'54382e23-fa66-4456-8f4c-eb6f2e14b6fa','77d3be60-3c7e-4fdd-811d-fc4f71da91be','1505374058785554432'),(1507984540797259776,'a0daa8ce-fff4-4fb8-8063-6027bf69a355','78fa6fba-442a-440e-90f3-1eef80d26088','1505374058785554432'),(1508357159421755392,'6b278443-1b52-4d40-9e9f-c9ced0d19047','79a10319-22e8-4d54-98fc-40050901b796',NULL),(1507984554768486400,'b0ac0392-769e-4002-9d9e-cec22f5df008','7f01a102-905c-45a4-8d24-eb296c7531c3','1505374058785554432'),(1509394201215778816,'4b69de9e-629f-437f-a6f1-deab6df2b2dd','818ff38d-da4e-4a25-99f2-41f75489c055','1507668022771339264'),(1513074488432939008,'50c982d1-491f-4913-9477-0fba040f6d16','89d97ed3-02ee-4ae9-b8f7-fc204c2768c6','1507713422647631872'),(1508347552242614272,'80b9e015-0c5f-4eae-b69b-052d9d820ea3','8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508301532284600320,'92a440b2-9b91-4307-a9f2-36cd8dc37f7b','9406894f-38da-4963-945f-a78b13b0d0de','1505374058785554432'),(1507632910440615936,'3f509e18-759e-4d44-87ea-0aa9b6d30370','9a84a128-8f1f-4663-8fa3-418070140d3e','1505374058785554432'),(1505519522298216448,'e52dbc35-f8e7-4879-945d-7842738be7d9','a6dcaa4c-ec97-438d-8086-6b58fe8b7408','1492055167686688768'),(1508302503995785216,'b85ae413-02ed-4db0-8527-2139c1667c2f','ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1507987721472200704,'f3fdb035-b8ed-4160-94ad-d85f7a754f1b','b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1510287472691527680,'6bd8d2e7-3b35-4210-866d-91ebb59c0757','ba3fe348-658f-448f-9dfd-5b8d621923d9','1507713422647631872'),(1506907941175840768,'b5b6b376-985f-4155-9296-030a77ff14d4','be41c8fe-635c-4e35-bcd3-19ea0710537e',NULL),(1508281328196403200,'99b29822-9994-4381-874c-95f36599fc0f','c0b12af1-02bc-4a23-801c-012ec6a03e5b','1505374058785554432'),(1507984528323399680,'950bcaea-250f-4ad5-b3cb-71995686badc','c54b474c-f1cf-4f0d-8e98-146ad37e4f32','1505374058785554432'),(1509369383091191808,'f37f3795-68fe-4f26-b6fa-c538333dbcb6','c80fef70-8d64-421e-b356-de41f92267fc','1505374058785554432'),(1506991192682807296,'e4d26a6e-c989-4622-a980-e2ae36ae20f9','ccb1f043-3c35-44da-bce5-a485e2d3f90c',NULL),(1507315547862220800,'9b5c94b2-0e32-41ed-8222-e4f07c31d6cf','d0ae7f88-d7f0-4ecf-85be-7c992fd18853','1492055167686688768'),(1507635872026087424,'f57a42c9-8bd0-41d0-a4e9-3564e02c8d95','e1c2e7c5-95de-4383-89b2-3a8703240e33','1505374058785554432'),(1512742158895960064,'408ebdac-b2e4-4e16-bec3-1d4f2b0a9794','e74728fe-58d8-4e13-aea3-55e3dd38062a','1505374058785554432'),(1509847824420847616,'d9728aaf-3831-4a72-8a83-4f4c1d312b94','ede814ef-ef91-42d1-aec5-06fd7500cb62','1505374058785554432'),(1507984578231422976,'41131cad-6c52-4113-828e-fd44a5a230ec','f2adbed1-5293-4ab5-9581-46ab91575f9c','1505374058785554432'),(1508408135809777664,'f23b6d6f-d673-4fbd-989f-2427d4981709','f52e31ae-0b03-46ca-bc67-ce864d19b4a6','1507713422647631872');
/*!40000 ALTER TABLE `bmerp_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product_descriptions`
--

DROP TABLE IF EXISTS `bmerp_product_descriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product_descriptions` (
  `pd_id` varchar(255) NOT NULL COMMENT '文本id',
  `html` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '富文本描述',
  `text` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '纯文本描述',
  `short` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '断描述',
  `product_id` varchar(255) DEFAULT NULL COMMENT '产品id',
  PRIMARY KEY (`pd_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product_descriptions`
--

LOCK TABLES `bmerp_product_descriptions` WRITE;
/*!40000 ALTER TABLE `bmerp_product_descriptions` DISABLE KEYS */;
INSERT INTO `bmerp_product_descriptions` VALUES ('00a1e53f-77a2-46d1-a63d-2d1d181861a7','','','','4f602538-e29a-4cad-98b7-8bcc1947afef'),('1fa6ad89-a18d-44e1-b0ab-d6196bbc2289','','','','6389d68d-692e-47ea-823e-79441a8219f3'),('29d572af-ed61-470a-8271-b7e1905756e3','','','','2e618e43-a348-4bc6-b843-4202688a188c'),('3d5428b0-3356-485a-96e2-4d9a5f008779','','','','3ba36bc3-f7e1-40d1-90d6-08c3e6cfeab4'),('3f509e18-759e-4d44-87ea-0aa9b6d30370','','','','9a84a128-8f1f-4663-8fa3-418070140d3e'),('408ebdac-b2e4-4e16-bec3-1d4f2b0a9794','','','','e74728fe-58d8-4e13-aea3-55e3dd38062a'),('41131cad-6c52-4113-828e-fd44a5a230ec','','','','f2adbed1-5293-4ab5-9581-46ab91575f9c'),('422b8854-9aff-4a02-a5b0-b08a125430cd','','','','1a92cd65-0399-4f5a-8983-ea566d9b8a01'),('43c561be-3164-4b3c-98f6-8eea5fc26ff0','','','','36218519-6a33-4a91-84ff-5809b5e42966'),('445ae638-3559-4566-904e-9ed1c6e8cb7e','<p><span style=\"font-family: &quot;PingFang SC&quot;; font-size: 20px; font-weight: 900; background-color: rgb(255, 255, 255);\">重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装</span></p>','','','6a2b7a80-9b92-4f0e-b357-7bc0bdc61674'),('4b69de9e-629f-437f-a6f1-deab6df2b2dd','','','','818ff38d-da4e-4a25-99f2-41f75489c055'),('50c982d1-491f-4913-9477-0fba040f6d16','','','','89d97ed3-02ee-4ae9-b8f7-fc204c2768c6'),('54382e23-fa66-4456-8f4c-eb6f2e14b6fa','','','','77d3be60-3c7e-4fdd-811d-fc4f71da91be'),('6b278443-1b52-4d40-9e9f-c9ced0d19047','','','','79a10319-22e8-4d54-98fc-40050901b796'),('6bd8d2e7-3b35-4210-866d-91ebb59c0757','','','','ba3fe348-658f-448f-9dfd-5b8d621923d9'),('6df4db20-9158-489f-8534-8cdb488f89db','','','','712c5a28-8883-4a86-be87-4f252440940d'),('7df9b411-a62e-4858-9e9e-46cc37b61318','','','','38d6cbd7-ff5a-4f63-8a0a-f11d8291cf78'),('7f1ae943-a392-430d-a612-2fae3edbadb8','','','','3654b087-0ad9-4e94-a3df-e3269a3a635b'),('7f360e0d-1e48-40ba-8dd5-0bae6f1b5632','','','','026fddd6-bdc1-4229-8873-d52d61308687'),('80b9e015-0c5f-4eae-b69b-052d9d820ea3','','','','8ac1c2b9-d595-4e66-8892-35bafc546ef6'),('92a440b2-9b91-4307-a9f2-36cd8dc37f7b','','','','9406894f-38da-4963-945f-a78b13b0d0de'),('950bcaea-250f-4ad5-b3cb-71995686badc','','','','c54b474c-f1cf-4f0d-8e98-146ad37e4f32'),('99b29822-9994-4381-874c-95f36599fc0f','','','','c0b12af1-02bc-4a23-801c-012ec6a03e5b'),('9b5c94b2-0e32-41ed-8222-e4f07c31d6cf','','','','d0ae7f88-d7f0-4ecf-85be-7c992fd18853'),('a0daa8ce-fff4-4fb8-8063-6027bf69a355','','','','78fa6fba-442a-440e-90f3-1eef80d26088'),('a1ddc631-6f50-43c7-b7fa-446eb1c82972','','','','70e485d0-5486-4fd1-990c-733d55cee14f'),('b0ac0392-769e-4002-9d9e-cec22f5df008','','','','7f01a102-905c-45a4-8d24-eb296c7531c3'),('b2f27589-d7b2-4afb-a3be-d8a5950c450d','','','','635b4f5a-a0b3-4f59-9b1f-4d8c5a4071c9'),('b5b6b376-985f-4155-9296-030a77ff14d4','','','','be41c8fe-635c-4e35-bcd3-19ea0710537e'),('b6600e3b-bd41-40e4-b63e-20ae242264bc','','','','5b0aa73f-b81d-4c8b-bb27-66445da7e8da'),('b85ae413-02ed-4db0-8527-2139c1667c2f','','','','ac8816b0-6000-4c86-8664-e72bbbaafb77'),('d9728aaf-3831-4a72-8a83-4f4c1d312b94','','','','ede814ef-ef91-42d1-aec5-06fd7500cb62'),('dfcc79cf-6f8a-4f43-bf1f-070173f65179','','','','5893ef24-cd82-4394-b515-64d0220efc42'),('e1b53fc8-e6e3-4851-92ab-a873c92e0b8b','','','','11092659-1be1-4b9f-880a-29d1e8919ab3'),('e4d26a6e-c989-4622-a980-e2ae36ae20f9','','','','ccb1f043-3c35-44da-bce5-a485e2d3f90c'),('e52dbc35-f8e7-4879-945d-7842738be7d9','','','','a6dcaa4c-ec97-438d-8086-6b58fe8b7408'),('f23b6d6f-d673-4fbd-989f-2427d4981709','','','','f52e31ae-0b03-46ca-bc67-ce864d19b4a6'),('f37f3795-68fe-4f26-b6fa-c538333dbcb6','','','','c80fef70-8d64-421e-b356-de41f92267fc'),('f3fdb035-b8ed-4160-94ad-d85f7a754f1b','','','','b2af8220-58c9-4516-a4ff-205a1f87cae7'),('f57a42c9-8bd0-41d0-a4e9-3564e02c8d95','','','','e1c2e7c5-95de-4383-89b2-3a8703240e33'),('fc1dbafe-1054-40ac-9650-487614aab1e4','','','','230340f2-36b9-4f46-a01b-a129cd4eceb6');
/*!40000 ALTER TABLE `bmerp_product_descriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product_images`
--

DROP TABLE IF EXISTS `bmerp_product_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product_images` (
  `product_ImageId` varchar(255) NOT NULL COMMENT 'id',
  `src` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '图片地址',
  `sort` int DEFAULT NULL COMMENT '排序',
  `product_id` varchar(255) DEFAULT NULL COMMENT '产品id',
  PRIMARY KEY (`product_ImageId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product_images`
--

LOCK TABLES `bmerp_product_images` WRITE;
/*!40000 ALTER TABLE `bmerp_product_images` DISABLE KEYS */;
INSERT INTO `bmerp_product_images` VALUES ('002e7e4b-d337-462f-bae3-8ebf2774bbbe','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg',0,NULL),('014d065c-2135-499e-be69-df1e4e7dae9c','https://cross-border.xyhstore.com/api/resource/202206131609334863499.jpg',2,NULL),('0290f626-93bb-4f97-b435-3fcf236b8857','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',0,NULL),('02fec96e-a677-4fc5-9062-b15a30dbd064','https://cross-border.xyhstore.com/api/resource/202206131603047431963.jpg',3,NULL),('03c17dd2-4ae8-4a4a-8245-d107159e773a','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg',0,NULL),('04ab37cf-f89a-4987-bb6f-b54548c4917e','http://192.168.18.122:4568/api/resource/202212221519244307679.jpg',0,NULL),('05f6f43b-2f54-412e-8238-35f0c5f49c39','http://192.168.18.98:4568/api/resource/202212260930249527106.jpg',0,NULL),('06a6d74c-daaf-4056-88c6-75bc74d53fce','https://cross-border.xyhstore.com/api/resource/202209061416531295953.jpg',0,'f52e31ae-0b03-46ca-bc67-ce864d19b4a6'),('09439358-2c8a-43a8-943d-db729fccbfb2','http://192.168.18.122:4568/api/resource/202212221519244307679.jpg',0,'38d6cbd7-ff5a-4f63-8a0a-f11d8291cf78'),('0ad314e7-7bbd-458a-9542-0e1623a1a3d2','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,'be41c8fe-635c-4e35-bcd3-19ea0710537e'),('0def4888-2bfa-40de-8405-95877c1d51f1','http://192.168.0.131:4568/api/resource/202211231138046423659.png',0,NULL),('0e69f08f-8d92-4d0d-a8f5-66aa757d5ef3','http://192.168.18.98:4568/api/resource/202212261040190478708.jpg',0,NULL),('0ed885c5-60c0-4b8b-815b-b5df8bedff57','https://cross-border.xyhstore.com/api/resource/202206131609244517203.jpg',0,NULL),('0ee6db97-a681-4da9-9969-93f4473fd1b5','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,NULL),('12bf5594-728b-477e-aa7c-6fdafbcafde1','http://192.168.18.122:4568/api/resource/202212191100582177182.jpg',0,NULL),('14d72a71-29ea-48b6-ad3f-fbd74e8a5bc6','http://192.168.0.131:4568/api/resource/202211231107302835180.jpg',0,'712c5a28-8883-4a86-be87-4f252440940d'),('16521be6-4518-41e0-98a5-bcee52f1e3f4','http://192.168.18.122:4568/api/resource/202212191100582177182.jpg',0,'6389d68d-692e-47ea-823e-79441a8219f3'),('23d7b97f-01d2-4f79-980d-00d7ca6824f4','https://cross-border.xyhstore.com/api/resource/202206131603018086191.jpg',2,'ba3fe348-658f-448f-9dfd-5b8d621923d9'),('29ae1fb6-2ffe-4d9f-9d76-60da91a96c62','https://cross-border.xyhstore.com/api/resource/202206131602573196583.jpg',0,'ba3fe348-658f-448f-9dfd-5b8d621923d9'),('2b8a86e1-3aee-4376-9ee6-b26a0f3a97ea','https://cross-border.xyhstore.com/api/resource/202206131609364151920.jpg',3,'b2af8220-58c9-4516-a4ff-205a1f87cae7'),('2e2b729d-5097-4e61-bc9f-7612e0b836d0','https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/23/202212230828137020701.jpg',0,NULL),('32e9578d-238d-4d7a-82f1-1634d4ec4ae6','http://192.168.18.98:4568/api/resource/202212281038588254800.jpg',0,'77d3be60-3c7e-4fdd-811d-fc4f71da91be'),('353cffce-1b4f-4df4-b69b-4399e4ce240d','https://cross-border.xyhstore.com/api/resource/202206131609271590940.jpg',1,NULL),('3ddc7414-2fd4-43ab-bc2c-6fc29a0cdeca','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,'f2adbed1-5293-4ab5-9581-46ab91575f9c'),('3e89b860-10c3-4ee7-85db-b0d22cd6a887','http://192.168.18.98:4568/api/resource/202212271716026743772.jpg',0,'ede814ef-ef91-42d1-aec5-06fd7500cb62'),('3ebf6e9f-2616-4f41-b5ad-b741bf284dfb','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,'7f01a102-905c-45a4-8d24-eb296c7531c3'),('3f160658-c54c-41e1-9136-c4d91c30e3b8','http://192.168.18.98:4568/api/resource/202212260930249527106.jpg',0,'635b4f5a-a0b3-4f59-9b1f-4d8c5a4071c9'),('4128d373-7836-4206-9c8f-24565c4b13f2','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg',0,NULL),('42880bf1-2850-4aa7-9163-3516c2ab4e35','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,NULL),('42dcea75-9c8f-4981-8a80-98189d73d19f','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg',0,NULL),('441ea080-075f-4cdc-ba5f-3a3ab8e46eae','https://cross-border.xyhstore.com/api/resource/202206131609334863499.jpg',2,NULL),('44ec04e3-a7bc-40c4-bfe2-e1262927b39e','http://192.168.18.98:4568/api/resource/202212271716026743772.jpg',0,NULL),('44ee4676-44ef-43ae-a558-67ac2f0eba05','http://192.168.18.98:4568/api/resource/202212230635118887320.jpg',0,NULL),('458aad15-4321-43fa-a9cc-f0c7b22aec70','http://192.168.18.122:4568/api/resource/202212161149555482485.jpg',0,'c54b474c-f1cf-4f0d-8e98-146ad37e4f32'),('4ad080f6-410e-4f41-99d5-ae5854d8a6ee','https://cross-border.xyhstore.com/api/resource/202205231631028644732.jpg',0,'89d97ed3-02ee-4ae9-b8f7-fc204c2768c6'),('4ad33de7-01c0-4856-a16b-06e87b5d6223','http://192.168.18.122:4568/api/resource/202212231051046567022.jpg',0,NULL),('4add49ea-c3aa-47ee-9577-6ccab56695ea','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',0,'6a2b7a80-9b92-4f0e-b357-7bc0bdc61674'),('4b08df56-4fbd-4e69-9059-29f3522a43ac','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg',0,'e1c2e7c5-95de-4383-89b2-3a8703240e33'),('4ddcd95a-eed4-4173-b1e4-c3e5fdd02793','http://192.168.18.98:4568/api/resource/202212230635118887320.jpg',0,'5b0aa73f-b81d-4c8b-bb27-66445da7e8da'),('535451d8-9c75-4271-91b7-605a35e546a7','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',0,'ccb1f043-3c35-44da-bce5-a485e2d3f90c'),('54adc9eb-8c74-4d5f-9154-1ace820d9841','https://cross-border.xyhstore.com/api/resource/202206131603018086191.jpg',2,NULL),('54ee8174-358e-4750-9cdf-620d06fe6dff','https://cross-border.xyhstore.com/api/resource/202206131609364151920.jpg',3,NULL),('55920b81-946a-43b5-97e4-2101ac04224d','https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2023/01/04/202301040854536015083.jpg',0,'e74728fe-58d8-4e13-aea3-55e3dd38062a'),('58db8dea-8f8a-4747-857d-c41856795373','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,NULL),('5a6a798c-75ab-4eb1-a9bd-976007ca9ab1','http://192.168.18.98:4568/api/resource/202212281038588254800.jpg',0,NULL),('5ebb062b-207e-459d-b58c-49fe29981bc7','https://cross-border.xyhstore.com/api/resource/202206131609244517203.jpg',0,'b2af8220-58c9-4516-a4ff-205a1f87cae7'),('6487275a-eeee-48ce-b23b-bf6c948ffecf','https://cross-border.xyhstore.com/api/resource/202206131602596502461.jpg',1,'ba3fe348-658f-448f-9dfd-5b8d621923d9'),('6611cfe8-70a4-44fd-9872-b85b660dd2b3','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg',0,'9a84a128-8f1f-4663-8fa3-418070140d3e'),('693c3658-d462-486f-8b70-8108e42b6583','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,NULL),('6cda7084-8b0a-48ec-b1ea-11a9478c39de','https://cross-border.xyhstore.com/api/resource/202206131609364151920.jpg',3,NULL),('6d79dd26-40bd-4835-9c4c-1fa0aea2e63d','http://192.168.0.131:4568/api/resource/202211231138046423659.png',0,'4f602538-e29a-4cad-98b7-8bcc1947afef'),('6dd3ee56-a094-4b04-9a52-862dc0bfb602','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,'a6dcaa4c-ec97-438d-8086-6b58fe8b7408'),('6ee5a417-43af-44cf-bf2e-809ff0cb3ca4','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,'5893ef24-cd82-4394-b515-64d0220efc42'),('732dbb34-37e2-4141-b304-f343638edc46','http://192.168.18.122:4568/api/resource/202212221823135905420.jpg',0,'70e485d0-5486-4fd1-990c-733d55cee14f'),('755c2ea5-a079-4558-a666-92c69bb821f5','http://192.168.18.98:4568/api/resource/202212261040190478708.jpg',0,'2e618e43-a348-4bc6-b843-4202688a188c'),('773e06e7-9004-4a4e-b86a-3b0f761703a2','https://cross-border.xyhstore.com/api/resource/202209061416531295953.jpg',0,NULL),('7ccfcefb-2693-41b2-acc2-7ebae8419d62','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg',0,NULL),('7d659871-a6ac-4fc6-b436-85a6f4ab002d','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg',0,NULL),('7dbb3162-b928-4374-986d-927ba86e7031','http://192.168.0.131:4568/api/resource/202211241645360563476.jpg',0,'78fa6fba-442a-440e-90f3-1eef80d26088'),('84ba3610-0d86-47e8-8fc4-63bd8ec7761a','http://192.168.18.122:4568/api/resource/202212221519244307679.jpg',0,NULL),('8585c481-5edd-48e2-aac8-373eac720381','https://cross-border.xyhstore.com/api/resource/202206131609271590940.jpg',1,'b2af8220-58c9-4516-a4ff-205a1f87cae7'),('8761c5db-229a-4caa-9699-816d3e688691','http://192.168.18.122:4568/api/resource/202212230930299323124.jpg',0,'c0b12af1-02bc-4a23-801c-012ec6a03e5b'),('87d55668-1b3b-4088-b821-f896c02d02ab','https://cross-border.xyhstore.com/api/resource/202206241836315240712.png',2,'11092659-1be1-4b9f-880a-29d1e8919ab3'),('880571ea-8a51-47ac-9d27-08e68caab5cb','https://cross-border.xyhstore.com/api/resource/202209091553553992994.jpg',0,'3654b087-0ad9-4e94-a3df-e3269a3a635b'),('8913da7f-fb10-43f1-adec-b777b375de57','http://192.168.18.122:4568/api/resource/202212191100582177182.jpg',0,NULL),('8e5d3a4a-4f7c-4d97-bea3-33a4f6123300','http://192.168.18.122:4568/api/resource/202212221823135905420.jpg',0,NULL),('8e5fb227-9961-4038-b3c9-45f4895c9132','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,NULL),('907001a7-92ee-48ee-b211-c726a18e3a88','http://192.168.18.122:4568/api/resource/202212161153114146882.jpg',0,'230340f2-36b9-4f46-a01b-a129cd4eceb6'),('91c9fd05-2cf0-4a3b-be86-970cc8bbcc93','https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,NULL),('91e034f8-94ca-4184-8579-444b47b10836','http://192.168.18.98:4568/api/resource/202212281038588254800.jpg',0,NULL),('94028feb-f9bf-4ad7-8515-047ce018c7a0','https://cross-border.xyhstore.com/api/resource/202206241836203241031.png',0,'11092659-1be1-4b9f-880a-29d1e8919ab3'),('99cb06b6-ccc4-4803-913e-8e9b2b9eccb0','https://cross-border.xyhstore.com/api/resource/202206131609364151920.jpg',3,NULL),('a198d480-b19d-4c0c-8fc4-5f5b5be7a76e','http://192.168.18.98:4568/api/resource/202212230422582592671.jpg',0,'026fddd6-bdc1-4229-8873-d52d61308687'),('a2f5ded6-c1f3-4f33-b85b-f3a2388d1cb3','http://192.168.18.98:4568/api/resource/202212260934393114130.jpg',0,'c80fef70-8d64-421e-b356-de41f92267fc'),('a6667743-07d7-4993-af15-16442cdd01fd','http://192.168.0.131:4568/api/resource/202211241645360563476.jpg',0,NULL),('a7258ed4-a9bc-4b24-ad7c-bce4eb87cb18','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg',0,NULL),('a90e60e4-fa6b-4484-81cf-71b38ffef6c6','https://cross-border.xyhstore.com/api/resource/202206131602596502461.jpg',1,NULL),('ac55bdd1-6ce9-432b-a713-6f9e43625d98','https://cross-border.xyhstore.com/api/resource/202206131609244517203.jpg',0,NULL),('ac654572-cfa5-4763-92f7-163f7acebcdf','http://192.168.18.122:4568/api/resource/202212191100582177182.jpg',0,NULL),('b0088469-ce53-4d7c-a973-97a7b834a8e2','http://192.168.18.122:4568/api/resource/202212161153114146882.jpg',0,NULL),('b183ed43-64e9-4890-ab7e-965c09c0f477','https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,NULL),('be364361-d667-4381-9495-5d264e1aadc0','https://cross-border.xyhstore.com/api/resource/202206241836267458311.png',1,'11092659-1be1-4b9f-880a-29d1e8919ab3'),('be9a867b-3286-4fac-b81f-27951b4ca785','https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,'8ac1c2b9-d595-4e66-8892-35bafc546ef6'),('c23f77a0-0e68-4e4a-a041-f6f0063f2f43','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,NULL),('c3ea20bf-86be-4d52-9726-b3e4edfa47a1','http://192.168.0.131:4568/api/resource/202211231107302835180.jpg',0,NULL),('c5e7ffc2-6261-410f-bff7-2a76ab9086cc','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg',0,NULL),('c76fe9e7-2c54-43e0-b3e3-150872d5b705','https://cross-border.xyhstore.com/api/resource/202206131609334863499.jpg',2,'b2af8220-58c9-4516-a4ff-205a1f87cae7'),('c97e86ec-b584-4a43-a4e5-2f82dcbf8458','http://192.168.18.122:4568/api/resource/202212161153114146882.jpg',0,NULL),('cb4a8e53-f6fe-40b6-90c5-4cea66b1d24c','https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2023/01/04/202301040854536015083.jpg',0,NULL),('ce135074-0f5f-4e93-9dda-7ecc541f3f3b','http://192.168.18.122:4568/api/resource/202212161155460048113.jpg',0,NULL),('ce71d34a-82be-4492-8e45-45c65927fe56','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg',0,NULL),('d1a5a825-2622-4a3c-99d3-343d417bc027','http://192.168.18.122:4568/api/resource/202212191100582177182.jpg',0,'d0ae7f88-d7f0-4ecf-85be-7c992fd18853'),('d3156291-d6fb-432d-9a98-eae39805f580','https://cross-border.xyhstore.com/api/resource/202209091553553992994.jpg',0,NULL),('d69a2a01-bb7f-4b4f-8e35-0938ca866638','http://192.168.18.122:4568/api/resource/202212231051046567022.jpg',0,'9406894f-38da-4963-945f-a78b13b0d0de'),('d8e1a1bd-cdf4-40a9-94b8-1ec958b6a82c','https://cross-border.xyhstore.com/api/resource/202209061441542937706.jpg',0,'3ba36bc3-f7e1-40d1-90d6-08c3e6cfeab4'),('d96c3dd4-7f1a-494c-939f-5a34f6e7fd5c','https://cross-border.xyhstore.com/api/resource/202206131603047431963.jpg',3,'ba3fe348-658f-448f-9dfd-5b8d621923d9'),('dc3eae9b-f4c6-48a5-ba11-5d58409f063d','http://192.168.18.122:4568/api/resource/202212231051046567022.jpg',0,NULL),('de3d7102-375b-4122-97c8-59a037fb2320','http://192.168.18.98:4568/api/resource/202212230635118887320.jpg',0,NULL),('e1176135-81ad-4ee3-b1a9-c2879e83b9fc','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg',0,NULL),('e19fcc01-26cf-4991-9f9b-3c4a2a01e4b0','https://cross-border.xyhstore.com/api/resource/202206131609334863499.jpg',2,NULL),('e8c7fb15-b8f6-4ebb-8b23-f43fbe4e2785','https://cross-border.xyhstore.com/api/resource/202206131602573196583.jpg',0,NULL),('e98f8af7-866c-45a1-b3cd-c17e0dad5424','http://192.168.18.122:4568/api/resource/202212230930299323124.jpg',0,NULL),('e9944446-6e36-4642-8d28-e38b5a82ea12','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg',0,'1a92cd65-0399-4f5a-8983-ea566d9b8a01'),('ea8c58e1-6061-4547-9dd6-971457795de7','http://192.168.18.122:4568/api/resource/202212161149555482485.jpg',0,NULL),('ecc83506-d85c-4da5-a739-bbbe86bb835b','https://cross-border.xyhstore.com/api/resource/202209061416531295953.jpg',0,NULL),('ee1cc1f8-e293-48b1-8fc2-0d3853e8f2f7','https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,'ac8816b0-6000-4c86-8664-e72bbbaafb77'),('f05d306f-59ac-45f9-9836-96b1476d7ee0','https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/23/202212230828137020701.jpg',0,'818ff38d-da4e-4a25-99f2-41f75489c055'),('f36a02eb-8c5c-4297-b4b9-e2a98d7d2ba6','https://cross-border.xyhstore.com/api/resource/202209091553553992994.jpg',0,'79a10319-22e8-4d54-98fc-40050901b796'),('f4ce0307-deaf-4963-abce-1a7abf4db9d5','http://192.168.18.122:4568/api/resource/202212161155460048113.jpg',0,'36218519-6a33-4a91-84ff-5809b5e42966'),('f5d08482-fd69-429c-9f78-db90e1fc3050','https://cross-border.xyhstore.com/api/resource/202206131609271590940.jpg',1,NULL),('f9286b6e-cc3e-410a-b966-e9f2acdbea61','http://192.168.0.131:4568/api/resource/202211241645360563476.jpg',0,NULL),('f9fa06a6-5812-4fcd-98aa-efbddd1bbec4','https://cross-border.xyhstore.com/api/resource/202206131609244517203.jpg',0,NULL),('fb4d2506-584f-4a11-a3e5-b7d0e87d6b71','http://192.168.18.122:4568/api/resource/202212221519244307679.jpg',0,NULL),('fc0b3005-ab4d-41db-a9c6-c4c4191d1211','https://cross-border.xyhstore.com/api/resource/202209061441542937706.jpg',0,NULL),('fd5ad01f-78d6-41d3-a83e-a26ad9a60a22','https://cross-border.xyhstore.com/api/resource/202206131609271590940.jpg',1,NULL),('fd60158a-429e-41ca-ae34-a3ebbc2cf9a7','http://192.168.18.98:4568/api/resource/202212230635118887320.jpg',0,NULL);
/*!40000 ALTER TABLE `bmerp_product_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product_options`
--

DROP TABLE IF EXISTS `bmerp_product_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product_options` (
  `product_optionId` varchar(255) NOT NULL COMMENT 'id',
  `option_name` varchar(255) DEFAULT NULL COMMENT '选项名',
  `option_sort` int DEFAULT NULL COMMENT '排序',
  `option_values` varchar(255) DEFAULT NULL COMMENT '值的数组',
  `product_id` varchar(255) DEFAULT NULL COMMENT '产品id',
  PRIMARY KEY (`product_optionId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product_options`
--

LOCK TABLES `bmerp_product_options` WRITE;
/*!40000 ALTER TABLE `bmerp_product_options` DISABLE KEYS */;
INSERT INTO `bmerp_product_options` VALUES ('10700137-ebc2-45ae-b25e-6eb561501d68','颜色',0,'粉色','3654b087-0ad9-4e94-a3df-e3269a3a635b'),('11c52da0-5049-4e65-bd8f-8acc835a480a','适合年龄',0,'1-3岁,3-5岁,5-7岁,7-9岁','79a10319-22e8-4d54-98fc-40050901b796'),('122fa7aa-6b66-4df2-abf1-c16655c751e1','尺寸',0,'110,120,130,140',NULL),('12512114-da54-4b21-98aa-90c3716a2b86','大小',0,'大,中,小',NULL),('18783d5d-f51a-4c88-b2e6-f2d73f04b0f7','大小',0,'60,70,80','5893ef24-cd82-4394-b515-64d0220efc42'),('21ccc494-e494-45b3-be88-cbfc5f48d304','Size',0,'One Size','6a2b7a80-9b92-4f0e-b357-7bc0bdc61674'),('28bf9f06-1618-467f-a962-910faa24afa2','颜色',0,'红色,黄色,蓝色',NULL),('2a21c205-ef66-448b-ac44-ea5afc37eb77','颜色',0,'蓝色,白色,绿色,红色,黑色',NULL),('33243bcd-a444-4601-8ac7-9088e943437b','大小',0,'60,70,80',NULL),('335b6638-7c5d-4793-8126-2652e9c2b332','颜色',0,'红,黄,蓝,粉','7f01a102-905c-45a4-8d24-eb296c7531c3'),('3793bbcc-cc76-4fdb-82ee-76b56d3cffd2','颜色',0,'白色,红色','818ff38d-da4e-4a25-99f2-41f75489c055'),('4550db49-e142-413e-b4de-e4dd41a534bc','尺寸',0,'120,130','89d97ed3-02ee-4ae9-b8f7-fc204c2768c6'),('49111c29-f517-4b9f-be2b-e734b1b99da6','颜色',0,'不可更改',NULL),('4b2d568a-f063-4393-9dc3-28a1930756ee','大小',0,'大,中,小',NULL),('4dc43508-2ba9-4835-bd82-eda42f077fc9','尺寸',0,'110,120,130,140','b2af8220-58c9-4516-a4ff-205a1f87cae7'),('50134ce3-c9ba-4f7f-893d-25d2aa0bbdff','适合年龄',0,'1-3岁,3-5岁,5-7岁,7-9岁','3654b087-0ad9-4e94-a3df-e3269a3a635b'),('50582867-f5d2-47a7-848f-fc465f39e825','款式',0,'恐龙,老虎,兔子,熊猫',NULL),('538a1a41-90c0-4006-abcd-f34d4f85c324','颜色',0,'蓝色,白色,绿色,红色,黑色','8ac1c2b9-d595-4e66-8892-35bafc546ef6'),('545074a8-878e-4f46-814a-c3cfd958f256','尺寸',0,'100,110,120,130',NULL),('56e708cb-7a92-4dbd-8197-c5e8aa65e411','大小',0,'大,中,小','a6dcaa4c-ec97-438d-8086-6b58fe8b7408'),('596a7156-2dc1-44d9-9ea5-500a24420b34','大小',0,'大,中,小',NULL),('5bff864c-6be6-4b87-a697-a6290a1f0c13','颜色',0,'蓝色,白色,绿色,红色,黑色','ac8816b0-6000-4c86-8664-e72bbbaafb77'),('62304847-437c-4e96-9861-0dd4891af88a','款式',0,'恐龙,老虎,兔子,熊猫',NULL),('64214438-be0e-4d2d-9f2e-5f32403df7db','大小',0,'60,70,80','78fa6fba-442a-440e-90f3-1eef80d26088'),('646288f7-5496-4e64-a79e-6597395b2aad','尺寸',0,'100,200','ac8816b0-6000-4c86-8664-e72bbbaafb77'),('676ab7f2-db48-4b0d-ac18-275e73cf1755','Size',0,'One Size',NULL),('6d019d58-aebc-4801-bd13-beb0ed3af387','码数',0,'大,小','89d97ed3-02ee-4ae9-b8f7-fc204c2768c6'),('6d6fe766-bcdc-4666-95ab-cdcfc30ae2c9','颜色',0,'绿色','ba3fe348-658f-448f-9dfd-5b8d621923d9'),('6e8a4609-5abb-4802-97be-b33ccb383378','款式',0,'不可更改',NULL),('71c816d2-08f0-4aa0-b418-7c2955991acc','大小',0,'大,中,小','4f602538-e29a-4cad-98b7-8bcc1947afef'),('741deddc-497c-4ddc-856f-ba5e10e263cd','大小',0,'大,中,小',NULL),('763a0704-4f37-4b47-ac25-04b55f17012b','颜色',0,'粉色',NULL),('78aaf30e-6d3c-4b8c-bba1-483ad27bf5a6','大小1',0,'大,小','ac8816b0-6000-4c86-8664-e72bbbaafb77'),('7da1a30c-be0e-4a14-bc6d-eabde58fde1e','尺寸',0,'100,200',NULL),('7f07a596-2f67-4a05-a530-9ce26bd7a102','大小1',0,'大,小',NULL),('82e8e19c-0a55-429f-80f0-4a9264ab9abb','尺寸',0,'100,110,120,130','11092659-1be1-4b9f-880a-29d1e8919ab3'),('869071f3-e19d-4f71-a83c-d1c6a10c6ed5','大小',0,'大,中,小','f2adbed1-5293-4ab5-9581-46ab91575f9c'),('86a3e240-1c7d-40b4-8132-838aedb87f93','颜色',0,'红色,黄色,蓝色','712c5a28-8883-4a86-be87-4f252440940d'),('8957e783-e5be-4a3a-8b5a-2a76e72f968c','颜色',0,'不可更改','f52e31ae-0b03-46ca-bc67-ce864d19b4a6'),('8ab71ca1-305c-493f-9a7c-db0fac717de4','款式',0,'不可更改','3ba36bc3-f7e1-40d1-90d6-08c3e6cfeab4'),('8c9190ea-3498-48bb-9d46-e7f0bf4c4c56','大小',0,'大,中,小',NULL),('8ea2e7cb-8c81-4304-b9c2-520a0e94957c','尺寸',0,'110,120,130,140',NULL),('95718c58-3642-4f5f-ab3c-1fa108e9c3f6','颜色',0,'白色','11092659-1be1-4b9f-880a-29d1e8919ab3'),('96eb8c14-6257-4d5a-9ef5-918c77542931','尺寸',0,'100,200',NULL),('a56bbb85-f6a5-4d16-9113-f1ae92de8ca2','Color',0,'White','6a2b7a80-9b92-4f0e-b357-7bc0bdc61674'),('a5e2c85b-55c0-4ee8-98c1-3e481129c940','尺寸',0,'100,200','8ac1c2b9-d595-4e66-8892-35bafc546ef6'),('af68e7be-7157-4562-8641-2c0c911252cc','尺寸',0,'100,110,120,130','ba3fe348-658f-448f-9dfd-5b8d621923d9'),('bbda159d-2a90-4cd4-8eb1-8f64182744d2','大小1',0,'大,小','8ac1c2b9-d595-4e66-8892-35bafc546ef6'),('be159b8a-a777-43ef-9e98-9bf47c707209','大小',0,'60,70,80','be41c8fe-635c-4e35-bcd3-19ea0710537e'),('c1791d37-d90d-47ba-b494-0995bed999bc','大小1',0,'大,小',NULL),('c26ec5d3-288d-408f-9f4b-2a325375691f','颜色',0,'绿色',NULL),('c3dfd34e-3482-4f93-a1cc-604923eaea01','大小',0,'大,中,小',NULL),('c5ed0f7b-2d5c-457f-a87d-a69f196b0d9e','颜色',0,'蓝色,白色,绿色,红色,黑色',NULL),('cafd6ab5-1a05-4e8e-b171-714c984ccf9f','适合年龄',0,'1-3岁,3-5岁,5-7岁,7-9岁',NULL),('ce4ae0fc-188b-4906-9341-bef800ed115a','颜色',0,'红,黄,蓝,粉',NULL),('cf434a12-e93f-4b9e-8192-83cf2f300a4c','颜色',0,'不可更改',NULL),('d3920bc5-6e5b-43d1-b622-fa9a6fa8c8c2','款式',0,'恐龙,老虎,兔子,熊猫',NULL),('d725be50-18eb-4d9c-b335-910ad6be6e1e','Color',0,'White',NULL),('e12460eb-e413-4ff7-bd63-6508cba60cfc','款式',0,'恐龙,老虎,兔子,熊猫','b2af8220-58c9-4516-a4ff-205a1f87cae7'),('e82e889a-ff36-49d1-9753-22fab52f833e','大小',0,'60,70,80',NULL),('e8faf1a0-95f8-4a25-bd3e-a8d9ee95485e','颜色',0,'粉色','79a10319-22e8-4d54-98fc-40050901b796'),('fa992a1f-8852-4644-8f2b-10acda3a76ff','颜色',0,'白色,红色',NULL),('fcf46799-c73c-4d00-aaa0-e72fffcef575','尺寸',0,'110,120,130,140',NULL);
/*!40000 ALTER TABLE `bmerp_product_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product_packmaterials`
--

DROP TABLE IF EXISTS `bmerp_product_packmaterials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product_packmaterials` (
  `id` bigint NOT NULL COMMENT ' 包装材料id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `quantity` int DEFAULT NULL COMMENT '数量',
  `product_id` varchar(255) DEFAULT NULL COMMENT '产品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product_packmaterials`
--

LOCK TABLES `bmerp_product_packmaterials` WRITE;
/*!40000 ALTER TABLE `bmerp_product_packmaterials` DISABLE KEYS */;
INSERT INTO `bmerp_product_packmaterials` VALUES (1497488256156045312,'羊羔绒','',1,'6a2b7a80-9b92-4f0e-b357-7bc0bdc61674');
/*!40000 ALTER TABLE `bmerp_product_packmaterials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product_requirements`
--

DROP TABLE IF EXISTS `bmerp_product_requirements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product_requirements` (
  `product_RequirementId` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '名称',
  `content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '内容',
  `product_id` varchar(255) DEFAULT NULL COMMENT '产品id',
  PRIMARY KEY (`product_RequirementId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product_requirements`
--

LOCK TABLES `bmerp_product_requirements` WRITE;
/*!40000 ALTER TABLE `bmerp_product_requirements` DISABLE KEYS */;
/*!40000 ALTER TABLE `bmerp_product_requirements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product_skus`
--

DROP TABLE IF EXISTS `bmerp_product_skus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product_skus` (
  `sku_id` bigint NOT NULL COMMENT 'skuid',
  `code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'skucode',
  `specification` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '产品规则信息,如颜色尺码',
  `cost_price` decimal(10,2) DEFAULT NULL COMMENT '成本价',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '图片',
  `weight` int DEFAULT NULL COMMENT '重量',
  `length` int DEFAULT NULL COMMENT '长度',
  `width` int DEFAULT NULL COMMENT '宽',
  `height` int DEFAULT NULL COMMENT '高',
  `is_valid` bit(1) DEFAULT NULL COMMENT '是否有效',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `sort` int DEFAULT NULL COMMENT '排序',
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '类型',
  `status` varchar(100) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL COMMENT '产品id',
  `banma_master_app_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product_skus`
--

LOCK TABLES `bmerp_product_skus` WRITE;
/*!40000 ALTER TABLE `bmerp_product_skus` DISABLE KEYS */;
INSERT INTO `bmerp_product_skus` VALUES (1497490072574889984,'22112200001-White-One-Size','White;One Size',112.00,'https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',60,60,60,60,_binary '','',0,'普通',NULL,'6a2b7a80-9b92-4f0e-b357-7bc0bdc61674','1492055167686688768'),(1505519522365325312,'174-34-1669173126161-03','小',50.00,'http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,0,0,0,_binary '','',0,'普通',NULL,'a6dcaa4c-ec97-438d-8086-6b58fe8b7408','1492055167686688768'),(1505519522365325313,'174-34-1669173122982-02','中',50.00,'http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,0,0,0,_binary '','',1,'普通',NULL,'a6dcaa4c-ec97-438d-8086-6b58fe8b7408','1492055167686688768'),(1505519522365325314,'174-34-1669173120174-01','大',50.00,'http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,0,0,0,_binary '','',2,'普通',NULL,'a6dcaa4c-ec97-438d-8086-6b58fe8b7408','1492055167686688768'),(1506901406026584064,'294-34-144109600983-03','',17.00,'http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'5893ef24-cd82-4394-b515-64d0220efc42',NULL),(1506901406026584065,'175-34-1669279512341-02','',17.00,'http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,0,0,0,_binary '','',1,'普通',NULL,'5893ef24-cd82-4394-b515-64d0220efc42',NULL),(1506901406026584066,'176-34-1677779584552-01','',17.00,'http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,0,0,0,_binary '','',2,'普通',NULL,'5893ef24-cd82-4394-b515-64d0220efc42',NULL),(1506907941188423680,'394-34-1449600983-03','',17.00,'http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',1,0,0,0,_binary '','',0,'普通',NULL,'be41c8fe-635c-4e35-bcd3-19ea0710537e',NULL),(1506907941188423681,'125-34-166919512341-02','',19.00,'http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,2,0,0,_binary '','',1,'普通',NULL,'be41c8fe-635c-4e35-bcd3-19ea0710537e',NULL),(1506907941188423682,'176-34-1677179584552-01','',18.00,'http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,3,0,0,_binary '','',2,'普通',NULL,'be41c8fe-635c-4e35-bcd3-19ea0710537e',NULL),(1506991192900911104,'344-34-1411110983-03','',10.00,'https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,0,0,0,_binary '','',0,'普通',NULL,'ccb1f043-3c35-44da-bce5-a485e2d3f90c',NULL),(1506991192900911105,'125-34-13419512341-02','',19.00,'https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',0,2,0,0,_binary '','',1,'普通',NULL,'ccb1f043-3c35-44da-bce5-a485e2d3f90c',NULL),(1506991192900911106,'176-34-16741179584552-01','',11.00,'https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',0,3,0,0,_binary '','',2,'普通',NULL,'ccb1f043-3c35-44da-bce5-a485e2d3f90c',NULL),(1507311249887608832,'174-34-1671430275796-01','',0.01,'http://192.168.18.122:4568/api/resource/202212191413126131442.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'1a92cd65-0399-4f5a-8983-ea566d9b8a01','1492055167686688768'),(1507315547870609408,'174-28-1671418849189-01','',0.01,'http://192.168.18.122:4568/api/resource/202212191101124621093.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'d0ae7f88-d7f0-4ecf-85be-7c992fd18853','1492055167686688768'),(1507632910646136832,'174-34-1671430275796-01','',0.01,'http://192.168.18.122:4568/api/resource/202212191413126131442.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'9a84a128-8f1f-4663-8fa3-418070140d3e','1505374058785554432'),(1507635872030281728,'174-35-1671165000459-01','',0.01,'http://192.168.18.122:4568/api/resource/202212161230168255723.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'e1c2e7c5-95de-4383-89b2-3a8703240e33','1505374058785554432'),(1507707915199324160,'2529','不可更改',10.00,'https://cross-border.xyhstore.com/api/resource/202209061442410581999.jpg',0,12,13,14,_binary '','',0,'普通',NULL,'3ba36bc3-f7e1-40d1-90d6-08c3e6cfeab4','1507668022771339264'),(1507731996913184768,'2547','130;白色',29.00,'https://cross-border.xyhstore.com/api/resource/202206241837044660019.png',0,12,11,13,_binary '','',0,'普通',NULL,'11092659-1be1-4b9f-880a-29d1e8919ab3',NULL),(1507731996913184769,'2548','120;白色',29.00,'https://cross-border.xyhstore.com/api/resource/202206241837044660019.png',0,12,11,13,_binary '','',1,'普通',NULL,'11092659-1be1-4b9f-880a-29d1e8919ab3',NULL),(1507731996913184770,'2549','110;白色',29.00,'https://cross-border.xyhstore.com/api/resource/202206241837044660019.png',0,12,11,13,_binary '','',2,'普通',NULL,'11092659-1be1-4b9f-880a-29d1e8919ab3',NULL),(1507731996913184771,'2550','100;白色',29.00,'https://cross-border.xyhstore.com/api/resource/202206241837044660019.png',0,12,11,13,_binary '','',3,'普通',NULL,'11092659-1be1-4b9f-880a-29d1e8919ab3',NULL),(1507983655903973376,'174-28-1671418849189-01','',0.01,'http://192.168.18.122:4568/api/resource/202212191101124621093.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'6389d68d-692e-47ea-823e-79441a8219f3','1505374058785554432'),(1507984497721757696,'174-37-1671162939701-01','',0.01,'http://192.168.18.122:4568/api/resource/202212161155554449198.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'36218519-6a33-4a91-84ff-5809b5e42966','1505374058785554432'),(1507984514155040768,'174-34-1671162782654-01','',0.01,'http://192.168.18.122:4568/api/resource/202212161153191051107.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'230340f2-36b9-4f46-a01b-a129cd4eceb6','1505374058785554432'),(1507984528331788288,'174-30-1671162437186-01','',0.01,'http://192.168.18.122:4568/api/resource/202212161150158329205.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'c54b474c-f1cf-4f0d-8e98-146ad37e4f32','1505374058785554432'),(1507984540822425600,'174-34-1669279600983-03','80',17.00,'http://192.168.0.131:4568/api/resource/202211241645360563476.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'78fa6fba-442a-440e-90f3-1eef80d26088','1505374058785554432'),(1507984540822425601,'174-34-1669279598711-02','70',17.00,'http://192.168.0.131:4568/api/resource/202211241645360563476.jpg',0,0,0,0,_binary '','',1,'普通',NULL,'78fa6fba-442a-440e-90f3-1eef80d26088','1505374058785554432'),(1507984540822425602,'174-34-1669279584552-01','60',17.00,'http://192.168.0.131:4568/api/resource/202211241645360563476.jpg',0,0,0,0,_binary '','',2,'普通',NULL,'78fa6fba-442a-440e-90f3-1eef80d26088','1505374058785554432'),(1507984554776875008,'174-34-1669272014509-04','粉',25.00,'http://192.168.0.131:4568/api/resource/202211241440225820754.png',0,0,0,0,_binary '','',0,'普通',NULL,'7f01a102-905c-45a4-8d24-eb296c7531c3','1505374058785554432'),(1507984554776875009,'174-34-1669272009510-03','蓝',25.00,'http://192.168.0.131:4568/api/resource/202211241440260583258.png',0,0,0,0,_binary '','',1,'普通',NULL,'7f01a102-905c-45a4-8d24-eb296c7531c3','1505374058785554432'),(1507984554776875010,'174-34-1669272005453-02','黄',25.00,'http://192.168.0.131:4568/api/resource/202211241440304702183.jpg',0,0,0,0,_binary '','',2,'普通',NULL,'7f01a102-905c-45a4-8d24-eb296c7531c3','1505374058785554432'),(1507984554776875011,'174-34-1669272002133-01','红',25.00,'http://192.168.0.131:4568/api/resource/202211241440349631511.jpg',0,0,0,0,_binary '','',3,'普通',NULL,'7f01a102-905c-45a4-8d24-eb296c7531c3','1505374058785554432'),(1507984567187820544,'174-34-1669174734792-03','小',20.00,'http://192.168.0.131:4568/api/resource/202211231139039005736.png',0,0,0,0,_binary '','',0,'普通',NULL,'4f602538-e29a-4cad-98b7-8bcc1947afef','1505374058785554432'),(1507984567187820545,'174-34-1669174732701-02','中',20.00,'http://192.168.0.131:4568/api/resource/202211231139104960937.png',0,0,0,0,_binary '','',1,'普通',NULL,'4f602538-e29a-4cad-98b7-8bcc1947afef','1505374058785554432'),(1507984567187820546,'174-34-1669174729414-01','大',20.00,'http://192.168.0.131:4568/api/resource/202211231139166090040.jpg',0,0,0,0,_binary '','',2,'普通',NULL,'4f602538-e29a-4cad-98b7-8bcc1947afef','1505374058785554432'),(1507984578239811584,'174-34-1669173126161-03','小',50.00,'http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,0,0,0,_binary '','',0,'普通',NULL,'f2adbed1-5293-4ab5-9581-46ab91575f9c','1505374058785554432'),(1507984578239811585,'174-34-1669173122982-02','中',50.00,'http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,0,0,0,_binary '','',1,'普通',NULL,'f2adbed1-5293-4ab5-9581-46ab91575f9c','1505374058785554432'),(1507984578239811586,'174-34-1669173120174-01','大',50.00,'http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,0,0,0,_binary '','',2,'普通',NULL,'f2adbed1-5293-4ab5-9581-46ab91575f9c','1505374058785554432'),(1507984595352571904,'174-34-1669172949868-03','蓝色',10.00,'http://192.168.0.131:4568/api/resource/202211231107302835180.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'712c5a28-8883-4a86-be87-4f252440940d','1505374058785554432'),(1507984595352571905,'174-34-1669172944860-02','黄色',10.00,'http://192.168.0.131:4568/api/resource/202211231107302835180.jpg',0,0,0,0,_binary '','',1,'普通',NULL,'712c5a28-8883-4a86-be87-4f252440940d','1505374058785554432'),(1507984595352571906,'174-34-1669172937875-01','红色',10.00,'http://192.168.0.131:4568/api/resource/202211231107302835180.jpg',0,0,0,0,_binary '','',2,'普通',NULL,'712c5a28-8883-4a86-be87-4f252440940d','1505374058785554432'),(1507987721488977920,'2551','140;熊猫',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611034795414.jpg',0,11,13,12,_binary '','',0,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977921,'2552','130;熊猫',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611034795414.jpg',0,11,13,12,_binary '','',1,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977922,'2553','120;熊猫',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611034795414.jpg',0,11,13,12,_binary '','',2,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977923,'2554','110;熊猫',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611034795414.jpg',0,11,13,12,_binary '','',3,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977924,'2555','兔子;140',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611113911538.jpg',0,11,13,12,_binary '','',4,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977925,'2556','兔子;130',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611113911538.jpg',0,11,13,12,_binary '','',5,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977926,'2557','兔子;120',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611113911538.jpg',0,11,13,12,_binary '','',6,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977927,'2558','110;兔子',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611113911538.jpg',0,11,13,12,_binary '','',7,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977928,'2559','老虎;140',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611199399390.jpg',0,11,13,12,_binary '','',8,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977929,'2560','老虎;130',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611199399390.jpg',0,11,13,12,_binary '','',9,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977930,'2561','老虎;120',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611199399390.jpg',0,11,13,12,_binary '','',10,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977931,'2562','110;老虎',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611199399390.jpg',0,11,13,12,_binary '','',11,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977932,'2563','恐龙;140',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611282454421.jpg',0,11,13,12,_binary '','',12,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977933,'2564','恐龙;130',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611282454421.jpg',0,11,13,12,_binary '','',13,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977934,'2565','恐龙;120',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611282454421.jpg',0,11,13,12,_binary '','',14,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977935,'2566','110;恐龙',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611282454421.jpg',0,11,13,12,_binary '','',15,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1508015327932338176,'174-34-1671693554370-01','',0.01,'http://192.168.18.122:4568/api/resource/202212221520050192602.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'38d6cbd7-ff5a-4f63-8a0a-f11d8291cf78','1505374058785554432'),(1508052856106668032,'174-35-1671704584703-01','',0.01,'http://192.168.18.122:4568/api/resource/202212221823264807557.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'70e485d0-5486-4fd1-990c-733d55cee14f','1505374058785554432'),(1508281328431284224,'174-34-1671759018804-01','',0.01,'http://192.168.18.122:4568/api/resource/202212230930431872949.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'c0b12af1-02bc-4a23-801c-012ec6a03e5b','1505374058785554432'),(1508301532288794624,'174-34-1671763853073-01','',0.01,'http://192.168.18.122:4568/api/resource/202212231051158450217.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'9406894f-38da-4963-945f-a78b13b0d0de','1505374058785554432'),(1508302504230666240,'2571','黑色;200;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/19/202212191138093423163.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666241,'2572','黑色;200;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/19/202212191138170004214.jpg',0,0,0,0,_binary '','',1,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666242,'2573','黑色;100;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',2,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666243,'2574','黑色;100;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',3,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666244,'2575','红色;200;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',4,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666245,'2576','红色;200;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',5,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666246,'2577','红色;100;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',6,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666247,'2578','红色;100;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',7,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666248,'2579','绿色;200;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',8,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666249,'2580','绿色;200;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',9,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666250,'2581','绿色;100;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',10,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666251,'2582','绿色;100;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',11,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666252,'2583','白色;200;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',12,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666253,'2584','白色;200;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',13,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666254,'2585','白色;100;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',14,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666255,'2586','白色;100;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',15,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666256,'2587','蓝色;200;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',16,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666257,'2588','蓝色;200;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',17,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666258,'2589','蓝色;100;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',18,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508302504230666259,'2590','蓝色;100;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',19,'普通',NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508308246622912512,'2591','粉色;1-3岁',0.01,'https://cross-border.xyhstore.com/api/resource/202209091553553992994.jpg',0,12,13,13,_binary '','',0,'普通',NULL,'3654b087-0ad9-4e94-a3df-e3269a3a635b','1507713422647631872'),(1508308246622912513,'2592','粉色;7-9岁',0.01,'https://cross-border.xyhstore.com/api/resource/202209091553553992994.jpg',0,12,13,13,_binary '','',1,'普通',NULL,'3654b087-0ad9-4e94-a3df-e3269a3a635b','1507713422647631872'),(1508308246622912514,'2593','粉色;5-7岁',0.01,'https://cross-border.xyhstore.com/api/resource/202209091553553992994.jpg',0,12,13,13,_binary '','',2,'普通',NULL,'3654b087-0ad9-4e94-a3df-e3269a3a635b','1507713422647631872'),(1508308246622912515,'2594','粉色;3-5岁',0.01,'https://cross-border.xyhstore.com/api/resource/202209091553553992994.jpg',0,12,13,13,_binary '','',3,'普通',NULL,'3654b087-0ad9-4e94-a3df-e3269a3a635b','1507713422647631872'),(1508324641528102912,'174-35-1671769369723-01','',0.01,'http://192.168.18.98:4568/api/resource/202212230423074022937.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'026fddd6-bdc1-4229-8873-d52d61308687',NULL),(1508347552381026304,'2597','黑色;200;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/19/202212191138093423163.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026305,'2598','黑色;200;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/19/202212191138170004214.jpg',0,0,0,0,_binary '','',1,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026306,'2599','黑色;100;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',2,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026307,'2600','黑色;100;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',3,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026308,'2601','红色;200;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',4,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026309,'2602','红色;200;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',5,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026310,'2603','红色;100;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',6,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026311,'2604','红色;100;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',7,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026312,'2605','绿色;200;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',8,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026313,'2606','绿色;200;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',9,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026314,'2607','绿色;100;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',10,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026315,'2608','绿色;100;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',11,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026316,'2609','白色;200;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',12,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026317,'2610','白色;200;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',13,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026318,'2611','白色;100;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',14,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026319,'2612','白色;100;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',15,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026320,'2613','蓝色;200;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',16,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026321,'2614','蓝色;200;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',17,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026322,'2615','蓝色;100;小',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',18,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508347552381026323,'2616','蓝色;100;大',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg',0,0,0,0,_binary '','',19,'普通',NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508357159438532608,'2620','粉色;1-3岁',0.01,'https://cross-border.xyhstore.com/api/resource/202209091553553992994.jpg',0,12,13,13,_binary '','',0,'普通',NULL,'79a10319-22e8-4d54-98fc-40050901b796',NULL),(1508357159438532609,'2621','粉色;7-9岁',0.01,'https://cross-border.xyhstore.com/api/resource/202209091553553992994.jpg',0,12,13,13,_binary '','',1,'普通',NULL,'79a10319-22e8-4d54-98fc-40050901b796',NULL),(1508357159438532610,'2622','粉色;5-7岁',0.01,'https://cross-border.xyhstore.com/api/resource/202209091553553992994.jpg',0,12,13,13,_binary '','',2,'普通',NULL,'79a10319-22e8-4d54-98fc-40050901b796',NULL),(1508357159438532611,'2623','粉色;3-5岁',0.01,'https://cross-border.xyhstore.com/api/resource/202209091553553992994.jpg',0,12,13,13,_binary '','',3,'普通',NULL,'79a10319-22e8-4d54-98fc-40050901b796',NULL),(1508357887133499392,'174-35-1671777304165-01','',0.01,'http://192.168.18.98:4568/api/resource/202212230635324348738.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'5b0aa73f-b81d-4c8b-bb27-66445da7e8da','1505374058785554432'),(1508408135818166272,'2650','不可更改',20.00,'https://cross-border.xyhstore.com/api/resource/202209061416531295953.jpg',1,11,11,11,_binary '','',0,'普通',NULL,'f52e31ae-0b03-46ca-bc67-ce864d19b4a6','1507713422647631872'),(1509368362998710272,'174-35-1672018217586-01','',0.01,'http://192.168.18.98:4568/api/resource/202212260930518386973.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'635b4f5a-a0b3-4f59-9b1f-4d8c5a4071c9','1505374058785554432'),(1509369383128940544,'174-35-1672018469199-01','',0.01,'http://192.168.18.98:4568/api/resource/202212260934551284479.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'c80fef70-8d64-421e-b356-de41f92267fc','1505374058785554432'),(1509385894241517568,'174-34-1672022412072-01','',0.01,'http://192.168.18.98:4568/api/resource/202212261040307062528.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'2e618e43-a348-4bc6-b843-4202688a188c','1505374058785554432'),(1509394201236750336,'2656','红色',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/23/202212230828194962295.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'818ff38d-da4e-4a25-99f2-41f75489c055','1507668022771339264'),(1509394201236750337,'2657','白色',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/23/202212230828224897188.jpg',0,0,0,0,_binary '','',1,'普通',NULL,'818ff38d-da4e-4a25-99f2-41f75489c055','1507668022771339264'),(1509847824429236224,'174-34-1672132480312-01','',0.01,'http://192.168.18.98:4568/api/resource/202212271716026158515.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'ede814ef-ef91-42d1-aec5-06fd7500cb62','1505374058785554432'),(1510110376165916672,'174-35-1672195129195-01','',10.00,'http://192.168.18.98:4568/api/resource/202212281039203884523.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'77d3be60-3c7e-4fdd-811d-fc4f71da91be','1505374058785554432'),(1510287472943185920,'2673','绿色;130',19.90,'https://cross-border.xyhstore.com/api/resource/202206131604053372539.jpg',0,11,12,13,_binary '','',0,'普通',NULL,'ba3fe348-658f-448f-9dfd-5b8d621923d9','1507713422647631872'),(1510287472943185921,'2674','绿色;120',19.90,'https://cross-border.xyhstore.com/api/resource/202206131604053372539.jpg',0,11,12,13,_binary '','',1,'普通',NULL,'ba3fe348-658f-448f-9dfd-5b8d621923d9','1507713422647631872'),(1510287472943185922,'2675','110;绿色',19.90,'https://cross-border.xyhstore.com/api/resource/202206131604053372539.jpg',0,11,12,13,_binary '','',2,'普通',NULL,'ba3fe348-658f-448f-9dfd-5b8d621923d9','1507713422647631872'),(1510287472943185923,'2676','100;绿色',19.90,'https://cross-border.xyhstore.com/api/resource/202206131604053372539.jpg',0,11,12,13,_binary '','',3,'普通',NULL,'ba3fe348-658f-448f-9dfd-5b8d621923d9','1507713422647631872'),(1512742158904348672,'174-34-1672822454320-01','',0.01,'https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2023/01/04/202301040854536015083.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'e74728fe-58d8-4e13-aea3-55e3dd38062a','1505374058785554432'),(1513074488575545344,'2707','130;小',19.00,'https://cross-border.xyhstore.com/api/resource/202205231631028644732.jpg',0,12,13,15,_binary '','',0,'普通',NULL,'89d97ed3-02ee-4ae9-b8f7-fc204c2768c6','1507713422647631872'),(1513074488575545345,'2710','大;130',29.00,'https://cross-border.xyhstore.com/api/resource/202205231631028644732.jpg',0,12,13,15,_binary '','',1,'普通',NULL,'89d97ed3-02ee-4ae9-b8f7-fc204c2768c6','1507713422647631872'),(1513074488575545346,'2712','120;小',19.00,'https://cross-border.xyhstore.com/api/resource/202205231631028644732.jpg',0,12,13,15,_binary '','',2,'普通',NULL,'89d97ed3-02ee-4ae9-b8f7-fc204c2768c6','1507713422647631872'),(1513074488575545347,'2713','大;120',29.00,'https://cross-border.xyhstore.com/api/resource/202205231631028644732.jpg',0,12,13,15,_binary '','',3,'普通',NULL,'89d97ed3-02ee-4ae9-b8f7-fc204c2768c6','1507713422647631872');
/*!40000 ALTER TABLE `bmerp_product_skus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product_skus_combinedata`
--

DROP TABLE IF EXISTS `bmerp_product_skus_combinedata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product_skus_combinedata` (
  `sku_cmbdId` varchar(255) NOT NULL COMMENT 'id',
  `sku_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'skucode',
  `quantity` int DEFAULT NULL COMMENT '数量',
  `psku_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'skuid',
  PRIMARY KEY (`sku_cmbdId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product_skus_combinedata`
--

LOCK TABLES `bmerp_product_skus_combinedata` WRITE;
/*!40000 ALTER TABLE `bmerp_product_skus_combinedata` DISABLE KEYS */;
/*!40000 ALTER TABLE `bmerp_product_skus_combinedata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product_skus_options`
--

DROP TABLE IF EXISTS `bmerp_product_skus_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product_skus_options` (
  `sku_optionId` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '选项名',
  `value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '选项值',
  `psku_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'skuid',
  PRIMARY KEY (`sku_optionId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product_skus_options`
--

LOCK TABLES `bmerp_product_skus_options` WRITE;
/*!40000 ALTER TABLE `bmerp_product_skus_options` DISABLE KEYS */;
INSERT INTO `bmerp_product_skus_options` VALUES ('00257269-07a8-48c2-8835-0e460f1dcfe9','尺寸','100',NULL),('0102f0ef-3635-42fe-9b29-61038e8edb9f','大小1','小','1508302504230666254'),('01086dee-9e22-41c7-975b-180193b4c039','大小','60','1507984540822425602'),('014d29b5-8dfc-4a77-be50-fd4e763caf8d','尺寸','110',NULL),('01553fa4-e95c-47d3-ab48-1d63bacec1f8','款式','兔子',NULL),('0206a04c-7aa2-4b85-b814-99d1dd397ae9','大小1','大','1508347552381026317'),('02dee21d-747b-481a-87ca-723b17000043','尺寸','200',NULL),('0468de45-3090-48f3-b35a-47d4e6122d7d','款式','恐龙',NULL),('04b23b21-dd36-455e-b13a-7ddb9b813487','大小','大',NULL),('04dde1a3-62fc-4a6d-9da0-e3b8bdfb6678','尺寸','200',NULL),('0658127e-176d-4dc6-a7bf-16736db4f832','款式','兔子',NULL),('06de5556-db6c-45e0-80a6-43db6c793546','尺寸','130',NULL),('0829ee55-d3b6-4393-9d88-53b7ff8f65f9','大小1','大','1508302504230666259'),('0890e46a-eefd-4b82-a133-bf902cbe9f39','颜色','红色',NULL),('090ca312-3906-4c7e-b650-b0bad4573720','款式','老虎',NULL),('09129a9c-3ac3-4a25-95cf-7fe5f4c17fcd','大小1','小','1508302504230666242'),('093da574-06b4-4956-acca-06ea4a12a772','尺寸','100','1508302504230666243'),('09d10c4b-c90b-4292-b890-28e832d3cddc','尺寸','120',NULL),('0a2ddc1e-769b-4c13-ac78-abf14d81383d','尺寸','200',NULL),('0a4858ac-e490-4756-af94-3b72673970d9','尺寸','130','1513074488575545345'),('0a5f0873-837b-461f-864c-726368548f5c','颜色','绿色','1508347552381026313'),('0b002d0a-cab5-4811-91df-d2d2c2f65e40','尺寸','200','1508302504230666256'),('0c9d677e-27ce-4f7e-a32b-dfa9edca5a32','颜色','粉','1507984554776875008'),('0d030830-7712-403b-b263-682af4c3652e','尺寸','200','1508347552381026320'),('0d400e3e-94af-43dc-a5bd-911255606336','大小','80','1507984540822425600'),('0e426250-258d-400d-97ee-d17567b75763','款式','兔子','1507987721488977927'),('0ea3c481-6e66-4f4d-a554-7055218a4a57','颜色','白色',NULL),('0f26a7e3-8093-4191-841a-ad52db162f05','尺寸','100','1508302504230666246'),('0f4c16a6-dbc4-4128-912b-b1ddae76b9a9','款式','熊猫',NULL),('0f98c556-ba89-4b2e-be13-e0f60e9bfbae','大小1','小','1508347552381026304'),('0fc87894-0163-4a47-99dd-c2eb8c465e1d','大小1','大',NULL),('0fcbc328-0aed-4104-b343-833cb97a99bd','款式','老虎',NULL),('10b7cc31-3199-4dbf-8234-5fb823b5abda','尺寸','100','1508347552381026319'),('10fba3cf-7e6f-4974-878c-f1a67fa3132c','尺寸','120',NULL),('1171e847-4f75-44db-81d7-88791485a1bf','颜色','蓝色',NULL),('11f2e92c-cca1-4787-b298-3634479972b0','颜色','红色',NULL),('12951bd5-bdfc-41ae-abb1-4aeee701025f','尺寸','200',NULL),('13613333-a613-483d-ab7c-0e9521842716','大小1','大','1508302504230666245'),('142d6397-0fab-4f58-b4e1-b20b002fbcc7','款式','恐龙',NULL),('1440810e-6b94-42cc-965b-97d53dd11efd','适合年龄','3-5岁',NULL),('145bdea3-3b34-4f2f-8302-3207db352e13','颜色','绿色','1508302504230666248'),('14818f63-8a44-4924-9c33-df2bf49601c0','尺寸','140',NULL),('15a1c253-3ba6-4072-a00a-fc5daf498263','款式','熊猫',NULL),('170131c7-7e28-424a-a447-dde18a78f8ad','颜色','不可更改',NULL),('172fd0dc-5baf-49be-b9f1-09a1981479be','款式','兔子','1507987721488977926'),('185ece76-5540-45a1-85a2-59b225ccc99f','大小1','小','1508302504230666240'),('1943253e-5bb5-48c3-a2d5-c0eb0427880e','颜色','绿色','1508302504230666250'),('1996f7c7-e2d1-42f7-9edc-46b4d829bb71','颜色','蓝色',NULL),('1a025226-64fa-4673-99a2-22a04a3e92dd','尺寸','130',NULL),('1a08844c-4fd5-414b-8d7c-f3359bbcffba','款式','恐龙',NULL),('1a77c77f-010d-4ef6-9c39-75c9fccb7159','尺寸','130','1507987721488977925'),('1a8ec01d-c1f5-4596-82e8-1cfb08fe33c5','颜色','黑色','1508302504230666243'),('1badda24-ac9c-4df9-9ace-44d9d6e118b2','大小1','大',NULL),('1c1e87b9-a92b-4636-9d86-96df021899d6','大小1','大','1508347552381026319'),('1ce6940b-b670-4ce6-a395-cb7dfaaaba3d','尺寸','200',NULL),('1e160c8f-68b2-4aa3-a150-33da328f8ec7','尺寸','200',NULL),('1ee81f40-df99-4280-9412-fdc30e977f45','款式','兔子',NULL),('1f4d5185-3597-459c-be1c-835d2beb71c7','大小','中',NULL),('1f8a78b2-a8dd-4f6c-bc6c-d1efa88b9fcd','尺寸','200','1508347552381026305'),('1fa14efc-bdd4-4071-bba8-5e872ff592fd','尺寸','140',NULL),('2054e046-5c3d-439b-8d7a-bf9a10eef0ab','尺寸','140','1507987721488977920'),('20690694-f11b-4953-be09-2c7fcc76d28e','尺寸','120',NULL),('20cad5bb-11fa-4087-aef2-627e16e92bea','颜色','粉色','1508308246622912512'),('21906736-3222-4a89-acf8-69ba448e9817','大小1','小',NULL),('21ca4872-2e74-472b-8cac-e9a560ca23bf','颜色','绿色',NULL),('22beea5f-f2f6-454b-a812-8bad757a954b','颜色','蓝色','1508302504230666257'),('2378e0a5-9c83-48e0-9fe1-2bbb622f65be','大小1','大',NULL),('242582d3-1714-47bd-b083-be663f3c82a9','颜色','绿色','1508347552381026312'),('247983cc-ef0c-4e59-98c6-19ee807a96d9','颜色','绿色','1510287472943185921'),('2513ca4b-af3e-4bd0-ab2b-ec61e6735b03','尺寸','100','1508347552381026315'),('25a445d7-126f-495f-8efd-41f9f1010782','尺寸','140',NULL),('25ea1036-f71a-4848-b9e7-eb120994e08c','颜色','绿色','1508302504230666249'),('266a960f-0ba5-4501-9466-3ae2f43cef04','款式','不可更改',NULL),('267d16c3-dd8c-438c-8d8e-a94a749074fa','尺寸','200','1508347552381026313'),('2782c21b-5ab2-49e7-9be0-21378b781b9a','大小','60',NULL),('278dcef6-a541-4f1f-8aac-6546b93216a6','大小','70','1507984540822425601'),('281bf611-d12f-45ba-8564-1d56d4891932','大小1','大','1508302504230666257'),('2838018d-2a50-494e-aac5-4de1ad5bfe9c','款式','兔子',NULL),('2853e4fa-97b3-4966-932d-84f95c7c2bdc','大小','小','1507984578239811584'),('285b5238-d9f3-457f-963b-41e24dd03194','尺寸','110',NULL),('2a03e0cf-2c72-4c3d-a761-7b994eeed721','尺寸','130',NULL),('2a8c421f-2e55-42ce-8894-6884192266f2','尺寸','200','1508347552381026309'),('2a9f1eda-9af2-4e7c-b123-ad8494ff257c','适合年龄','5-7岁','1508357159438532610'),('2ac17db4-c42c-4aef-83e0-ccb7d18f6325','大小1','大','1508302504230666243'),('2b3c64f7-d000-4fe2-8424-502ef94087a8','适合年龄','1-3岁','1508357159438532608'),('2b6c19d1-0f45-42ac-aafe-2f3ea6c8dfb9','颜色','白色','1507731996913184768'),('2ba2039a-671f-47ab-a332-e231438da091','尺寸','130',NULL),('2d8fb19f-3a4f-468a-bf55-ff767ddda83b','颜色','蓝',NULL),('2e54c1ca-5391-45a6-88cd-f6282ea41fff','码数','大','1513074488575545347'),('2e7686f3-a1ab-4e44-8e30-8619977a4b24','尺寸','140',NULL),('300605c2-8be9-490c-87db-4507c6408d2b','颜色','白色',NULL),('3171ba19-a061-4566-b70f-9feaf62afb17','尺寸','200','1508302504230666253'),('31bab7f2-e458-4baf-9dea-d36d74232293','适合年龄','7-9岁','1508357159438532609'),('321d49cb-a3ad-4a46-87f1-2d075619c372','颜色','绿色','1510287472943185920'),('332723e1-1571-4c94-9071-b2b18be958aa','大小','中',NULL),('33b025e3-7199-47ea-947b-452c45a4158e','尺寸','110','1507731996913184770'),('33d2bae2-b844-4438-9004-676bcdb42985','尺寸','110','1507987721488977935'),('34b1cb4d-efa2-4e9f-96cc-a2171fe6c117','颜色','绿色',NULL),('3514a1f5-15e9-4b1d-b7be-c1f2374380a3','颜色','红色','1508302504230666247'),('35eab7c8-f570-4fdd-bc59-a42288fb5e2a','大小1','大','1508302504230666247'),('36e91f1d-7529-4474-9186-0954fe11f0fb','尺寸','200',NULL),('3703ef82-62bc-4ddd-8927-70e6ff14b7b7','大小1','小',NULL),('37d47252-a28b-4888-876d-0d807ba573ae','颜色','绿色','1508302504230666251'),('38b9000b-5c1a-4627-9d34-c1280f8bc524','大小1','小',NULL),('391797bc-4dc1-42a1-ac39-a901f3cc6749','颜色','蓝色',NULL),('39a1e7d9-7e4a-4a24-b6a6-201bfe578a9c','颜色','黑色',NULL),('39c9a1e1-4689-47c8-9131-7d14e5b3f387','大小1','大','1508347552381026315'),('3a9394c6-9e20-47d3-9e20-54bb8ff3e146','尺寸','120','1513074488575545346'),('3ade01b1-7f3e-4689-b3e4-520422446c03','款式','熊猫',NULL),('3b23be5f-d883-4a0a-ad86-746fce99b09d','尺寸','120',NULL),('3b3c7de7-55ed-4bcc-a1f8-0ab17275bd89','Color','White','1497490072574889984'),('3ba23911-9d90-4b8b-8554-f2cd28c3be0a','大小','大',NULL),('3ca5d967-aee9-48d3-a238-e781c6c08047','尺寸','200',NULL),('3cac3e6e-df52-427f-bada-264f1623cee3','大小1','小','1508302504230666252'),('3d0d612a-34dc-4e6f-8827-b0517d39a4be','颜色','蓝色','1508347552381026321'),('3edaed71-e130-47f4-a5be-f79ed0fde341','尺寸','120',NULL),('3f0c5c51-bfd2-4ed4-80ee-07983e64e90e','颜色','白色',NULL),('3fd1eef6-4a06-4274-a18d-e71b05e2cf2c','颜色','白色','1508347552381026317'),('3fdca000-a1b3-4fe2-aad1-6fd1f36b506a','大小1','大','1508347552381026309'),('401522cb-683c-4ecb-9424-da9ae7005629','颜色','红色',NULL),('4055ff95-92cd-4c6f-890f-548959049b5f','尺寸','100',NULL),('413cef3f-8108-4576-967b-b603674f5dae','大小1','小','1508302504230666244'),('41c3b6ea-4bc0-445e-983a-e3f2f0a4901b','款式','老虎',NULL),('43187255-dc6e-454d-9b93-34ea366450b2','尺寸','100','1508347552381026314'),('44745294-1e54-42aa-a57a-5024d4ffe5c3','适合年龄','7-9岁','1508308246622912513'),('45cfcdae-be4f-41ec-96de-3c6a7ad2747a','颜色','粉色',NULL),('45e44e8e-be8c-4ecf-a08f-d202f5b205ca','款式','恐龙',NULL),('462c7495-176a-460f-8acf-97bb0cec7e97','Size','One Size',NULL),('465fc1cf-e38b-4ef4-b208-0e957231ac21','适合年龄','1-3岁',NULL),('4676029c-cc4c-4c40-bc01-f84da7d09ac4','款式','老虎',NULL),('48c932bf-4b88-4358-b2b8-3db42ebbc0e4','颜色','粉色','1508308246622912513'),('49b88f9f-823c-4ade-b5ab-fa9118fcdbcc','颜色','蓝色',NULL),('49fdbd9a-7b1d-4966-8d62-a9c4cba6dc79','款式','老虎','1507987721488977931'),('4b367406-5dec-4373-bb00-6a543cbdc3be','尺寸','130',NULL),('4baf1962-e9c7-4977-877b-7b872a035c76','颜色','白色','1508302504230666252'),('4bbfc034-35f4-4a17-9624-7650b2cc6467','尺寸','200','1508302504230666245'),('4bcf646d-b5f0-4dd0-8956-7982802128d9','款式','恐龙','1507987721488977932'),('4c0aeef2-1f60-4c06-b962-732c80956b95','大小1','小','1508347552381026314'),('4c47ef7f-5c75-4879-880b-127075d491ba','码数','小','1513074488575545346'),('4d212373-451a-4449-b525-3323c303e3ff','大小1','小',NULL),('4d23770a-332f-457f-aad2-409c25370286','款式','熊猫',NULL),('4d2b6dd1-0f05-41b7-b44e-2a7cebd36575','大小1','小',NULL),('4dbb01e1-27ad-4ffe-adb9-79b0f7c7edca','尺寸','100',NULL),('4ddeeb86-8c5e-44f1-a375-5d26ec35eaca','颜色','红色',NULL),('4eb21efd-2b11-4599-9587-a67024f806c4','大小1','小',NULL),('4eda7b63-d942-46fc-a3f7-1d6474c49204','尺寸','200','1508302504230666257'),('4ee6624b-dbe5-45ba-b256-3d9f5d1d375d','大小','大',NULL),('4f737b0a-de79-41db-9893-46e02f895603','尺寸','200','1508302504230666241'),('4fca8ec0-8cd0-4a41-8c1c-764664e2e43e','颜色','黑色','1508302504230666240'),('4fd221c5-0985-437b-b885-1fc986ec1387','颜色','黑色',NULL),('4fe32797-14c0-4c29-ab72-bdff260bc59e','尺寸','200','1508302504230666244'),('50b285dc-8400-45a2-a9af-b57293b74ac9','款式','恐龙',NULL),('50c7e1cc-af35-4b8e-a5aa-1349bdff446e','尺寸','100',NULL),('5159ffd3-99da-41ba-80e9-6bbf72c54135','颜色','白色','1508347552381026316'),('51c45137-5ce3-415b-8844-3a6878f134ed','颜色','白色',NULL),('51f32018-688d-41ae-b490-8a3f8f0c25d6','颜色','红色','1508347552381026310'),('5369b48d-938a-4204-b885-49c95cda97d3','大小1','大',NULL),('537eacaa-7dda-401c-b158-f1eb5ae2065a','颜色','红色',NULL),('53c9b7e8-1216-4291-900e-4d3959957302','款式','熊猫',NULL),('53ddab5f-1512-4cdb-bb7d-d0ce64894fda','大小1','大',NULL),('5452118a-50d7-4868-a9f6-08c90a8d4464','尺寸','100',NULL),('5611f3f8-2c77-4708-a1ae-c036f5dd4d9b','大小1','大',NULL),('576fbf9c-61f1-417f-86db-b06a23459a85','颜色','黄色','1507984595352571905'),('57748745-8908-4ad8-8bec-aba354652535','款式','老虎',NULL),('5801ffd8-524c-4109-948e-b6c9feef4ba2','颜色','蓝色','1508302504230666259'),('581d8a30-4734-498a-929b-83022b4ec160','尺寸','100',NULL),('58a1222b-e27e-401b-8b9f-20f2a0bf458b','颜色','绿色','1508347552381026314'),('592870b6-f5a6-4ad7-ae86-08748a806e04','颜色','黑色',NULL),('596f790b-f8d7-4a8f-b631-53300ef7b517','颜色','黑色',NULL),('596fd7d7-5cfe-41da-bcac-78577a9bb194','颜色','绿色',NULL),('59f11ad8-e964-4b9a-ad79-c8d9ce99eead','大小1','小','1508302504230666258'),('5a9b5493-9885-46d5-96e3-e4d0a50c33ef','颜色','白色',NULL),('5ab54ecc-552c-4a6f-912d-dd65a88c6ab8','尺寸','200',NULL),('5ad43643-07ca-422d-8707-b1b2bbd8bf85','大小1','大',NULL),('5af4ca95-7903-4efb-a41c-834a671b8f18','颜色','红色','1508347552381026311'),('5b090891-7c0c-441e-bb7b-7af15478bdeb','尺寸','130',NULL),('5b282468-d12a-4523-bd94-05119e16997a','尺寸','130',NULL),('5b4ecf15-3928-4972-8cc0-b3c1784dc98b','尺寸','100',NULL),('5c628a33-08d0-48ed-b496-777f17bac244','尺寸','130',NULL),('5c6cc07f-84b3-4112-a380-b97245f1cabf','大小1','小',NULL),('5c8ba516-4fec-45c6-b4d2-51a3064af5b5','颜色','粉色',NULL),('5cb3f1d3-7684-4501-9ac8-597ba2e972d9','大小1','小',NULL),('5d4ecdd8-6959-42d8-bfb3-8d93c6f7593c','尺寸','100','1508302504230666250'),('5d9df5ea-8f07-4749-88c9-46bc9084c277','颜色','蓝色',NULL),('5e3f79cc-8fcd-4629-8843-6b4b9b526656','尺寸','100',NULL),('6174ee60-31a7-46ca-b06b-d30d8a9c0ce0','大小1','大',NULL),('626c554c-0c09-4253-87cb-470e159e46dc','码数','小','1513074488575545344'),('62d4c603-ec66-4d3e-a14c-f00fc5ce1368','颜色','蓝色','1508302504230666256'),('62dfaa02-ee83-4f7d-aaea-c89a133b85c3','适合年龄','3-5岁','1508357159438532611'),('6383c2de-adc4-4948-ac03-1318042bd079','颜色','蓝','1507984554776875009'),('6390b4a7-19c3-4bef-bf7f-b1dc5a9d36ae','款式','兔子','1507987721488977925'),('6403d778-9757-4b00-810e-198b4a9620e7','尺寸','200','1508347552381026317'),('64b32883-f38b-473d-8bbd-3ad8fbed3102','款式','兔子',NULL),('6604ad6d-d202-4166-b211-408cd61ed50b','尺寸','140','1507987721488977924'),('66aaa42b-6ab5-48a6-a7d9-c5401c6cc872','尺寸','140',NULL),('66b9bd9d-8c95-4485-9b8b-d029aefef04b','大小1','小','1508347552381026318'),('6870430a-7ca8-46b6-a702-fec490cd6e41','尺寸','100','1508302504230666247'),('68ce9fe3-ae19-4e77-a130-cf319c2baa13','款式','不可更改','1507707915199324160'),('69288821-db9d-42a0-af50-76a5423580d5','码数','大','1513074488575545345'),('6960960a-3f0f-487f-901e-c61af509f21b','款式','兔子',NULL),('69cc4c90-78f5-4a17-b9f9-0b5a00702ead','大小1','小',NULL),('6a23c7a8-4cfa-492d-8c97-7ec17b10bb48','尺寸','120',NULL),('6a5e06e8-7d2b-4ba5-a09a-88ded915aa15','尺寸','120','1507987721488977922'),('6a99d105-8409-474d-9802-c6d98cf3a9d1','大小','中',NULL),('6afb9608-c6d3-4c94-ae7a-4abc70a4f7bd','颜色','绿色','1510287472943185922'),('6b50eab1-96ca-4495-9b4e-6b8f2822a310','适合年龄','3-5岁','1508308246622912515'),('6c954c49-2472-4cef-a78f-43a9d36df65f','颜色','粉色',NULL),('6cf1ca88-97bc-419f-987a-59554d13ea16','大小','大',NULL),('6e3063aa-a000-43f0-901d-4b0ea7510bf7','款式','老虎','1507987721488977929'),('6e727f89-b3ee-4450-b190-49ba8a6b49b5','尺寸','110','1507987721488977927'),('6e847a84-b2ca-4a46-b90e-33106e049a79','大小1','小',NULL),('6f04c939-6917-4091-84c1-171d0523497f','尺寸','100','1508302504230666242'),('6f4b4e95-8b1a-4d72-8b45-98b6357cba54','款式','老虎',NULL),('6f9d5252-69c9-4120-80f1-64d3a7f9945f','适合年龄','1-3岁','1508308246622912512'),('6ff47688-0c73-4586-8e02-9eec692d95af','尺寸','100','1508347552381026307'),('70363820-67b8-4e63-b564-d6c7d86b7b48','颜色','白色','1508347552381026319'),('71fad58f-42fe-4917-a5e0-e4464135517d','尺寸','100',NULL),('720f66d8-8391-45c3-9989-ec978339e695','款式','恐龙',NULL),('72e8f2cc-b680-40c2-8c21-bfa085ac8105','尺寸','110',NULL),('731a1073-47fd-4f47-a754-00ab0584aa8d','颜色','蓝色','1508347552381026323'),('734bb8f1-0b1b-48d9-8346-bd1bcb50d551','尺寸','100','1508347552381026310'),('7398ff71-773f-4a88-8552-9d703710eb50','大小1','小',NULL),('739f2738-bf8c-495c-b44a-a58fb92aa18c','尺寸','140',NULL),('7436add5-7513-4f5c-bfe9-d63a7b4051a9','尺寸','110',NULL),('743fb3be-00e7-4b38-b7df-fd08a725a793','尺寸','200',NULL),('7477e2f5-de10-4079-b43c-b46482883ba6','颜色','绿色','1508347552381026315'),('75cfe690-f2f5-4566-9135-93db9233f42b','尺寸','200',NULL),('75dbfb28-490c-484e-b530-78971065c36e','款式','老虎',NULL),('76262b2f-e54d-417c-8647-e2385b732017','颜色','黑色','1508347552381026305'),('766c82a5-dfcf-478e-980a-ffc7fe37500a','大小1','大',NULL),('772ed66a-0913-4a64-ae34-802b0f365cdf','大小','小',NULL),('77d034cd-ef34-43f5-aece-5de6bd26ab2d','尺寸','110',NULL),('7808870a-1c8c-4ec4-a5f9-6213c2afa244','尺寸','120',NULL),('78960b99-48fb-45d5-8faf-3033e0de1715','适合年龄','7-9岁',NULL),('79d93404-057d-4502-8d10-e44bf0603db2','尺寸','130','1507987721488977933'),('7a02364d-0bae-404a-bef0-436223d42604','颜色','白色','1508347552381026318'),('7af7dbbe-2395-4255-b4b6-c8791dd52849','大小1','小',NULL),('7b4d84a3-b857-4e07-ac6f-b54b99910264','款式','恐龙',NULL),('7b90d9c4-c910-46e9-8c0d-aa7a04a57a09','款式','兔子',NULL),('7ba2da2c-5df2-492e-9e1c-2381fd635e12','尺寸','110',NULL),('7bfdc47e-90ba-4a8b-8fed-4cf1992d24f8','尺寸','120','1507987721488977930'),('7d5cc5a7-eaaa-4a39-b7f1-6bfa045f7b4a','款式','恐龙',NULL),('7de5fe2d-5229-4f70-8400-fd2ab5929f4e','大小1','大',NULL),('7e1c2a41-76f5-4dc6-bfa7-5d70edee0936','尺寸','100',NULL),('7e468f2d-7f81-4824-a63f-63c7fcf10117','尺寸','100','1508302504230666255'),('7e6bb7b0-9f70-47f1-9df2-d82509ab82a9','尺寸','200',NULL),('7ec3f606-c55e-4d3a-ae25-863366babbde','颜色','粉色','1508357159438532610'),('7f6ffa5d-372c-4749-a390-4ecacd1ecbe9','尺寸','100','1508302504230666259'),('800c2e0a-e444-4fa1-b392-a996a2b50cd4','尺寸','120',NULL),('801f2f6d-afc8-4487-9ba1-beb1eff2f905','款式','老虎','1507987721488977930'),('80de0219-d527-4729-8dde-aa9c08fbdf0c','尺寸','130',NULL),('82318ec2-6a92-4471-ad63-65b241db8fdb','颜色','白色',NULL),('82e2e1ef-9ec5-49a8-af2f-08f0b0e2b05f','颜色','粉色','1508357159438532609'),('82e5635e-781e-4b5e-8ef6-8c6b22359778','尺寸','120','1510287472943185921'),('82e5e81d-7033-4c82-ad3f-9e3e591a2b9e','尺寸','200','1508302504230666249'),('83759d80-59a6-4330-815f-47fe860953d9','尺寸','130','1510287472943185920'),('839661f0-2f46-4b6d-8a98-4c02f43b1569','颜色','绿色',NULL),('83d3dde0-ff26-48e0-aad4-8f97cb18f718','颜色','蓝色',NULL),('84a692e4-3fad-41e0-8204-9a9021867b86','款式','熊猫',NULL),('8547121b-2ff7-4d26-a41a-034a4c655e7b','颜色','绿色',NULL),('86f74e91-dc5d-4caf-88fe-81082bda01fa','颜色','白色','1508302504230666254'),('87596f10-8655-4b3c-8f94-bcfe6c1676d0','款式','恐龙','1507987721488977935'),('87625f32-507c-43c6-b47d-9589ca8cc01b','尺寸','140',NULL),('88943cb1-1e92-425f-a64e-16b17480859a','Color','White',NULL),('88b16168-7398-4f4d-b5ce-26b0425913bf','颜色','粉色','1508357159438532608'),('88d070da-da87-4e61-bec1-c87cf2f60246','大小','小',NULL),('897be9bd-7134-43d0-b503-1c8ea1ea835c','颜色','白色','1508302504230666255'),('89a837a5-a2f7-4cf6-96df-59c02bc21e9d','大小1','小','1508302504230666256'),('89ca2653-15b9-49b7-8a15-92093ec64762','款式','熊猫',NULL),('89fea59a-580b-478c-bb01-5db61f1059ea','款式','熊猫','1507987721488977920'),('89fee7b9-1844-47a3-8699-603158552040','尺寸','200','1508302504230666252'),('8a0be6f9-1b2e-4379-895a-121e0a0b478f','颜色','绿色',NULL),('8a8038f0-0cb1-46ca-af0c-632d26e77e7e','大小1','小',NULL),('8a8ae83c-74ed-417c-a411-27caed421eac','大小1','大',NULL),('8b1a0937-edb9-4f59-b0e2-3764af4052f5','颜色','黑色','1508347552381026304'),('8b2aa487-df57-4d5c-a669-d64d84034fd5','颜色','黄色',NULL),('8c3bbe4b-74cb-44c3-8f8c-81312cc36a84','尺寸','110','1507987721488977931'),('8cd2434a-d962-4717-ba7d-7ed5ddf261aa','颜色','绿色',NULL),('8d6a5fb4-fab7-45c3-9468-3b8448a4b3e5','颜色','白色','1509394201236750337'),('8db67ba5-6ce2-4aca-afc5-207d332851ba','大小1','大','1508347552381026307'),('8e262c85-a7ae-4e6a-af7f-100966d2acc1','颜色','红',NULL),('8f3bf951-f6fd-4d41-acef-28cd629fbfb8','颜色','绿色',NULL),('8fc4a04b-e0a9-4632-b1bd-ca5b42be0499','大小1','大','1508302504230666241'),('90ff171d-ce0f-415f-b674-1d5f5a0a7f2e','颜色','黑色',NULL),('91d449ec-abe6-4436-a852-e92bc99c5679','尺寸','110','1507987721488977923'),('91e1e2e4-805f-4e0d-99e0-a04ef907da1b','尺寸','140',NULL),('9261842a-78d7-477d-ac32-5190f418b777','尺寸','120','1507987721488977934'),('927b18a9-17a8-445c-a200-feccc86d9346','款式','老虎',NULL),('932be52a-026a-412f-8654-f6d961104b40','颜色','红色',NULL),('9481526d-f504-4d1a-8f65-ceaef418bc92','尺寸','200',NULL),('94ad379e-6f95-4e85-a1ab-10956e218f1b','适合年龄','5-7岁','1508308246622912514'),('9547effc-af0b-483a-ae4d-76f31c53c61e','颜色','黄','1507984554776875010'),('95a2dfcd-9c9e-49f7-820d-d1baddd51551','尺寸','110',NULL),('96670b06-9787-4bf7-931b-6a5ee0cd2f87','大小1','大',NULL),('97e92d4b-3c6a-4569-8b19-0855ad677923','颜色','红色','1508302504230666245'),('986551fd-d5e4-49dc-b598-788bb566cb26','大小1','大','1508302504230666255'),('9874ed8b-9e3d-47c3-bf00-8f372e7ee545','款式','老虎',NULL),('9a4ece1d-4c87-4ee5-89c8-8810f34a2688','款式','熊猫',NULL),('9a64aacd-e4ac-4234-bf9f-f18289156d46','颜色','蓝色',NULL),('9a73a071-001c-4f68-95e8-6a7d0156a749','尺寸','200',NULL),('9a79dd85-5208-4144-a4b8-d3986e4f9e83','尺寸','200','1508302504230666240'),('9abf50b6-aa37-4d7a-8c73-9caaa79d6dfb','颜色','白色','1508302504230666253'),('9b2f278e-7b69-46ed-b46d-d3afc09d9ce2','颜色','绿色',NULL),('9bb13118-7b2f-46fa-8afe-739d2383e0f6','颜色','黑色','1508302504230666241'),('9bd06a07-b13f-44f2-9367-3fd2f1799aeb','尺寸','120',NULL),('9bd88d7d-d796-40e0-b77f-97f10d96e06a','大小1','小',NULL),('9be4b489-cf47-4328-a4d8-fa51823954ea','尺寸','100',NULL),('9bfb8a12-3e92-418f-92b9-e1d172e96b0e','尺寸','120','1507987721488977926'),('9c387247-58f5-45f6-9c82-f84f0e1ff12d','款式','熊猫',NULL),('9ce0f78a-1d51-483b-9715-e10d157baa8b','颜色','蓝色',NULL),('9f85a0f2-7931-42e2-8fe4-6f2b4fdece85','颜色','红色','1509394201236750336'),('9ff6d904-7649-413f-b42f-81b03449a2cc','尺寸','100',NULL),('a076d4cb-f097-44ed-9c3f-b5b96d09b246','大小1','大','1508347552381026313'),('a1032199-eb37-4a09-bd3a-4091b0e95363','颜色','不可更改','1508408135818166272'),('a1a2425d-05ce-4704-863e-5692ce290255','大小','70',NULL),('a2093044-56d0-45ef-ab50-63b2c80226ab','大小','80',NULL),('a29de393-9525-4c1c-af0f-60e9f35a9259','尺寸','100','1508302504230666254'),('a3ad5ac7-cd97-4a12-880e-8a68f9b0406d','大小1','大','1508302504230666253'),('a5a7b871-c2ca-4ca1-9be1-e57b6f37e23f','颜色','红色','1508347552381026308'),('a5d91166-1b98-4d46-bf9f-c3f8f3fb873c','尺寸','200',NULL),('a5dd9a49-13d8-429f-ac8f-d859a6560dc6','大小1','大','1508302504230666249'),('a5e7b623-d2b2-43b3-bf5a-1180db2e2f7e','尺寸','200',NULL),('a6a98bf8-d9e2-4ac4-8e2f-2462b70cb1a9','大小','大','1507984578239811586'),('a83dbfa7-f539-47fc-9d26-c388ea44871b','颜色','红色','1508347552381026309'),('a83f42c0-de6c-4973-b228-b705e1e93574','颜色','绿色',NULL),('a8d46dbc-86b2-422d-9db7-4d653dd2e586','尺寸','100',NULL),('a90a7025-9f20-4bf7-b181-c5694520a419','款式','恐龙',NULL),('a9535475-7ba1-4288-9d80-2fafc6c0b4bb','大小1','小','1508347552381026308'),('a9fad74d-1717-467d-8ce5-368aca920bc8','大小1','大','1508347552381026323'),('aa79b9f0-46fc-4c58-8457-2d922d8a0c45','颜色','黑色','1508302504230666242'),('aa9c7da5-4e38-4fbc-ba90-284344f057b4','款式','熊猫','1507987721488977921'),('aad10f4d-f004-4608-abf1-26022d8f9b52','大小1','大',NULL),('aaf69be1-d64e-4758-89ba-0989594d4427','颜色','红色','1508302504230666244'),('abab05db-f826-44de-ad06-1c605706bdd5','尺寸','200','1508347552381026312'),('abc88409-9151-466a-8c8d-30068b81e16c','款式','兔子',NULL),('acb1a983-afa3-4798-94c2-6dfc37c41c4b','大小1','小','1508347552381026312'),('ad744b81-7e36-457a-9aeb-a401d8531b59','颜色','白色',NULL),('ad94bb9c-2115-4c5c-9941-74b9ff2ba4d4','尺寸','100','1508347552381026318'),('ae19967b-9c31-42f4-9812-6ba9a7f4d8ba','尺寸','130','1513074488575545344'),('ae4e3b13-995e-4302-af67-4d5ad9522d82','尺寸','200',NULL),('af5dcd41-725d-44ad-b11c-96a0b4570610','款式','老虎',NULL),('b0206ecb-f3b6-44bf-b5f9-840df86ab5bf','款式','恐龙','1507987721488977934'),('b02d7894-6bb8-4644-92fe-ed5d99e92067','尺寸','130',NULL),('b0348e12-3a0d-4b12-8c93-7523954e39c1','大小','中','1505519522365325313'),('b0954dbc-7432-4ed9-9809-9cf5379ad475','大小','小','1507984567187820544'),('b17da3f6-7775-4ad0-a841-9207a7d98e8f','大小','中','1507984567187820545'),('b1ef6e55-2253-40c2-b18d-d342a79e1d21','颜色','黑色','1508347552381026307'),('b2c86e1e-589b-459c-9c40-513766f58923','大小','中','1507984578239811585'),('b422620b-4cca-4ff7-a185-16849d22fe5c','大小1','小','1508347552381026310'),('b474fca8-8afd-403a-87f2-d1946a6bb146','尺寸','200','1508302504230666248'),('b4a9a4be-b026-42b3-b5e4-b132c1d95834','大小','小',NULL),('b4f2a64c-40c5-453c-b3fe-9734b1e93ebf','大小1','小','1508302504230666248'),('b5b26479-6b88-49b1-abc7-a26a7c815008','款式','老虎',NULL),('b6001b73-3108-41b5-a691-bf99f45b387e','尺寸','100',NULL),('b6631686-5799-44a1-be1d-0e602fee83d2','尺寸','200',NULL),('b6ad7b32-0cc3-4666-ac76-bf39b6c2419e','款式','恐龙',NULL),('b6ae9eb5-f1ab-42ea-b6d2-f86aef4e1a1c','颜色','红色',NULL),('b72fee25-582f-4582-beb3-d2ff251fcf88','颜色','黄',NULL),('b784ab47-6b15-4239-8f7d-d6a640fdbe4d','大小1','小','1508347552381026306'),('b8590408-f036-43bf-9fea-0c1e6607034d','颜色','白色','1507731996913184771'),('b8f7a203-fd60-418f-bfca-6cebe77051d4','大小1','大','1508347552381026305'),('b9f6f7ca-c3bb-44d9-8154-04a217fcc92d','尺寸','130','1507987721488977921'),('bb5b65e9-0fc3-45f9-9490-c09a1879787e','款式','恐龙',NULL),('bb68a5b9-2187-4e7a-a2c9-84db4b90c309','尺寸','140',NULL),('bc16d6b2-4ee4-43d7-b2f2-1a4e658e0884','尺寸','100','1508347552381026306'),('bc3a28e1-4ea9-4aa9-a322-1938249ae18d','大小1','大','1508347552381026311'),('bc401155-98f5-4e79-b7d7-d1a0c6222f5b','颜色','红色','1508302504230666246'),('bcdbfbdd-5fb2-40eb-8232-221f4852a27a','款式','熊猫','1507987721488977923'),('bdd85abb-fb42-462d-b371-9d7119d7d173','颜色','粉色','1508308246622912514'),('bdd916c4-5b6c-4882-9ab8-4073a65b4e67','颜色','蓝色','1508347552381026320'),('be5c2398-0a47-437f-a364-0882d96db4b5','尺寸','120',NULL),('be9a089f-9312-45e1-8bc3-0377cbdea2a8','尺寸','200',NULL),('bf5a3657-773e-486a-8a6d-f03a1e569348','颜色','蓝色','1508347552381026322'),('bf614aa9-9ee4-458d-9103-9e40581e8ef7','尺寸','100',NULL),('c0c76238-6527-476e-874e-3393be583e6f','尺寸','110',NULL),('c0fea490-a847-4fae-920a-9ae6d3a831dd','大小1','大',NULL),('c307979c-305a-4f5f-b801-8233fb56d660','款式','兔子','1507987721488977924'),('c3322fab-c231-4772-b901-08cdd0c69d53','款式','老虎',NULL),('c35c33af-d541-445d-b677-8b294b37ec0d','大小','大',NULL),('c3afce2e-d79e-4572-99d0-d3fb175b0571','尺寸','100','1508347552381026323'),('c3bd06d7-8469-4a41-b8a9-e3b7365bcb30','尺寸','140',NULL),('c3c0da3c-1901-4697-8e35-16130206c852','大小1','小',NULL),('c3c75a6b-a599-4f70-8ffa-2b9636ea91d4','尺寸','110',NULL),('c3dde7e8-cd70-4d82-a7fd-84d50de97615','颜色','红','1507984554776875011'),('c429314b-fbe7-4101-9037-d64b12e3a630','颜色','绿色','1510287472943185923'),('c4437bcf-fa71-4786-9cb1-10f2f142d68c','款式','熊猫',NULL),('c4ea9554-59b3-4368-ac92-09141795eaf7','尺寸','100',NULL),('c75e0dee-33b3-460f-887a-8ef5a3d40edb','尺寸','140','1507987721488977928'),('c7721c82-129d-4d26-a807-ddbda9e7fe6e','尺寸','110',NULL),('c7e2a636-ca2b-4abb-b9b3-5f8bb1b03479','颜色','粉色',NULL),('c809df51-a1ca-4b5e-9075-ea97bf02eba4','大小','中',NULL),('c8e70b73-7cb4-4e4a-8894-92771b06129f','大小1','大',NULL),('ca4e7661-7ae2-40c9-b507-2a88ad6a7365','颜色','绿色',NULL),('ca9dc948-d3a0-4222-81b7-137f847496a8','大小1','小','1508302504230666246'),('cafef3c4-3469-48b3-987f-37496f896b7b','大小1','小','1508302504230666250'),('cb14fdb0-1a04-44dd-96de-2f4f5d92ec57','大小1','小',NULL),('cba8abfb-e722-400e-bbf4-7251695063a0','尺寸','100','1508302504230666258'),('ccb2680e-bae6-444b-aab4-9cdc578f2902','大小1','大',NULL),('cdb226c4-8a22-45ff-a5d4-6c483cff1bdb','尺寸','110',NULL),('cdf15fa2-0de4-4642-a8fe-da67a9443875','大小','小',NULL),('ce10b681-f761-4580-8d03-93a1aca641c9','大小1','小','1508347552381026322'),('cf69acd2-60bc-4423-afbe-38d15549b7f8','大小','大',NULL),('d04be068-6922-45c6-ad88-a2dc957bd78b','尺寸','100','1508302504230666251'),('d09af84c-37b9-4b36-8779-a7cb23acb5ae','大小1','大',NULL),('d153cce6-2862-44ed-9831-d7104fc51240','大小','大','1507984567187820546'),('d1e32115-7da7-4c87-b6f7-a63818d4960d','颜色','黑色','1508347552381026306'),('d1f30feb-da89-4e0c-b068-054f8a1b64a7','颜色','粉色','1508357159438532611'),('d203376d-e95c-441d-ad11-c4a65dfedc02','尺寸','100',NULL),('d21401fd-5bcc-4c92-b4ee-88f6eb4a8d8a','款式','熊猫','1507987721488977922'),('d24577f1-7a42-4615-9b47-d52e73eb48bf','颜色','红色','1507984595352571906'),('d41c1e24-1180-4386-8126-d8fda95d80a2','款式','恐龙',NULL),('d42b0fb1-4897-46ce-ab37-a3f5b5c175c0','尺寸','100',NULL),('d48e07fc-8165-4a53-91a9-7f87457684fd','大小1','大',NULL),('d518e96f-7949-4d51-b9fa-20dee39ad2bc','尺寸','100','1510287472943185923'),('d7546438-2c58-4617-a0a8-b46a871de3d3','尺寸','100','1508347552381026322'),('d7a8bcb6-d6ab-4290-8507-1981d7a37f53','大小','80',NULL),('d7bcb8f1-f704-4c7a-beda-83dfa27a203f','款式','兔子',NULL),('d7efdc4a-a394-4315-ad20-826c32ed6d55','大小','大','1505519522365325314'),('d9d02d55-4996-439e-bf93-282048480aeb','尺寸','200','1508347552381026316'),('dabd827c-ea7b-43b2-acf7-ef14aece7906','尺寸','140',NULL),('daddbf49-960e-4447-98d1-3762864fe997','颜色','白色','1507731996913184770'),('daeac2bb-e494-4d66-8c18-77cb166bd5fb','大小','小',NULL),('db438f51-234a-4343-a8e9-b31ec8b7fe18','尺寸','130','1507731996913184768'),('db735bd9-f4be-4bda-8799-3c00446e2801','Size','One Size','1497490072574889984'),('db7cc2a3-421f-4eaa-91f4-a3e9ff0e49a3','颜色','蓝色','1508302504230666258'),('db831dae-7e24-4634-8d2d-977206916fb4','大小','小','1505519522365325312'),('db916535-373b-40e5-99f7-1cf590df37a9','颜色','黑色',NULL),('dc94307d-bfc1-4152-87a5-43c22dc3e91c','颜色','白色','1507731996913184769'),('dd8da746-6247-4b98-a994-ec8fb0301dcc','款式','老虎','1507987721488977928'),('df0f5264-9e44-4203-94da-1724873660a7','大小1','大',NULL),('df5bf011-d379-4cfd-a4b3-e0ee7963baf1','款式','兔子',NULL),('dfd8f772-a315-400b-8038-ddf08e650b41','颜色','红色',NULL),('dffd0dbf-0309-4d71-9303-de49bce83bfa','尺寸','100','1507731996913184771'),('e0a8cca1-e07b-446c-871d-3259edd672d5','尺寸','120',NULL),('e1509b54-8545-4a25-8be7-98143b14517a','大小1','小',NULL),('e1c4ebde-dfae-4eed-bd14-e3534545755f','颜色','粉',NULL),('e3c12a26-9299-4436-a84f-1818ac8440be','尺寸','130','1507987721488977929'),('e3d8177c-6f17-409e-8beb-12977ec1d0a8','大小1','大','1508347552381026321'),('e3f2811e-355d-45d3-b80d-0eec31f1e8aa','大小1','大','1508302504230666251'),('e4d2c63a-755e-4ba7-a087-98ff69dbcc82','颜色','红色',NULL),('e53aeb9c-d8cd-495f-8351-9168008cef62','大小','60',NULL),('e5820040-07b3-4118-a291-e340ca2f58b7','尺寸','120','1507731996913184769'),('e6946871-6cd2-4930-b1b4-30ce462a4384','大小1','小',NULL),('e75ab0c6-59e2-45d2-93c1-246eb2946e9f','款式','熊猫',NULL),('e8580385-0777-44e8-87af-9579a89bff58','款式','兔子',NULL),('e8c9ea10-956a-415b-829a-b30c4aab59c0','尺寸','200',NULL),('e91c33ea-8f71-4ef7-972c-ff539d57059b','颜色','不可更改',NULL),('e9889e92-6196-47c4-bb1f-555aba139cda','颜色','红色',NULL),('ea386833-6fed-4af4-83e6-50176a9ae2d3','大小','中',NULL),('ea50d99d-85c6-43f8-ab30-89a925dc4da1','颜色','白色',NULL),('ea693aae-6bbf-4f11-b239-a7a6d75ee52e','尺寸','140',NULL),('eab50091-efe7-45c6-92e8-da1cf1564f77','尺寸','100',NULL),('eb0120aa-2a2d-437c-842f-3432a883be6a','尺寸','100',NULL),('eda3e72e-2a7a-47db-b125-79d6f61ad0db','大小1','小','1508347552381026316'),('eed57b4e-7f0e-423e-9e22-dbaaaffbc514','款式','兔子',NULL),('efc04060-3d2d-4ed8-8e4d-eba6de9ec277','颜色','绿色',NULL),('efc96570-7ca7-4346-81b0-407ff157888a','款式','恐龙','1507987721488977933'),('efcd8e71-d0df-4666-8768-a6fecf512b75','尺寸','200','1508347552381026304'),('f04589ad-b0f0-45b2-ad59-c9b1343f037b','尺寸','200','1508347552381026321'),('f059b754-3d0f-4e29-afe0-47cf7e6c5124','颜色','蓝色',NULL),('f062d265-42da-429d-9c5e-85486c230c17','适合年龄','5-7岁',NULL),('f0fafd64-1145-43da-a9f9-8263d440653a','尺寸','140','1507987721488977932'),('f1c45825-cc2c-4f73-b073-c3499a917992','尺寸','110',NULL),('f1c731ef-ab8a-4429-9d32-6922b587489e','大小','小',NULL),('f2e65ab1-6d79-4685-9cf2-3f7dd6077cbc','尺寸','130',NULL),('f2eb780b-3fb3-4420-b5ad-178e3cd0af52','尺寸','120','1513074488575545347'),('f35a3fa3-5cd5-4584-b9b9-38ff73d99bfb','尺寸','120',NULL),('f398f5e9-8925-4e6e-a3a7-3a03fce1c294','尺寸','100',NULL),('f3fa0e68-7da5-44b4-92c0-2041b4dbf194','大小','70',NULL),('f4bdca49-d30c-41b6-9d4b-e24bfaaadf42','尺寸','200','1508347552381026308'),('f5079fc4-2bc8-40c1-b959-3d9d3c3c9803','款式','熊猫',NULL),('f5230df0-2d29-49b5-ad37-595e2fc312da','大小1','小',NULL),('f64ae86a-7502-4122-8b36-69a1fd04ea30','大小1','小','1508347552381026320'),('f718d70f-15b2-428f-9a84-d2e123815bb2','尺寸','110','1510287472943185922'),('f7ed166c-1edd-41b3-9983-cbac39c7b443','大小','中',NULL),('f80035fc-36c7-4cbe-a01b-be2d1482d76e','大小1','小',NULL),('f8145bdb-c548-4813-9de9-c61829fe854f','颜色','黑色',NULL),('f83db8c2-ac15-4216-afa6-297e5f806513','尺寸','100','1508347552381026311'),('f88cfefb-6f3d-454e-8e56-a3b385853511','尺寸','110',NULL),('fa14a08d-0b41-4b29-9498-a0c1a2482f0c','颜色','蓝色','1507984595352571904'),('fb5f6899-3dfc-457d-9add-2024cfe1ef99','颜色','白色',NULL),('fb852d10-bf17-419a-bd33-bc5f852aff16','尺寸','120',NULL),('fbf920bb-907d-4704-b0a7-12ca333c42b4','尺寸','130',NULL),('fc5102c3-5c18-4715-aa64-1824c5d2a35e','颜色','黑色',NULL),('fd7dbe98-d164-4f73-9fc3-30814c884668','大小1','大',NULL),('ff823b17-4a08-4f30-bf8e-a246c272c50d','尺寸','130',NULL),('ffa7d191-57b3-4748-a965-dcf133983c5e','颜色','粉色','1508308246622912515');
/*!40000 ALTER TABLE `bmerp_product_skus_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product_sources`
--

DROP TABLE IF EXISTS `bmerp_product_sources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product_sources` (
  `product_SourceId` varchar(255) NOT NULL COMMENT 'id',
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '供应商url',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `is_default` bit(1) DEFAULT NULL COMMENT '是否默认',
  `product_id` varchar(255) DEFAULT NULL COMMENT '产品id',
  PRIMARY KEY (`product_SourceId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product_sources`
--

LOCK TABLES `bmerp_product_sources` WRITE;
/*!40000 ALTER TABLE `bmerp_product_sources` DISABLE KEYS */;
/*!40000 ALTER TABLE `bmerp_product_sources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product_spu`
--

DROP TABLE IF EXISTS `bmerp_product_spu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product_spu` (
  `spu_id` bigint NOT NULL COMMENT 'spuid',
  `code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'spu',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '标题',
  `leimu_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'erp类目id',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '图片地址',
  `source` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '产品来源',
  `default_supplier_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '默认的供应商id',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `is_exempt_quality` bit(1) DEFAULT NULL COMMENT '是否免检',
  `keywords` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '热销词（逗号分隔）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `source_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `tagIDs` varchar(100) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `banma_master_app_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`spu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product_spu`
--

LOCK TABLES `bmerp_product_spu` WRITE;
/*!40000 ALTER TABLE `bmerp_product_spu` DISABLE KEYS */;
INSERT INTO `bmerp_product_spu` VALUES (1497121263854817280,'22112200001','重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb','32781a0f-50bd-4fd5-b15a-af5500e69a33','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp','手工创建','b4643438-15b5-4fee-9071-af5600fdece6','',_binary '','爆款','2022-11-22 14:25:24','2022-12-19 10:25:57',NULL,NULL,NULL,'6a2b7a80-9b92-4f0e-b357-7bc0bdc61674','1492055167686688768'),(1506901405753954304,'388','千样公主裙-小童','bd755fca-0c6f-4727-a39c-af4801414ca2','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-19 14:08:12','2022-12-19 14:08:12',NULL,NULL,NULL,'5893ef24-cd82-4394-b515-64d0220efc42',NULL),(1506907941175840768,'3818','千样公主裙-小童','bd755fca-0c6f-4727-a39c-af4801414ca2','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-19 14:34:10','2022-12-19 14:34:10',NULL,NULL,NULL,'be41c8fe-635c-4e35-bcd3-19ea0710537e',NULL),(1506991192682807296,'38118','1样公主裙-小童','bd755fca-0c6f-4727-a39c-af4801414ca2','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-19 20:04:58','2022-12-19 20:04:58',NULL,NULL,NULL,'ccb1f043-3c35-44da-bce5-a485e2d3f90c',NULL),(1507632910440615936,'195','百样裙子-童童','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-21 14:34:56','2022-12-22 15:19:05',NULL,NULL,NULL,'9a84a128-8f1f-4663-8fa3-418070140d3e','1505374058785554432'),(1507635872026087424,'193','阿松大','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-21 14:46:42','2022-12-22 13:53:57',NULL,NULL,NULL,'e1c2e7c5-95de-4383-89b2-3a8703240e33','1505374058785554432'),(1507707914964443136,'125','古风绣花旗袍裙','65f0666e-b5f5-4501-b86f-af7201386cb2','https://cross-border.xyhstore.com/api/resource/202209061441542937706.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-21 19:32:58','2022-12-21 19:34:29',NULL,NULL,NULL,'3ba36bc3-f7e1-40d1-90d6-08c3e6cfeab4','1507668022771339264'),(1507731996833492992,'127','【纯棉短袖T恤】2022夏季新款女童可爱动漫印花休闲打底衫','65f0666e-b5f5-4501-b86f-af7201386cb2','https://cross-border.xyhstore.com/api/resource/202206241836203241031.png','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-21 21:08:40','2022-12-21 21:08:40',NULL,NULL,NULL,'11092659-1be1-4b9f-880a-29d1e8919ab3',NULL),(1507983655807504384,'194','如图如图','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212191100582177182.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:48:40','2022-12-22 13:57:46',NULL,NULL,NULL,'6389d68d-692e-47ea-823e-79441a8219f3','1505374058785554432'),(1507984497713369088,'192','啊实打实','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212161155460048113.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:01','2022-12-22 13:54:19',NULL,NULL,NULL,'36218519-6a33-4a91-84ff-5809b5e42966','1505374058785554432'),(1507984514062766080,'191','阿斯顿撒旦','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212161153114146882.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:05','2022-12-22 13:58:44',NULL,NULL,NULL,'230340f2-36b9-4f46-a01b-a129cd4eceb6','1505374058785554432'),(1507984528323399680,'190','测试测试','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212161149555482485.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:08','2022-12-22 13:58:40',NULL,NULL,NULL,'c54b474c-f1cf-4f0d-8e98-146ad37e4f32','1505374058785554432'),(1507984540797259776,'188','百样公主裙-小童','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.0.131:4568/api/resource/202211241645360563476.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:11','2022-12-22 13:58:36',NULL,NULL,NULL,'78fa6fba-442a-440e-90f3-1eef80d26088','1505374058785554432'),(1507984554768486400,'187','百样公主裙-大童','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:14','2022-12-22 13:58:32',NULL,NULL,NULL,'7f01a102-905c-45a4-8d24-eb296c7531c3','1505374058785554432'),(1507984567175237632,'186','百样裙子-小童','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.0.131:4568/api/resource/202211231138046423659.png','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:17','2022-12-22 13:58:29',NULL,NULL,NULL,'4f602538-e29a-4cad-98b7-8bcc1947afef','1505374058785554432'),(1507984578231422976,'185','百样裙子-大童','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.0.131:4568/api/resource/202211231110592318001.png','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:20','2022-12-22 13:58:18',NULL,NULL,NULL,'f2adbed1-5293-4ab5-9581-46ab91575f9c','1505374058785554432'),(1507984595339988992,'184','百样裙子-童装','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.0.131:4568/api/resource/202211231107302835180.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:24','2022-12-22 13:58:23',NULL,NULL,NULL,'712c5a28-8883-4a86-be87-4f252440940d','1505374058785554432'),(1507987721472200704,'128','宝宝夏装婴儿半袖纯棉t恤衫儿童冰丝短袖男童女童上衣服小童夏季','a46b3b17-f882-460c-a2cd-af7f011551ac','https://cross-border.xyhstore.com/api/resource/202206131609244517203.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 14:04:49','2023-01-05 14:59:19',NULL,NULL,NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1508015327919755264,'197','小样','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212221519244307679.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 15:54:31','2022-12-22 17:24:37',NULL,NULL,NULL,'38d6cbd7-ff5a-4f63-8a0a-f11d8291cf78','1505374058785554432'),(1508052856094085120,'198','小小样','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212221823135905420.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 18:23:39','2022-12-22 18:26:34',NULL,NULL,NULL,'70e485d0-5486-4fd1-990c-733d55cee14f','1505374058785554432'),(1508281328196403200,'199','小小裙','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212230930299323124.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-23 09:31:31','2022-12-23 11:16:14',NULL,NULL,NULL,'c0b12af1-02bc-4a23-801c-012ec6a03e5b','1505374058785554432'),(1508301532284600320,'200','啊实打实','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212231051046567022.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-23 10:51:48','2022-12-23 17:50:57',NULL,NULL,NULL,'9406894f-38da-4963-945f-a78b13b0d0de','1505374058785554432'),(1508302503995785216,'129','儿童装','a46b3b17-f882-460c-a2cd-af7f011551ac','https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-23 10:55:39','2023-01-05 14:58:33',NULL,NULL,NULL,'ac8816b0-6000-4c86-8664-e72bbbaafb77','1507713422647631872'),(1508308246606135296,'130','粉色帽子-女（多款式）','65f0666e-b5f5-4501-b86f-af7201386cb2','https://cross-border.xyhstore.com/api/resource/202209091553553992994.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-23 11:18:29','2022-12-23 13:50:11',NULL,NULL,NULL,'3654b087-0ad9-4e94-a3df-e3269a3a635b','1507713422647631872'),(1508324641523908608,'201','测试11111111','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.98:4568/api/resource/202212230422582592671.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-23 12:23:37','2022-12-23 12:23:37',NULL,NULL,NULL,'026fddd6-bdc1-4229-8873-d52d61308687',NULL),(1508347552242614272,'132','儿童装','65f0666e-b5f5-4501-b86f-af7201386cb2','https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/16/202212161336287397377.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-23 13:54:40','2022-12-23 13:54:40',NULL,NULL,NULL,'8ac1c2b9-d595-4e66-8892-35bafc546ef6',NULL),(1508357159421755392,'133','粉色帽子-女（多款式）','65f0666e-b5f5-4501-b86f-af7201386cb2','https://cross-border.xyhstore.com/api/resource/202209091553553992994.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-23 14:32:50','2022-12-23 14:32:50',NULL,NULL,NULL,'79a10319-22e8-4d54-98fc-40050901b796',NULL),(1508357887129305088,'203','裙子112222','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.98:4568/api/resource/202212230635118887320.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-23 14:35:44','2022-12-23 14:36:46',NULL,NULL,NULL,'5b0aa73f-b81d-4c8b-bb27-66445da7e8da','1505374058785554432'),(1508408135809777664,'135','新款夏装女童亚麻汉服套装古风绣花旗袍裙','42910732-69d0-4883-9147-af72015c79f6','https://cross-border.xyhstore.com/api/resource/202209061416531295953.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-23 17:55:24','2022-12-23 17:55:42',NULL,NULL,NULL,'f52e31ae-0b03-46ca-bc67-ce864d19b4a6','1507713422647631872'),(1509368362973544448,'204','百样小裙子','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.98:4568/api/resource/202212260930249527106.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-26 09:31:00','2022-12-26 09:33:56',NULL,NULL,NULL,'635b4f5a-a0b3-4f59-9b1f-4d8c5a4071c9','1505374058785554432'),(1509369383091191808,'205','百样大裙子','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.98:4568/api/resource/202212260934393114130.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-26 09:35:03','2022-12-26 09:35:03',NULL,NULL,NULL,'c80fef70-8d64-421e-b356-de41f92267fc','1505374058785554432'),(1509385894233128960,'206','百样小童裙子','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.98:4568/api/resource/202212261040190478708.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-26 10:40:40','2022-12-26 10:40:47',NULL,NULL,NULL,'2e618e43-a348-4bc6-b843-4202688a188c','1505374058785554432'),(1509394201215778816,'136','儿童短袖','65f0666e-b5f5-4501-b86f-af7201386cb2','https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2022/12/23/202212230828137020701.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-26 11:13:40','2022-12-26 11:13:46',NULL,NULL,NULL,'818ff38d-da4e-4a25-99f2-41f75489c055','1507668022771339264'),(1509847824420847616,'209','百样童装裙子','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.98:4568/api/resource/202212271716026743772.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-27 17:16:12','2022-12-27 17:16:17',NULL,NULL,NULL,'ede814ef-ef91-42d1-aec5-06fd7500cb62','1505374058785554432'),(1510110376157528064,'210','百样裙子童装','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.98:4568/api/resource/202212281038588254800.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-28 10:39:30','2022-12-28 10:40:44',NULL,NULL,NULL,'77d3be60-3c7e-4fdd-811d-fc4f71da91be','1505374058785554432'),(1510287472691527680,'137','男童短袖T恤夏款儿童2022新款小绅士夏装polo衫男童上衣','42910732-69d0-4883-9147-af72015c79f6','https://cross-border.xyhstore.com/api/resource/202206131602573196583.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-28 22:23:13','2022-12-28 22:23:27',NULL,NULL,NULL,'ba3fe348-658f-448f-9dfd-5b8d621923d9','1507713422647631872'),(1512742158895960064,'213','刊登斑马','89b8e168-1c20-42eb-8bcc-af7000ef029b','https://xy-cross.oss-cn-hangzhou.aliyuncs.com/2023/01/04/202301040854536015083.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2023-01-04 16:57:16','2023-01-04 20:58:53',NULL,NULL,NULL,'e74728fe-58d8-4e13-aea3-55e3dd38062a','1505374058785554432'),(1513074488432939008,'145','长裤待售','a46b3b17-f882-460c-a2cd-af7f011551ac','https://cross-border.xyhstore.com/api/resource/202205231631028644732.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2023-01-05 14:57:49','2023-01-05 14:57:49',NULL,NULL,NULL,'89d97ed3-02ee-4ae9-b8f7-fc204c2768c6','1507713422647631872');
/*!40000 ALTER TABLE `bmerp_product_spu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product_suppliers`
--

DROP TABLE IF EXISTS `bmerp_product_suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product_suppliers` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '供应商id',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `sort` int DEFAULT NULL COMMENT '排序',
  `supinfo_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '供应商详情id',
  `product_id` varchar(255) DEFAULT NULL COMMENT '产品id',
  `banma_master_app_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product_suppliers`
--

LOCK TABLES `bmerp_product_suppliers` WRITE;
/*!40000 ALTER TABLE `bmerp_product_suppliers` DISABLE KEYS */;
INSERT INTO `bmerp_product_suppliers` VALUES ('b4643438-15b5-4fee-9071-af5600fdece6','',0,'b4643438-15b5-4fee-9071-af5600fdece6','6a2b7a80-9b92-4f0e-b357-7bc0bdc61674','1492055167686688768');
/*!40000 ALTER TABLE `bmerp_product_suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product_suppliers_info`
--

DROP TABLE IF EXISTS `bmerp_product_suppliers_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product_suppliers_info` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '供应商信息id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '名称',
  `contact` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '联系方式',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '地址',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '网址',
  `qq` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'QQ',
  `we_chat` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '微信号',
  `wang_wang` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '旺旺号',
  `settlement_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '结算类型',
  `supplier_id` varchar(255) DEFAULT NULL COMMENT '供应商id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product_suppliers_info`
--

LOCK TABLES `bmerp_product_suppliers_info` WRITE;
/*!40000 ALTER TABLE `bmerp_product_suppliers_info` DISABLE KEYS */;
INSERT INTO `bmerp_product_suppliers_info` VALUES ('b4643438-15b5-4fee-9071-af5600fdece6','zzz','将之心','13799820202','福建省厦门市','','','','','','货到付款','b4643438-15b5-4fee-9071-af5600fdece6');
/*!40000 ALTER TABLE `bmerp_product_suppliers_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_product_tags`
--

DROP TABLE IF EXISTS `bmerp_product_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product_tags` (
  `product_TagId` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'tag名称',
  `product_id` varchar(255) DEFAULT NULL COMMENT '产品id',
  PRIMARY KEY (`product_TagId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_product_tags`
--

LOCK TABLES `bmerp_product_tags` WRITE;
/*!40000 ALTER TABLE `bmerp_product_tags` DISABLE KEYS */;
INSERT INTO `bmerp_product_tags` VALUES ('408d211b-d25d-4661-bd44-7fa4a8728010','休闲',NULL),('6788cf8a-51a4-4b33-9ef1-50d57fc1550e','休闲',NULL),('7533b5b2-fe0f-4170-b51d-e609e0d291dd','休闲','6a2b7a80-9b92-4f0e-b357-7bc0bdc61674'),('ad9f6a4a-49d3-45b3-90f8-74383a344b1e','休闲',NULL),('e99c84ae-7030-480d-9c3c-35960f7b8a90','休闲',NULL);
/*!40000 ALTER TABLE `bmerp_product_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_properties`
--

DROP TABLE IF EXISTS `bmerp_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_properties` (
  `X_BANMA_APP_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '应用名',
  `X_BANMA_MASTER_APP_ID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '应用id',
  `X_BANMA_MASTER_APP_SECRET` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '应用密钥',
  `X_BANMA_MASTER_APP_ACCOUNT` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '应用账户',
  `X_BANMA_MASTER_SIGN_METHOD` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '认证方式',
  `X_BANMA_MASTER_SIGN_ALGORITHM` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '签名加密',
  `X_BANMA_MASTER_APP_STATUS` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `X_BANMA_MASTER_APP_SCOPES` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  UNIQUE KEY `bmerp_properties_pk` (`X_BANMA_MASTER_APP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_properties`
--

LOCK TABLES `bmerp_properties` WRITE;
/*!40000 ALTER TABLE `bmerp_properties` DISABLE KEYS */;
INSERT INTO `bmerp_properties` VALUES ('选品中心','1492055167686688768','a7437ad68d0f457b9788e17f92e13b0f','15060715230','IP白名单','SHA256',NULL,NULL),('默认应用','1505374058785554432','8609ec0f8fff46af9f0c0dc6a4bf9316','13003988209','签名','sha256',NULL,NULL),('默认应用','1507349451440668672','950cf874cf114015bddf9336e81c8c64','18596945810','签名',NULL,'已上线','all_read,all_write'),(NULL,'1507713422647631872','2bb5216c0c624f7d9aa7ea1afab00c3b','15985852996',NULL,'sha256',NULL,NULL),(NULL,'1507668022771339264','b80ced401e0749e2941b0e1968314404','15959011762',NULL,'sha256',NULL,NULL),('默认应用','1508327460637261824','efc5da8c62ef4055a3b9b92b3ce84894','18060575020','签名',NULL,'已上线','all_read,all_write'),('默认应用','1508331755721998336','94f24c17ab954baebce3ab28c14d4919','18065558891','签名',NULL,'已上线','all_read,all_write'),('默认应用','1508413493005533184','00174f0caa3f42ef8148cd59b4119244','17353519723','签名',NULL,'已上线','all_read,all_write'),('默认应用','1512790605292392448','9b30de41bbf4470d9c7949714a1a551d','18300568264','签名',NULL,'已上线','all_read,all_write'),('默认应用','1371383319572253423','g15gfyc6862aa4e2cb45654d01031478','1500000000','签名',NULL,'已上线','all_read,all_write'),('默认应用','1512803518950428672','4470464a13974e43a6a0b9b1d8a8522e','18300568267','签名',NULL,'已上线','all_read,all_write'),(NULL,'11111111','111111','1111',NULL,NULL,NULL,NULL),(NULL,'22222','222222','111111',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `bmerp_properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_response_log`
--

DROP TABLE IF EXISTS `bmerp_response_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_response_log` (
  `request_id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `request_phone` bigint DEFAULT NULL,
  `request_body` text COLLATE utf8mb4_unicode_ci,
  `request_time` timestamp NULL DEFAULT NULL,
  `response_body` text COLLATE utf8mb4_unicode_ci,
  `success` bit(1) DEFAULT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_response_log`
--

LOCK TABLES `bmerp_response_log` WRITE;
/*!40000 ALTER TABLE `bmerp_response_log` DISABLE KEYS */;
INSERT INTO `bmerp_response_log` VALUES ('8004d0ce-0000-ce00-b63f-84710c7967bb',1500000000,'{\n    \"Account\": {\n        \"Phone\": \"1500000000\",\n        \"Email\": \"a@b.com\",\n        \"RealName\": \"test\",\n        \"Department\":\"\"\n    }\n}\n','2023-01-04 11:11:59','{\"Code\":400,\"Success\":false,\"Message\":\"参数错误:\\nApp.ID=>The input was not valid.。\\nApp.Status=>The input was not valid.。\\nApp.AuthType=>The input was not valid.。\\nAccount.ID=>The input was not valid.。\\nAccount.CreateTime=>The input was not valid.。\\nAccount.UpdateTime=>The input was not valid.。\",\"Time\":\"2023-01-04T19:11:59\",\"Data\":null,\"RequestId\":\"8004d0ce-0000-ce00-b63f-84710c7967bb\"}',_binary '\0');
/*!40000 ALTER TABLE `bmerp_response_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_storage`
--

DROP TABLE IF EXISTS `bmerp_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_storage` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '存储id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文件名',
  `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'url地址',
  `file_category_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文件分类',
  `file_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '文件类型',
  `size` int DEFAULT NULL COMMENT '大小',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `banma_master_app_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_storage`
--

LOCK TABLES `bmerp_storage` WRITE;
/*!40000 ALTER TABLE `bmerp_storage` DISABLE KEYS */;
/*!40000 ALTER TABLE `bmerp_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bmerp_store`
--

DROP TABLE IF EXISTS `bmerp_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_store` (
  `id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '店铺id',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '店铺名称',
  `platform` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '平台',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `banma_master_app_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'app_id',
  `banma_account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_store`
--

LOCK TABLES `bmerp_store` WRITE;
/*!40000 ALTER TABLE `bmerp_store` DISABLE KEYS */;
INSERT INTO `bmerp_store` VALUES ('1492814222537527296','12530610@qq.com-th','Lazada','2022-11-10 17:10:45','2022-12-10 17:35:13','1492055167686688768',NULL),('1492814222604636160','12530610@qq.com-sg','Lazada','2022-11-10 17:10:45','2022-12-10 17:35:14','1492055167686688768',NULL),('1492814222633996288','12530610@qq.com-vn','Lazada','2022-11-10 17:10:45','2022-12-10 17:35:16','1492055167686688768',NULL),('1492814222659162112','12530610@qq.com-ph','Lazada','2022-11-10 17:10:45','2022-12-10 17:35:17','1505374058785554432',NULL),('1492814222734659584','12530610@qq.com-id','Lazada','2022-11-10 17:10:45','2022-12-10 17:35:18','1492055167686688768',NULL),('1492814222759825408','12530610@qq.com-my','Lazada','2022-11-10 17:10:45','2022-12-10 17:35:20','1492055167686688768',NULL),('1497119990426374144','自有店铺','Self','2022-11-22 14:20:20','2022-11-22 14:20:20','1505374058785554432',NULL);
/*!40000 ALTER TABLE `bmerp_store` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-05 18:21:10
