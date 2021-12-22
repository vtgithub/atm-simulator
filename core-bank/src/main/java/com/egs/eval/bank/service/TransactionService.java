package com.egs.eval.bank.service;

import com.egs.eval.bank.service.model.TransactionResult;

import java.util.List;

public interface TransactionService {
    List<Integer> getPredefinedValues();

    TransactionResult withdraw(Integer value, String userId);

    TransactionResult deposit(Integer value, String userId);

    TransactionResult rollback(String transactionId);

    long getUserBalance(String userId);
}
