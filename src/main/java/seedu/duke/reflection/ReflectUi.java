package seedu.duke.reflection;

import java.util.Scanner;

public class ReflectUi {
    private static final int DEFAULT_SEPARATOR_LENGTH = 40;
    private static final Scanner SCANNER = new Scanner(System.in);
    private String separator = "=";
    public String readInput() {
        String inputLine;
        inputLine = SCANNER.nextLine();
        return inputLine;
    }
    public void printOutputMessage(String message) {
        printSeparator();
        System.out.println(message);
        printSeparator();
    }
    public void printSeparator() {
        for (int i = 0; i < DEFAULT_SEPARATOR_LENGTH; i += 1) {
            System.out.print(separator);
        }
        System.out.print(System.lineSeparator());
    }
}


