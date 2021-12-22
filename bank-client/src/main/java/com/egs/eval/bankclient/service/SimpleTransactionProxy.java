package com.egs.eval.bankclient.service;

import com.egs.eval.bankclient.remote.BankTransactionApiClient;
import com.egs.eval.bankclient.remote.FeignGeneralException;
import com.egs.eval.bankclient.remote.util.ExceptionConverter;
import com.egs.eval.bankclient.remote.model.BalanceResponse;
import com.egs.eval.bankclient.remote.model.PredefinedValueResponse;
import com.egs.eval.bankclient.remote.model.TransactionRequest;
import com.egs.eval.bankclient.remote.model.TransactionResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SimpleTransactionProxy implements TransactionProxy {

    private final BankTransactionApiClient bankTransactionApiClient;

    @CircuitBreaker(name = "bank-transaction", fallbackMethod = "withdrawFallBack")
    @Override
    public TransactionResponse withdraw(String token, TransactionRequest transactionRequest) {
        return bankTransactionApiClient.doWithdraw(transactionRequest, bearer(token));
    }
    private TransactionResponse withdrawFallBack(String token, TransactionRequest transactionRequest, Throwable throwable){
        throw getProperExceptionFromCoreBankException(throwable);
    }

    @CircuitBreaker(name = "bank-transaction", fallbackMethod = "depositFallBack")
    @Override
    public TransactionResponse deposit(String token, TransactionRequest transactionRequest) {
        return bankTransactionApiClient.doDeposit(transactionRequest, bearer(token));
    }
    private TransactionResponse depositFallBack(String token, TransactionRequest transactionRequest, Throwable throwable){
        throw getProperExceptionFromCoreBankException(throwable);
    }

    @CircuitBreaker(name = "bank-transaction", fallbackMethod = "rollbackFallBack")
    @Override
    public TransactionResponse rollback(String token, String transactionId) {
        return bankTransactionApiClient.doRollback(transactionId, bearer(token));
    }
    private TransactionResponse rollbackFallBack(String token, String transactionId, Throwable throwable){
        throw getProperExceptionFromCoreBankException(throwable);
    }

    @CircuitBreaker(name = "bank-transaction", fallbackMethod = "getPredefinedValuesFallBack")
    @Override
    public List<PredefinedValueResponse> getPredefinedValues(String token) {
        return bankTransactionApiClient.getPredefinedValues(bearer(token));
    }

    private List<PredefinedValueResponse> getPredefinedValuesFallBack(String token, Throwable e) {
        throw getProperExceptionFromCoreBankException(e);
    }

    @CircuitBreaker(name = "bank-transaction", fallbackMethod = "getBalanceFallBack")
    @Override
    public BalanceResponse getBalance(String token) {
        throw new PreconditionFailedException("d");
//        return bankTransactionApiClient.getBalance(bearer(token));
    }

    private BalanceResponse getBalanceFallBack(String token, Throwable throwable){
        throw getProperExceptionFromCoreBankException(throwable);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private String bearer(String token) {
        return "Bearer " + token;
    }

    private RuntimeException getProperExceptionFromCoreBankException(Throwable throwable) {
        if (throwable.getClass().equals(FeignGeneralException.class)){
            return ExceptionConverter.getProperExceptionFromFeignGeneralException((FeignGeneralException) throwable);
        }else{
            // circuit opened
            return new FeignGeneralException.ServiceUnavailable("server error. please try after 60 minutes!");
        }
    }
}
