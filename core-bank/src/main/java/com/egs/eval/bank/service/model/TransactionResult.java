package com.egs.eval.bank.service.model;

import lombok.Data;

@Data
public class TransactionResult {
    private String transactionId;
    private Long balance;
    private Long value;
}
