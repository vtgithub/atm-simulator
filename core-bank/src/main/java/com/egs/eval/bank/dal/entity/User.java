package com.egs.eval.bank.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Document("user")
public class User extends Entity {
    private String pin;
    private String fingerprint;
    private Set<String> cardSet;
    private Session activeSession;
    private Integer todayFailedLoginAttempts;

    @Data
    private static class Session {
        // TODO: 12/18/21 save token inside it and put @Cacheable on top of related service that use it
        private String token;
        private LocalDateTime createdDate;
    }
}
