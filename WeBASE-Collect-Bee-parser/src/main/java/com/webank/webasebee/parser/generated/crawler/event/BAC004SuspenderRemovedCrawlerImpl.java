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

import org.fisco.bcos.temp.BAC004;
import org.fisco.bcos.temp.BAC004.SuspenderRemovedEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC004SuspenderRemovedBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.AddressUtils;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC004.SuspenderRemovedCrawlerService", havingValue = "on")
public class BAC004SuspenderRemovedCrawlerImpl implements BcosEventCrawlerInterface {
	@Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;
    @Autowired
    private ContractGasProvider contractGasProvider;
    @Autowired
    private BAC004 contract;
    
    @Bean
    @ConditionalOnMissingBean
    public BAC004 getBAC004() {
        return BAC004.load(ContractConstants.EMPTY_ADDRESS, web3j, credentials, contractGasProvider); 

    }
	
	@Override
	public List<EventBO> handleReceipt(TransactionReceipt receipt, BigInteger blockTimeStamp) {
		List<SuspenderRemovedEventResponse> SuspenderRemovedEventResponseList = contract.getSuspenderRemovedEvents(receipt);
		List<EventBO> list = new ArrayList<>(SuspenderRemovedEventResponseList.size());
		if(CollectionUtils.isEmpty(SuspenderRemovedEventResponseList)) {
		    return list;
		}
		for (SuspenderRemovedEventResponse SuspenderRemovedEventResponse : SuspenderRemovedEventResponseList) {
			BAC004SuspenderRemovedBO bAC004SuspenderRemoved = new BAC004SuspenderRemovedBO();
			bAC004SuspenderRemoved.setIdentifier("BAC004SuspenderRemoved");		
			bAC004SuspenderRemoved.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC004SuspenderRemoved.setEventContractAddress(receipt.getContractAddress());
			bAC004SuspenderRemoved.setTxHash(receipt.getTransactionHash());
			bAC004SuspenderRemoved.setAccount(String.valueOf(SuspenderRemovedEventResponse.account));
			bAC004SuspenderRemoved.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot SuspenderRemoved:{}", bAC004SuspenderRemoved.toString());
			list.add(bAC004SuspenderRemoved);
		}
		return list;
	}
}
