package wellnus.exception;

/**
 * Category of Exceptions related to the <code>Tokenizer</code> interface and its operations/subclasses.
 * @see wellnus.storage.Tokenizer
 */
public class TokenizerException extends WellNusException {
    /**
     * Initializes an instance of TokenizerException with the given error message.
     * @param errorMessage Error message to show to the user
     */
    public TokenizerException(String errorMessage) {
        super(errorMessage);
    }
}
