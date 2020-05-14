package cz.fi.muni.PA165.rest.exceptions;

import cz.fi.muni.PA165.api.exceptions.DnDServiceException;

public class ExceptionSorter {

    public static RuntimeException throwException(Exception ex){
        if (ex instanceof DnDServiceException){
            switch (((DnDServiceException) ex).getErrorStatus()){
                case RESOURCE_NOT_FOUND:
                    return new ResourceNotFoundException(ex.getLocalizedMessage());
                case CONFLICT:
                    return new ConflictException(ex.getLocalizedMessage());
                case NOT_LOGGED_IN:
                    return new NotLoggedInException(ex.getLocalizedMessage());
                case BAD_LOGIN:
                    return new BadLoginException(ex.getLocalizedMessage());
                case INTERNAL:
                default:
                    return new InternalServerErrorException(ex.getLocalizedMessage());
            }
        }
        if (ex instanceof IllegalArgumentException)
            return new InvalidRequestException(ex.getLocalizedMessage());
        return new InternalServerErrorException(ex);
    }
}
