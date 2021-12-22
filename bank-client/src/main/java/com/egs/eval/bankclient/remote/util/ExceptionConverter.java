package com.egs.eval.bankclient.remote.util;

import com.egs.eval.bankclient.remote.FeignGeneralException;
import com.egs.eval.bankclient.service.AuthenticationFailedException;
import com.egs.eval.bankclient.service.TooManyRequestsException;
import com.egs.eval.bankclient.service.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Slf4j
public class ExceptionConverter {

    public static RuntimeException getProperExceptionFromFeignGeneralException(FeignGeneralException e) {
        log.warn("core bank call exception occured", e);
        HttpStatus status = HttpStatus.resolve(e.getStatus());
        if (Objects.isNull(status))
            return e;
        return switch (status) {
            case UNAUTHORIZED -> new AuthenticationFailedException(e.getBody());
            case TOO_MANY_REQUESTS -> new TooManyRequestsException(e.getBody());
            case PRECONDITION_FAILED, CONFLICT, BAD_REQUEST -> new ValidationException(e.getBody());
            case NOT_FOUND -> new RuntimeException(e.getBody());
            default -> new FeignGeneralException(e.getStatus(), e.getBody());
        };
    }

}
