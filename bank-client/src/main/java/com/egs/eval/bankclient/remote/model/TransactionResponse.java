package com.egs.eval.bankclient.remote.model;

import lombok.Data;

@Data
public class TransactionResponse {
    private Integer value;
    private Long balance;
    private String transactionId;
}
