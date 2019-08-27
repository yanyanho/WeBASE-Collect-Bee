package com.webank.webasebee.core.app.trans;


import com.webank.webasebee.common.vo.CommonResponse;
import com.webank.webasebee.core.api.manager.EventManager;
import com.webank.webasebee.core.api.manager.MethodManager;
import com.webank.webasebee.db.vo.UnitQueryPageReq;
import com.webank.webasebee.db.vo.UnitSpecificationQueryPageReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransLogService {

    @Autowired
    private EventManager eventManager;
    @Autowired
    private MethodManager methodManager;


    public CommonResponse findTransLog(String unitName, String contractAddress, String userAddress, int pageNumber, int pageSize) throws UnsupportedEncodingException {

        UnitSpecificationQueryPageReq param = new UnitSpecificationQueryPageReq();
        param.setUnitName(unitName);
        param.setPageNo(pageNumber);
        param.setPageSize(pageSize);


        if (StringUtils.isNotBlank(userAddress)) {
            Map<String, String> orConditionMap = new HashMap<>();
            if (!"BAC003TransferSingle".equals(unitName)) {
                orConditionMap.put("from", userAddress);
                orConditionMap.put("to", userAddress);
            } else {
                orConditionMap.put("_from", userAddress);
                orConditionMap.put("_to", userAddress);
            }
            param.setOrConditions(orConditionMap);
        }


        Map<String, String> andConditionMap = new HashMap<>();
        andConditionMap.put("eventContractAddress", contractAddress);
        param.setAndConditions(andConditionMap);

        return eventManager.getPageListByReq(param);
    }


    public CommonResponse findAccountInfo(String contractName, int pageNumber, int pageSize) {
        UnitQueryPageReq<String> param = new UnitQueryPageReq<>();
        param.setUnitName(contractName + contractName);
        param.setPageNo(pageNumber);
        param.setPageSize(pageSize);

        return methodManager.find(param);
    }
}
