/**
 * Copyright (C) 2018 WeBank, Inc. All Rights Reserved.
 */
package com.webank.webasebee.parser.generated.crawler.event;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import org.fisco.bcos.temp.BAC005;
import org.fisco.bcos.temp.BAC005.ApprovalForAllEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC005ApprovalForAllBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC005.ApprovalForAllCrawlerService", havingValue = "on")
public class BAC005ApprovalForAllCrawlerImpl implements BcosEventCrawlerInterface {
	@Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;
    @Autowired
    private ContractGasProvider contractGasProvider;
    @Autowired
    private BAC005 contract;
    
    @Bean
    @ConditionalOnMissingBean
    public BAC005 getBAC005() {
        return BAC005.load(ContractConstants.EMPTY_ADDRESS, web3j, credentials, contractGasProvider); 

    }
	
	@Override
	public List<EventBO> handleReceipt(TransactionReceipt receipt, BigInteger blockTimeStamp) {
		List<ApprovalForAllEventResponse> ApprovalForAllEventResponseList = contract.getApprovalForAllEvents(receipt);
		List<EventBO> list = new ArrayList<>(ApprovalForAllEventResponseList.size());
		if(CollectionUtils.isEmpty(ApprovalForAllEventResponseList)) {
		    return list;
		}
		for (ApprovalForAllEventResponse ApprovalForAllEventResponse : ApprovalForAllEventResponseList) {
			BAC005ApprovalForAllBO bAC005ApprovalForAll = new BAC005ApprovalForAllBO();
			bAC005ApprovalForAll.setIdentifier("BAC005ApprovalForAll");		
			bAC005ApprovalForAll.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC005ApprovalForAll.setEventContractAddress(receipt.getContractAddress());
			bAC005ApprovalForAll.setTxHash(receipt.getTransactionHash());
			bAC005ApprovalForAll.setContractAddress(String.valueOf(ApprovalForAllEventResponse.contractAddress));
			bAC005ApprovalForAll.setOwner(String.valueOf(ApprovalForAllEventResponse.owner));
			bAC005ApprovalForAll.setOperator(String.valueOf(ApprovalForAllEventResponse.operator));
			bAC005ApprovalForAll.setApproved(String.valueOf(ApprovalForAllEventResponse.approved));
			bAC005ApprovalForAll.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot ApprovalForAll:{}", bAC005ApprovalForAll.toString());
			list.add(bAC005ApprovalForAll);
		}
		return list;
	}
}
