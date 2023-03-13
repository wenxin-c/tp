package wellnus;

import java.util.Scanner;

public class WellNus {
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
        System.out.println("Hello from" + System.lineSeparator());
        System.out.println(WellNus.getWellnusLogo());
    }

}

