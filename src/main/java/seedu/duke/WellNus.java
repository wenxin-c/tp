
package seedu.duke;

import java.util.Scanner;

public class WellNus {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = "\n" +
                ",--.   ,--.       ,--.,--.,--.  ,--.,--. ,--. ,---.     |  |        |  |     \n" +
                "|  |   |  | ,---. |  ||  ||  ,'.|  ||  | |  |'   .-',---|  |---.,---|  |---. \n" +
                "|  |.'.|  || .-. :|  ||  ||  |' '  ||  | |  |`.  `-.'---|  |---''---|  |---' \n" +
                "|   ,'.   |\\   --.|  ||  ||  | `   |'  '-'  '.-'    |   |  |        |  |     \n" +
                "'--'   '--' `----'`--'`--'`--'  `--' `-----' `-----'    `--'        `--'     \n" ;

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}

