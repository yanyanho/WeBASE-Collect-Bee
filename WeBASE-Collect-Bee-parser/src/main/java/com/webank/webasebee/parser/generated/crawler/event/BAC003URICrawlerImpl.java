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

import org.fisco.bcos.temp.BAC003;
import org.fisco.bcos.temp.BAC003.URIEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC003URIBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC003.URICrawlerService", havingValue = "on")
public class BAC003URICrawlerImpl implements BcosEventCrawlerInterface {
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
		List<URIEventResponse> URIEventResponseList = contract.getURIEvents(receipt);
		List<EventBO> list = new ArrayList<>(URIEventResponseList.size());
		if(CollectionUtils.isEmpty(URIEventResponseList)) {
		    return list;
		}
		for (URIEventResponse URIEventResponse : URIEventResponseList) {
			BAC003URIBO bAC003URI = new BAC003URIBO();
			bAC003URI.setIdentifier("BAC003URI");		
			bAC003URI.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC003URI.setEventContractAddress(receipt.getContractAddress());
			bAC003URI.setTxHash(receipt.getTransactionHash());
			bAC003URI.set_id(BigIntegerUtils.toLong(URIEventResponse._id));
			bAC003URI.set_value(String.valueOf(URIEventResponse._value));
			bAC003URI.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot URI:{}", bAC003URI.toString());
			list.add(bAC003URI);
		}
		return list;
	}
}
