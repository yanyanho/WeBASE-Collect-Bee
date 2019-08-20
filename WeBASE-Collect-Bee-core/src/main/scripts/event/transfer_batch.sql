CREATE TABLE b_a_c003_transfer_batch_event (
  	pk_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  	block_height bigint(20) NOT NULL COMMENT '交易所在块高',
	_operator varchar(255) NOT NULL,
	_from varchar(255) NOT NULL,
	_to varchar(255) NOT NULL,
	_ids varchar(2048) NOT NULL,
	_values varchar(2048) NOT NULL,
	KEY `_ids` (`_ids`),
  	`updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 	PRIMARY KEY (pk_id)
) COMMENT='b_a_c003_transfer_batch_event' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;