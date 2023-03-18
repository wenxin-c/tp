package wellnus.exception;

/**
 * AtomicHabitException is thrown when a conceptual/logical error occurs in the AtomicHabit package <br>
 * <p>
 * This exception should only be used within the AtomicHabit package.
 * It differentiates between WellNUS and regular Java exceptions,
 * which allows better pinpointing of errors to the AtomicHabit logic.
 */
public class AtomicHabitException extends WellNusException {
    public AtomicHabitException(String errorMessage) {
        super(errorMessage);
    }
}
