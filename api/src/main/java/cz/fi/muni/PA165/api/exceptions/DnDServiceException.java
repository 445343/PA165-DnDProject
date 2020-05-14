package cz.fi.muni.PA165.api.exceptions;

/**
 * Exception thrown by service layer
 * @author Boris Jadus
 */
public class DnDServiceException extends RuntimeException {

    private ErrorStatus errorStatus;

    public DnDServiceException(String message) {
        super(message);
    }

    public DnDServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DnDServiceException(Throwable ex, ErrorStatus status){
        super(ex);
        this.errorStatus = status;
    }

    public DnDServiceException(String message, ErrorStatus status){
        super(message);
        this.errorStatus = status;
    }
    public ErrorStatus getErrorStatus(){
        return errorStatus;
    }

}
