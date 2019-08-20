package com.webank.webasebee.core.app.trans;


import com.webank.webasebee.common.vo.CommonDataResponse;
import com.webank.webasebee.common.vo.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransLogService {

    @Autowired
    RestTemplate restTemplate;


    public CommonResponse findTransLog(String unitName, String contractAddress, String userAddress, int pageNumber, int pageSize) throws UnsupportedEncodingException {

      String url = "http://localhost:8899/"+"api/event/specification/get";
     Map<String, Object> imap = new HashMap<>();
     imap.put("pageNo",pageNumber);
     imap.put("pageSize",pageSize);


        if(userAddress != null && userAddress != "" ) {
          Map<String, Object> orConditionMap = new HashMap<>();
         orConditionMap.put("from",userAddress);
         orConditionMap.put("to",userAddress);
         imap.put("orConditions",orConditionMap);
        }

     imap.put("unitName",unitName);

     Map<String, Object> andConditionMap = new HashMap<>();
     andConditionMap.put("contractAddress",contractAddress);
     imap.put("andConditions",andConditionMap);

     CommonDataResponse result = restTemplate.postForObject(url,imap, CommonDataResponse.class);
     return result;
    }


    public CommonResponse findAccountInfo( String contractName, int pageNumber, int pageSize) {

      String url = "http://localhost:8899/"+"api/method/name/get";
     Map<String, Object> imap = new HashMap<>();
     imap.put("pageNo",pageNumber);
     imap.put("pageSize",pageSize);
      imap.put("unitName",contractName+contractName);

     CommonDataResponse result = restTemplate.postForObject(url,imap, CommonDataResponse.class);
     return result;
    }
}
