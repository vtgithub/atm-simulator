package com.egs.eval.bank.api.rest;

import com.egs.eval.bank.api.rest.model.BalanceResponse;
import com.egs.eval.bank.api.rest.model.PredefinedValueResponse;
import com.egs.eval.bank.api.rest.model.TransactionRequest;
import com.egs.eval.bank.api.rest.model.TransactionResponse;
import com.egs.eval.bank.service.JwtTokenGranter;
import com.egs.eval.bank.service.TransactionService;
import com.egs.eval.bank.service.model.TransactionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransactionFacade {

    private final TransactionService service;
    private final JwtTokenGranter tokenGranter;
    private final TransactionFacadeMapper mapper;

    public List<PredefinedValueResponse> getPredefinedValueList() {
        return service.getPredefinedValues().stream()
                .map(PredefinedValueResponse::new)
                .collect(Collectors.toList());
    }

    public TransactionResponse withdraw(TransactionRequest transactionRequest, String authorizationHeader) {
        String userId = tokenGranter.getUserIdFromToken(AuthUtil.getBearerToken(authorizationHeader));
        TransactionResult transactionResult = service.withdraw(transactionRequest.getValue(), userId);
        return mapper.getTransactionResponseFromResultAndValue(transactionResult, transactionRequest.getValue());
    }

    public TransactionResponse deposit(TransactionRequest transactionRequest, String authorizationHeader) {
        String userId = tokenGranter.getUserIdFromToken(AuthUtil.getBearerToken(authorizationHeader));
        TransactionResult transactionResult = service.deposit(transactionRequest.getValue(), userId);
        return mapper.getTransactionResponseFromResultAndValue(transactionResult, transactionRequest.getValue());
    }

    public BalanceResponse getBalance(String authorizationHeader) {
        String userId = tokenGranter.getUserIdFromToken(AuthUtil.getBearerToken(authorizationHeader));
        long balance = service.getUserBalance(userId);
        return new BalanceResponse(balance);
    }

    public TransactionResponse rollback(String transactionId) {
        TransactionResult transactionResult = service.rollback(transactionId);
        return mapper.getTransactionResponseFromResult(transactionResult);
    }
}
