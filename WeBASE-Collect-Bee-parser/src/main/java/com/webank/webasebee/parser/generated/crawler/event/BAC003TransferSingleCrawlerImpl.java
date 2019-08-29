/**
 * Copyright (C) 2018 WeBank, Inc. All Rights Reserved.
 */
package com.webank.webasebee.parser.generated.crawler.event;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.fisco.bcos.temp.BAC003;
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

import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC003TransferSingleBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC003.TransferSingleCrawlerService", havingValue = "on")
public class BAC003TransferSingleCrawlerImpl implements BcosEventCrawlerInterface {
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
		List<BAC003.TransferSingleEventResponse> TransferSingleEventResponseList = contract.getTransferSingleEvents(receipt);
		List<EventBO> list = new ArrayList<>(TransferSingleEventResponseList.size());
		if(CollectionUtils.isEmpty(TransferSingleEventResponseList)) {
		    return list;
		}
		for (BAC003.TransferSingleEventResponse TransferSingleEventResponse : TransferSingleEventResponseList) {
			BAC003TransferSingleBO bAC003TransferSingle = new BAC003TransferSingleBO();
			bAC003TransferSingle.setIdentifier("BAC003TransferSingle");		
			bAC003TransferSingle.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC003TransferSingle.setEventContractAddress(receipt.getContractAddress());
			bAC003TransferSingle.setTxHash(receipt.getTransactionHash());
			bAC003TransferSingle.set_operator(String.valueOf(TransferSingleEventResponse._operator));
			bAC003TransferSingle.set_from(String.valueOf(TransferSingleEventResponse._from));
			bAC003TransferSingle.set_to(String.valueOf(TransferSingleEventResponse._to));
			bAC003TransferSingle.set_id(BigIntegerUtils.toLong(TransferSingleEventResponse._id));
			bAC003TransferSingle.set_value(BigIntegerUtils.toLong(TransferSingleEventResponse._value));
			bAC003TransferSingle.set_data(BytesUtils.bytesArrayToString(TransferSingleEventResponse._data));
			bAC003TransferSingle.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot TransferSingle:{}", bAC003TransferSingle.toString());
			list.add(bAC003TransferSingle);
		}
		return list;
	}
}
