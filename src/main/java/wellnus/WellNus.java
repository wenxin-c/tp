package wellnus;

import wellnus.ui.TextUi;

import java.util.Scanner;

public class WellNus {
    private static final String GREETING_MESSAGE = "Very good day to you! Welcome to ";
    private static final String NEWLINE = System.lineSeparator();

    private static String getWellnusLogo() {
        return NEWLINE
                + ",--.   ,--.       ,--.,--.,--.  ,--.,--. ,--. ,---.     |  |        |  |     " + NEWLINE
                + "|  |   |  | ,---. |  ||  ||  ,'.|  ||  | |  |'   .-',---|  |---.,---|  |---. " + NEWLINE
                + "|  |.'.|  || .-. :|  ||  ||  |' '  ||  | |  |`.  `-.'---|  |---''---|  |---' " + NEWLINE
                + "|   ,'.   |\\   --.|  ||  ||  | `   |'  '-'  '.-'    |   |  |        |  |     " + NEWLINE
                + "'--'   '--' `----'`--'`--'`--'  `--' `-----' `-----'    `--'        `--'     " + NEWLINE;
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        TextUi textUi = new TextUi();
        textUi.printOutputMessage(WellNus.GREETING_MESSAGE + WellNus.NEWLINE
                + WellNus.getWellnusLogo());
    }

}

