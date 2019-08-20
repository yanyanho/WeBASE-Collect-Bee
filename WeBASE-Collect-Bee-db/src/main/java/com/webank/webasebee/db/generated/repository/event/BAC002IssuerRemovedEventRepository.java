/**
 * Copyright (C) 2018 WeBank, Inc. All Rights Reserved.
 */
package com.webank.webasebee.db.generated.repository.event;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.webank.webasebee.db.generated.entity.event.BAC002IssuerRemoved;
import com.webank.webasebee.db.repository.RollbackInterface;


@Repository
public interface BAC002IssuerRemovedEventRepository extends JpaRepository<BAC002IssuerRemoved, Long>, JpaSpecificationExecutor<BAC002IssuerRemoved>, RollbackInterface {
	public BAC002IssuerRemoved findTopByOrderByBlockHeightDesc();
	
	@Transactional
    @Modifying
    @Query(value = "delete from  #{#entityName} where block_height >= ?1", nativeQuery = true)
    public void rollback(long blockHeight);
    
    @Transactional
    @Modifying
    @Query(value = "delete from  #{#entityName} where block_height >= ?1 and block_height< ?2", nativeQuery = true)
    public void rollback(long startBlockHeight, long endBlockHeight);
}
