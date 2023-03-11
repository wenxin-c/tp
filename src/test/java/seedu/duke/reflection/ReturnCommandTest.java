package seedu.duke.reflection;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.BadCommandException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ReturnCommandTest {
    private static final String RETURN_COMMAND = "return";
    private static final String RETURN_COMMAND_WRONG_FORMAT = "return back";

    // Test whether ReturnCommand execute() method can terminate self reflection or not.
    @Test
    void execute_checkIsExit_expectTrue() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setArgumentPayload(RETURN_COMMAND);
        HashMap<String, String> returnArgumentPayload = reflectionManager.getArgumentPayload();
        ReturnCommand returnCmd = new ReturnCommand(returnArgumentPayload);
        returnCmd.execute();
        assertEquals(true, reflectionManager.getIsExit());
    }

    // Test whether isExit is false for a new ReflectionManager object after exiting from previous one.
    @Test
    void execute_checkNewObjectIsExit_expectFalse() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setArgumentPayload(RETURN_COMMAND);
        HashMap<String, String> returnArgumentPayload = reflectionManager.getArgumentPayload();
        ReturnCommand returnCmd = new ReturnCommand(returnArgumentPayload);
        returnCmd.execute();
        ReflectionManager newReflectionManager = new ReflectionManager();
        assertEquals(false, newReflectionManager.getIsExit());
    }

    // Test whether wrong format command exception is caught or not.
    @Test
    void execute_checkWrongCmdFormat_expectFalse() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setArgumentPayload(RETURN_COMMAND_WRONG_FORMAT);
        HashMap<String, String> returnArgumentPayload = reflectionManager.getArgumentPayload();
        ReturnCommand returnCmd = new ReturnCommand(returnArgumentPayload);
        returnCmd.execute();
        ReflectionManager newReflectionManager = new ReflectionManager();
        assertEquals(false, newReflectionManager.getIsExit());
    }
}