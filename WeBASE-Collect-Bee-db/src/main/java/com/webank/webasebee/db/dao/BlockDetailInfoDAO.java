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
package com.webank.webasebee.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webank.webasebee.common.bo.data.BlockDetailInfoBO;
import com.webank.webasebee.db.entity.BlockDetailInfo;
import com.webank.webasebee.db.repository.BlockDetailInfoRepository;

import cn.hutool.core.bean.BeanUtil;

/**
 * BlockDetailInfoDAO
 *
 * @author maojiayu
 * @data Dec 12, 2018 2:45:13 PM
 *
 */
@Component
public class BlockDetailInfoDAO implements SaveInterface<BlockDetailInfo> {
    @Autowired
    private BlockDetailInfoRepository blockDetailInfoRepository;
    
    public void save(BlockDetailInfoBO bo) {
        BlockDetailInfo blockDetailInfo = new BlockDetailInfo();
        BeanUtil.copyProperties(bo, blockDetailInfo, true);
        save(blockDetailInfo);
    }

    public void save(BlockDetailInfo blockDetailInfo) {
        BaseDAO.saveWithTimeLog(blockDetailInfoRepository, blockDetailInfo);
    }

    public BlockDetailInfo getBlockDetailInfoByBlockHeight(long blockHeight) {
        return blockDetailInfoRepository.findByBlockHeight(blockHeight);
    }

    public BlockDetailInfo getBlockDetailInfoByBlockHash(String blockHash) {
        return blockDetailInfoRepository.findByBlockHash(blockHash);
    }

    public long sumByTxCountBetweens(long beginIndex, long endIndex) {
        return blockDetailInfoRepository.sumByTxCountBetweens(beginIndex, endIndex);
    }
    
    public BlockDetailInfo getCurrentMaxBlock(){
        return blockDetailInfoRepository.findTopByOrderByBlockHeightDesc();
    }
}
