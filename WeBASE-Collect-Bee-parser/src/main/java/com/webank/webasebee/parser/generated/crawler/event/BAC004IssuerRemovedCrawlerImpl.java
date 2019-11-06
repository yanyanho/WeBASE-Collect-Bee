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
import org.fisco.bcos.temp.BAC004.IssuerRemovedEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC004IssuerRemovedBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC004.IssuerRemovedCrawlerService", havingValue = "on")
public class BAC004IssuerRemovedCrawlerImpl implements BcosEventCrawlerInterface {
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
		List<IssuerRemovedEventResponse> IssuerRemovedEventResponseList = contract.getIssuerRemovedEvents(receipt);
		List<EventBO> list = new ArrayList<>(IssuerRemovedEventResponseList.size());
		if(CollectionUtils.isEmpty(IssuerRemovedEventResponseList)) {
		    return list;
		}
		for (IssuerRemovedEventResponse IssuerRemovedEventResponse : IssuerRemovedEventResponseList) {
			BAC004IssuerRemovedBO bAC004IssuerRemoved = new BAC004IssuerRemovedBO();
			bAC004IssuerRemoved.setIdentifier("BAC004IssuerRemoved");		
			bAC004IssuerRemoved.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC004IssuerRemoved.setEventContractAddress(receipt.getContractAddress());
			bAC004IssuerRemoved.setTxHash(receipt.getTransactionHash());
			bAC004IssuerRemoved.setAccount(String.valueOf(IssuerRemovedEventResponse.account));
			bAC004IssuerRemoved.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot IssuerRemoved:{}", bAC004IssuerRemoved.toString());
			list.add(bAC004IssuerRemoved);
		}
		return list;
	}
}
