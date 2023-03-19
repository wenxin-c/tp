package wellnus.reflection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import wellnus.exception.BadCommandException;

/**
 * Class to test different tests for HomeCommand Class utilising JUnit tests
 * Test cases will involve expected outputs and correct exception handling
 */
class HomeCommandTest {
    private static final String HOME_COMMAND = "home";
    private static final String HOME_COMMAND_WRONG_FORMAT = "home back";
    private static final String HOME_COMMAND_WITH_SPACES = "   home   ";
    private static final boolean IS_NOT_EXIT = false;
    private static final boolean IS_EXIT = true;

    // Test whether ReturnCommand execute() method can terminate self reflection or not.
    @Test
    void execute_checkIsExit_expectTrue() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setArgumentPayload(HOME_COMMAND);
        HashMap<String, String> returnArgumentPayload = reflectionManager.getArgumentPayload();
        HomeCommand returnCmd = new HomeCommand(returnArgumentPayload);
        returnCmd.execute();
        assertEquals(IS_EXIT, reflectionManager.getIsExit());
    }

    // Test whether isExit is false for a new ReflectionManager object after exiting from previous one.
    @Test
    void execute_checkNewObjectIsExit_expectFalse() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setArgumentPayload(HOME_COMMAND);
        HashMap<String, String> returnArgumentPayload = reflectionManager.getArgumentPayload();
        HomeCommand returnCmd = new HomeCommand(returnArgumentPayload);
        returnCmd.execute();
        ReflectionManager newReflectionManager = new ReflectionManager();
        assertEquals(IS_NOT_EXIT, newReflectionManager.getIsExit());
    }

    // Test whether wrong format command exception is caught or not.
    @Test
    void execute_checkWrongCmdFormat_expectException() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setArgumentPayload(HOME_COMMAND_WRONG_FORMAT);
        HashMap<String, String> returnArgumentPayload = reflectionManager.getArgumentPayload();
        HomeCommand returnCmd = new HomeCommand(returnArgumentPayload);
        assertThrows(BadCommandException.class, () -> {
            returnCmd.validateCommand(returnArgumentPayload);
        });
    }

    // Test whether leading/dangling spaces will be removed.
    @Test
    void execute_checkSpaceRemoval_success() throws BadCommandException {
        ReflectionManager reflectionManager = new ReflectionManager();
        reflectionManager.setCommandType(HOME_COMMAND_WITH_SPACES);
        String homeCommand = reflectionManager.getCommandType();
        assertEquals(HOME_COMMAND, homeCommand);
    }
}

