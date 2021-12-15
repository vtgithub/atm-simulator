package com.egs.eval.bank.api.rest.model;

import lombok.Data;

@Data
public class TransactionResponse {
    private Integer value;
    private Long balance;
    private String transactionId;
}
