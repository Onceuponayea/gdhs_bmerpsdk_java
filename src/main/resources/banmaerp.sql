-- MySQL dump 10.13  Distrib 8.0.30, for Linux (x86_64)
--
-- Host: localhost    Database: banmaerp_ljl
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
  `bmerp_account` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bmerp_account_data_access`
--

DROP TABLE IF EXISTS `bmerp_account_data_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_account_data_access` (
  `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id',
  `data_access_mode` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限模式',
  `data` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '店铺id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bmerp_order`
--

DROP TABLE IF EXISTS `bmerp_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order` (
  `order_UUID` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `order_masterId` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `bmerp_order_fulfillment`
--

DROP TABLE IF EXISTS `bmerp_order_fulfillment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order_fulfillment` (
  `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id',
  `express_company` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流公司',
  `express_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流单号',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间，可能为null',
  `order_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`order_refund_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `bmerp_order_tracking`
--

DROP TABLE IF EXISTS `bmerp_order_tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order_tracking` (
  `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id',
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态',
  `express_no` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流单号',
  `receipted_time` datetime DEFAULT NULL COMMENT '签收时间，可以为null',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `order_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bmerp_order_tracking_info`
--

DROP TABLE IF EXISTS `bmerp_order_tracking_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_order_tracking_info` (
  `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id',
  `date` datetime DEFAULT NULL COMMENT '时间',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述信息',
  `location` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '位置信息，可能为空',
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原始状态',
  `track_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '物流跟踪id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bmerp_product`
--

DROP TABLE IF EXISTS `bmerp_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_product` (
  `product_spuid` bigint DEFAULT NULL,
  `product_DescriptionId` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_uuid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`product_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`spu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `bmerp_properties`
--

DROP TABLE IF EXISTS `bmerp_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bmerp_properties` (
  `X_BANMA_APP_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '应用名',
  `X_BANMA_MASTER_APP_ID` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '应用id',
  `X_BANMA_MASTER_APP_SECRET` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '应用密钥',
  `X_BANMA_MASTER_APP_ACCOUNT` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '应用账户',
  `X_BANMA_MASTER_SIGN_METHOD` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '认证方式',
  `X_BANMA_MASTER_SIGN_ALGORITHM` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '签名加密',
  `X_BANMA_MASTER_APP_STATUS` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `X_BANMA_MASTER_APP_SCOPES` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  UNIQUE KEY `bmerp_properties_pk` (`X_BANMA_MASTER_APP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-09 12:36:28
