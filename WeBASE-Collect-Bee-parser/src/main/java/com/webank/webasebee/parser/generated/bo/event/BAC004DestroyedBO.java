/**
 * Copyright (C) 2018 WeBank, Inc. All Rights Reserved.
 */
package com.webank.webasebee.parser.generated.bo.event;

import com.webank.webasebee.common.bo.data.EventBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=true)
public class BAC004DestroyedBO extends EventBO {
    
	private String operator;
	private String from;
	private Long amount;
	private String data;
	private String operatorData;
}
