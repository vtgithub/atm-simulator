package com.egs.eval.bank.service.mapper;

import com.egs.eval.bank.dal.entity.Transaction;
import com.egs.eval.bank.service.model.TransactionResult;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface TransactionMapper {

    Transaction getTransaction(String userId, Integer value, String transactionId);
    TransactionResult getTransactionResult(String transactionId, long balance);
}
