package wellnus.gamification.util;

import wellnus.gamification.GamificationManager;
import wellnus.ui.TextUi;

/**
 * Provides helper methods for printing to the user's screen with the gamification feature's unique style.
 */
public class GamificationUi extends TextUi {
    public static final String SEPARATOR = "#";
    private static final int NUM_CHAR_IN_SEPARATOR = 70;
    private static final String CELEBRATE_LEVEL_UP_MESSAGE = "Congratulations! Level up";
    private static final String GOODBYE_MESSAGE = "Thank you for using the gamification feature! Return anytime";
    private static final String LOGO =
            "    ______                _ _____            __  _           " + System.lineSeparator()
            + "   / ____/___ _____ ___  (_) __(_)________ _/ /_(_)___  ____ " + System.lineSeparator()
            + "  / / __/ __ `/ __ `__ \\/ / /_/ / ___/ __ `/ __/ / __ \\/ __ \\" + System.lineSeparator()
            + " / /_/ / /_/ / / / / / / / __/ / /__/ /_/ / /_/ / /_/ / / / /" + System.lineSeparator()
            + " \\____/\\__,_/_/ /_/ /_/_/_/ /_/\\___/\\__,_/\\__/_/\\____/_/ /_/ ";
    private static final int SHIFT_ONE_DECIMAL_DIVISOR = 10;
    private static final String WRONG_NUM_CHAR_IN_SEPARATOR_MESSAGE = "Wrong NUM_CHAR_IN_SEPARATOR value in "
            + "GamificationUi";
    private static final String XP_BAR_CHAR = "=";
    private static final String XP_BAR_HEAD = ">";
    private static final String XP_BOX_LEFT = "[";
    private static final String XP_BOX_RIGHT = "]";
    private static final String XP_TILL_NEXT_LVL_MESSAGE = "%d more XP to Level %d";

    /**
     * Returns a new instance of GamificationUi with custom cursorName and separator
     * for our unique gamification style.
     */
    public GamificationUi() {
        super();
        super.setCursorName(GamificationManager.FEATURE_NAME);
        super.setSeparator(SEPARATOR);
        super.setSeparatorLength(NUM_CHAR_IN_SEPARATOR);
    }

    private static void printGamificationSeparator() {
        for (int i = 0; i < NUM_CHAR_IN_SEPARATOR; i += 1) {
            System.out.print(SEPARATOR);
        }
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints a congratulations message in the case where the user just levelled up.
     */
    public static void printCelebrateLevelUp() {
        printGamificationSeparator();
        printGamificationMessage(CELEBRATE_LEVEL_UP_MESSAGE);
        printGamificationSeparator();
    }

    /**
     * Prints a goodbye message when the user exits from the gamification feature.
     */
    public static void printGoodbye() {
        printGamificationSeparator();
        printGamificationMessage(GOODBYE_MESSAGE);
        printGamificationSeparator();
    }

    /**
     * Prints the gamification feature's unique logo.
     */
    public static void printLogo() {
        printGamificationSeparator();
        System.out.println("    Welcome to");
        System.out.println(LOGO);
        printGamificationSeparator();
    }

    /**
     * Prints the given message with the gamification feature's unique style.
     * @param msg Message to display on the user's screen
     */
    public static void printGamificationMessage(String msg) {
        assert NUM_CHAR_IN_SEPARATOR >= (msg.length() + 2) : WRONG_NUM_CHAR_IN_SEPARATOR_MESSAGE;
        System.out.print(SEPARATOR);
        int howManySeparator = 2;
        int minimalPadding = 1;
        // If assertion is not enabled in JVM, we still want to prevent crashing WellNUS++
        int leftPadding = NUM_CHAR_IN_SEPARATOR < (msg.length() + 2) ? minimalPadding
                : ((NUM_CHAR_IN_SEPARATOR - msg.length() - howManySeparator) / 2);
        System.out.print(" ".repeat(leftPadding));
        System.out.print(msg);
        // Likewise, if assertion is not enabled in JVM, we still want to prevent crashing WellNUS++
        int rightPadding = NUM_CHAR_IN_SEPARATOR < (msg.length() + 2) ? minimalPadding
                : (NUM_CHAR_IN_SEPARATOR - msg.length() - howManySeparator - leftPadding);
        System.out.print(" ".repeat(rightPadding));
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the statistics for the user's XP points.
     * @param gamData GamificationData object containing the user's current XP data
     * @param shouldPrintXpRemaining Whether to print the amount of XP remaining before the user levels up
     */
    public static void printXpBar(GamificationData gamData, boolean shouldPrintXpRemaining) {
        int currentLevelXp = gamData.getXpForCurrentLevelOnly();
        int currentLevelTotalXp = currentLevelXp + gamData.getXpToReachNextLevel();
        int xpLevel = gamData.getXpLevel();
        int howManyXpBarSegments = currentLevelXp * SHIFT_ONE_DECIMAL_DIVISOR / currentLevelTotalXp;
        int numSpacesInXpBar = gamData.getXpToReachNextLevel() + 1;
        String padding = " ".repeat(numSpacesInXpBar);
        String xpBoxBuilder = XP_BOX_LEFT
                + XP_BAR_CHAR.repeat(howManyXpBarSegments) + XP_BAR_HEAD
                + padding + XP_BOX_RIGHT;
        printGamificationSeparator();
        printGamificationMessage(String.format("Current XP: Level %d %s", xpLevel, xpBoxBuilder));
        if (shouldPrintXpRemaining) {
            int nextLevel = xpLevel + 1;
            printGamificationMessage(String.format(XP_TILL_NEXT_LVL_MESSAGE,
                    gamData.getXpToReachNextLevel(), nextLevel));
        }
        printGamificationSeparator();
    }
}
