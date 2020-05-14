package cz.fi.muni.PA165.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Request is colliding with specification")
public class ConflictException extends RuntimeException {
    public ConflictException(String message){
        super(message);
    }
}
