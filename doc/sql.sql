CREATE TABLE `commodity_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '类型名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_delete` int(11) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


CREATE TABLE `number_manage` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `key` varchar(11) DEFAULT NULL COMMENT 'NO key',
  `value` int(11) DEFAULT NULL COMMENT 'no当前值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


CREATE TABLE `recycle_collector` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `collector_no` varchar(100) DEFAULT '' COMMENT '用户编号',
  `wechat_no` varchar(100) DEFAULT NULL COMMENT '微信号',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` int(11) DEFAULT NULL COMMENT '用户状态',
  `address` varchar(100) DEFAULT NULL COMMENT '现住址',
  `avatar` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_delete` int(11) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


CREATE TABLE `recycle_commodity` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `collector_no` varchar(100) NOT NULL DEFAULT '' COMMENT '收集人NO',
  `village_id` int(11) DEFAULT NULL COMMENT '小区ID',
  `commodity_no` varchar(100) DEFAULT '' COMMENT '商品编号',
  `commodity_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `commodity_picture` varchar(1000) DEFAULT NULL COMMENT '商品图片',
  `img_url` varchar(2000) DEFAULT NULL COMMENT '图片详情链接',
  `commodity_status` int(11) DEFAULT NULL COMMENT '商品状态',
  `commodity_type` int(11) DEFAULT NULL COMMENT '商品类型',
  `recycle_time` datetime DEFAULT NULL COMMENT '回收时间',
  `recycle_price` decimal(11,2) DEFAULT NULL COMMENT '回收价格',
  `expect_sell_price` decimal(11,2) DEFAULT NULL COMMENT '预期出售价格',
  `actual_sell_price` decimal(11,2) DEFAULT NULL COMMENT '实际出售价格',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_delete` int(11) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;


CREATE TABLE `recycle_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `title` varchar(1000) DEFAULT NULL COMMENT '消息头',
  `message` varchar(1000) DEFAULT NULL COMMENT '消息',
  `text` text COMMENT '消息体',
  `is_delete` int(11) DEFAULT NULL COMMENT '是否删除 1删除 0未删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

CREATE TABLE `recycle_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_no` varchar(100) DEFAULT NULL COMMENT '订单编号',
  `uid` varchar(100) DEFAULT NULL COMMENT '商品回收用户唯一编号',
  `city` int(11) NOT NULL COMMENT '下单城市ID',
  `address` varchar(255) DEFAULT NULL COMMENT '下单地址（必备）',
  `phone` varchar(20) DEFAULT NULL COMMENT '下单联系手机号（必备）',
  `start_time` datetime DEFAULT NULL COMMENT '回收开始时间点',
  `end_time` datetime DEFAULT NULL COMMENT '回收截止时间点',
  `order_img` varchar(100) NOT NULL DEFAULT '' COMMENT '订单照片',
  `status` int(11) DEFAULT NULL COMMENT '订单状态（1:已下单，2:已完成）',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_by` varchar(100) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

CREATE TABLE `recycle_reverse_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_no` varchar(100) DEFAULT NULL COMMENT '订单号',
  `commodity_no` varchar(100) DEFAULT NULL COMMENT '商品编号',
  `uid` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `address` varchar(255) DEFAULT NULL COMMENT '下单地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '下单手机号',
  `start_time` datetime DEFAULT NULL COMMENT '送货开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '送货结束时间',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `remark` varchar(255) DEFAULT '0' COMMENT '备注',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(100) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_by` varchar(100) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `recycle_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_no` varchar(11) DEFAULT NULL COMMENT '用户编号',
  `open_id` varchar(11) DEFAULT NULL COMMENT '微信唯一值',
  `phone` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `longitude` varchar(11) NOT NULL DEFAULT '' COMMENT '经度',
  `latitude` varchar(11) NOT NULL DEFAULT '' COMMENT '纬度',
  `is_delete` int(11) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(11) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_by` varchar(11) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;