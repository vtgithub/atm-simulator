package com.egs.eval.bank.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document("transaction")
public class Transaction extends Entity {
    private String userId;
    private Long value;
    private String transactionId;
}
