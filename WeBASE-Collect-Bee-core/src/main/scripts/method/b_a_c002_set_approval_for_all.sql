CREATE TABLE b_a_c002_set_approval_for_all_method (
  	pk_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  	block_height bigint(20) NOT NULL COMMENT '交易所在块高',
  	tx_hash varchar(100) NOT NULL COMMENT '交易哈希',
	_to varchar(255) NOT NULL,
	_approved tinyint NOT NULL,
  	`updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 	PRIMARY KEY (pk_id)
) COMMENT='b_a_c002_set_approval_for_all_method' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;