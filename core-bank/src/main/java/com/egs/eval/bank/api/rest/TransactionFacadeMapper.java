package com.egs.eval.bank.api.rest;


import com.egs.eval.bank.api.rest.model.TransactionResponse;
import com.egs.eval.bank.service.model.TransactionResult;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface TransactionFacadeMapper {

    TransactionResponse getTransactionResponseFromResultAndValue(TransactionResult transactionResult, Integer value);

    TransactionResponse getTransactionResponseFromResult(TransactionResult transactionResult);
}
