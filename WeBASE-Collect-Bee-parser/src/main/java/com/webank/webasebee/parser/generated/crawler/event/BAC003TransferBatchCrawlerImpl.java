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
import org.fisco.bcos.temp.BAC003.TransferBatchEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC003TransferBatchBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC003.TransferBatchCrawlerService", havingValue = "on")
public class BAC003TransferBatchCrawlerImpl implements BcosEventCrawlerInterface {
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
		List<TransferBatchEventResponse> TransferBatchEventResponseList = contract.getTransferBatchEvents(receipt);
		List<EventBO> list = new ArrayList<>(TransferBatchEventResponseList.size());
		if(CollectionUtils.isEmpty(TransferBatchEventResponseList)) {
		    return list;
		}
		for (TransferBatchEventResponse TransferBatchEventResponse : TransferBatchEventResponseList) {
			BAC003TransferBatchBO bAC003TransferBatch = new BAC003TransferBatchBO();
			bAC003TransferBatch.setIdentifier("BAC003TransferBatch");		
			bAC003TransferBatch.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC003TransferBatch.setEventContractAddress(receipt.getContractAddress());
			bAC003TransferBatch.setTxHash(receipt.getTransactionHash());
			bAC003TransferBatch.set_operator(String.valueOf(TransferBatchEventResponse._operator));
			bAC003TransferBatch.set_from(String.valueOf(TransferBatchEventResponse._from));
			bAC003TransferBatch.set_to(String.valueOf(TransferBatchEventResponse._to));
			bAC003TransferBatch.set_ids(JacksonUtils.toJson(TransferBatchEventResponse._ids));
			bAC003TransferBatch.set_values(JacksonUtils.toJson(TransferBatchEventResponse._values));
			bAC003TransferBatch.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot TransferBatch:{}", bAC003TransferBatch.toString());
			list.add(bAC003TransferBatch);
		}
		return list;
	}
}
