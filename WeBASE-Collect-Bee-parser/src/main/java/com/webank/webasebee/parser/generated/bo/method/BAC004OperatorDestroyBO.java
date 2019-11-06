/**
 * Copyright (C) 2018 WeBank, Inc. All Rights Reserved.
 */
package com.webank.webasebee.parser.generated.bo.method;

import com.webank.webasebee.common.bo.data.MethodBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=true)
public class BAC004OperatorDestroyBO extends MethodBO {
	private String account;
	private long amount;
	private String data;
	private String operatorData;
}
