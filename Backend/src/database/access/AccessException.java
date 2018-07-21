package database.access;

/**
 * Created by Antonio Zaitoun on 21/07/2018.
 */
public class AccessException extends RuntimeException {

    public AccessException() { }

    public AccessException(String message) {
        super(message);
    }
}
