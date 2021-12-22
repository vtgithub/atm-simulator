package com.egs.eval.bankclient.service;

import com.egs.eval.bankclient.remote.model.BalanceResponse;
import com.egs.eval.bankclient.remote.model.PredefinedValueResponse;
import com.egs.eval.bankclient.remote.model.TransactionRequest;
import com.egs.eval.bankclient.remote.model.TransactionResponse;

import java.util.List;

public interface TransactionProxy {
    TransactionResponse withdraw(String token, TransactionRequest transactionRequest);
    TransactionResponse deposit(String token, TransactionRequest transactionRequest);
    TransactionResponse rollback(String token, String transactionId);
    List<PredefinedValueResponse> getPredefinedValues(String token);
    BalanceResponse getBalance(String token);
}
