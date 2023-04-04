package wellnus.exception;

/**
 * ReflectionException is thrown when a conceptual/logical error occurs in the reflection package <br>
 * <p>
 * This exception should only be used within the reflection package.
 */
public class ReflectionException extends WellNusException {
    public ReflectionException(String message) {
        super(message);
    }
}
