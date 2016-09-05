package exceptions;

import play.api.UsefulException;

/**
 * Created by almothafar on 4/7/2016.
 */
public class ApplicationException extends UsefulException {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
