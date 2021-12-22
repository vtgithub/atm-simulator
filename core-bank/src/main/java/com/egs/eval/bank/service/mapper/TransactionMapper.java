package com.egs.eval.bank.service.mapper;

import com.egs.eval.bank.dal.entity.Transaction;
import com.egs.eval.bank.service.model.TransactionResult;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface TransactionMapper {

    Transaction getTransaction(String userId, Integer value, String transactionId);
    TransactionResult getTransactionResult(Transaction transaction, long balance);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "rolledBackFor", source = "transaction.transactionId")
    @Mapping(target = "value", expression = "java(transaction.getValue() * -1)")
    @Mapping(target = "userId", source = "transaction.userId")
    @Mapping(target = "transactionId", source = "transactionId")
    Transaction getRollbackTransactionFromTransaction(Transaction transaction, String transactionId);
}
