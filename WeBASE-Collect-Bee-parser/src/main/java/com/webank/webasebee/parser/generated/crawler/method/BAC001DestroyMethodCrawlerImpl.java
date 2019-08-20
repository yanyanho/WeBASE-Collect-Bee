/**
 * Copyright (C) 2018 WeBank, Inc. All Rights Reserved.
 */
package com.webank.webasebee.parser.generated.crawler.method;


import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.protocol.core.methods.response.AbiDefinition.NamedType;
import org.fisco.bcos.web3j.protocol.core.methods.response.Transaction;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import com.webank.webasebee.parser.crawler.face.BcosMethodCrawlerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import com.webank.webasebee.common.constants.ContractConstants;
import com.webank.webasebee.common.tools.AddressUtils;
import com.webank.webasebee.common.tools.BigIntegerUtils;
import com.webank.webasebee.common.tools.BoolUtils;
import com.webank.webasebee.common.tools.BytesUtils;
import com.webank.webasebee.common.tools.MethodUtils;
import lombok.extern.slf4j.Slf4j;
import com.webank.webasebee.common.bo.contract.ContractMapsInfo;
import com.webank.webasebee.common.bo.data.MethodBO;
import com.webank.webasebee.parser.generated.bo.method.BAC001DestroyBO;

@Slf4j
@Service
@ConditionalOnProperty(name = "monitor.BAC001.destroyMethodCrawlerService", havingValue = "on")
public class BAC001DestroyMethodCrawlerImpl implements BcosMethodCrawlerInterface {

	@Autowired
	private ContractMapsInfo contractMapsInfo; 

	@Override
	public MethodBO transactionHandler(Transaction transaction, BigInteger blockTimeStamp,
	 Map.Entry<String, String> entry, String methodName, Map<String, String> txHashContractAddress) {
		log.debug("Begin process BAC001Destroy Transaction");
		BAC001DestroyBO entity = new BAC001DestroyBO();
		entity.setTxHash(transaction.getHash());
		entity.setBlockHeight(transaction.getBlockNumber().longValue());
		
		String input = transaction.getInput();
		String mapKey = null;
		if(transaction.getTo() == null || transaction.getTo().equals(ContractConstants.EMPTY_ADDRESS)){
			input = input.substring(2 + entry.getKey().length());
			mapKey = entry.getValue() + entry.getValue();
			entity.setContractAddress(txHashContractAddress.get(entity.getTxHash()));
		}else{
			input = input.substring(10);
			mapKey = methodName;
			entity.setContractAddress(transaction.getTo());
		}
		
		log.debug("input : {}", input);
				
		List<NamedType> list = contractMapsInfo.getMethodFiledsMap().get(mapKey);
		List<Type> params = FunctionReturnDecoder.decode(input, MethodUtils.getMethodTypeReferenceList(list));
		
		entity.setValue(BigIntegerUtils.toLong(params.get(0).getValue()));
		entity.setData(String.valueOf(params.get(1).getValue()));
		entity.setBlockTimeStamp(new Date(blockTimeStamp.longValue()));	
		entity.setIdentifier("BAC001Destroy");			
		
		log.debug("end process BAC001Destroy Transaction");
		return entity;
	}
}
