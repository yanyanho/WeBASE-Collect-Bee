/**
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
package com.webank.webasebee.core.app.web3japi;
import com.webank.webasebee.core.exception.FrontException;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.methods.response.BcosBlock;
import org.fisco.bcos.web3j.protocol.core.methods.response.NodeVersion.Version;
import org.fisco.bcos.web3j.protocol.core.methods.response.Transaction;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.txdecode.BaseException;
import org.fisco.bcos.web3j.tx.txdecode.ConstantCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;


/**
 * Web3Api manage.
 */
@Slf4j
@Service
public class Web3ApiService {

    @Autowired
    Web3j web3j;

    public BigInteger getBlockNumber(int groupId) throws BaseException {

        BigInteger blockNumber;
        try {
            blockNumber = web3j.getBlockNumber().send().getBlockNumber();
        } catch (IOException e) {
            log.error("getBlockNumber fail.", e);
            throw new BaseException(ConstantCode.NODE_REQUEST_FAILED);
        }
        return blockNumber;
    }

    /**
     * getBlockByNumber.
     *
     * @param blockNumber blockNumber
     */
    public BcosBlock.Block getBlockByNumber(int groupId, BigInteger blockNumber) {
        BcosBlock.Block block;
        try {
            block = web3j.getBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), true)
                .send().getBlock();
        } catch (Exception e) {
            log.info("get blocknumber failed" + e.getMessage());
            log.error("getBlockByNumber fail. blockNumber:{} , groupID: {}", blockNumber, groupId);
            block = null;
        }
        return block;
    }

    /**
     * getBlockByHash.
     *
     * @param blockHash blockHash
     */
    public BcosBlock.Block getBlockByHash(int groupId, String blockHash) {
        BcosBlock.Block block;
        try {
            block = web3j.getBlockByHash(blockHash, true).send().getBlock();

        } catch (IOException e) {
            log.error("getBlockByHash fail. blockHash:{} ", blockHash);
            throw new FrontException(ConstantCode.NODE_REQUEST_FAILED);
        }
        return block;
    }


    /**
     * getTransactionReceipt.
     *
     * @param transHash transHash
     */
    public TransactionReceipt getTransactionReceipt(int groupId, String transHash) {

        TransactionReceipt transactionReceipt = null;
        try {
            Optional<TransactionReceipt> opt = web3j
                .getTransactionReceipt(transHash).send().getTransactionReceipt();
            if (opt.isPresent()) {
                transactionReceipt = opt.get();
            }
        } catch (IOException e) {
            log.error("getTransactionReceipt fail. transHash:{} ", transHash);
            throw new FrontException(ConstantCode.NODE_REQUEST_FAILED);
        }
        return transactionReceipt;
    }

    /**
     * getTransactionByHash.
     *
     * @param transHash transHash
     */
    public Transaction getTransactionByHash(int groupId, String transHash) {

        Transaction transaction = null;
        try {
            Optional<Transaction> opt = web3j.getTransactionByHash(transHash).send()
                .getTransaction();
            if (opt.isPresent()) {
                transaction = opt.get();
            }
        } catch (IOException e) {
            log.error("getTransactionByHash fail. transHash:{} ", transHash);
            throw new FrontException(ConstantCode.NODE_REQUEST_FAILED);
        }
        return transaction;
    }

    /**
     * getClientVersion.
     */
    public Version getClientVersion() {
        Version version;
        try {

            version = web3j.getNodeVersion().send().getNodeVersion();
        } catch (IOException e) {
            log.error("getClientVersion fail.");
            throw new FrontException(ConstantCode.NODE_REQUEST_FAILED);
        }
        return version;
    }



}
