package com.egs.eval.bankclient.service;

import com.egs.eval.bankclient.remote.BankAuthApiClient;
import com.egs.eval.bankclient.remote.FeignGeneralException;
import com.egs.eval.bankclient.remote.model.AuthRequest;
import com.egs.eval.bankclient.remote.model.AuthTypesResponse;
import com.egs.eval.bankclient.remote.model.TokenResponse;
import com.egs.eval.bankclient.remote.util.ExceptionConverter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleAuthProxy implements AuthProxy {

    private final BankAuthApiClient bankAuthApiClient;

    @Override
    public AuthTypesResponse getAuthTypes() {
        return bankAuthApiClient.getAuthMechanisms();
    }

    @CircuitBreaker(name = "bank-auth", fallbackMethod = "getFreshTokenFallBack")
    @Override
    public TokenResponse getFreshToken(AuthRequest authRequest) {
        return bankAuthApiClient.getToken(authRequest);
    }
    private TokenResponse getFreshTokenFallBack(AuthRequest authRequest, Throwable throwable){
        RuntimeException exception = getProperExceptionFromCoreBankException(throwable);
        if (exception instanceof TooManyRequestsException){
            System.out.println(" ______________ card locked ...");
            // TODO: 12/22/21 lock card
        }
        throw exception;
    }

    private RuntimeException getProperExceptionFromCoreBankException(Throwable throwable) {
        if (throwable.getClass().equals(FeignGeneralException.class)){
            return ExceptionConverter.getProperExceptionFromFeignGeneralException((FeignGeneralException) throwable);
        }else{
            // circuit opened
            return new FeignGeneralException.ServiceUnavailable("server error. please try after 30 seconds!");
        }
    }
}
