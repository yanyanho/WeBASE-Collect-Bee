CREATE TABLE b_a_c003_create_method (
  	pk_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  	block_height bigint(20) NOT NULL COMMENT '交易所在块高',
  	tx_hash varchar(100) NOT NULL COMMENT '交易哈希',
	_initial_supply bigint NOT NULL,
	_uri varchar(128) NOT NULL,
	_data varchar(512) NOT NULL,
  	`updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 	PRIMARY KEY (pk_id)
) COMMENT='b_a_c003_create_method' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;