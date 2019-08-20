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

import com.webank.webasebee.common.vo.CommonResponse;
import com.webank.webasebee.core.api.manager.AccountInfoApiManager;
import com.webank.webasebee.core.api.manager.MethodManager;
import com.webank.webasebee.core.app.base.BaseController;
import com.webank.webasebee.core.app.base.ResponseEntity;
import com.webank.webasebee.core.app.trans.TransLogService;
import com.webank.webasebee.core.exception.BaseException;
import com.webank.webasebee.db.vo.ContractNameQueryReq;
import com.webank.webasebee.db.vo.UnitParaQueryPageReq;
import com.webank.webasebee.db.vo.UnitQueryPageReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * TransController.
 * 
 */
@Api(value = "/asset", tags = "asset interface")
@Slf4j
@RestController
@RequestMapping(value = "/asset")
public class AssetController extends BaseController {
    @Autowired
    AssetService assetService;
    @Autowired
    TransLogService transLogService;
    @Autowired
    private MethodManager methodManager;


    @Autowired
    private AccountInfoApiManager accountInfoApiManager;

//  查资产列表详情。
    /**
     * assetList.
     *
     * @return
     */
    @ApiOperation(value = "assetList", notes = "Get asset infos")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "pageNumber", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "path")})
    @GetMapping("/list")
    public CommonResponse assetList(
            @RequestParam(defaultValue = "BAC001") String contractName,
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "50") int pageSize) {

        return transLogService.findAccountInfo(contractName,pageNumber, pageSize);
    }


    /**
     * asset balance.
     *
     * @return
     */
    @ApiOperation(value = "assetAccounts", notes = "Get asset's accounts")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assetId", value = "assetId", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "address", value = "address", required = true, dataType = "String", paramType = "path")})
    @GetMapping("/balance")
    public ResponseEntity assetBalance(@RequestParam(defaultValue = "BAC001") String contractName,
                                        @RequestParam String contractAddress,
                                        @RequestParam String userAddress) throws BaseException {
        return assetService.assetBalance(contractName,contractAddress, userAddress);
   }

   //  查资产状态
    /**
     * assetInfo.
     *
     * @return
     */
    @ApiOperation(value = "asset info", notes = "Get asset info by id")
    @ApiImplicitParam(name = "assetId", value = "assetId", required = true, dataType = "int", paramType = "path")
    @GetMapping("/status")
    public ResponseEntity assetStatus(
            @RequestParam(defaultValue = "BAC001") String contractName,
            @RequestParam String contractAddress) throws BaseException {
        return assetService.assetStatus(contractName,contractAddress);
    }

    @GetMapping("/bac003")
    public CommonResponse bac003assetList(
            @RequestParam(defaultValue = "BAC003") String contractName,
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "50") int pageSize) {
        ContractNameQueryReq contractNameQueryReq = new ContractNameQueryReq();
        contractNameQueryReq.setContractName(contractName);
        contractNameQueryReq.setPageNo(pageNumber);
        contractNameQueryReq.setPageSize(pageSize);
        return  accountInfoApiManager.getAccountsPageListByReq(contractNameQueryReq);
    }


    @GetMapping("/bac003-info")
    public CommonResponse bac003assetListOfOneContractAddress(
            @RequestParam String contractAddress,
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "50") int pageSize) {
        UnitParaQueryPageReq unitParaQueryPageReq = new UnitParaQueryPageReq();
        unitParaQueryPageReq.setUnitName("BAC003Create");
        unitParaQueryPageReq.setReqParaName("contractAddress");
        unitParaQueryPageReq.setReqParaValue(contractAddress);

        unitParaQueryPageReq.setPageNo(pageNumber);
        unitParaQueryPageReq.setPageSize(pageSize);
        return  methodManager.getPageListByReq(unitParaQueryPageReq);
    }

    @GetMapping("/bac003/balance")
    public ResponseEntity bac003AssetBalance(@RequestParam(defaultValue = "BAC003") String contractName,
                                       @RequestParam String contractAddress,
                                       @RequestParam BigInteger id,
                                       @RequestParam String userAddress) throws BaseException {
        return assetService.bac003AssetBalance(contractName,contractAddress, userAddress,id);
    }

}
