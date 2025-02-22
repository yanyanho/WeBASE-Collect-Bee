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
package com.webank.webasebee.core.exception;


import com.webank.webasebee.core.app.base.RetCode;

/**
 * BaseException.
 * 
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = 1L;
    private RetCode retCode;

    public BaseException(RetCode retCode) {
        super(retCode.getMsg());
        this.retCode = retCode;
    }

    public BaseException(int code, String msg) {
        super(msg);
        this.retCode = new RetCode(code, msg);
    }

    public RetCode getRetCode() {
        return retCode;
    }
}
