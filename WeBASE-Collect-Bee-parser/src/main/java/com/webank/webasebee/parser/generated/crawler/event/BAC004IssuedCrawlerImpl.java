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
import org.fisco.bcos.temp.BAC004.IssuedEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC004IssuedBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC004.IssuedCrawlerService", havingValue = "on")
public class BAC004IssuedCrawlerImpl implements BcosEventCrawlerInterface {
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
		List<IssuedEventResponse> IssuedEventResponseList = contract.getIssuedEvents(receipt);
		List<EventBO> list = new ArrayList<>(IssuedEventResponseList.size());
		if(CollectionUtils.isEmpty(IssuedEventResponseList)) {
		    return list;
		}
		for (IssuedEventResponse IssuedEventResponse : IssuedEventResponseList) {
			BAC004IssuedBO bAC004Issued = new BAC004IssuedBO();
			bAC004Issued.setIdentifier("BAC004Issued");		
			bAC004Issued.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC004Issued.setEventContractAddress(receipt.getContractAddress());
			bAC004Issued.setTxHash(receipt.getTransactionHash());
			bAC004Issued.setOperator(String.valueOf(IssuedEventResponse.operator));
			bAC004Issued.setTo(String.valueOf(IssuedEventResponse.to));
			bAC004Issued.setAmount(BigIntegerUtils.toLong(IssuedEventResponse.amount));
			bAC004Issued.setData(BytesUtils.bytesArrayToString(IssuedEventResponse.data));
			bAC004Issued.setOperatorData(BytesUtils.bytesArrayToString(IssuedEventResponse.operatorData));
			bAC004Issued.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot Issued:{}", bAC004Issued.toString());
			list.add(bAC004Issued);
		}
		return list;
	}
}
