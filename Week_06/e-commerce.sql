SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `store` CASCADE
;

DROP TABLE IF EXISTS `orders` CASCADE
;

DROP TABLE IF EXISTS `order_details` CASCADE
;

DROP TABLE IF EXISTS `goods_category` CASCADE
;

DROP TABLE IF EXISTS `goods` CASCADE
;

DROP TABLE IF EXISTS `customer` CASCADE
;

CREATE TABLE `store` (
  `store_id` bigint NOT NULL COMMENT 'store_id',
  `store_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'store_name',
  `reg_date` timestamp NULL DEFAULT NULL COMMENT 'reg_date',
  `current_rank` tinyint DEFAULT NULL COMMENT 'current_rank',
  `admin_id` bigint DEFAULT NULL COMMENT 'admin_id',
  `updater` bigint DEFAULT NULL COMMENT 'updater',
  `update_time` timestamp NULL DEFAULT NULL COMMENT 'update_time',
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `orders` (
  `order_id` bigint NOT NULL COMMENT 'order_id',
  `customer_id` bigint NOT NULL COMMENT 'customer_id',
  `customer_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'customer_name',
  `deliver_address` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `total_price` double DEFAULT '0' COMMENT 'total_price',
  `payed_amount` double DEFAULT '0' COMMENT 'payed_amount',
  `discount` decimal(5,2) DEFAULT '1.00' COMMENT 'discount',
  `order_status` tinyint DEFAULT '1' COMMENT 'order_status',
  `create_time` timestamp NULL DEFAULT NULL COMMENT 'create_time',
  `pay_cust_id` bigint DEFAULT NULL COMMENT 'pay_cust_id',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT 'pay_time',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `order_details` (
  `detail_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  `seq` smallint NOT NULL,
  `store_id` bigint DEFAULT NULL,
  `goods_id` bigint DEFAULT NULL,
  `goods_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'goods_name',
  `price` double DEFAULT NULL,
  `payed_amount` double DEFAULT NULL,
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'remark',
  `category_id` int DEFAULT NULL,
  `updater` bigint DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `goods_category` (
  `category_id` int NOT NULL COMMENT 'category_id',
  `cate_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT 'cate_name',
  `creator` bigint DEFAULT NULL COMMENT 'creator',
  `create_time` timestamp NULL DEFAULT NULL COMMENT 'create_time',
  `updater` bigint DEFAULT NULL COMMENT 'updater',
  `update_time` timestamp NULL DEFAULT NULL COMMENT 'update_time',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `goods` (
  `goods_id` bigint NOT NULL COMMENT 'goods_id',
  `category_id` int DEFAULT '0' COMMENT 'goods_category',
  `store_id` bigint DEFAULT '0' COMMENT 'store_id',
  `goods_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT 'show_name',
  `price` double DEFAULT '0' COMMENT 'price',
  `discount` double DEFAULT '1' COMMENT 'discount',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT 'description',
  `goods_flag` tinyint DEFAULT '1' COMMENT 'off_flag',
  `creater` bigint DEFAULT '0' COMMENT 'creater',
  `create_time` timestamp NULL DEFAULT NULL COMMENT 'create_time',
  `updater` bigint DEFAULT '0' COMMENT 'updater',
  `update_time` timestamp NULL DEFAULT NULL COMMENT 'update_time',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `customer` (
  `customer_id` bigint NOT NULL COMMENT 'customer_id',
  `nickname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'nickname',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'name',
  `cert_card` varchar(18) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'cert_card',
  `phone_number` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'phone_number',
  `reg_date` timestamp NULL DEFAULT NULL COMMENT 'reg_date',
  `update_time` timestamp NULL DEFAULT NULL COMMENT 'update_time',
  `updater` bigint DEFAULT NULL COMMENT 'updater',
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS=1