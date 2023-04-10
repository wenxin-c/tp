package wellnus.focus;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wellnus.command.CommandParser;
import wellnus.exception.BadCommandException;
import wellnus.exception.WellNusException;
import wellnus.focus.command.StartCommand;
import wellnus.focus.feature.FocusManager;
import wellnus.focus.feature.Session;
import wellnus.ui.TextUi;


public class FocusTest {
    private static final String START_COMMAND = "start";
    private final CommandParser parser;
    private final ByteArrayOutputStream outputStreamCaptor;
    private final Session session;

    public FocusTest() {
        this.session = new Session();
        this.parser = new CommandParser();
        this.outputStreamCaptor = new ByteArrayOutputStream();
    }

    private String getMessageFrom(String uiOutput) {
        TextUi textUi = new TextUi();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        textUi.printSeparator();
        String separator = outputStream.toString().trim();
        StringBuilder resultBuilder = new StringBuilder();
        String[] outputLines = uiOutput.split(System.lineSeparator());
        for (String outputLine : outputLines) {
            String trimmedOutputLine = outputLine.trim();
            if (!trimmedOutputLine.equals(separator)) {
                resultBuilder.append(trimmedOutputLine).append(System.lineSeparator());
            }
        }
        return resultBuilder.toString().trim();
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    /**
     * Test whether start command starts the timer program.
     * Stops program immediately after.
     *
     * @throws BadCommandException
     */
    @Test
    void startTimer_checkResult_success() throws WellNusException {
        HashMap<String, String> arguments = parser.parseUserInput(START_COMMAND);
        StartCommand startCommand = new StartCommand(arguments, session);
        startCommand.execute();
        Assertions.assertEquals(true, session.getSession().get(0).getIsRunning());
        session.getSession().get(0).setStop();
    }

    /**
     * Test FocusManager to throw {@link BadCommandException} when an invalid command is given to the FocusManager
     */
    @Test
    public void startTimer_invalidCommand_badCommandExceptionThrown() {
        // Test false command by user
        FocusManager focusManager = new FocusManager();
        String command = "wrong";
        Assertions.assertThrows(BadCommandException.class, () -> {
            focusManager.testInvalidCommand(command);
        });
    }
}

