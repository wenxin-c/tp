package wellnus;

import wellnus.ui.TextUi;

import java.util.Scanner;

public class WellNus {
    private static final String GREETING_MESSAGE = "Very good day to you! Welcome to ";
    private static final String NEWLINE = System.lineSeparator();
    private final TextUi textUi;

    public WellNus() {
        this.textUi = new TextUi();
    }

    private TextUi getTextUi() {
        return this.textUi;
    }

    private static String getWellnusLogo() {
        return NEWLINE
                + ",--.   ,--.       ,--.,--.,--.  ,--.,--. ,--. ,---.     |  |        |  |     " + NEWLINE
                + "|  |   |  | ,---. |  ||  ||  ,'.|  ||  | |  |'   .-',---|  |---.,---|  |---. " + NEWLINE
                + "|  |.'.|  || .-. :|  ||  ||  |' '  ||  | |  |`.  `-.'---|  |---''---|  |---' " + NEWLINE
                + "|   ,'.   |\\   --.|  ||  ||  | `   |'  '-'  '.-'    |   |  |        |  |     " + NEWLINE
                + "'--'   '--' `----'`--'`--'`--'  `--' `-----' `-----'    `--'        `--'     " + NEWLINE;
    }

    private void greet() {
        this.getTextUi().printOutputMessage(WellNus.GREETING_MESSAGE + WellNus.NEWLINE
                + WellNus.getWellnusLogo());
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new WellNus().start();
    }

    public void start() {
        this.greet();
    }

}

