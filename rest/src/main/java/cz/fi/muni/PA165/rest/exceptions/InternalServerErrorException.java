package cz.fi.muni.PA165.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unexpected error occurred")
public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException(String message){
        super(message);
    }

    public InternalServerErrorException(Throwable ex){
        super(ex);
    }
}
