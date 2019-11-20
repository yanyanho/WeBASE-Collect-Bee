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
@Entity(name = "b_a_c001_decrease_allowance_method")
@Table(name = "b_a_c001_decrease_allowance_method", indexes = { @Index(name = "block_height", columnList = "block_height"),
        @Index(name = "block_timestamp", columnList = "block_timestamp"),
        @Index(name = "tx_hash", columnList = "tx_hash")})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BAC001DecreaseAllowance extends IdEntity {
	@Column(name = "block_height")
    private long blockHeight;
    @Column(name = "tx_hash")
    private String txHash;
    @Column(name = "contract_address")
    private String contractAddress;
    @Column(name = "method_status")
    private String methodStatus;
	@Column(name = "_spender")
	private String spender;
	@Column(name = "_subtracted_value")
	private long subtractedValue;
	@Column(name = "block_timestamp")
	private Date blockTimeStamp;
	@UpdateTimestamp
    @Column(name = "depot_updatetime")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date depotUpdatetime;
}
