package com.egs.eval.bank.service;

import com.egs.eval.bank.service.model.AuthenticationMechanism;
import com.egs.eval.bank.service.model.FailureModel;
import com.egs.eval.bank.service.model.UserQueryModel;

import java.util.function.Supplier;

public interface Authenticator {
    /**
     * could have multiple implementation
     * @param input is everyThing that we decided to authenticate with
     * @param cardNumber
     * @return  generated token with expiration mechanism
     */
    String authenticate(String cardNumber, String input);

    /**
     * defines the implementation strategy is fitted or not
     * @param authenticationMechanism
     * @return
     */
    boolean matches(AuthenticationMechanism authenticationMechanism);

    default RuntimeException doFailureWithMessageSupply(Supplier<FailureModel> failureSupplier){
        FailureModel failureModel = failureSupplier.get();
        if (failureModel.getNumberOfAttempts() > 3)
            return new NumberOfAttemptsExceededException(failureModel.getMessage());
        return new NotAuthenticatedException(failureModel.getMessage());
    }
}
