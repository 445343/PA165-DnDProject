package cz.fi.muni.PA165.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You need to be logged in")
public class NotLoggedInException extends RuntimeException{
    public NotLoggedInException(String message){
        super(message);
    }
}
