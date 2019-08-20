package com.webank.webasebee.core.app.web3japi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.fisco.bcos.web3j.protocol.core.methods.response.BcosBlock;
import org.fisco.bcos.web3j.protocol.core.methods.response.Transaction;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Web3ApiController.
 *
 */
@Api(value = "/web3", tags = "web3j interface")
@RestController
@RequestMapping(value = "/{groupId}/web3")
public class Web3ApiController {

    @Autowired
    Web3ApiService web3ApiService;


    @ApiOperation(value = "getBlockByNumber", notes = "Get block information based on block height")
    @ApiImplicitParam(name = "blockNumber", value = "blockNumber", required = true, dataType = "BigInteger", paramType = "path")
    @GetMapping("/blockByNumber/{blockNumber}")
    public BcosBlock.Block getBlockByNumber(@PathVariable int groupId, @PathVariable BigInteger blockNumber) {
        return web3ApiService.getBlockByNumber(groupId,blockNumber);
    }

    @ApiOperation(value = "getBlockByHash", notes = "Get block information based on block hash")
    @ApiImplicitParam(name = "blockHash", value = "blockHash", required = true, dataType = "String", paramType = "path")
    @GetMapping("/blockByHash/{blockHash}")
    public BcosBlock.Block getBlockByHash(@PathVariable int groupId, @PathVariable String blockHash)  {
        return web3ApiService.getBlockByHash(groupId,blockHash);
    }

    @ApiOperation(value = "getTransactionReceipt", notes = "Get a transaction receipt based on the transaction hash")
    @ApiImplicitParam(name = "transHash", value = "transHash", required = true, dataType = "String", paramType = "path")
    @GetMapping("/transactionReceipt/{transHash}")
    public TransactionReceipt getTransactionReceipt(@PathVariable int groupId, @PathVariable String transHash)  {
        return web3ApiService.getTransactionReceipt(groupId,transHash);
    }

    @ApiOperation(value = "getTransactionByHash",
            notes = "Get transaction information based on transaction hash")
    @ApiImplicitParam(name = "transHash", value = "transHash", required = true, dataType = "String", paramType = "path")
    @GetMapping("/transaction/{transHash}")
    public Transaction getTransactionByHash(@PathVariable int groupId, @PathVariable String transHash)  {
        return web3ApiService.getTransactionByHash(groupId,transHash);
    }
}
