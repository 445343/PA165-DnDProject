package cz.fi.muni.PA165.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad login attempt")
public class BadLoginException extends RuntimeException {
    public BadLoginException(String message){
        super(message);
    }
}
