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
import org.fisco.bcos.temp.BAC004.DestroyedEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC004DestroyedBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.AddressUtils;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC004.DestroyedCrawlerService", havingValue = "on")
public class BAC004DestroyedCrawlerImpl implements BcosEventCrawlerInterface {
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
		List<DestroyedEventResponse> DestroyedEventResponseList = contract.getDestroyedEvents(receipt);
		List<EventBO> list = new ArrayList<>(DestroyedEventResponseList.size());
		if(CollectionUtils.isEmpty(DestroyedEventResponseList)) {
		    return list;
		}
		for (DestroyedEventResponse DestroyedEventResponse : DestroyedEventResponseList) {
			BAC004DestroyedBO bAC004Destroyed = new BAC004DestroyedBO();
			bAC004Destroyed.setIdentifier("BAC004Destroyed");		
			bAC004Destroyed.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC004Destroyed.setEventContractAddress(receipt.getContractAddress());
			bAC004Destroyed.setTxHash(receipt.getTransactionHash());
			bAC004Destroyed.setOperator(String.valueOf(DestroyedEventResponse.operator));
			bAC004Destroyed.setFrom(String.valueOf(DestroyedEventResponse.from));
			bAC004Destroyed.setAmount(BigIntegerUtils.toLong(DestroyedEventResponse.amount));
			bAC004Destroyed.setData(BytesUtils.bytesArrayToString(DestroyedEventResponse.data));
			bAC004Destroyed.setOperatorData(BytesUtils.bytesArrayToString(DestroyedEventResponse.operatorData));
			bAC004Destroyed.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot Destroyed:{}", bAC004Destroyed.toString());
			list.add(bAC004Destroyed);
		}
		return list;
	}
}
