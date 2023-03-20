package wellnus.exception;

/**
 * StorageException is thrown when a conceptual/logical error occurs in Storage.
 * <p>
 * This exception is only thrown within the functional code for Storage.
 */
public class StorageException extends Exception {
    public StorageException(String message) {
        super(message);
    }
}
