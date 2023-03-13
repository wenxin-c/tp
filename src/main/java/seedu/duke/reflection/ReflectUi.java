package seedu.duke.reflection;

import seedu.duke.ui.TextUi;

/**
 * This section is to be updated with main UI class
 */
public class ReflectUi extends TextUi {
    private static final String SEPARATOR = "=";

    /**
     * Call setSeparator() method inherited from TextUi superclass to re-define separator.
     */
    public ReflectUi() {
        setSeparator(SEPARATOR);
    }

    /**
     * Print greeting logo.
     *
     * @param logo Greeting logo
     */
    public void printLogo(String logo) {
        printSeparator();
        System.out.print(logo);
    }
}

