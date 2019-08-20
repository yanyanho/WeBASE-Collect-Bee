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
public class BAC003IncreaseBO extends MethodBO {
	private long _id;
	private String _to;
	private String _quantities;
	private String _data;
}
