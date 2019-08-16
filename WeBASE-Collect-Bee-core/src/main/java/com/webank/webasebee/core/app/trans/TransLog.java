package com.webank.webasebee.core.app.trans;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class TransLog {
 private int pkId;
 private String sender;
 private String to;
 private BigInteger value;
 private String  data;
 private LocalDateTime blockTimeStamp;
 private String transHash;
 private BigInteger blockNumber;

}
