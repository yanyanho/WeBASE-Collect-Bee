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
public class BAC003TransferSingleBO extends EventBO {
    
	private String _operator;
	private String _from;
	private String _to;
	private Long _id;
	private Long _value;
	private String _data;
}
