/**
 * Copyright (C) 2018 WeBank, Inc. All Rights Reserved.
 */
package com.webank.webasebee.parser.generated.crawler.event;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.fisco.bcos.temp.BAC003;
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

import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC003ApprovalForAllBO;
import com.webank.webasebee.common.constants.ContractConstants;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC003.ApprovalForAllCrawlerService", havingValue = "on")
public class BAC003ApprovalForAllCrawlerImpl implements BcosEventCrawlerInterface {
	@Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;
    @Autowired
    private ContractGasProvider contractGasProvider;
    @Autowired
    private BAC003 contract;
    
    @Bean
    @ConditionalOnMissingBean
    public BAC003 getBAC003() {
        return BAC003.load(ContractConstants.EMPTY_ADDRESS, web3j, credentials, contractGasProvider); 

    }
	
	@Override
	public List<EventBO> handleReceipt(TransactionReceipt receipt, BigInteger blockTimeStamp) {
		List<BAC003.ApprovalForAllEventResponse> ApprovalForAllEventResponseList = contract.getApprovalForAllEvents(receipt);
		List<EventBO> list = new ArrayList<>(ApprovalForAllEventResponseList.size());
		if(CollectionUtils.isEmpty(ApprovalForAllEventResponseList)) {
		    return list;
		}
		for (BAC003.ApprovalForAllEventResponse ApprovalForAllEventResponse : ApprovalForAllEventResponseList) {
			BAC003ApprovalForAllBO bAC003ApprovalForAll = new BAC003ApprovalForAllBO();
			bAC003ApprovalForAll.setIdentifier("BAC003ApprovalForAll");		
			bAC003ApprovalForAll.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC003ApprovalForAll.setEventContractAddress(receipt.getContractAddress());
			bAC003ApprovalForAll.setTxHash(receipt.getTransactionHash());
			bAC003ApprovalForAll.set_owner(String.valueOf(ApprovalForAllEventResponse._owner));
			bAC003ApprovalForAll.set_operator(String.valueOf(ApprovalForAllEventResponse._operator));
			bAC003ApprovalForAll.set_approved(String.valueOf(ApprovalForAllEventResponse._approved));
			bAC003ApprovalForAll.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot ApprovalForAll:{}", bAC003ApprovalForAll.toString());
			list.add(bAC003ApprovalForAll);
		}
		return list;
	}
}
