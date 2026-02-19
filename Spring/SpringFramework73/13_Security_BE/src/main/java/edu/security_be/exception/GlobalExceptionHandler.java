package edu.security_be.exception;

import edu.security_be.dto.APIResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIResponse handleUsernameNotFoundException(UsernameNotFoundException e) {
        return new APIResponse(
                HttpStatus.NOT_FOUND.value(),
                "Username not found",
                e.getMessage()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public APIResponse handleBadCredentialsException(BadCredentialsException e) {
        return new APIResponse(
                HttpStatus.NOT_FOUND.value(),
                "Username and password incorrect",
                e.getMessage()
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public APIResponse handleExpiredJwtException(ExpiredJwtException e) {
        return new APIResponse(
                HttpStatus.NOT_FOUND.value(),
                "Token is expired",
                e.getMessage()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public APIResponse handleRuntimeException(RuntimeException e) {
        return new APIResponse(
                HttpStatus.NOT_FOUND.value(),
                "Internal Server Error",
                e.getMessage()
        );
    }
}
