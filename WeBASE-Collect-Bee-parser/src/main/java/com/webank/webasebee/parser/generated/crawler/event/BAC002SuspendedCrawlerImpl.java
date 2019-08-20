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

import org.fisco.bcos.temp.BAC002;
import org.fisco.bcos.temp.BAC002.SuspendedEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC002SuspendedBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC002.SuspendedCrawlerService", havingValue = "on")
public class BAC002SuspendedCrawlerImpl implements BcosEventCrawlerInterface {
	@Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;
    @Autowired
    private ContractGasProvider contractGasProvider;
    @Autowired
    private BAC002 contract;
    
    @Bean
    @ConditionalOnMissingBean
    public BAC002 getBAC002() {
        return BAC002.load(ContractConstants.EMPTY_ADDRESS, web3j, credentials, contractGasProvider); 

    }
	
	@Override
	public List<EventBO> handleReceipt(TransactionReceipt receipt, BigInteger blockTimeStamp) {
		List<SuspendedEventResponse> SuspendedEventResponseList = contract.getSuspendedEvents(receipt);
		List<EventBO> list = new ArrayList<>(SuspendedEventResponseList.size());
		if(CollectionUtils.isEmpty(SuspendedEventResponseList)) {
		    return list;
		}
		for (SuspendedEventResponse SuspendedEventResponse : SuspendedEventResponseList) {
			BAC002SuspendedBO bAC002Suspended = new BAC002SuspendedBO();
			bAC002Suspended.setIdentifier("BAC002Suspended");		
			bAC002Suspended.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC002Suspended.setEventContractAddress(receipt.getContractAddress());
			bAC002Suspended.setTxHash(receipt.getTransactionHash());
			bAC002Suspended.setAccount(String.valueOf(SuspendedEventResponse.account));
			bAC002Suspended.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot Suspended:{}", bAC002Suspended.toString());
			list.add(bAC002Suspended);
		}
		return list;
	}
}
