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
import org.fisco.bcos.temp.BAC004.SendEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC004SendBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.AddressUtils;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC004.SendCrawlerService", havingValue = "on")
public class BAC004SendCrawlerImpl implements BcosEventCrawlerInterface {
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
		List<SendEventResponse> SendEventResponseList = contract.getSendEvents(receipt);
		List<EventBO> list = new ArrayList<>(SendEventResponseList.size());
		if(CollectionUtils.isEmpty(SendEventResponseList)) {
		    return list;
		}
		for (SendEventResponse SendEventResponse : SendEventResponseList) {
			BAC004SendBO bAC004Send = new BAC004SendBO();
			bAC004Send.setIdentifier("BAC004Send");		
			bAC004Send.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC004Send.setEventContractAddress(receipt.getContractAddress());
			bAC004Send.setTxHash(receipt.getTransactionHash());
			bAC004Send.setFrom(String.valueOf(SendEventResponse.from));
			bAC004Send.setTo(String.valueOf(SendEventResponse.to));
			bAC004Send.setValue(BigIntegerUtils.toLong(SendEventResponse.value));
			bAC004Send.setData(BytesUtils.bytesArrayToString(SendEventResponse.data));
			bAC004Send.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot Send:{}", bAC004Send.toString());
			list.add(bAC004Send);
		}
		return list;
	}
}
