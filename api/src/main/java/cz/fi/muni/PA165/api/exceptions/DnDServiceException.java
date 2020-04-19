package cz.fi.muni.PA165.api.exceptions;

/**
 * Exception thrown by service layer
 * @author Boris Jadus
 */
public class DnDServiceException extends RuntimeException {

    public DnDServiceException(String message) {
        super(message);
    }

    public DnDServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
