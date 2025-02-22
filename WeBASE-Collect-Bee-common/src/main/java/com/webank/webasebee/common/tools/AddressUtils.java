/**
 * Copyright 2014-2019 the original author or authors.
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
package com.webank.webasebee.common.tools;

import java.math.BigInteger;

import org.fisco.bcos.web3j.abi.datatypes.Address;

/**
 * AddressUtils
 *
 * @Description: AddressUtils
 * @author maojiayu
 * @data Jan 30, 2019 6:05:47 PM
 *
 */
public class AddressUtils {

    public static String bigIntegerToString(BigInteger bi) {
        Address address = new Address(bi);
        return address.toString();
    }

    public static String bigIntegerToString(Object obj) {
        return bigIntegerToString((BigInteger) obj);
    }

}
