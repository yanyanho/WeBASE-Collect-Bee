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
import org.fisco.bcos.temp.BAC002.UnSuspendedEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC002UnSuspendedBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC002.UnSuspendedCrawlerService", havingValue = "on")
public class BAC002UnSuspendedCrawlerImpl implements BcosEventCrawlerInterface {
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
		List<UnSuspendedEventResponse> UnSuspendedEventResponseList = contract.getUnSuspendedEvents(receipt);
		List<EventBO> list = new ArrayList<>(UnSuspendedEventResponseList.size());
		if(CollectionUtils.isEmpty(UnSuspendedEventResponseList)) {
		    return list;
		}
		for (UnSuspendedEventResponse UnSuspendedEventResponse : UnSuspendedEventResponseList) {
			BAC002UnSuspendedBO bAC002UnSuspended = new BAC002UnSuspendedBO();
			bAC002UnSuspended.setIdentifier("BAC002UnSuspended");		
			bAC002UnSuspended.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC002UnSuspended.setEventContractAddress(receipt.getContractAddress());
			bAC002UnSuspended.setTxHash(receipt.getTransactionHash());
			bAC002UnSuspended.setAccount(String.valueOf(UnSuspendedEventResponse.account));
			bAC002UnSuspended.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot UnSuspended:{}", bAC002UnSuspended.toString());
			list.add(bAC002UnSuspended);
		}
		return list;
	}
}
