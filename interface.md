# 目录

<!-- TOC -->

- [目录](#目录)
- [1.资产信息接口](#1资产信息接口)
    - [1.1. 查询资产列表](#11-查询资产列表)
    - [1.2. 查资产状态](#12-查资产状态)
    - [1.3. 获取账户资产余额](#13-获取账户资产余额)
    - [1.4. 获取账户资产余额](#14-获取账户资产余额)
    - [1.5. 获取资产集合](#15-获取资产集合)
    - [1.6. 获取账户BAC003资产余额](#16-获取账户bac003资产余额)
- [2.交易记录接口](#2交易记录接口)
    - [2.1. 查询交易历史记录列表](#21-查询交易历史记录列表)
- [3.sdk接口接口](#3sdk接口接口)
    - [3.1. 获取交易回执接口](#31-获取交易回执接口)
- [3.2. 根据交易hash获取交易信息接口](#32-根据交易hash获取交易信息接口)

<!-- /TOC -->


# 1.资产信息接口

## 1.1. 查询资产列表

### 接口描述

> 根据合约名称分页查出资产信息列表

### 接口URL

**http://localhost:8899/asset/list?pageNumber={pageNumber}&pageSize={pageSize}&contractName={contractName}**

### 调用方法

HTTP GET

### 请求参数

**1）参数表**

| **序号** | **中文** | **参数名**   | **类型**       | **最大长度** | **必填** | **说明**                           |
| --- | -------- | ------------ | ------ | ------------ | --- | ---------------------- |
| 1   | 合约名称 | contractName | String     |      | 是   |   BAC001、BAC002、BAC003，默认BAC001  |
| 2   | 页码 | pageNumber      | int         |              | 是   |    默认1   |
| 3   | 每页记录数  | pageSize      | int|              | 是       | 默认50 |

**2）数据格式**
```
http://localhost:8899/asset/list?pageNumber=1&pageSize=10&contractName=BAC002
```


### 响应参数

**1）参数表**

| **序号** | **中文** | **参数名** | **类型** | **必填** | **说明**              |
| -------- | -------- | ---------- | -------- | -------- | --------------------- |
| 1        | 返回码   | status       | String   | 是       | 0成功  |
| 2        | 返回数据 | data       | Object   |          |                       |
| 2.1        | 页码 | data.pageNo       | int   |     是     |                     |
| 2.2        | 每页记录数 | data.pageSize       | int   |     是     |                     |
| 2.3        | 总记录数 | data.totalCount       | int   |     是     |                     |
| 2.4        | 资产信息列表 | data.result       | list   |     是     |        |
| 2.4.1.1        | 主键 | data.result[0].pkId  | int   |     是     |        |
| 2.4.1.2        | 块高 | data.result[0].blockHeight  | bigInteger   |     是     |        |
| 2.4.1.3        | 交易哈希 | data.result[0].txHash  | String   |     是     |        |
| 2.4.1.4        | 合约地址 | data.result[0].contractAddress  | String   |     是     |        |
| 2.4.1.5        | 状态 | data.result[0].methodStatus  | String   |     是     |   0x0资产创建成功    |
| 2.4.1.6        | 描述 | data.result[0].description  | String   |     否     |       |
| 2.4.1.7        | 出块时间 | data.result[0].blockTimeStamp  | String   |     是     |       |



**2）数据格式**
```
{
	"status": 0,
	"data": {
		"pageNo": 1,
		"pageSize": 10,
		"totalCount": 1,
		"orderBy": "blockTimeStamp",
		"order": "DESC",
		"result": [{
			"pkId": 4,
			"blockHeight": 592,
			"txHash": "0x7199bf4c718786f4816091d149651aae4ca9a937e22b3b74b92f046cee241358",
			"contractAddress": "0xd5152f16d6d6bc4ed34f56bc5eee6ef4e829fc97",
			"methodStatus": "0x0",
			"description": "AAA",
			"shortName": "AAA",
			"blockTimeStamp": "2019-08-28 16:37:51",
			"depotUpdatetime": "2019-09-05 10:30:21"
		}]
	}
}
```




## 1.2. 查资产状态

### 接口描述

> 根据合约名称跟合约地址查询资产状态

### 接口URL

**http://localhost:8899/asset/status?contractName={contractName}&contractAddress={contractAddress}**

### 调用方法

HTTP GET

### 请求参数

**1）参数表**

| **序号** | **中文** | **参数名**   | **类型**       | **最大长度** | **必填** | **说明**                           |
| --- | -------- | ------------ | ------ | ------------ | --- | ---------------------- |
| 1   | 合约名称 | contractName | String   |    | 是   |   BAC001、BAC002、BAC003，默认BAC001|
| 2   | 合约地址 | contractAddress | String   |    | 是   |   |

**2）数据格式**
```
http://localhost:8899/asset/status?contractName=BAC001&contractAddress=0x170069ab7f7643ed0b4b507459ee3c29c5a87d2b
```


### 响应参数

**1）参数表**

| **序号** | **中文** | **参数名** | **类型** | **必填** | **说明**              |
| -------- | -------- | ---------- | -------- | -------- | --------------------- |
| 1        | 返回码   | code       | String   | 是       | 0成功  |
| 2        | 结果描述   | message       | String   | 是       |   |
| 3        | 返回数据 | data       | Object   |          |          |
| 3.1      | 资产发行总量 | data.assetTotal       | int   |          |          |
| 3.2      | 资产状态 | data.assetStatus       | int   |   |   0正常，2暂停   |



**2）数据格式**
```
{
	"code": 0,
	"message": "success",
	"data": {
		"assetTotal": 11111111111,
		"assetStatus": 0
	}
}
```



## 1.3. 获取账户资产余额

### 接口描述

> 获取账户资产余额

### 接口URL

**http://localhost:8899/asset/balance?contractName={contractName}&contractAddress={contractAddress}&userAddress={userAddress}**

### 调用方法

HTTP GET

### 请求参数

**1）参数表**

| **序号** | **中文** | **参数名**   | **类型**       | **最大长度** | **必填** | **说明**                           |
| --- | -------- | ------------ | ------ | ------------ | --- | ---------------------- |
| 1   | 合约名称 | contractName | String   |    | 是   |   BAC001、BAC002、BAC003，默认BAC001|
| 2   | 合约地址 | contractAddress | String   |    | 是   |  |
| 3   | 用户地址 | userAddress | String   |    | 是   |   |


**2）数据格式**
```
http://localhost:8899/asset/balance?contractName=BAC001&contractAddress=0x170069ab7f7643ed0b4b507459ee3c29c5a87d2b&userAddress=0x170069ab7f7643ed0b4b507459ee3c29c5a87d2b
```


### 响应参数

**1）参数表**

| **序号** | **中文** | **参数名** | **类型** | **必填** | **说明**              |
| -------- | -------- | ---------- | -------- | -------- | --------------------- |
| 1        | 返回码   | code       | String   | 是       | 0成功  |
| 2        | 结果描述   | message       | String   | 是       |   |
| 3        | 返回数据 | data       | Object   |          |          |
| 3.1      | 资产余额 | data.balance       | int   |          |          |



**2）数据格式**
```
{
	"code": 0,
	"message": "success",
	"data": {
		"balance": 0
	}
}
```



## 1.4. 获取账户资产余额

### 接口描述

> 获取账户资产余额

### 接口URL

**http://localhost:8899/asset/balance?contractName={contractName}&contractAddress={contractAddress}&userAddress={userAddress}**

### 调用方法

HTTP GET

### 请求参数

**1）参数表**

| **序号** | **中文** | **参数名**   | **类型**       | **最大长度** | **必填** | **说明**                           |
| --- | -------- | ------------ | ------ | ------------ | --- | ---------------------- |
| 1   | 合约名称 | contractName | String   |    | 是   |   BAC001、BAC002、BAC003，默认BAC001|
| 2   | 合约地址 | contractAddress | String   |    | 是   |  |
| 3   | 用户地址 | userAddress | String   |    | 是   |   |


**2）数据格式**
```
http://localhost:8899/asset/balance?contractName=BAC001&contractAddress=0x170069ab7f7643ed0b4b507459ee3c29c5a87d2b&userAddress=0x170069ab7f7643ed0b4b507459ee3c29c5a87d2b
```


### 响应参数

**1）参数表**

| **序号** | **中文** | **参数名** | **类型** | **必填** | **说明**              |
| -------- | -------- | ---------- | -------- | -------- | --------------------- |
| 1        | 返回码   | code       | String   | 是       | 0成功  |
| 2        | 结果描述   | message       | String   | 是       |   |
| 3        | 返回数据 | data       | Object   |          |          |
| 3.1      | 资产余额 | data.balance       | int   |          |          |



**2）数据格式**
```
{
	"code": 0,
	"message": "success",
	"data": {
		"balance": 0
	}
}
```


## 1.5. 获取资产集合

### 接口描述

> 根据合约地址获取BAC003下的资产集合

### 接口URL

**http://localhost:8899/asset/bac003-info?pageNumber={pageNumber}&pageSize={pageSize}&contractAddress={contractAddress}**

### 调用方法

HTTP GET

### 请求参数

**1）参数表**

| **序号** | **中文** | **参数名**   | **类型**       | **最大长度** | **必填** | **说明**                           |
| --- | -------- | ------------ | ------ | ------------ | --- | ---------------------- |
| 1   | 合约地址 | contractAddress | String     |      | 是   |     |
| 2   | 页码 | pageNumber      | int         |              | 是   |    默认1   |
| 3   | 每页记录数  | pageSize      | int|              | 是       | 默认50 |


**2）数据格式**
```
http://localhost:8899/asset/bac003-info?pageNumber=1&pageSize=100&contractAddress=0x1ab120e6f1164c341169c4bcfe758e0d29d74ee2
```


### 响应参数

**1）参数表**

| **序号** | **中文** | **参数名** | **类型** | **必填** | **说明**              |
| -------- | -------- | ---------- | -------- | -------- | --------------------- |
| 1        | 返回码   | status       | String   | 是       | 0成功  |
| 2        | 返回数据 | data       | Object   |          |                       |
| 2.1        | 页码 | data.pageNo       | int   |     是     |                     |
| 2.2        | 每页记录数 | data.pageSize       | int   |     是     |                     |
| 2.3        | 总记录数 | data.totalCount       | int   |     是     |                     |
| 2.4        | 资产信息列表 | data.result       | list   |     是     |        |
| 2.4.1.1        | 主键 | data.result[0].pkId  | int   |     是     |        |
| 2.4.1.2        | 块高 | data.result[0].blockHeight  | bigInteger   |     是     |        |
| 2.4.1.3        | 交易哈希 | data.result[0].txHash  | String   |     是     |        |
| 2.4.1.4        | 合约地址 | data.result[0].contractAddress  | String   |     是     |        |
| 2.4.1.5        | 合约地址 | data.result[0].methodStatus  | int   |     是     |        |
| 2.4.1.6        | 初始额度 | data.result[0]._initialSupply  | int   |     是     |        |
| 2.4.1.7        | 资产描述 | data.result[0]._uri  | String   |     是     |        |
| 2.4.1.8        | 备注 | data.result[0]._data  | String   |     是     |        |
| 2.4.1.9        | 创建时间 | data.result[0].blockTimeStamp  | String   |     是     |        |




**2）数据格式**
```
{
	"status": 0,
	"data": {
		"pageNo": 1,
		"pageSize": 100,
		"totalCount": 1,
		"orderBy": "blockTimeStamp",
		"order": "DESC",
		"result": [{
			"pkId": 1,
			"blockHeight": 563,
			"txHash": "0x9cc22db952db69964bedbc2ee97a25cfbe9ec605142c5992cb369b6ff6dac223",
			"contractAddress": "0x1ab120e6f1164c341169c4bcfe758e0d29d74ee2",
			"methodStatus": "0x0",
			"_initialSupply": 3000000,
			"_uri": "token 3",
			"_data": "for 3",
			"blockTimeStamp": "2019-08-28 11:44:15",
			"depotUpdatetime": "2019-09-05 10:30:20"
		}]
	}
}
```




## 1.6. 获取账户BAC003资产余额

### 接口描述

> 获取账户BAC003资产余额

### 接口URL

**http://localhost:8899/asset/bac003/balance?contractAddress={contractAddress}&userAddress={userAddress}&id={id}**

### 调用方法

HTTP GET

### 请求参数

**1）参数表**

| **序号** | **中文** | **参数名**   | **类型**       | **最大长度** | **必填** | **说明**                           |
| --- | -------- | ------------ | ------ | ------------ | --- | ---------------------- |
| 1   | 合约名称 | contractName | String   |    | 是   |   BAC001、BAC002、BAC003，默认BAC003|
| 2   | 合约地址 | contractAddress | String   |    | 是   |   |
| 3   | 资产ID | id | String   |    | 是   |   |
| 4   | 用户地址 | userAddress | String   |    | 是   |   |







**2）数据格式**
```
http://localhost:8899/asset/bac003/balance?contractAddress=0xfe5f52847a070ed1d280d5da749389fe6db5b866&userAddress=0x148947262ec5e21739fe3a931c29e8b84ee34a0f&id=2
```


### 响应参数

**1）参数表**

| **序号** | **中文** | **参数名** | **类型** | **必填** | **说明**              |
| -------- | -------- | ---------- | -------- | -------- | --------------------- |
| 1        | 返回码   | status       | String   | 是       | 0成功  |
| 2        | 结果描述   | message       | String   | 是       |   |
| 3        | 返回数据 | data       | Object   |          |          |
| 3.1      | 当前资产额度 | data.balance       | int   |          |          |



**2）数据格式**
```
{
	"code": 0,
	"message": "success",
	"data": {
		"balance": 100
	}
}
```










# 2.交易记录接口

## 2.1. 查询交易历史记录列表

### 接口描述

> 根据合约名称跟合约地址查询资产状态

### 接口URL

**http://localhost:8899/translog?pageNumber={pageNumber}&pageSize={pageSize}&contractAddress={contractAddress}?userAddress={userAddress}**

### 调用方法

HTTP GET

### 请求参数

**1）参数表**

| **序号** | **中文** | **参数名**   | **类型**       | **最大长度** | **必填** | **说明**                           |
| --- | -------- | ------------ | ------ | ------------ | --- | ---------------------- |
| 1   | 资产合约地址 | contractAddress | String   |    | 是   |   |
| 2   | 查询类别 | unitName | String   |    | 是   | BAC001Send，BAC002Send，BAC003Send，默认：BAC001Send  |
| 3  | 页码 | pageNumber      | int         |              | 是   |    默认1   |
| 4   | 每页记录数  | pageSize      | int|              | 是       | 默认50 |
| 5   | 用户地址 | userAddress | String   |    | 否   |   |


**2）数据格式**
```
http://localhost:8899/translog?pageNumber=1&pageSize=10&contractAddress=0x170069ab7f7643ed0b4b507459ee3c29c5a87d2b
```


### 响应参数

**1）参数表**

| **序号** | **中文** | **参数名** | **类型** | **必填** | **说明**              |
| -------- | -------- | ---------- | -------- | -------- | --------------------- |
| 1        | 返回码   | status       | String   | 是       | 0成功  |
| 2        | 返回数据 | data       | Object   |          |                       |
| 2.1        | 页码 | data.pageNo       | int   |     是     |                     |
| 2.2        | 每页记录数 | data.pageSize       | int   |     是     |                     |
| 2.3        | 总记录数 | data.totalCount       | int   |     是     |                     |
| 2.4        | 资产信息列表 | data.result       | list   |     是     |        |
| 2.4.1.1        | 主键 | data.result[0].pkId  | int   |     是     |        |
| 2.4.1.2        | 块高 | data.result[0].blockHeight  | bigInteger   |     是     |        |
| 2.4.1.3        | 交易哈希 | data.result[0].txHash  | String   |     是     |        |
| 2.4.1.4        | 合约地址 | data.result[0].eventContractAddress  | String   |  是     |   |
| 2.4.1.5        | 合约地址 | data.result[0].contractAddress  | String   |  是     |   |
| 2.4.1.6        | 发起者 | data.result[0].from  | String   |  是     |   |
| 2.4.1.7        | 接收者 | data.result[0].to  | String   |  是     |   |
| 2.4.1.8        | 交易额度 | data.result[0].value  | int   |  是     |   |
| 2.4.1.9        | 描述 | data.result[0].data  | String   |  是     |   |
| 2.4.1.10        | 出块时间 | data.result[0].blockTimeStamp  | String   |  是     |   |




**2）数据格式**
```
{
	"status": 0,
	"data": {
		"pageNo": 1,
		"pageSize": 10,
		"totalCount": 1,
		"orderBy": "blockTimeStamp",
		"order": "DESC",
		"result": [{
			"pkId": 276,
			"blockHeight": 1890,
			"txHash": "0xe064d1c8a1851965d0e8b92841cb517fe6bb33dfd1089f5c065468ab9b380c86",
			"eventContractAddress": "0x170069ab7f7643ed0b4b507459ee3c29c5a87d2b",
			"contractAddress": "0x170069ab7f7643ed0b4b507459ee3c29c5a87d2b",
			"from": "0x0000000000000000000000000000000000000000",
			"to": "0x8cf9b16d9622392c5c376fed7bc63ffd8415dd16",
			"value": 11111111111,
			"data": "",
			"blockTimeStamp": "2019-09-17 15:58:57",
			"depotUpdatetime": "2019-09-18 10:07:53"
		}]
	}
}
```


# 3.sdk接口接口

## 3.1. 获取交易回执接口

### 接口描述

> 根据交易hash获取交易回执

### 接口URL

**http://localhost:8899/{groupId}/web3/transactionReceipt/{transHash}**

### 调用方法

HTTP GET

### 请求参数

**1）参数表**

| **序号** | **中文** | **参数名** | **类型** | **最大长度** | **必填** | **说明** |
| -------- | -------- | ---------- | -------- | ------------ | -------- | -------- |
| 1        | 群组编号 | groupId  | int   |              | 是       |          |
| 2        | 交易hash | transHash  | String   |              | 是       |          |

**2）数据格式**
```
http://localhost:8081/1/web3/transactionReceipt/0xb2c733b742045e61c0fd6e7e2bafece04d56262a4887de9f78dad2c5dd2f944b
```

### 响应参数

**2）数据格式**
```
{
	"transactionHash": "0xe064d1c8a1851965d0e8b92841cb517fe6bb33dfd1089f5c065468ab9b380c86",
	"transactionIndex": 0,
	"blockHash": "0x48c6f61eeb53945134425583455e79351386bd0806a10c09c902c80c0319691a",
	"blockNumber": 1890,
	"gasUsed": 3521500,
	"contractAddress": "0x170069ab7f7643ed0b4b507459ee3c29c5a87d2b",
	"root": null,
	"status": "0x0",
	"from": "0x8cf9b16d9622392c5c376fed7bc63ffd8415dd16",
	"to": "0x0000000000000000000000000000000000000000",
	"input": "inputvalue",
	"output": "0x",
	"logs": [{
		"removed": false,
		"logIndex": null,
		"transactionIndex": null,
		"transactionHash": null,
		"blockHash": null,
		"blockNumber": null,
		"address": "0x170069ab7f7643ed0b4b507459ee3c29c5a87d2b",
		"data": "0x",
		"type": null,
		"topics": ["0x05e7c881d716bee8cb7ed92293133ba156704252439e5c502c277448f04e20c2", "0x0000000000000000000000008cf9b16d9622392c5c376fed7bc63ffd8415dd16"],
		"blockNumberRaw": null,
		"transactionIndexRaw": null,
		"logIndexRaw": null
	}, {
		"removed": false,
		"logIndex": null,
		"transactionIndex": null,
		"transactionHash": null,
		"blockHash": null,
		"blockNumber": null,
		"address": "0x170069ab7f7643ed0b4b507459ee3c29c5a87d2b",
		"data": "0x",
		"type": null,
		"topics": ["0xf4fbb5a5e62a703643fe5be0722720f728980fdde74f11d76eca7e13bdc3301d", "0x0000000000000000000000008cf9b16d9622392c5c376fed7bc63ffd8415dd16"],
		"blockNumberRaw": null,
		"transactionIndexRaw": null,
		"logIndexRaw": null
	}, {
		"removed": false,
		"logIndex": null,
		"transactionIndex": null,
		"transactionHash": null,
		"blockHash": null,
		"blockNumber": null,
		"address": "0x170069ab7f7643ed0b4b507459ee3c29c5a87d2b",
		"data": "0x00000000000000000000000000000000000000000000000000000002964619c700000000000000000000000000000000000000000000000000000000000000400000000000000000000000000000000000000000000000000000000000000000",
		"type": null,
		"topics": ["0xcf0f9f1eee875cfebd14defd14de0773aab4c233e702ae0ffb510aa33c3f1c4c", "0x000000000000000000000000170069ab7f7643ed0b4b507459ee3c29c5a87d2b", "0x0000000000000000000000000000000000000000000000000000000000000000", "0x0000000000000000000000008cf9b16d9622392c5c376fed7bc63ffd8415dd16"],
		"blockNumberRaw": null,
		"transactionIndexRaw": null,
		"logIndexRaw": null
	}],
	"logsBloom": "logsBloom_value",
	"statusOK": true,
	"blockNumberRaw": "0x762",
	"transactionIndexRaw": "0x0",
	"gasUsedRaw": "0x35bbdc"
}
```






# 3.2. 根据交易hash获取交易信息接口

### 接口描述

> 根据交易hash获取交易信息

### 接口URL

**http://localhost:8899/{groupId}/web3/transaction/{transHash}**

### 调用方法

HTTP GET

### 请求参数

**1）参数表**

| **序号** | **中文** | **参数名** | **类型** | **最大长度** | **必填** | **说明** |
| -------- | -------- | ---------- | -------- | ------------ | -------- | -------- |
| 1        | 群组编号 | groupId  | int   |              | 是       |          |
| 2        | 交易hash | transHash  | String   |              | 是       |          |

**2）数据格式**
```
http://localhost:8899/1/web3/transaction/0xe064d1c8a1851965d0e8b92841cb517fe6bb33dfd1089f5c065468ab9b380c86
```

### 响应参数


**1）数据格式**
```
{
	"hash": "0xe064d1c8a1851965d0e8b92841cb517fe6bb33dfd1089f5c065468ab9b380c86",
	"nonce": 458582236908375710384740944147845402517975601853672275827148130532206448266,
	"blockHash": "0x48c6f61eeb53945134425583455e79351386bd0806a10c09c902c80c0319691a",
	"blockNumber": 1890,
	"transactionIndex": 0,
	"from": "0x8cf9b16d9622392c5c376fed7bc63ffd8415dd16",
	"to": "0x0000000000000000000000000000000000000000",
	"value": 0,
	"gasPrice": 1,
	"gas": 2100000000,
	"input": "input_value",
	"creates": null,
	"publicKey": null,
	"raw": null,
	"r": null,
	"s": null,
	"v": 0,
	"blockNumberRaw": "0x762",
	"transactionIndexRaw": "0x0",
	"nonceRaw": "0x1038c608990c7f5627740630cc50788f8387f8b779afaec6e652ae8f67a0e8a",
	"valueRaw": "0x0",
	"gasRaw": "0x7d2b7500",
	"gasPriceRaw": "0x1"
}
```