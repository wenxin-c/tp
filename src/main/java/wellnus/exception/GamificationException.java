package wellnus.exception;

/**
 * Category of Exceptions related to the gamification feature.
 */
public class GamificationException extends WellNusException {
    /**
     * Returns an instance of the GamificationException.
     * @param message Error message to display on the user's screen
     */
    public GamificationException(String message) {
        super(message);
    }
}
