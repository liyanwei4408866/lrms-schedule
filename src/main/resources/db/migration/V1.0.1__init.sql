/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     2016/7/6 16:27:44                            */
/*==============================================================*/
create table `lrms_access_control_record` (
	id varchar(40) not null,
	sn varchar(40) not null COMMENT '4-7 sn设备号',
	record_index int COMMENT '8-11 记录索引 0 位最早一条 -1(0xffffffff)位最后一条',
	record_type TINYINT COMMENT '12 记录类型 0x00=无记录 0x01=刷卡记录 0x02=门磁/按钮/启动/远程开门 0x03=报警记录 0xFF=索引被覆盖',
	record_result TINYINT COMMENT '13 有效性 0 不通过 1 通过',
	doorno TINYINT COMMENT '14 门号',
	direction TINYINT COMMENT '15 进门出门 1 进门 2 出门',
	cardno varchar(100) not null COMMENT '16-19 卡号',
	record_accesstime datetime COMMENT '20-26 刷卡时间',
	record_errorcode TINYINT COMMENT '27 错误码',
	batch_id varchar(40) not null,
	PRIMARY KEY (`id`),
	KEY `Index_access_control_record_sn` (`sn`),
	KEY `Index_access_control_record_batch_id` (`batch_id`),
	KEY `Index_access_control_record_cardno` (`cardno`) USING BTREE
);

alter table accesslog modify column id varchar(40);
