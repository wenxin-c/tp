package seedu.duke.reflection;

import seedu.duke.ui.TextUi;

/**
 * This section is to be updated with main UI class
 */
public class ReflectUi extends TextUi {
    private static final String SEPARATOR = "=";

    /**
     * TODO: Call setSeparator() method inherited from TextUi superclass to re-define separator.
     */
    public ReflectUi() {
        setSeparator(SEPARATOR);
    }

    public void printLogo(String logo) {
        System.out.print(logo);
    }

    /**
     * TODO: Print greeting logo.
     *
     * @param logo Greeting logo
     */
    public void printLogoWithSeparator(String logo) {
        printSeparator();
        printLogo(logo);
    }
}

