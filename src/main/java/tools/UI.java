package tools;

import java.util.Scanner;

/**
 * UI class for reading user inputs and printing outputs
 */
public class UI {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MAIN_SEPARATOR = "=";
    private static final String ATOMIC_SEPARATOR = "-";
    private static final String REFLECT_SEPARATOR = "~";

    /**
     * Read user input and return back the command string
     *
     * @return
     */
    public static String readInput() {
        String inputLine;
        inputLine = SCANNER.nextLine();
        return inputLine;
    }

    /**
     * The function is to draw line separator between output lines.<br/>
     * Different features have different types of line separator/interface.<br/>
     * Parameter Feature takes on string value "m", "a" or "r"<br/>
     * "m" is for the main WellNUS++ interface, <br/>
     * "a" is for atomic habit interface, <br/>
     * "r" is for self reflect interface. <br/>
     * Parameter length is to determine the length of the separator to accommodate to various length of outputs.
     *
     * @param feature
     * @param length
     */
    public static void drawLineSeparator(String feature, int length) {
        String chosenSeparator;

        if (feature.equalsIgnoreCase("a")) {
            chosenSeparator = ATOMIC_SEPARATOR;
        }else if(feature.equalsIgnoreCase("r")) {
            chosenSeparator = REFLECT_SEPARATOR;
        }else{
            chosenSeparator = MAIN_SEPARATOR;
        }
        for (int i=0; i<length; i+=1) {
            System.out.print(chosenSeparator);
        }
        System.out.print('\n');
    }

}
