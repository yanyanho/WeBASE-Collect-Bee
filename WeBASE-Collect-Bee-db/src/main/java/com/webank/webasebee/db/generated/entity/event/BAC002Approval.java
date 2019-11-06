/**
 * Copyright (C) 2018 WeBank, Inc. All Rights Reserved.
 */
package com.webank.webasebee.db.generated.entity.event;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.webank.webasebee.db.entity.IdEntity;

@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
@Entity(name = "b_a_c002_approval_event")
@Table(name = "b_a_c002_approval_event", indexes = { @Index(name = "block_height", columnList = "block_height"),
        @Index(name = "block_timestamp", columnList = "block_timestamp"),
        @Index(name = "tx_hash", columnList = "tx_hash")})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BAC002Approval extends IdEntity {
	@Column(name = "block_height")
    private long blockHeight;
    @Column(name = "tx_hash")
	private String txHash;
	@Column(name = "_contract_address")
	private String contractAddress;
	@Column(name = "_owner")
	private String owner;
	@Column(name = "_approved")
	private String approved;
	@Column(name = "_asset_id")
	private Long assetId;
	@Column(name = "block_timestamp")
	private Date blockTimeStamp;	
	@UpdateTimestamp
    @Column(name = "depot_updatetime")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date depotUpdatetime;
}
