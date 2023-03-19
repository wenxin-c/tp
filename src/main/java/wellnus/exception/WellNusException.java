package wellnus.exception;

/**
 * WellNusException is thrown when a conceptual/logical error occurs in WellNUS++
 * <p>
 * This exception may be thrown for any general error in WellNUS++'s execution.
 * It is meant to differentiate between Java exceptions to allow better pinpointing of errors.
 */
public class WellNusException extends Exception {
    public WellNusException(String errorMessage) {
        super(errorMessage);
    }
}
