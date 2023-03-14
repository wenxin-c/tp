package wellnus.atomichabit.command;

public class CommandResult {
    private final String feedbackToUser;

    /**
     * Constructor to be executed when class is initialized.
     *
     * @param feedbackToUser feedback to be printed to user
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Retrieves the private String feedbackToUser.
     *
     * @return String to be printed as output to user
     */
    public String getCommandResult() {
        return feedbackToUser;
    }
}


