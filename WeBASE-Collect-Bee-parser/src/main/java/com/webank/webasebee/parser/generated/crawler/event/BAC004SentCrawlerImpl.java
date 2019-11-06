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
import org.fisco.bcos.temp.BAC004.SentEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC004SentBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC004.SentCrawlerService", havingValue = "on")
public class BAC004SentCrawlerImpl implements BcosEventCrawlerInterface {
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
		List<SentEventResponse> SentEventResponseList = contract.getSentEvents(receipt);
		List<EventBO> list = new ArrayList<>(SentEventResponseList.size());
		if(CollectionUtils.isEmpty(SentEventResponseList)) {
		    return list;
		}
		for (SentEventResponse SentEventResponse : SentEventResponseList) {
			BAC004SentBO bAC004Sent = new BAC004SentBO();
			bAC004Sent.setIdentifier("BAC004Sent");		
			bAC004Sent.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC004Sent.setEventContractAddress(receipt.getContractAddress());
			bAC004Sent.setTxHash(receipt.getTransactionHash());
			bAC004Sent.setOperator(String.valueOf(SentEventResponse.operator));
			bAC004Sent.setFrom(String.valueOf(SentEventResponse.from));
			bAC004Sent.setTo(String.valueOf(SentEventResponse.to));
			bAC004Sent.setAmount(BigIntegerUtils.toLong(SentEventResponse.amount));
			bAC004Sent.setData(BytesUtils.bytesArrayToString(SentEventResponse.data));
			bAC004Sent.setOperatorData(BytesUtils.bytesArrayToString(SentEventResponse.operatorData));
			bAC004Sent.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot Sent:{}", bAC004Sent.toString());
			list.add(bAC004Sent);
		}
		return list;
	}
}
