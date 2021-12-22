package com.egs.eval.bank.api.rest;

import com.egs.eval.bank.api.rest.model.AuthRequest;
import com.egs.eval.bank.api.rest.model.AuthTypesResponse;
import com.egs.eval.bank.api.rest.model.TokenResponse;
import com.egs.eval.bank.service.model.AuthenticationMechanism;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade {

    private final AuthenticationFactory authFactory;

    public TokenResponse authenticate(AuthRequest authRequest) {
        String token = authFactory.getAuthMechanism(authRequest.getAuthType())
                .authenticate(authRequest.getCardNumber(), authRequest.getValue());
        return new TokenResponse(token);
    }

    public AuthTypesResponse getAuthMechanisms() {
        List<String> mechanisms = Arrays.stream(AuthenticationMechanism.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return new AuthTypesResponse(mechanisms);
    }
}
