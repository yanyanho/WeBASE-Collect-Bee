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

package com.webank.webasebee.core.app.base;

public interface ConstantCode {
    // return success
    RetCode RET_SUCCEED = RetCode.mark(0, "success");

    // paramaters check
    String ACCOUNT_ID_IS_EMPTY = "{\"code\":203001,\"msg\":\"account id cannot be empty\"}";
    String ACCOUNT_NAME_IS_EMPTY = "{\"code\":203002,\"msg\":\"account name cannot be empty\"}";
    String ACCOUNT_TYPE_IS_EMPTY = "{\"code\":203003,\"msg\":\"account type cannot be empty\"}";
    String ASSET_ID_IS_EMPTY = "{\"code\":203004,\"msg\":\"asset id cannot be empty\"}";
    String ASSET_NAME_IS_EMPTY = "{\"code\":203005,\"msg\":\"asset name cannot be empty\"}";
    String ASSET_TYPE_IS_EMPTY = "{\"code\":203006,\"msg\":\"asset type cannot be empty\"}";
    String FROM_ADDRESS_IS_EMPTY = "{\"code\":203007,\"msg\":\"from's address cannot be empty\"}";
    String TO_ADDRESS_IS_EMPTY = "{\"code\":203008,\"msg\":\"to's address cannot be empty\"}";
    String TYPE_IS_EMPTY = "{\"code\":203009,\"msg\":\"handle type cannot be empty\"}";
    String GOODS_NAME_IS_EMPTY = "{\"code\":203010,\"msg\":\"goods name cannot be empty\"}";

    // general error
    RetCode ACCOUNT_NAME_IS_EXISTS = RetCode.mark(303001, "account name is already exists");
    RetCode ACCOUNT_TYPE_NOT_EXISTS = RetCode.mark(303002, "account type is not exists");
    RetCode ACCOUNT_NOT_EXISTS = RetCode.mark(303003, "account is not exists");
    RetCode ACCOUNT_IS_EXISTS = RetCode.mark(303004, "account is already exists");
    RetCode ACCOUNT_NOT_MINTER = RetCode.mark(303005, "this account is not minter");
    RetCode ACCOUNT_NOT_PAUSER = RetCode.mark(303006, "this account is not pauser");
    RetCode ASSET_NOT_EXISTS = RetCode.mark(303007, "asset is not exists");
    RetCode ASSET_TYPE_NOT_EXISTS = RetCode.mark(303008, "asset type is not exists");
    RetCode ASSET_ADDRESS_IS_EMPTY = RetCode.mark(303009, "asset address is empty");
    RetCode ASSET_BEEN_PAUSED = RetCode.mark(303010, "asset has been paused");
    RetCode PAUSE_TYPE_NOT_EXISTS = RetCode.mark(303011, "pause type is not exists");
    RetCode TOKENID_BEEN_INCREASED = RetCode.mark(303012, "tokenId has been increased");
    RetCode ASSET_NAME_IS_EXISTS = RetCode.mark(303013, "asset name is already exists");
    RetCode BALANCE_NOT_ENOUGH = RetCode.mark(303014, "the balance is not enough");
    RetCode ADDRESS_FORMAT_WRONG = RetCode.mark(303015, "receiving address's format is wrong");
    RetCode GOODS_NOT_ENOUGH = RetCode.mark(303016, "goods's counts is not enough");

    // system error
    RetCode SYSTEM_ERROR = RetCode.mark(103001, "system error");
    RetCode PARAM_VAILD_FAIL = RetCode.mark(103002, "param valid fail");
}
