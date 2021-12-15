package com.egs.eval.bank.api.rest;

import com.egs.eval.bank.service.Authenticator;
import com.egs.eval.bank.service.model.AuthenticationMechanism;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationFactory {

    private final List<Authenticator> authenticatorList;

    public Authenticator getAuthMechanism(AuthenticationMechanism authType) {
        for (Authenticator authenticator : authenticatorList) {
            if (authenticator.matches(authType))
                return authenticator;
        }
        throw new IllegalArgumentException("Authentication algorithm not defined!");
    }
}
