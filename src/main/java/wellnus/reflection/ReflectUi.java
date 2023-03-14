package wellnus.reflection;

import wellnus.ui.TextUi;

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

    private void printLogo(String logo) {
        System.out.print(logo);
    }

    /**
     * Print greeting logo with separator.
     *
     * @param logo Greeting logo
     */
    protected void printLogoWithSeparator(String logo) {
        printSeparator();
        printLogo(logo);
    }
}

