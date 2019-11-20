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

import org.fisco.bcos.temp.BAC001;
import org.fisco.bcos.temp.BAC001.ApprovalEventResponse;
import com.webank.webasebee.common.bo.data.EventBO;
import com.webank.webasebee.parser.crawler.face.BcosEventCrawlerInterface;
import com.webank.webasebee.parser.generated.bo.event.BAC001ApprovalBO;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.AddressUtils;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.JacksonUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ConditionalOnProperty(name = "monitor.BAC001.ApprovalCrawlerService", havingValue = "on")
public class BAC001ApprovalCrawlerImpl implements BcosEventCrawlerInterface {
	@Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;
    @Autowired
    private ContractGasProvider contractGasProvider;
    @Autowired
    private BAC001 contract;
    
    @Bean
    @ConditionalOnMissingBean
    public BAC001 getBAC001() {
        return BAC001.load(ContractConstants.EMPTY_ADDRESS, web3j, credentials, contractGasProvider); 

    }
	
	@Override
	public List<EventBO> handleReceipt(TransactionReceipt receipt, BigInteger blockTimeStamp) {
		List<ApprovalEventResponse> ApprovalEventResponseList = contract.getApprovalEvents(receipt);
		List<EventBO> list = new ArrayList<>(ApprovalEventResponseList.size());
		if(CollectionUtils.isEmpty(ApprovalEventResponseList)) {
		    return list;
		}
		for (ApprovalEventResponse ApprovalEventResponse : ApprovalEventResponseList) {
			BAC001ApprovalBO bAC001Approval = new BAC001ApprovalBO();
			bAC001Approval.setIdentifier("BAC001Approval");		
			bAC001Approval.setBlockHeight(receipt.getBlockNumber().longValue());
			bAC001Approval.setEventContractAddress(receipt.getContractAddress());
			bAC001Approval.setTxHash(receipt.getTransactionHash());
			bAC001Approval.setContractAddress(String.valueOf(ApprovalEventResponse.contractAddress));
			bAC001Approval.setOwner(String.valueOf(ApprovalEventResponse.owner));
			bAC001Approval.setSpender(String.valueOf(ApprovalEventResponse.spender));
			bAC001Approval.setValue(BigIntegerUtils.toLong(ApprovalEventResponse.value));
			bAC001Approval.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));
			log.debug("depot Approval:{}", bAC001Approval.toString());
			list.add(bAC001Approval);
		}
		return list;
	}
}
