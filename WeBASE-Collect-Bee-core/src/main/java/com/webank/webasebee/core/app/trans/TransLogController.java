package com.webank.webasebee.core.app.trans;

import com.webank.webasebee.common.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequestMapping(value = "/translog")
public class TransLogController {

    @Autowired
    TransLogService transLogService;
    @GetMapping
    public CommonResponse getTransLog(@RequestParam(defaultValue = "BAC001Send") String unitName,
                                      @RequestParam String contractAddress,
                                      @RequestParam(required = false) String userAddress,
                                      @RequestParam(required = false, defaultValue = "1") int pageNumber,
                                      @RequestParam(required = false, defaultValue = "50") int pageSize
                                       ) throws UnsupportedEncodingException {
        CommonResponse transLog = transLogService.findTransLog(unitName,contractAddress, userAddress, pageNumber,pageSize);
        return transLog;
    }


}
