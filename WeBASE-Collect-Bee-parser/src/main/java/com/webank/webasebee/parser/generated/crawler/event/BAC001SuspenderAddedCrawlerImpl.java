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

import org.fisco.bcos.temp.BAC001;
import org.fisco.bcos.temp.BAC001.SuspenderAddedEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC001SuspenderAddedBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.AddressUtils;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC001.SuspenderAddedCrawlerService", havingValue = "on")
public class BAC001SuspenderAddedCrawlerImpl implements BcosEventCrawlerInterface {
	@Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;
    @Autowired
    private ContractGasProvider contractGasProvider;
    @Autowired
    private BAC001 contract;
    
    @Bean
    @ConditionalOnMissingBean
    public BAC001 getBAC001() {
        return BAC001.load(ContractConstants.EMPTY_ADDRESS, web3j, credentials, contractGasProvider); 

    }
	
	@Override
	public List<EventBO> handleReceipt(TransactionReceipt receipt, BigInteger blockTimeStamp) {
		List<SuspenderAddedEventResponse> SuspenderAddedEventResponseList = contract.getSuspenderAddedEvents(receipt);
		List<EventBO> list = new ArrayList<>(SuspenderAddedEventResponseList.size());
		if(CollectionUtils.isEmpty(SuspenderAddedEventResponseList)) {
		    return list;
		}
		for (SuspenderAddedEventResponse SuspenderAddedEventResponse : SuspenderAddedEventResponseList) {
			BAC001SuspenderAddedBO bAC001SuspenderAdded = new BAC001SuspenderAddedBO();
			bAC001SuspenderAdded.setIdentifier("BAC001SuspenderAdded");		
			bAC001SuspenderAdded.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC001SuspenderAdded.setEventContractAddress(receipt.getContractAddress());
			bAC001SuspenderAdded.setTxHash(receipt.getTransactionHash());
			bAC001SuspenderAdded.setAccount(String.valueOf(SuspenderAddedEventResponse.account));
			bAC001SuspenderAdded.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot SuspenderAdded:{}", bAC001SuspenderAdded.toString());
			list.add(bAC001SuspenderAdded);
		}
		return list;
	}
}
