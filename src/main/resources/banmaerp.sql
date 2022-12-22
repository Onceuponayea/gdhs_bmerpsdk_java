-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: 110.87.104.50    Database: banmaerp
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
INSERT INTO `bmerp_account` VALUES (26455,'15060715230','eric.linjialong@gmail.com','15060715230','','2022-10-27 15:21:37','2022-11-10 16:46:41','','',NULL,NULL),(26679,'谢晓斌','','13696919609','','2022-11-10 16:44:16','2022-12-16 15:14:00','','',NULL,NULL),(27213,'小蒋4','13122119606@qq.com','13221119606','采购部','2022-12-12 20:54:57','2022-12-16 04:56:21','','',NULL,NULL),(27214,'小蒋3','17122919606@qq.com','17221919606','采购部','2022-12-12 20:58:36','2022-12-16 14:42:45','','',NULL,NULL),(27278,'磊少','','15260926991','','2022-12-15 18:16:41','2022-12-16 04:25:04','','',NULL,NULL),(27349,'lian123','','18596945810','','2022-12-21 03:48:34','2022-12-21 03:48:34','Normal','MasterAccount','1507349451440668672',NULL),(27364,'hqx','','15985852996','','2022-12-22 03:54:51','2022-12-22 03:54:51','Normal','MasterAccount','1507713422647631872',NULL),(27372,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL);
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
  `banma_master_app_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `banma_account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bmerp_account_data_access`
--

LOCK TABLES `bmerp_account_data_access` WRITE;
/*!40000 ALTER TABLE `bmerp_account_data_access` DISABLE KEYS */;
INSERT INTO `bmerp_account_data_access` VALUES ('04a96866-9bbb-4d0b-83a5-b4a721ce1cc2','2','1492814222537527296,1492814222604636160',NULL,NULL,NULL,27213),('15cfc909-ee84-441b-80c6-6729b35eaa6c','1','-1',NULL,NULL,NULL,26455),('18c69fe4-4aae-4398-af3f-36aa4820a7fd','0','-1',NULL,NULL,NULL,27372),('1bef3fa0-3ba2-4443-86f4-e70e4013c51c','2','1492814222604636160,1492814222734659584',NULL,NULL,NULL,27214),('2a15bd4e-eeff-44af-b9b9-b4e95d820aed','2','1492814222604636160,1492814222734659584',NULL,NULL,NULL,27214),('2d887a05-36f3-433b-92ac-1dbdd1f9c38f','2','1492814222659162112',NULL,NULL,NULL,27278),('2dad7ea4-3690-4cec-9e18-bca1393b287a','0','-1',NULL,NULL,NULL,27372),('39b0b601-e2ad-41a5-99a0-9be8896439b4','1','-1',NULL,NULL,NULL,26455),('3fab417a-648a-428c-8bdf-077339646f21','0','-1',NULL,NULL,NULL,27372),('4791d0db-a798-49e4-a097-971dd1aae636','2','1492814222659162112',NULL,NULL,NULL,27278),('4c8a3920-1ebf-4b84-b6de-b4b4355e8910','1','-1',NULL,NULL,NULL,26455),('5547da9e-507f-4f51-bc57-d480590d74ec','2','1492814222604636160,1492814222734659584',NULL,NULL,NULL,27214),('5f9ae17a-7daa-4689-b32f-9a3643a3ad62','0','-1',NULL,NULL,NULL,27337),('6b56073f-11e0-4a4b-ad99-c8ab127a1e44','2','1492814222659162112',NULL,NULL,NULL,27278),('6e0077aa-dcf5-4fda-8127-56c9cabeb665','2','1492814222659162112',NULL,NULL,NULL,27278),('6fbc7919-7c67-490f-9b66-48dffb433602','0','-1',NULL,NULL,NULL,27337),('717687f6-1e1f-41a5-b486-c131a39ac1e9','1','-1',NULL,NULL,NULL,26455),('809e8fae-98a9-4a17-a32d-482fce254cef','1','',NULL,NULL,NULL,26679),('9fe4f4ee-9769-4b62-8fce-059a02b2df7b','1','',NULL,NULL,NULL,26679),('b5535124-8c1e-4dcc-85e4-fa3b4cd60b94','2','1492814222537527296,1492814222604636160',NULL,NULL,NULL,27213),('bfebeb4b-03a9-4f61-8140-3a35e954c81b','1','',NULL,NULL,NULL,26679),('c74d0ee0-90d4-4e8b-ae62-ab39b0ecb595','2','1492814222537527296,1492814222604636160',NULL,NULL,NULL,27213),('cabe8c06-50c6-405f-9192-73149e80e4ab','1','',NULL,NULL,NULL,26679),('dc393c55-1403-409c-97d0-56ea19553c8d','2','1492814222604636160,1492814222734659584',NULL,NULL,NULL,27214),('f1025faa-f2dc-4578-a7f1-10d1d3865442','2','1492814222537527296,1492814222604636160',NULL,NULL,NULL,27213);
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
INSERT INTO `bmerp_order` VALUES ('54476144-d0cb-4cfb-9fae-69055c7c5703','1497122489925373952','1492055167686688768','26679-指定权限,27213-指定权限,27214-指定权限,27278-指定权限'),('58fab7a5-c1c5-419d-95a0-f7fe494ccb19','1502207479604322304','1492055167686688768','26679-指定权限,27213-指定权限,27214-指定权限,27278-指定权限'),('5cda3048-1410-42f9-a01e-45ed58c99732','1502207029920407552','1492055167686688768','26679-指定权限,27213-指定权限,27214-指定权限,27278-指定权限');
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
INSERT INTO `bmerp_order_details` VALUES ('1831fc08-918a-47b7-9fc3-af6300fb27a2','0','1502207029928796160','1497490072574889984','1497121263854817280','1497490072574889984','普通','22112200001-White-One-Size','White;One Size',112.00,'重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,1,112.00,'待审核','CNY','','','','00000000-0000-0000-0000-000000000000','未知',0,'未变更','White;One Size','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,'普通','','原始','00000000-0000-0000-0000-000000000000','',00000000000,NULL,NULL),('2f0de1b8-907e-4a06-a576-af6300fba55b','1507227076913012736','1502207479675625472','1497488584339361793','1497488584330973184','1497488584339361793','普通','N-22112300001-White-One-Size','White;One Size',111.00,'踏进银河 日系软糯毛茸茸外套小个子甜美加厚保暖羊羔绒棉服秋冬','https://gd4.alicdn.com/imgextra/i1/2601139430/O1CN01xn0Jro2JWzgjULs67_!!2601139430.jpg_400x400.jpg',1,1,111.00,'待发货','CNY','','','','0eeedc05-91aa-4aa5-b5db-af3b00fd2205','未知',0,'未变更','White;One Size','https://gd4.alicdn.com/imgextra/i1/2601139430/O1CN01xn0Jro2JWzgjULs67_!!2601139430.jpg_400x400.jpg',2,'普通','','原始','00000000-0000-0000-0000-000000000000','',00000000000,NULL,NULL),('e112b802-8c6d-4953-b32a-af5500ef0793','1497122579410849792','1497122489946345472','1497121263867400193','1497121263854817280','1497121263867400193','普通','22112200001-白色-175','白色;175',11.00,'重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,1,11.00,'待发货','CNY','','','','0eeedc05-91aa-4aa5-b5db-af3b00fd2205','未知',0,'未变更','白色;175','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,'普通','','原始','00000000-0000-0000-0000-000000000000','',00000000000,NULL,NULL),('e9578d60-fe02-4abb-a77e-af6300fba55b','1507227076913012736','1502207479637876736','1497490072574889984','1497121263854817280','1497490072574889984','普通','22112200001-White-One-Size','White;One Size',112.00,'重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,1,112.00,'待发货','CNY','','','','0eeedc05-91aa-4aa5-b5db-af3b00fd2205','未知',0,'未变更','White;One Size','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,'普通','','原始','00000000-0000-0000-0000-000000000000','',00000000000,NULL,NULL);
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
INSERT INTO `bmerp_order_master` VALUES ('1497122489925373952','1497119990426374144','26','20221122142700000001','20221122142700000001','2022-11-22 14:30:16','',11.00,'CNY',1.54,11.00,0.00,'CNY',0.00,0.00,0.00,'CNY',0.00,0.00,0.00,'CNY','待发货','已付款','','CN','未暂停','无退款',0,0.00,'','2022-11-22 14:30:16','','','2022-11-22 14:30:16','2022-11-22 14:30:16',_binary '\0',_binary '\0',NULL,'2022-11-22 14:30:16','2022-11-22 14:30:38',_binary '\0','无','','未知','无库存发货','','','',NULL,NULL,'','',NULL,'','未知',NULL,NULL,'54476144-d0cb-4cfb-9fae-69055c7c5703','1492055167686688768'),('1502207029920407552','1497119990426374144','26','20221122142700000005','20221122142700000005','2022-12-06 15:14:25','',112.00,'CNY',15.97,112.00,0.00,'CNY',0.00,0.00,0.00,'CNY',0.00,0.00,0.00,'CNY','待审核','已付款','','CN','未暂停','无退款',0,0.00,'','2022-12-06 15:14:25','','','2022-12-06 15:14:25','2022-12-06 15:14:25',_binary '\0',_binary '\0',NULL,'2022-12-06 15:14:25','2022-12-06 15:14:25',_binary '\0','无','','未知','无库存发货','','小蒋','',NULL,NULL,'','',NULL,'','未知',NULL,NULL,'5cda3048-1410-42f9-a01e-45ed58c99732','1492055167686688768'),('1502207479604322304','1497119990426374144','26','20221122142700000006','20221122142700000006','2022-12-06 15:16:12','',223.00,'CNY',31.80,223.00,0.00,'CNY',0.00,0.00,0.00,'CNY',0.00,0.00,0.00,'CNY','待发货','已付款','','CN','未暂停','无退款',0,0.00,'','2022-12-06 15:16:12','','','2022-12-06 15:16:12','2022-12-06 15:16:12',_binary '\0',_binary '\0',NULL,'2022-12-06 15:16:12','2022-12-20 11:42:18',_binary '\0','无','','未知','无库存发货','','小蒋','',NULL,NULL,'','',NULL,'','未知',NULL,NULL,'58fab7a5-c1c5-419d-95a0-f7fe494ccb19','1492055167686688768');
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
INSERT INTO `bmerp_product` VALUES (1507731996833492992,'e1b53fc8-e6e3-4851-92ab-a873c92e0b8b','11092659-1be1-4b9f-880a-29d1e8919ab3',NULL),(1507311249686282240,'422b8854-9aff-4a02-a5b0-b08a125430cd','1a92cd65-0399-4f5a-8983-ea566d9b8a01','1492055167686688768'),(1507984514062766080,'fc1dbafe-1054-40ac-9650-487614aab1e4','230340f2-36b9-4f46-a01b-a129cd4eceb6','1505374058785554432'),(1507984497713369088,'43c561be-3164-4b3c-98f6-8eea5fc26ff0','36218519-6a33-4a91-84ff-5809b5e42966','1505374058785554432'),(1507707914964443136,'3d5428b0-3356-485a-96e2-4d9a5f008779','3ba36bc3-f7e1-40d1-90d6-08c3e6cfeab4','1507668022771339264'),(1507984567175237632,'00a1e53f-77a2-46d1-a63d-2d1d181861a7','4f602538-e29a-4cad-98b7-8bcc1947afef','1505374058785554432'),(1506901405753954304,'dfcc79cf-6f8a-4f43-bf1f-070173f65179','5893ef24-cd82-4394-b515-64d0220efc42',NULL),(1507983655807504384,'1fa6ad89-a18d-44e1-b0ab-d6196bbc2289','6389d68d-692e-47ea-823e-79441a8219f3','1505374058785554432'),(1497121263854817280,'445ae638-3559-4566-904e-9ed1c6e8cb7e','6a2b7a80-9b92-4f0e-b357-7bc0bdc61674','1492055167686688768'),(1507984595339988992,'6df4db20-9158-489f-8534-8cdb488f89db','712c5a28-8883-4a86-be87-4f252440940d','1505374058785554432'),(1507984540797259776,'a0daa8ce-fff4-4fb8-8063-6027bf69a355','78fa6fba-442a-440e-90f3-1eef80d26088','1505374058785554432'),(1507984554768486400,'b0ac0392-769e-4002-9d9e-cec22f5df008','7f01a102-905c-45a4-8d24-eb296c7531c3','1505374058785554432'),(1507632910440615936,'c473ec8b-2da2-42c3-9faa-36cf5eea51cf','9a84a128-8f1f-4663-8fa3-418070140d3e','1505374058785554432'),(1505519522298216448,'e52dbc35-f8e7-4879-945d-7842738be7d9','a6dcaa4c-ec97-438d-8086-6b58fe8b7408','1492055167686688768'),(1507987721472200704,'237be185-c994-41b8-923d-84b08dae5c17','b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1506907941175840768,'b5b6b376-985f-4155-9296-030a77ff14d4','be41c8fe-635c-4e35-bcd3-19ea0710537e',NULL),(1507984528323399680,'950bcaea-250f-4ad5-b3cb-71995686badc','c54b474c-f1cf-4f0d-8e98-146ad37e4f32','1505374058785554432'),(1506991192682807296,'e4d26a6e-c989-4622-a980-e2ae36ae20f9','ccb1f043-3c35-44da-bce5-a485e2d3f90c',NULL),(1507315547862220800,'9b5c94b2-0e32-41ed-8222-e4f07c31d6cf','d0ae7f88-d7f0-4ecf-85be-7c992fd18853','1492055167686688768'),(1507635872026087424,'f57a42c9-8bd0-41d0-a4e9-3564e02c8d95','e1c2e7c5-95de-4383-89b2-3a8703240e33','1505374058785554432'),(1507984578231422976,'41131cad-6c52-4113-828e-fd44a5a230ec','f2adbed1-5293-4ab5-9581-46ab91575f9c','1505374058785554432');
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
INSERT INTO `bmerp_product_descriptions` VALUES ('00a1e53f-77a2-46d1-a63d-2d1d181861a7','','','','4f602538-e29a-4cad-98b7-8bcc1947afef'),('1fa6ad89-a18d-44e1-b0ab-d6196bbc2289','','','','6389d68d-692e-47ea-823e-79441a8219f3'),('237be185-c994-41b8-923d-84b08dae5c17','','','','b2af8220-58c9-4516-a4ff-205a1f87cae7'),('3d5428b0-3356-485a-96e2-4d9a5f008779','','','','3ba36bc3-f7e1-40d1-90d6-08c3e6cfeab4'),('41131cad-6c52-4113-828e-fd44a5a230ec','','','','f2adbed1-5293-4ab5-9581-46ab91575f9c'),('422b8854-9aff-4a02-a5b0-b08a125430cd','','','','1a92cd65-0399-4f5a-8983-ea566d9b8a01'),('43c561be-3164-4b3c-98f6-8eea5fc26ff0','','','','36218519-6a33-4a91-84ff-5809b5e42966'),('445ae638-3559-4566-904e-9ed1c6e8cb7e','<p><span style=\"font-family: &quot;PingFang SC&quot;; font-size: 20px; font-weight: 900; background-color: rgb(255, 255, 255);\">重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装</span></p>','','','6a2b7a80-9b92-4f0e-b357-7bc0bdc61674'),('6df4db20-9158-489f-8534-8cdb488f89db','','','','712c5a28-8883-4a86-be87-4f252440940d'),('950bcaea-250f-4ad5-b3cb-71995686badc','','','','c54b474c-f1cf-4f0d-8e98-146ad37e4f32'),('9b5c94b2-0e32-41ed-8222-e4f07c31d6cf','','','','d0ae7f88-d7f0-4ecf-85be-7c992fd18853'),('a0daa8ce-fff4-4fb8-8063-6027bf69a355','','','','78fa6fba-442a-440e-90f3-1eef80d26088'),('b0ac0392-769e-4002-9d9e-cec22f5df008','','','','7f01a102-905c-45a4-8d24-eb296c7531c3'),('b5b6b376-985f-4155-9296-030a77ff14d4','','','','be41c8fe-635c-4e35-bcd3-19ea0710537e'),('c473ec8b-2da2-42c3-9faa-36cf5eea51cf','','','','9a84a128-8f1f-4663-8fa3-418070140d3e'),('dfcc79cf-6f8a-4f43-bf1f-070173f65179','','','','5893ef24-cd82-4394-b515-64d0220efc42'),('e1b53fc8-e6e3-4851-92ab-a873c92e0b8b','','','','11092659-1be1-4b9f-880a-29d1e8919ab3'),('e4d26a6e-c989-4622-a980-e2ae36ae20f9','','','','ccb1f043-3c35-44da-bce5-a485e2d3f90c'),('e52dbc35-f8e7-4879-945d-7842738be7d9','','','','a6dcaa4c-ec97-438d-8086-6b58fe8b7408'),('f57a42c9-8bd0-41d0-a4e9-3564e02c8d95','','','','e1c2e7c5-95de-4383-89b2-3a8703240e33'),('fc1dbafe-1054-40ac-9650-487614aab1e4','','','','230340f2-36b9-4f46-a01b-a129cd4eceb6');
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
INSERT INTO `bmerp_product_images` VALUES ('002e7e4b-d337-462f-bae3-8ebf2774bbbe','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg',0,NULL),('0290f626-93bb-4f97-b435-3fcf236b8857','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',0,NULL),('03c17dd2-4ae8-4a4a-8245-d107159e773a','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg',0,NULL),('0ad314e7-7bbd-458a-9542-0e1623a1a3d2','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,'be41c8fe-635c-4e35-bcd3-19ea0710537e'),('0def4888-2bfa-40de-8405-95877c1d51f1','http://192.168.0.131:4568/api/resource/202211231138046423659.png',0,NULL),('0ed885c5-60c0-4b8b-815b-b5df8bedff57','https://cross-border.xyhstore.com/api/resource/202206131609244517203.jpg',0,'b2af8220-58c9-4516-a4ff-205a1f87cae7'),('0ee6db97-a681-4da9-9969-93f4473fd1b5','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,NULL),('12bf5594-728b-477e-aa7c-6fdafbcafde1','http://192.168.18.122:4568/api/resource/202212191100582177182.jpg',0,NULL),('14d72a71-29ea-48b6-ad3f-fbd74e8a5bc6','http://192.168.0.131:4568/api/resource/202211231107302835180.jpg',0,'712c5a28-8883-4a86-be87-4f252440940d'),('16521be6-4518-41e0-98a5-bcee52f1e3f4','http://192.168.18.122:4568/api/resource/202212191100582177182.jpg',0,'6389d68d-692e-47ea-823e-79441a8219f3'),('3ddc7414-2fd4-43ab-bc2c-6fc29a0cdeca','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,'f2adbed1-5293-4ab5-9581-46ab91575f9c'),('3ebf6e9f-2616-4f41-b5ad-b741bf284dfb','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,'7f01a102-905c-45a4-8d24-eb296c7531c3'),('4128d373-7836-4206-9c8f-24565c4b13f2','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg',0,NULL),('42880bf1-2850-4aa7-9163-3516c2ab4e35','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,NULL),('42dcea75-9c8f-4981-8a80-98189d73d19f','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg',0,NULL),('441ea080-075f-4cdc-ba5f-3a3ab8e46eae','https://cross-border.xyhstore.com/api/resource/202206131609334863499.jpg',2,'b2af8220-58c9-4516-a4ff-205a1f87cae7'),('458aad15-4321-43fa-a9cc-f0c7b22aec70','http://192.168.18.122:4568/api/resource/202212161149555482485.jpg',0,'c54b474c-f1cf-4f0d-8e98-146ad37e4f32'),('4add49ea-c3aa-47ee-9577-6ccab56695ea','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',0,'6a2b7a80-9b92-4f0e-b357-7bc0bdc61674'),('4b08df56-4fbd-4e69-9059-29f3522a43ac','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg',0,'e1c2e7c5-95de-4383-89b2-3a8703240e33'),('535451d8-9c75-4271-91b7-605a35e546a7','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',0,'ccb1f043-3c35-44da-bce5-a485e2d3f90c'),('54ee8174-358e-4750-9cdf-620d06fe6dff','https://cross-border.xyhstore.com/api/resource/202206131609364151920.jpg',3,'b2af8220-58c9-4516-a4ff-205a1f87cae7'),('58db8dea-8f8a-4747-857d-c41856795373','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,NULL),('693c3658-d462-486f-8b70-8108e42b6583','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,NULL),('6cda7084-8b0a-48ec-b1ea-11a9478c39de','https://cross-border.xyhstore.com/api/resource/202206131609364151920.jpg',3,NULL),('6d79dd26-40bd-4835-9c4c-1fa0aea2e63d','http://192.168.0.131:4568/api/resource/202211231138046423659.png',0,'4f602538-e29a-4cad-98b7-8bcc1947afef'),('6dd3ee56-a094-4b04-9a52-862dc0bfb602','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,'a6dcaa4c-ec97-438d-8086-6b58fe8b7408'),('6ee5a417-43af-44cf-bf2e-809ff0cb3ca4','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,'5893ef24-cd82-4394-b515-64d0220efc42'),('7ccfcefb-2693-41b2-acc2-7ebae8419d62','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg',0,NULL),('7d659871-a6ac-4fc6-b436-85a6f4ab002d','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg',0,'9a84a128-8f1f-4663-8fa3-418070140d3e'),('7dbb3162-b928-4374-986d-927ba86e7031','http://192.168.0.131:4568/api/resource/202211241645360563476.jpg',0,'78fa6fba-442a-440e-90f3-1eef80d26088'),('87d55668-1b3b-4088-b821-f896c02d02ab','https://cross-border.xyhstore.com/api/resource/202206241836315240712.png',2,'11092659-1be1-4b9f-880a-29d1e8919ab3'),('8913da7f-fb10-43f1-adec-b777b375de57','http://192.168.18.122:4568/api/resource/202212191100582177182.jpg',0,NULL),('8e5fb227-9961-4038-b3c9-45f4895c9132','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,NULL),('907001a7-92ee-48ee-b211-c726a18e3a88','http://192.168.18.122:4568/api/resource/202212161153114146882.jpg',0,'230340f2-36b9-4f46-a01b-a129cd4eceb6'),('94028feb-f9bf-4ad7-8515-047ce018c7a0','https://cross-border.xyhstore.com/api/resource/202206241836203241031.png',0,'11092659-1be1-4b9f-880a-29d1e8919ab3'),('a6667743-07d7-4993-af15-16442cdd01fd','http://192.168.0.131:4568/api/resource/202211241645360563476.jpg',0,NULL),('a7258ed4-a9bc-4b24-ad7c-bce4eb87cb18','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg',0,NULL),('ac654572-cfa5-4763-92f7-163f7acebcdf','http://192.168.18.122:4568/api/resource/202212191100582177182.jpg',0,NULL),('b0088469-ce53-4d7c-a973-97a7b834a8e2','http://192.168.18.122:4568/api/resource/202212161153114146882.jpg',0,NULL),('be364361-d667-4381-9495-5d264e1aadc0','https://cross-border.xyhstore.com/api/resource/202206241836267458311.png',1,'11092659-1be1-4b9f-880a-29d1e8919ab3'),('c23f77a0-0e68-4e4a-a041-f6f0063f2f43','http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,NULL),('c3ea20bf-86be-4d52-9726-b3e4edfa47a1','http://192.168.0.131:4568/api/resource/202211231107302835180.jpg',0,NULL),('c5e7ffc2-6261-410f-bff7-2a76ab9086cc','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg',0,NULL),('c97e86ec-b584-4a43-a4e5-2f82dcbf8458','http://192.168.18.122:4568/api/resource/202212161153114146882.jpg',0,NULL),('ce135074-0f5f-4e93-9dda-7ecc541f3f3b','http://192.168.18.122:4568/api/resource/202212161155460048113.jpg',0,NULL),('ce71d34a-82be-4492-8e45-45c65927fe56','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg',0,NULL),('d1a5a825-2622-4a3c-99d3-343d417bc027','http://192.168.18.122:4568/api/resource/202212191100582177182.jpg',0,'d0ae7f88-d7f0-4ecf-85be-7c992fd18853'),('d8e1a1bd-cdf4-40a9-94b8-1ec958b6a82c','https://cross-border.xyhstore.com/api/resource/202209061441542937706.jpg',0,'3ba36bc3-f7e1-40d1-90d6-08c3e6cfeab4'),('e1176135-81ad-4ee3-b1a9-c2879e83b9fc','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg',0,NULL),('e19fcc01-26cf-4991-9f9b-3c4a2a01e4b0','https://cross-border.xyhstore.com/api/resource/202206131609334863499.jpg',2,NULL),('e9944446-6e36-4642-8d28-e38b5a82ea12','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg',0,'1a92cd65-0399-4f5a-8983-ea566d9b8a01'),('ea8c58e1-6061-4547-9dd6-971457795de7','http://192.168.18.122:4568/api/resource/202212161149555482485.jpg',0,NULL),('f4ce0307-deaf-4963-abce-1a7abf4db9d5','http://192.168.18.122:4568/api/resource/202212161155460048113.jpg',0,'36218519-6a33-4a91-84ff-5809b5e42966'),('f5d08482-fd69-429c-9f78-db90e1fc3050','https://cross-border.xyhstore.com/api/resource/202206131609271590940.jpg',1,'b2af8220-58c9-4516-a4ff-205a1f87cae7'),('f9286b6e-cc3e-410a-b966-e9f2acdbea61','http://192.168.0.131:4568/api/resource/202211241645360563476.jpg',0,NULL),('f9fa06a6-5812-4fcd-98aa-efbddd1bbec4','https://cross-border.xyhstore.com/api/resource/202206131609244517203.jpg',0,NULL),('fc0b3005-ab4d-41db-a9c6-c4c4191d1211','https://cross-border.xyhstore.com/api/resource/202209061441542937706.jpg',0,NULL),('fd5ad01f-78d6-41d3-a83e-a26ad9a60a22','https://cross-border.xyhstore.com/api/resource/202206131609271590940.jpg',1,NULL);
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
INSERT INTO `bmerp_product_options` VALUES ('122fa7aa-6b66-4df2-abf1-c16655c751e1','尺寸',0,'110,120,130,140','b2af8220-58c9-4516-a4ff-205a1f87cae7'),('12512114-da54-4b21-98aa-90c3716a2b86','大小',0,'大,中,小',NULL),('18783d5d-f51a-4c88-b2e6-f2d73f04b0f7','大小',0,'60,70,80','5893ef24-cd82-4394-b515-64d0220efc42'),('21ccc494-e494-45b3-be88-cbfc5f48d304','Size',0,'One Size','6a2b7a80-9b92-4f0e-b357-7bc0bdc61674'),('28bf9f06-1618-467f-a962-910faa24afa2','颜色',0,'红色,黄色,蓝色',NULL),('33243bcd-a444-4601-8ac7-9088e943437b','大小',0,'60,70,80',NULL),('335b6638-7c5d-4793-8126-2652e9c2b332','颜色',0,'红,黄,蓝,粉','7f01a102-905c-45a4-8d24-eb296c7531c3'),('4b2d568a-f063-4393-9dc3-28a1930756ee','大小',0,'大,中,小',NULL),('56e708cb-7a92-4dbd-8197-c5e8aa65e411','大小',0,'大,中,小','a6dcaa4c-ec97-438d-8086-6b58fe8b7408'),('596a7156-2dc1-44d9-9ea5-500a24420b34','大小',0,'大,中,小',NULL),('62304847-437c-4e96-9861-0dd4891af88a','款式',0,'恐龙,老虎,兔子,熊猫',NULL),('64214438-be0e-4d2d-9f2e-5f32403df7db','大小',0,'60,70,80','78fa6fba-442a-440e-90f3-1eef80d26088'),('676ab7f2-db48-4b0d-ac18-275e73cf1755','Size',0,'One Size',NULL),('6e8a4609-5abb-4802-97be-b33ccb383378','款式',0,'不可更改',NULL),('71c816d2-08f0-4aa0-b418-7c2955991acc','大小',0,'大,中,小','4f602538-e29a-4cad-98b7-8bcc1947afef'),('741deddc-497c-4ddc-856f-ba5e10e263cd','大小',0,'大,中,小',NULL),('82e8e19c-0a55-429f-80f0-4a9264ab9abb','尺寸',0,'100,110,120,130','11092659-1be1-4b9f-880a-29d1e8919ab3'),('869071f3-e19d-4f71-a83c-d1c6a10c6ed5','大小',0,'大,中,小','f2adbed1-5293-4ab5-9581-46ab91575f9c'),('86a3e240-1c7d-40b4-8132-838aedb87f93','颜色',0,'红色,黄色,蓝色','712c5a28-8883-4a86-be87-4f252440940d'),('8ab71ca1-305c-493f-9a7c-db0fac717de4','款式',0,'不可更改','3ba36bc3-f7e1-40d1-90d6-08c3e6cfeab4'),('8c9190ea-3498-48bb-9d46-e7f0bf4c4c56','大小',0,'大,中,小',NULL),('8ea2e7cb-8c81-4304-b9c2-520a0e94957c','尺寸',0,'110,120,130,140',NULL),('95718c58-3642-4f5f-ab3c-1fa108e9c3f6','颜色',0,'白色','11092659-1be1-4b9f-880a-29d1e8919ab3'),('a56bbb85-f6a5-4d16-9113-f1ae92de8ca2','Color',0,'White','6a2b7a80-9b92-4f0e-b357-7bc0bdc61674'),('be159b8a-a777-43ef-9e98-9bf47c707209','大小',0,'60,70,80','be41c8fe-635c-4e35-bcd3-19ea0710537e'),('c3dfd34e-3482-4f93-a1cc-604923eaea01','大小',0,'大,中,小',NULL),('ce4ae0fc-188b-4906-9341-bef800ed115a','颜色',0,'红,黄,蓝,粉',NULL),('d3920bc5-6e5b-43d1-b622-fa9a6fa8c8c2','款式',0,'恐龙,老虎,兔子,熊猫','b2af8220-58c9-4516-a4ff-205a1f87cae7'),('d725be50-18eb-4d9c-b335-910ad6be6e1e','Color',0,'White',NULL),('e82e889a-ff36-49d1-9753-22fab52f833e','大小',0,'60,70,80',NULL);
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
INSERT INTO `bmerp_product_skus` VALUES (1497490072574889984,'22112200001-White-One-Size','White;One Size',112.00,'https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',60,60,60,60,_binary '','',0,'普通',NULL,'6a2b7a80-9b92-4f0e-b357-7bc0bdc61674','1492055167686688768'),(1505519522365325312,'174-34-1669173126161-03','小',50.00,'http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,0,0,0,_binary '','',0,'普通',NULL,'a6dcaa4c-ec97-438d-8086-6b58fe8b7408','1492055167686688768'),(1505519522365325313,'174-34-1669173122982-02','中',50.00,'http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,0,0,0,_binary '','',1,'普通',NULL,'a6dcaa4c-ec97-438d-8086-6b58fe8b7408','1492055167686688768'),(1505519522365325314,'174-34-1669173120174-01','大',50.00,'http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,0,0,0,_binary '','',2,'普通',NULL,'a6dcaa4c-ec97-438d-8086-6b58fe8b7408','1492055167686688768'),(1506901406026584064,'294-34-144109600983-03','',17.00,'http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'5893ef24-cd82-4394-b515-64d0220efc42',NULL),(1506901406026584065,'175-34-1669279512341-02','',17.00,'http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,0,0,0,_binary '','',1,'普通',NULL,'5893ef24-cd82-4394-b515-64d0220efc42',NULL),(1506901406026584066,'176-34-1677779584552-01','',17.00,'http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,0,0,0,_binary '','',2,'普通',NULL,'5893ef24-cd82-4394-b515-64d0220efc42',NULL),(1506907941188423680,'394-34-1449600983-03','',17.00,'http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',1,0,0,0,_binary '','',0,'普通',NULL,'be41c8fe-635c-4e35-bcd3-19ea0710537e',NULL),(1506907941188423681,'125-34-166919512341-02','',19.00,'http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,2,0,0,_binary '','',1,'普通',NULL,'be41c8fe-635c-4e35-bcd3-19ea0710537e',NULL),(1506907941188423682,'176-34-1677179584552-01','',18.00,'http://192.168.0.131:4568/api/resource/202211241439279042456.jpg',0,3,0,0,_binary '','',2,'普通',NULL,'be41c8fe-635c-4e35-bcd3-19ea0710537e',NULL),(1506991192900911104,'344-34-1411110983-03','',10.00,'https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',1,0,0,0,_binary '','',0,'普通',NULL,'ccb1f043-3c35-44da-bce5-a485e2d3f90c',NULL),(1506991192900911105,'125-34-13419512341-02','',19.00,'https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',0,2,0,0,_binary '','',1,'普通',NULL,'ccb1f043-3c35-44da-bce5-a485e2d3f90c',NULL),(1506991192900911106,'176-34-16741179584552-01','',11.00,'https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp',0,3,0,0,_binary '','',2,'普通',NULL,'ccb1f043-3c35-44da-bce5-a485e2d3f90c',NULL),(1507311249887608832,'174-34-1671430275796-01','',0.01,'http://192.168.18.122:4568/api/resource/202212191413126131442.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'1a92cd65-0399-4f5a-8983-ea566d9b8a01','1492055167686688768'),(1507315547870609408,'174-28-1671418849189-01','',0.01,'http://192.168.18.122:4568/api/resource/202212191101124621093.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'d0ae7f88-d7f0-4ecf-85be-7c992fd18853','1492055167686688768'),(1507632910646136832,'174-34-1671430275796-01','',0.01,'http://192.168.18.122:4568/api/resource/202212191413126131442.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'9a84a128-8f1f-4663-8fa3-418070140d3e','1505374058785554432'),(1507635872030281728,'174-35-1671165000459-01','',0.01,'http://192.168.18.122:4568/api/resource/202212161230168255723.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'e1c2e7c5-95de-4383-89b2-3a8703240e33','1505374058785554432'),(1507707915199324160,'2529','不可更改',10.00,'https://cross-border.xyhstore.com/api/resource/202209061442410581999.jpg',0,12,13,14,_binary '','',0,'普通',NULL,'3ba36bc3-f7e1-40d1-90d6-08c3e6cfeab4','1507668022771339264'),(1507731996913184768,'2547','130;白色',29.00,'https://cross-border.xyhstore.com/api/resource/202206241837044660019.png',0,12,11,13,_binary '','',0,'普通',NULL,'11092659-1be1-4b9f-880a-29d1e8919ab3',NULL),(1507731996913184769,'2548','120;白色',29.00,'https://cross-border.xyhstore.com/api/resource/202206241837044660019.png',0,12,11,13,_binary '','',1,'普通',NULL,'11092659-1be1-4b9f-880a-29d1e8919ab3',NULL),(1507731996913184770,'2549','110;白色',29.00,'https://cross-border.xyhstore.com/api/resource/202206241837044660019.png',0,12,11,13,_binary '','',2,'普通',NULL,'11092659-1be1-4b9f-880a-29d1e8919ab3',NULL),(1507731996913184771,'2550','100;白色',29.00,'https://cross-border.xyhstore.com/api/resource/202206241837044660019.png',0,12,11,13,_binary '','',3,'普通',NULL,'11092659-1be1-4b9f-880a-29d1e8919ab3',NULL),(1507983655903973376,'174-28-1671418849189-01','',0.01,'http://192.168.18.122:4568/api/resource/202212191101124621093.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'6389d68d-692e-47ea-823e-79441a8219f3','1505374058785554432'),(1507984497721757696,'174-37-1671162939701-01','',0.01,'http://192.168.18.122:4568/api/resource/202212161155554449198.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'36218519-6a33-4a91-84ff-5809b5e42966','1505374058785554432'),(1507984514155040768,'174-34-1671162782654-01','',0.01,'http://192.168.18.122:4568/api/resource/202212161153191051107.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'230340f2-36b9-4f46-a01b-a129cd4eceb6','1505374058785554432'),(1507984528331788288,'174-30-1671162437186-01','',0.01,'http://192.168.18.122:4568/api/resource/202212161150158329205.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'c54b474c-f1cf-4f0d-8e98-146ad37e4f32','1505374058785554432'),(1507984540822425600,'174-34-1669279600983-03','80',17.00,'http://192.168.0.131:4568/api/resource/202211241645360563476.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'78fa6fba-442a-440e-90f3-1eef80d26088','1505374058785554432'),(1507984540822425601,'174-34-1669279598711-02','70',17.00,'http://192.168.0.131:4568/api/resource/202211241645360563476.jpg',0,0,0,0,_binary '','',1,'普通',NULL,'78fa6fba-442a-440e-90f3-1eef80d26088','1505374058785554432'),(1507984540822425602,'174-34-1669279584552-01','60',17.00,'http://192.168.0.131:4568/api/resource/202211241645360563476.jpg',0,0,0,0,_binary '','',2,'普通',NULL,'78fa6fba-442a-440e-90f3-1eef80d26088','1505374058785554432'),(1507984554776875008,'174-34-1669272014509-04','粉',25.00,'http://192.168.0.131:4568/api/resource/202211241440225820754.png',0,0,0,0,_binary '','',0,'普通',NULL,'7f01a102-905c-45a4-8d24-eb296c7531c3','1505374058785554432'),(1507984554776875009,'174-34-1669272009510-03','蓝',25.00,'http://192.168.0.131:4568/api/resource/202211241440260583258.png',0,0,0,0,_binary '','',1,'普通',NULL,'7f01a102-905c-45a4-8d24-eb296c7531c3','1505374058785554432'),(1507984554776875010,'174-34-1669272005453-02','黄',25.00,'http://192.168.0.131:4568/api/resource/202211241440304702183.jpg',0,0,0,0,_binary '','',2,'普通',NULL,'7f01a102-905c-45a4-8d24-eb296c7531c3','1505374058785554432'),(1507984554776875011,'174-34-1669272002133-01','红',25.00,'http://192.168.0.131:4568/api/resource/202211241440349631511.jpg',0,0,0,0,_binary '','',3,'普通',NULL,'7f01a102-905c-45a4-8d24-eb296c7531c3','1505374058785554432'),(1507984567187820544,'174-34-1669174734792-03','小',20.00,'http://192.168.0.131:4568/api/resource/202211231139039005736.png',0,0,0,0,_binary '','',0,'普通',NULL,'4f602538-e29a-4cad-98b7-8bcc1947afef','1505374058785554432'),(1507984567187820545,'174-34-1669174732701-02','中',20.00,'http://192.168.0.131:4568/api/resource/202211231139104960937.png',0,0,0,0,_binary '','',1,'普通',NULL,'4f602538-e29a-4cad-98b7-8bcc1947afef','1505374058785554432'),(1507984567187820546,'174-34-1669174729414-01','大',20.00,'http://192.168.0.131:4568/api/resource/202211231139166090040.jpg',0,0,0,0,_binary '','',2,'普通',NULL,'4f602538-e29a-4cad-98b7-8bcc1947afef','1505374058785554432'),(1507984578239811584,'174-34-1669173126161-03','小',50.00,'http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,0,0,0,_binary '','',0,'普通',NULL,'f2adbed1-5293-4ab5-9581-46ab91575f9c','1505374058785554432'),(1507984578239811585,'174-34-1669173122982-02','中',50.00,'http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,0,0,0,_binary '','',1,'普通',NULL,'f2adbed1-5293-4ab5-9581-46ab91575f9c','1505374058785554432'),(1507984578239811586,'174-34-1669173120174-01','大',50.00,'http://192.168.0.131:4568/api/resource/202211231110592318001.png',0,0,0,0,_binary '','',2,'普通',NULL,'f2adbed1-5293-4ab5-9581-46ab91575f9c','1505374058785554432'),(1507984595352571904,'174-34-1669172949868-03','蓝色',10.00,'http://192.168.0.131:4568/api/resource/202211231107302835180.jpg',0,0,0,0,_binary '','',0,'普通',NULL,'712c5a28-8883-4a86-be87-4f252440940d','1505374058785554432'),(1507984595352571905,'174-34-1669172944860-02','黄色',10.00,'http://192.168.0.131:4568/api/resource/202211231107302835180.jpg',0,0,0,0,_binary '','',1,'普通',NULL,'712c5a28-8883-4a86-be87-4f252440940d','1505374058785554432'),(1507984595352571906,'174-34-1669172937875-01','红色',10.00,'http://192.168.0.131:4568/api/resource/202211231107302835180.jpg',0,0,0,0,_binary '','',2,'普通',NULL,'712c5a28-8883-4a86-be87-4f252440940d','1505374058785554432'),(1507987721488977920,'2551','140;熊猫',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611034795414.jpg',0,11,13,12,_binary '','',0,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977921,'2552','130;熊猫',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611034795414.jpg',0,11,13,12,_binary '','',1,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977922,'2553','120;熊猫',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611034795414.jpg',0,11,13,12,_binary '','',2,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977923,'2554','110;熊猫',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611034795414.jpg',0,11,13,12,_binary '','',3,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977924,'2555','兔子;140',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611113911538.jpg',0,11,13,12,_binary '','',4,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977925,'2556','兔子;130',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611113911538.jpg',0,11,13,12,_binary '','',5,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977926,'2557','兔子;120',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611113911538.jpg',0,11,13,12,_binary '','',6,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977927,'2558','110;兔子',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611113911538.jpg',0,11,13,12,_binary '','',7,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977928,'2559','老虎;140',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611199399390.jpg',0,11,13,12,_binary '','',8,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977929,'2560','老虎;130',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611199399390.jpg',0,11,13,12,_binary '','',9,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977930,'2561','老虎;120',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611199399390.jpg',0,11,13,12,_binary '','',10,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977931,'2562','110;老虎',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611199399390.jpg',0,11,13,12,_binary '','',11,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977932,'2563','恐龙;140',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611282454421.jpg',0,11,13,12,_binary '','',12,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977933,'2564','恐龙;130',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611282454421.jpg',0,11,13,12,_binary '','',13,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977934,'2565','恐龙;120',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611282454421.jpg',0,11,13,12,_binary '','',14,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872'),(1507987721488977935,'2566','110;恐龙',18.89,'https://cross-border.xyhstore.com/api/resource/202206131611282454421.jpg',0,11,13,12,_binary '','',15,'普通',NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872');
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
INSERT INTO `bmerp_product_skus_options` VALUES ('01086dee-9e22-41c7-975b-180193b4c039','大小','60','1507984540822425602'),('01553fa4-e95c-47d3-ab48-1d63bacec1f8','款式','兔子',NULL),('0468de45-3090-48f3-b35a-47d4e6122d7d','款式','恐龙','1507987721488977933'),('04b23b21-dd36-455e-b13a-7ddb9b813487','大小','大',NULL),('0658127e-176d-4dc6-a7bf-16736db4f832','款式','兔子',NULL),('06de5556-db6c-45e0-80a6-43db6c793546','尺寸','130',NULL),('090ca312-3906-4c7e-b650-b0bad4573720','款式','老虎','1507987721488977928'),('09d10c4b-c90b-4292-b890-28e832d3cddc','尺寸','120',NULL),('0c9d677e-27ce-4f7e-a32b-dfa9edca5a32','颜色','粉','1507984554776875008'),('0d400e3e-94af-43dc-a5bd-911255606336','大小','80','1507984540822425600'),('15a1c253-3ba6-4072-a00a-fc5daf498263','款式','熊猫','1507987721488977923'),('1ee81f40-df99-4280-9412-fdc30e977f45','款式','兔子','1507987721488977925'),('1f4d5185-3597-459c-be1c-835d2beb71c7','大小','中',NULL),('1fa14efc-bdd4-4071-bba8-5e872ff592fd','尺寸','140',NULL),('20690694-f11b-4953-be09-2c7fcc76d28e','尺寸','120','1507987721488977926'),('25a445d7-126f-495f-8efd-41f9f1010782','尺寸','140',NULL),('266a960f-0ba5-4501-9466-3ae2f43cef04','款式','不可更改',NULL),('2782c21b-5ab2-49e7-9be0-21378b781b9a','大小','60',NULL),('278dcef6-a541-4f1f-8aac-6546b93216a6','大小','70','1507984540822425601'),('2838018d-2a50-494e-aac5-4de1ad5bfe9c','款式','兔子','1507987721488977927'),('2853e4fa-97b3-4966-932d-84f95c7c2bdc','大小','小','1507984578239811584'),('285b5238-d9f3-457f-963b-41e24dd03194','尺寸','110',NULL),('2b6c19d1-0f45-42ac-aafe-2f3ea6c8dfb9','颜色','白色','1507731996913184768'),('2d8fb19f-3a4f-468a-bf55-ff767ddda83b','颜色','蓝',NULL),('332723e1-1571-4c94-9071-b2b18be958aa','大小','中',NULL),('33b025e3-7199-47ea-947b-452c45a4158e','尺寸','110','1507731996913184770'),('3b23be5f-d883-4a0a-ad86-746fce99b09d','尺寸','120',NULL),('3b3c7de7-55ed-4bcc-a1f8-0ab17275bd89','Color','White','1497490072574889984'),('3ba23911-9d90-4b8b-8554-f2cd28c3be0a','大小','大',NULL),('3edaed71-e130-47f4-a5be-f79ed0fde341','尺寸','120',NULL),('41c3b6ea-4bc0-445e-983a-e3f2f0a4901b','款式','老虎',NULL),('45e44e8e-be8c-4ecf-a08f-d202f5b205ca','款式','恐龙',NULL),('462c7495-176a-460f-8acf-97bb0cec7e97','Size','One Size',NULL),('4676029c-cc4c-4c40-bc01-f84da7d09ac4','款式','老虎',NULL),('4b367406-5dec-4373-bb00-6a543cbdc3be','尺寸','130','1507987721488977925'),('4ee6624b-dbe5-45ba-b256-3d9f5d1d375d','大小','大',NULL),('50b285dc-8400-45a2-a9af-b57293b74ac9','款式','恐龙',NULL),('53c9b7e8-1216-4291-900e-4d3959957302','款式','熊猫','1507987721488977921'),('576fbf9c-61f1-417f-86db-b06a23459a85','颜色','黄色','1507984595352571905'),('57748745-8908-4ad8-8bec-aba354652535','款式','老虎',NULL),('5b090891-7c0c-441e-bb7b-7af15478bdeb','尺寸','130','1507987721488977929'),('5c628a33-08d0-48ed-b496-777f17bac244','尺寸','130',NULL),('6383c2de-adc4-4948-ac03-1318042bd079','颜色','蓝','1507984554776875009'),('66aaa42b-6ab5-48a6-a7d9-c5401c6cc872','尺寸','140','1507987721488977920'),('68ce9fe3-ae19-4e77-a130-cf319c2baa13','款式','不可更改','1507707915199324160'),('6a23c7a8-4cfa-492d-8c97-7ec17b10bb48','尺寸','120','1507987721488977930'),('6a99d105-8409-474d-9802-c6d98cf3a9d1','大小','中',NULL),('6cf1ca88-97bc-419f-987a-59554d13ea16','大小','大',NULL),('72e8f2cc-b680-40c2-8c21-bfa085ac8105','尺寸','110','1507987721488977923'),('739f2738-bf8c-495c-b44a-a58fb92aa18c','尺寸','140',NULL),('7436add5-7513-4f5c-bfe9-d63a7b4051a9','尺寸','110',NULL),('75dbfb28-490c-484e-b530-78971065c36e','款式','老虎','1507987721488977930'),('772ed66a-0913-4a64-ae34-802b0f365cdf','大小','小',NULL),('7b4d84a3-b857-4e07-ac6f-b54b99910264','款式','恐龙','1507987721488977932'),('7b90d9c4-c910-46e9-8c0d-aa7a04a57a09','款式','兔子',NULL),('7ba2da2c-5df2-492e-9e1c-2381fd635e12','尺寸','110',NULL),('7d5cc5a7-eaaa-4a39-b7f1-6bfa045f7b4a','款式','恐龙','1507987721488977934'),('80de0219-d527-4729-8dde-aa9c08fbdf0c','尺寸','130',NULL),('84a692e4-3fad-41e0-8204-9a9021867b86','款式','熊猫',NULL),('87625f32-507c-43c6-b47d-9589ca8cc01b','尺寸','140','1507987721488977928'),('88943cb1-1e92-425f-a64e-16b17480859a','Color','White',NULL),('88d070da-da87-4e61-bec1-c87cf2f60246','大小','小',NULL),('89ca2653-15b9-49b7-8a15-92093ec64762','款式','熊猫',NULL),('8b2aa487-df57-4d5c-a669-d64d84034fd5','颜色','黄色',NULL),('8e262c85-a7ae-4e6a-af7f-100966d2acc1','颜色','红',NULL),('91e1e2e4-805f-4e0d-99e0-a04ef907da1b','尺寸','140','1507987721488977924'),('9547effc-af0b-483a-ae4d-76f31c53c61e','颜色','黄','1507984554776875010'),('9874ed8b-9e3d-47c3-bf00-8f372e7ee545','款式','老虎','1507987721488977929'),('9bd06a07-b13f-44f2-9367-3fd2f1799aeb','尺寸','120','1507987721488977934'),('9c387247-58f5-45f6-9c82-f84f0e1ff12d','款式','熊猫',NULL),('a1a2425d-05ce-4704-863e-5692ce290255','大小','70',NULL),('a2093044-56d0-45ef-ab50-63b2c80226ab','大小','80',NULL),('a6a98bf8-d9e2-4ac4-8e2f-2462b70cb1a9','大小','大','1507984578239811586'),('abc88409-9151-466a-8c8d-30068b81e16c','款式','兔子',NULL),('af5dcd41-725d-44ad-b11c-96a0b4570610','款式','老虎','1507987721488977931'),('b0348e12-3a0d-4b12-8c93-7523954e39c1','大小','中','1505519522365325313'),('b0954dbc-7432-4ed9-9809-9cf5379ad475','大小','小','1507984567187820544'),('b17da3f6-7775-4ad0-a841-9207a7d98e8f','大小','中','1507984567187820545'),('b2c86e1e-589b-459c-9c40-513766f58923','大小','中','1507984578239811585'),('b4a9a4be-b026-42b3-b5e4-b132c1d95834','大小','小',NULL),('b6ad7b32-0cc3-4666-ac76-bf39b6c2419e','款式','恐龙',NULL),('b72fee25-582f-4582-beb3-d2ff251fcf88','颜色','黄',NULL),('b8590408-f036-43bf-9fea-0c1e6607034d','颜色','白色','1507731996913184771'),('bb5b65e9-0fc3-45f9-9490-c09a1879787e','款式','恐龙',NULL),('bb68a5b9-2187-4e7a-a2c9-84db4b90c309','尺寸','140',NULL),('be5c2398-0a47-437f-a364-0882d96db4b5','尺寸','120','1507987721488977922'),('c0c76238-6527-476e-874e-3393be583e6f','尺寸','110','1507987721488977927'),('c3322fab-c231-4772-b901-08cdd0c69d53','款式','老虎',NULL),('c35c33af-d541-445d-b677-8b294b37ec0d','大小','大',NULL),('c3dde7e8-cd70-4d82-a7fd-84d50de97615','颜色','红','1507984554776875011'),('c4437bcf-fa71-4786-9cb1-10f2f142d68c','款式','熊猫',NULL),('c7721c82-129d-4d26-a807-ddbda9e7fe6e','尺寸','110','1507987721488977935'),('c809df51-a1ca-4b5e-9075-ea97bf02eba4','大小','中',NULL),('cdf15fa2-0de4-4642-a8fe-da67a9443875','大小','小',NULL),('cf69acd2-60bc-4423-afbe-38d15549b7f8','大小','大',NULL),('d153cce6-2862-44ed-9831-d7104fc51240','大小','大','1507984567187820546'),('d24577f1-7a42-4615-9b47-d52e73eb48bf','颜色','红色','1507984595352571906'),('d41c1e24-1180-4386-8126-d8fda95d80a2','款式','恐龙','1507987721488977935'),('d7a8bcb6-d6ab-4290-8507-1981d7a37f53','大小','80',NULL),('d7bcb8f1-f704-4c7a-beda-83dfa27a203f','款式','兔子','1507987721488977924'),('d7efdc4a-a394-4315-ad20-826c32ed6d55','大小','大','1505519522365325314'),('dabd827c-ea7b-43b2-acf7-ef14aece7906','尺寸','140','1507987721488977932'),('daddbf49-960e-4447-98d1-3762864fe997','颜色','白色','1507731996913184770'),('daeac2bb-e494-4d66-8c18-77cb166bd5fb','大小','小',NULL),('db438f51-234a-4343-a8e9-b31ec8b7fe18','尺寸','130','1507731996913184768'),('db735bd9-f4be-4bda-8799-3c00446e2801','Size','One Size','1497490072574889984'),('db831dae-7e24-4634-8d2d-977206916fb4','大小','小','1505519522365325312'),('dc94307d-bfc1-4152-87a5-43c22dc3e91c','颜色','白色','1507731996913184769'),('dffd0dbf-0309-4d71-9303-de49bce83bfa','尺寸','100','1507731996913184771'),('e1c4ebde-dfae-4eed-bd14-e3534545755f','颜色','粉',NULL),('e4d2c63a-755e-4ba7-a087-98ff69dbcc82','颜色','红色',NULL),('e53aeb9c-d8cd-495f-8351-9168008cef62','大小','60',NULL),('e5820040-07b3-4118-a291-e340ca2f58b7','尺寸','120','1507731996913184769'),('e75ab0c6-59e2-45d2-93c1-246eb2946e9f','款式','熊猫','1507987721488977922'),('ea386833-6fed-4af4-83e6-50176a9ae2d3','大小','中',NULL),('eed57b4e-7f0e-423e-9e22-dbaaaffbc514','款式','兔子','1507987721488977926'),('f059b754-3d0f-4e29-afe0-47cf7e6c5124','颜色','蓝色',NULL),('f1c45825-cc2c-4f73-b073-c3499a917992','尺寸','110',NULL),('f1c731ef-ab8a-4429-9d32-6922b587489e','大小','小',NULL),('f2e65ab1-6d79-4685-9cf2-3f7dd6077cbc','尺寸','130','1507987721488977921'),('f3fa0e68-7da5-44b4-92c0-2041b4dbf194','大小','70',NULL),('f5079fc4-2bc8-40c1-b959-3d9d3c3c9803','款式','熊猫','1507987721488977920'),('f7ed166c-1edd-41b3-9983-cbac39c7b443','大小','中',NULL),('f88cfefb-6f3d-454e-8e56-a3b385853511','尺寸','110','1507987721488977931'),('fa14a08d-0b41-4b29-9498-a0c1a2482f0c','颜色','蓝色','1507984595352571904'),('fb852d10-bf17-419a-bd33-bc5f852aff16','尺寸','120',NULL),('fbf920bb-907d-4704-b0a7-12ca333c42b4','尺寸','130','1507987721488977933'),('ff823b17-4a08-4f30-bf8e-a246c272c50d','尺寸','130',NULL);
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
INSERT INTO `bmerp_product_spu` VALUES (1497121263854817280,'22112200001','重磅卫衣男秋冬款连帽加绒加厚美式复古运动外套春秋潮牌宽松男装bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb','32781a0f-50bd-4fd5-b15a-af5500e69a33','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp','手工创建','b4643438-15b5-4fee-9071-af5600fdece6','',_binary '','爆款','2022-11-22 14:25:24','2022-12-19 10:25:57',NULL,NULL,NULL,'6a2b7a80-9b92-4f0e-b357-7bc0bdc61674','1492055167686688768'),(1506901405753954304,'388','千样公主裙-小童','bd755fca-0c6f-4727-a39c-af4801414ca2','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-19 14:08:12','2022-12-19 14:08:12',NULL,NULL,NULL,'5893ef24-cd82-4394-b515-64d0220efc42',NULL),(1506907941175840768,'3818','千样公主裙-小童','bd755fca-0c6f-4727-a39c-af4801414ca2','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-19 14:34:10','2022-12-19 14:34:10',NULL,NULL,NULL,'be41c8fe-635c-4e35-bcd3-19ea0710537e',NULL),(1506991192682807296,'38118','1样公主裙-小童','bd755fca-0c6f-4727-a39c-af4801414ca2','https://gw.alicdn.com/imgextra/i3/2966343102/O1CN01Z8e6HU1YmlNKvfP4k_!!2966343102.jpg_Q75.jpg_.webp','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-19 20:04:58','2022-12-19 20:04:58',NULL,NULL,NULL,'ccb1f043-3c35-44da-bce5-a485e2d3f90c',NULL),(1507632910440615936,'195','百样裙子-童童','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212191411243212073.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-21 14:34:56','2022-12-22 13:57:42',NULL,NULL,NULL,'9a84a128-8f1f-4663-8fa3-418070140d3e','1505374058785554432'),(1507635872026087424,'193','阿松大','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212161230088036451.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-21 14:46:42','2022-12-22 13:53:57',NULL,NULL,NULL,'e1c2e7c5-95de-4383-89b2-3a8703240e33','1505374058785554432'),(1507707914964443136,'125','古风绣花旗袍裙','65f0666e-b5f5-4501-b86f-af7201386cb2','https://cross-border.xyhstore.com/api/resource/202209061441542937706.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-21 19:32:58','2022-12-21 19:34:29',NULL,NULL,NULL,'3ba36bc3-f7e1-40d1-90d6-08c3e6cfeab4','1507668022771339264'),(1507731996833492992,'127','【纯棉短袖T恤】2022夏季新款女童可爱动漫印花休闲打底衫','65f0666e-b5f5-4501-b86f-af7201386cb2','https://cross-border.xyhstore.com/api/resource/202206241836203241031.png','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-21 21:08:40','2022-12-21 21:08:40',NULL,NULL,NULL,'11092659-1be1-4b9f-880a-29d1e8919ab3',NULL),(1507983655807504384,'194','如图如图','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212191100582177182.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:48:40','2022-12-22 13:57:46',NULL,NULL,NULL,'6389d68d-692e-47ea-823e-79441a8219f3','1505374058785554432'),(1507984497713369088,'192','啊实打实','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212161155460048113.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:01','2022-12-22 13:54:19',NULL,NULL,NULL,'36218519-6a33-4a91-84ff-5809b5e42966','1505374058785554432'),(1507984514062766080,'191','阿斯顿撒旦','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212161153114146882.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:05','2022-12-22 13:58:44',NULL,NULL,NULL,'230340f2-36b9-4f46-a01b-a129cd4eceb6','1505374058785554432'),(1507984528323399680,'190','测试测试','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.18.122:4568/api/resource/202212161149555482485.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:08','2022-12-22 13:58:40',NULL,NULL,NULL,'c54b474c-f1cf-4f0d-8e98-146ad37e4f32','1505374058785554432'),(1507984540797259776,'188','百样公主裙-小童','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.0.131:4568/api/resource/202211241645360563476.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:11','2022-12-22 13:58:36',NULL,NULL,NULL,'78fa6fba-442a-440e-90f3-1eef80d26088','1505374058785554432'),(1507984554768486400,'187','百样公主裙-大童','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.0.131:4568/api/resource/202211241439279042456.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:14','2022-12-22 13:58:32',NULL,NULL,NULL,'7f01a102-905c-45a4-8d24-eb296c7531c3','1505374058785554432'),(1507984567175237632,'186','百样裙子-小童','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.0.131:4568/api/resource/202211231138046423659.png','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:17','2022-12-22 13:58:29',NULL,NULL,NULL,'4f602538-e29a-4cad-98b7-8bcc1947afef','1505374058785554432'),(1507984578231422976,'185','百样裙子-大童','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.0.131:4568/api/resource/202211231110592318001.png','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:20','2022-12-22 13:58:18',NULL,NULL,NULL,'f2adbed1-5293-4ab5-9581-46ab91575f9c','1505374058785554432'),(1507984595339988992,'184','百样裙子-童装','89b8e168-1c20-42eb-8bcc-af7000ef029b','http://192.168.0.131:4568/api/resource/202211231107302835180.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 13:52:24','2022-12-22 13:58:23',NULL,NULL,NULL,'712c5a28-8883-4a86-be87-4f252440940d','1505374058785554432'),(1507987721472200704,'128','宝宝夏装婴儿半袖纯棉t恤衫儿童冰丝短袖男童女童上衣服小童夏季','65f0666e-b5f5-4501-b86f-af7201386cb2','https://cross-border.xyhstore.com/api/resource/202206131609244517203.jpg','平台同步','00000000-0000-0000-0000-000000000000','',_binary '\0','','2022-12-22 14:04:49','2022-12-22 14:05:03',NULL,NULL,NULL,'b2af8220-58c9-4516-a4ff-205a1f87cae7','1507713422647631872');
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
INSERT INTO `bmerp_properties` VALUES ('选品中心','1492055167686688768','a7437ad68d0f457b9788e17f92e13b0f','15060715230','IP白名单','SHA256',NULL,NULL),('默认应用','1505374058785554432','8609ec0f8fff46af9f0c0dc6a4bf9316','13003988209','签名','sha256','已上线','all_read,all_write'),('默认应用','1507349451440668672','950cf874cf114015bddf9336e81c8c64','18596945810','签名',NULL,'已上线','all_read,all_write'),('默认应用','1507713422647631872','2bb5216c0c624f7d9aa7ea1afab00c3b','15985852996','签名','sha256','已上线','all_read,all_write'),(NULL,'1507668022771339264','b80ced401e0749e2941b0e1968314404','15959011762',NULL,'sha256',NULL,NULL);
/*!40000 ALTER TABLE `bmerp_properties` ENABLE KEYS */;
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
  `id` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '店铺id',
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
INSERT INTO `bmerp_store` VALUES ('1492814222537527296','12530610@qq.com-th','Lazada','2022-11-10 17:10:45','2022-12-10 17:35:13','1492055167686688768',NULL),('1492814222604636160','12530610@qq.com-sg','Lazada','2022-11-10 17:10:45','2022-12-10 17:35:14','1492055167686688768',NULL),('1492814222633996288','12530610@qq.com-vn','Lazada','2022-11-10 17:10:45','2022-12-10 17:35:16','1492055167686688768',NULL),('1492814222659162112','12530610@qq.com-ph','Lazada','2022-11-10 17:10:45','2022-12-10 17:35:17','1492055167686688768',NULL),('1492814222734659584','12530610@qq.com-id','Lazada','2022-11-10 17:10:45','2022-12-10 17:35:18','1492055167686688768',NULL),('1492814222759825408','12530610@qq.com-my','Lazada','2022-11-10 17:10:45','2022-12-10 17:35:20','1492055167686688768',NULL),('1497119990426374144','自有店铺','Self','2022-11-22 14:20:20','2022-11-22 14:20:20','1492055167686688768',NULL);
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

-- Dump completed on 2022-12-22 14:28:03
