package leczner.jon.HurtLocker;

/**
 * Created by jonathanleczner on 10/17/16.
 */
public class InvalidFormException extends Exception {

    public InvalidFormException(String message) {
        super(message);
    }

    public InvalidFormException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
