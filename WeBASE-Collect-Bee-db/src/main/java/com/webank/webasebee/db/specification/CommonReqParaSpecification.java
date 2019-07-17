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
package com.webank.webasebee.db.specification;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.assertj.core.util.Lists;
import org.springframework.data.jpa.domain.Specification;

import com.webank.webasebee.db.tools.JpaUtils;
import com.webank.webasebee.db.vo.CommonBiParaQueryPageReq;
import com.webank.webasebee.db.vo.CommonParaQueryPageReq;

import cn.hutool.core.date.DateException;
import cn.hutool.core.date.DateUtil;

/**
 * CommonReqParaSpecification
 *
 * @Description: CommonReqParaSpecification
 * @author graysonzhang
 * @data 2018年12月24日 上午10:59:31
 *
 */
public class CommonReqParaSpecification {

    public static <T> Specification queryByCriteriaEqual(CommonParaQueryPageReq<T> req) throws DateException {
        return (root, query, cb) -> {

            return cb.equal(root.get(req.getReqParaName()),
                    transformValue(req.getReqParaName(), req.getReqParaValue()));
        };
    }

    public static <T> Specification queryByCriteriaEqual(CommonBiParaQueryPageReq<T> req) throws DateException {
        return (root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList(
                    cb.equal(root.get(req.getReqParaName1()),
                            transformValue(req.getReqParaName1(), req.getReqParaValue1())),
                    cb.equal(root.get(req.getReqParaName2()),
                            transformValue(req.getReqParaName2(), req.getReqParaValue2())));
            return JpaUtils.andTogether(predicates, cb);

        };
    }

    public static <T> Object transformValue(String paraName, T paraValue) {
        if (paraName.endsWith("TimeStamp") || paraName.endsWith("Updatetime")) {
            return DateUtil.parseDate((String) paraValue);
        } else {
            return paraValue;
        }

    }
}
