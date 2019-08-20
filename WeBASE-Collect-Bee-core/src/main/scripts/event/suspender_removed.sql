CREATE TABLE b_a_c002_suspender_removed_event (
  	pk_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  	block_height bigint(20) NOT NULL COMMENT '交易所在块高',
	_account varchar(255) NOT NULL,
  	`updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 	PRIMARY KEY (pk_id)
) COMMENT='b_a_c002_suspender_removed_event' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;