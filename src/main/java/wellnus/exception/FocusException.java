package wellnus.exception;

/**
 * FocusException is thrown when a conceptual/logical error occurs in Focus.
 * <p>
 * This exception is only thrown within the functional code for Focus.
 */
public class FocusException extends WellNusException {
    public FocusException(String message) {
        super(message);
    }

}
