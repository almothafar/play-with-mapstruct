package exceptions;

import play.api.UsefulException;

/**
 * Created by almothafar on 3/8/2016.
 */
public class NoRequestBodyException extends UsefulException {

    public NoRequestBodyException(String message) {
        super(message);
    }

    public NoRequestBodyException(String message, Throwable cause) {
        super(message, cause);
    }
}
