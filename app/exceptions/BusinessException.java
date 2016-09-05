package exceptions;

import play.api.UsefulException;

/**
 * Created by almothafar on 3/8/2016.
 */
public class BusinessException extends UsefulException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
