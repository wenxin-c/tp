package wellnus.reflection;

import org.junit.jupiter.api.Test;
import wellnus.exception.BadCommandException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReturnCommandTest {
    private static final String RETURN_COMMAND = "return";
    private static final String RETURN_COMMAND_WRONG_FORMAT = "return back";
    private static final boolean IS_NOT_EXIT = false;
    private static final boolean IS_EXIT = true;

    // Test whether ReturnCommand execute() method can terminate self reflection or not.
    @Test
    void execute_checkIsExit_expectTrue() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setArgumentPayload(RETURN_COMMAND);
        HashMap<String, String> returnArgumentPayload = reflectionManager.getArgumentPayload();
        ReturnCommand returnCmd = new ReturnCommand(returnArgumentPayload);
        returnCmd.execute();
        assertEquals(IS_EXIT, reflectionManager.getIsExit());
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
        assertEquals(IS_NOT_EXIT, newReflectionManager.getIsExit());
    }

    // Test whether wrong format command exception is caught or not.
    @Test
    void execute_checkWrongCmdFormat_expectException() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setArgumentPayload(RETURN_COMMAND_WRONG_FORMAT);
        HashMap<String, String> returnArgumentPayload = reflectionManager.getArgumentPayload();
        ReturnCommand returnCmd = new ReturnCommand(returnArgumentPayload);
        assertThrows(BadCommandException.class, () -> {
            returnCmd.validateCommand(returnArgumentPayload);
        });
    }
}

