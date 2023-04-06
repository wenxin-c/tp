package wellnus.atomichabit.feature;

import wellnus.ui.TextUi;

/**
 * This section is to be updated with main UI class
 */
public class AtomicHabitUi extends TextUi {
    private static final String SEPARATOR = "~";

    /**
     * Call setSeparator() method inherited from TextUi superclass to re-define separator.
     */
    public AtomicHabitUi() {
        setSeparator(SEPARATOR);
    }

    private void printLogo(String logo) {
        System.out.print(logo);
    }

    protected void printLogoWithSeparator(String logo) {
        printSeparator();
        printLogo(logo);
    }
}

