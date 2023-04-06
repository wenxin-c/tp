package wellnus.atomichabit.feature;

import java.io.InputStream;

import wellnus.ui.TextUi;

/**
 * This class is to provide a customised interface and output message formatting for the atomic habit feature.
 */
public class AtomicHabitUi extends TextUi {
    private static final String SEPARATOR = "~";

    /**
     * Call setSeparator() method inherited from TextUi superclass to re-define separator.
     */
    public AtomicHabitUi() {
        super();
        setSeparator(SEPARATOR);
    }

    /**
     * Constructor for AtomicHabitUi to include specified input stream for testing purposes.
     *
     * @param inputStream An InputStream object representing the input stream to be used
     */
    public AtomicHabitUi(InputStream inputStream) {
        super(inputStream);
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

