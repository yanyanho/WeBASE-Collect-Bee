/**
 * Copyright (C) 2018 WeBank, Inc. All Rights Reserved.
 */
package com.webank.webasebee.db.generated.entity.method;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import com.webank.webasebee.db.entity.IdEntity;

@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
@Entity(name = "b_a_c003_create_method")
@Table(name = "b_a_c003_create_method", indexes = { @Index(name = "block_height", columnList = "block_height"),
        @Index(name = "block_timestamp", columnList = "block_timestamp"),
        @Index(name = "tx_hash", columnList = "tx_hash")})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BAC003Create extends IdEntity {
	@Column(name = "block_height")
    private long blockHeight;
    @Column(name = "tx_hash")
    private String txHash;
    @Column(name = "contract_address")
    private String contractAddress;
    @Column(name = "method_status")
    private String methodStatus;
	@Column(name = "_initial_supply")
	private long _initialSupply;
	@Column(name = "_uri")
	private String _uri;
	@Column(name = "_data")
	private String _data;
	@Column(name = "block_timestamp")
	private Date blockTimeStamp;
	@UpdateTimestamp
    @Column(name = "depot_updatetime")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date depotUpdatetime;
}
