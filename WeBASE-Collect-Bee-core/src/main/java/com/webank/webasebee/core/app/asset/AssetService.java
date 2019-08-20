/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.webank.webasebee.core.app.asset;


import com.webank.webasebee.core.app.base.ConstantCode;
import com.webank.webasebee.core.app.base.ConstantProperties;
import com.webank.webasebee.core.app.base.ResponseEntity;
import com.webank.webasebee.core.exception.BaseException;
import lombok.extern.slf4j.Slf4j;

import org.fisco.bcos.temp.BAC001;
import org.fisco.bcos.temp.BAC002;
import org.fisco.bcos.temp.BAC003;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * TransService.
 * 
 */
@Slf4j
@Service
public class AssetService {
    @Autowired
    private Web3j web3j;

    /**
     * assetBalance.
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public ResponseEntity assetBalance(String contractName, String contractAddress, String userAddress) throws BaseException {
        ResponseEntity response = new ResponseEntity(ConstantCode.RET_SUCCEED);

        // get assetInfo

        // get info from chain
        BigInteger balance = new BigInteger("0");
        if(!userAddress.equals("0x0000000000000000000000000000000000000000")) {
            try {
                if (AssetType.BAC001.getValue().equals(contractName)) {
                    BAC001 bac001 = BAC001.load(contractAddress, web3j,
                            getCredentials(),
                            ConstantProperties.GAS_PRICE, ConstantProperties.GAS_LIMIT);
                    BigInteger minUnit = bac001.minUnit().send();
                    BigInteger unit = BigInteger.valueOf((long) Math.pow(10, minUnit.doubleValue()));
                    balance = bac001.balance(userAddress).send().divide(unit);
                } else {
                    BAC002 bac002 = BAC002.load(contractAddress, web3j,
                            getCredentials(),
                            ConstantProperties.GAS_PRICE, ConstantProperties.GAS_LIMIT);
                    balance = bac002.balance(userAddress).send();
                }
            } catch (Exception e) {
                log.error("assetBalance assetId:{} Exception", contractName, e);
                throw new BaseException(ConstantCode.SYSTEM_ERROR);
            }
        }

        Map<String, Object> data = new HashMap<>();
        data.put("balance", balance);
        response.setData(data);

        log.info("assetBalance end.");
        return response;
    }


    public ResponseEntity assetStatus(String contractName, String contractAddress) throws BaseException {
        ResponseEntity response = new ResponseEntity(ConstantCode.RET_SUCCEED);
        RspAssetInfo assetInfo = new RspAssetInfo();

        // get info from chain
        BigInteger totalAmount = new BigInteger("0") ;
        Boolean status = true;
        try {
            if (AssetType.BAC001.getValue().equals(contractName)) {
                BAC001 bac001 = BAC001.load(contractAddress, web3j, getCredentials(),
                        ConstantProperties.GAS_PRICE, ConstantProperties.GAS_LIMIT);
                status = bac001.suspended().send();
                BigInteger minUnit = bac001.minUnit().send();
                BigInteger unit = BigInteger.valueOf((long) Math.pow(10, minUnit.doubleValue()));
                totalAmount = bac001.totalAmount().send().divide(unit);
            }
            else if(AssetType.BAC002.getValue().equals(contractName)) {
                BAC002 bac002 = BAC002.load(contractAddress, web3j, getCredentials(),
                        ConstantProperties.GAS_PRICE, ConstantProperties.GAS_LIMIT);
                status = bac002.suspended().send();
                totalAmount = bac002.totalSupply().send();

            }
        } catch (Exception e) {
            log.error("assetInfo contractAddress:{} Exception", contractAddress, e);
            throw new BaseException(ConstantCode.SYSTEM_ERROR);
        }

        // return
        assetInfo.setAssetTotal(totalAmount);
        assetInfo.setAssetStatus(status == true ? 1 : 0);
        response.setData(assetInfo);
        log.info("assetInfo end.");
        return response;
    }


    public Credentials getCredentials() {

        return Credentials.create("2");
    }
}

