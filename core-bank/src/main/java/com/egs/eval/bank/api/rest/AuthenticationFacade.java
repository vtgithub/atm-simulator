package com.egs.eval.bank.api.rest;

import com.egs.eval.bank.api.rest.model.AuthDto;
import com.egs.eval.bank.api.rest.model.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade {

    private final AuthenticationFactory authFactory;

    public TokenResponse authenticate(AuthDto authDto) {
        String token = authFactory.getAuthMechanism(authDto.getAuthType())
                .authenticate(authDto.getCardNumber(), authDto.getValue());
        return new TokenResponse(token);
    }
}
