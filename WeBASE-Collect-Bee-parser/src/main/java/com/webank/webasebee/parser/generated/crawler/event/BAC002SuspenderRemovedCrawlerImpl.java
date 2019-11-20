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
import org.fisco.bcos.temp.BAC002.SuspenderRemovedEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC002SuspenderRemovedBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.AddressUtils;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC002.SuspenderRemovedCrawlerService", havingValue = "on")
public class BAC002SuspenderRemovedCrawlerImpl implements BcosEventCrawlerInterface {
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
		List<SuspenderRemovedEventResponse> SuspenderRemovedEventResponseList = contract.getSuspenderRemovedEvents(receipt);
		List<EventBO> list = new ArrayList<>(SuspenderRemovedEventResponseList.size());
		if(CollectionUtils.isEmpty(SuspenderRemovedEventResponseList)) {
		    return list;
		}
		for (SuspenderRemovedEventResponse SuspenderRemovedEventResponse : SuspenderRemovedEventResponseList) {
			BAC002SuspenderRemovedBO bAC002SuspenderRemoved = new BAC002SuspenderRemovedBO();
			bAC002SuspenderRemoved.setIdentifier("BAC002SuspenderRemoved");		
			bAC002SuspenderRemoved.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC002SuspenderRemoved.setEventContractAddress(receipt.getContractAddress());
			bAC002SuspenderRemoved.setTxHash(receipt.getTransactionHash());
			bAC002SuspenderRemoved.setAccount(String.valueOf(SuspenderRemovedEventResponse.account));
			bAC002SuspenderRemoved.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot SuspenderRemoved:{}", bAC002SuspenderRemoved.toString());
			list.add(bAC002SuspenderRemoved);
		}
		return list;
	}
}
