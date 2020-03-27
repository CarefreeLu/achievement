
CREATE TABLE `trade_data_index` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`trade_no`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交易号' ,
`business_type`  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '行业类型(交易所属行业类型，可以找到对应的行业交易信息表)' ,
`business_platform_id`  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '平台ID(交易所属行业平台身份)' ,
`identity_id`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份ID(归属人/企业身份ID（微信/支付宝/诺诺）)' ,
`identity_source`  varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份来源' ,
`create_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `idx_trade_no_business_platform_id_business_type` (`trade_no`, `business_platform_id`, `business_type`) USING BTREE 
)
;

CREATE TABLE `trade_data_operation_invoice_index` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`trade_data_index_id`  bigint(20) NOT NULL ,
`operation_serial_no`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`create_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `idx_trade_data_index_id` (`trade_data_index_id`) USING BTREE 
)
;

CREATE TABLE `trade_data_taxi` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一编号' ,
`trade_data_index_id`  bigint(20) NOT NULL COMMENT '交易数据索引表ID' ,
`trade_no`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交易号(车牌号+时间 格式：yyyyMMddHHmmss)' ,
`business_platform_id`  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`payee_tax_no`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款方税号（销方税号）' ,
`amount`  decimal(15,2) NULL DEFAULT NULL COMMENT '交易金额(换算成元)' ,
`license_plate_no`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号' ,
`device_no`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备号(出租车车载设备)' ,
`start_time`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上车时间' ,
`end_time`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '下车时间' ,
`start_site`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上车地点' ,
`end_site`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下车地点' ,
`start_longitude`  decimal(12,8) NULL DEFAULT NULL COMMENT '上车经度' ,
`start_latitude`  decimal(12,8) NULL DEFAULT NULL COMMENT '上车纬度' ,
`start_azimuth`  decimal(12,8) NULL DEFAULT NULL COMMENT '上车方位角' ,
`end_longitude`  decimal(12,8) NULL DEFAULT NULL COMMENT '下车经度' ,
`end_latitude`  decimal(12,8) NULL DEFAULT NULL COMMENT '下车纬度' ,
`end_azimuth`  decimal(12,8) NULL DEFAULT NULL COMMENT '下车方位角' ,
`unit_price`  decimal(8,2) NULL DEFAULT NULL COMMENT '单价(换算成元)' ,
`mileage`  decimal(8,2) NULL DEFAULT NULL COMMENT '里程(换算成公里)' ,
`waiting_time`  bigint(20) NULL DEFAULT NULL COMMENT '等候时间' ,
`travel_status`  tinyint(1) NULL DEFAULT NULL COMMENT '行程状态 0：行程中，1：行程结束，2：行程异常' ,
`create_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `idx_trade_no_business_platform_id` (`trade_no`, `business_platform_id`) USING BTREE 
)
;