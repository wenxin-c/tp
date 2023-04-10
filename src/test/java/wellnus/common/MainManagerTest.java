package wellnus.common;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import wellnus.atomichabit.feature.AtomicHabitManager;
import wellnus.command.Command;
import wellnus.command.ExitCommand;
import wellnus.command.HelpCommand;
import wellnus.exception.BadCommandException;
import wellnus.focus.feature.FocusManager;
import wellnus.gamification.GamificationManager;
import wellnus.manager.Manager;
import wellnus.reflection.feature.ReflectionManager;

/**
 * Tests the important behaviours of <code>MainManager</code> to ensure
 * the class is functioning as intended and expected.
 */
public class MainManagerTest {

    /**
     * Checks that the 'exit' command of <code>MainManager</code> is recognised
     * correctly.
     */
    @Test
    public void readCommand_exitWellNus_success() {
        String exitKeyword = "exit";
        Command exitCommand = null;
        try {
            exitCommand = new MainManager().getMainCommandFor(exitKeyword);
        } catch (BadCommandException badCommandException) {
            fail();
        }
        assertNotNull(exitCommand);
        assertTrue(exitCommand instanceof ExitCommand);
    }

    /**
     * Checks that the 'help' command of <code>MainManager</code> is recognised
     * correctly.
     */
    @Test
    public void readCommand_helpWellnus_success() {
        String helpKeyword = "help";
        Command helpCommand = null;
        try {
            helpCommand = new MainManager().getMainCommandFor(helpKeyword);
        } catch (BadCommandException badCommandException) {
            fail();
        }
        assertNotNull(helpCommand);
        assertTrue(helpCommand instanceof HelpCommand);
    }

    /**
     * Checks that the 'hb' keyword is recognised by <code>MainManager</code> correctly.
     */
    @Test
    public void readCommand_hbCommand_success() {
        String hbKeyword = "hb";
        MainManager mainManager = new MainManager();
        mainManager.setSupportedFeatureManagers();
        Optional<Manager> hbManager = mainManager.getManagerFor(hbKeyword);
        if (hbManager.isEmpty()) {
            fail();
        }
        if (!(hbManager.get() instanceof AtomicHabitManager)) {
            fail();
        }
    }

    /**
     * Checks that the 'ft' keyword is recognised by <code>MainManager</code> correctly.
     */
    @Test
    public void readCommand_ftCommand_success() {
        String ftKeyword = "ft";
        MainManager mainManager = new MainManager();
        mainManager.setSupportedFeatureManagers();
        Optional<Manager> ftManager = mainManager.getManagerFor(ftKeyword);
        if (ftManager.isEmpty()) {
            fail();
        }
        if (!(ftManager.get() instanceof FocusManager)) {
            fail();
        }
    }

    /**
     * Checks that the 'gamif' keyword is recognised by <code>MainManager</code> correctly.
     */
    @Test
    public void readCommand_gamifCommand_success() {
        String gamifKeyword = "gamif";
        MainManager mainManager = new MainManager();
        mainManager.setSupportedFeatureManagers();
        Optional<Manager> gamifManager = mainManager.getManagerFor(gamifKeyword);
        if (gamifManager.isEmpty()) {
            fail();
        }
        if (!(gamifManager.get() instanceof GamificationManager)) {
            fail();
        }
    }

    /**
     * Checks that the 'reflect' keyword is recognised by <code>MainManager</code> correctly.
     */
    @Test
    public void readCommand_reflectCommand_success() {
        String reflectKeyword = "reflect";
        MainManager mainManager = new MainManager();
        mainManager.setSupportedFeatureManagers();
        Optional<Manager> reflectManager = mainManager.getManagerFor(reflectKeyword);
        if (reflectManager.isEmpty()) {
            fail();
        }
        if (!(reflectManager.get() instanceof ReflectionManager)) {
            fail();
        }
    }

    /**
     * Checks that <code>MainManager</code> can detect unrecognised keywords/commands
     * successfully and throw the correct <code>Exception</code>.
     */
    @Test
    public void readCommand_unrecognisedFeature_exceptionThrown() {
        String unrecognisedFeature = "test hello world";
        assertThrows(BadCommandException.class, () -> new MainManager().getMainCommandFor(unrecognisedFeature));
    }
}
