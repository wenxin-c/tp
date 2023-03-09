package seedu.duke;
import java.util.Scanner;
public class AtomicHabitManager {

    private final String HABIT_DIVIDER = "—---------------------------------------------------------------";
    private final String lineSeperator = System.lineSeparator();
    private AtomicHabitList habitList;
    private boolean isExit = false;
    private static String[] userInput;

    private static Scanner myScanner = new Scanner(System.in);

    public void run() {
        start();
        while (!isExit) {
            userInput = takeInput();
            String payload = "";
            for (int i = 1; i < userInput.length; i++) {
                payload += userInput[i] + " ";
            }
            if (userInput[0].equals("add")) {

                AtomicHabit habit = new AtomicHabit(payload);
                habitList.setAtomicHabits(habit);
                System.out.println(HABIT_DIVIDER);
                System.out.println("Yay! You have added a new habit:" + habit + " was successfully added");
                System.out.println(HABIT_DIVIDER);
            } else if (userInput[0].equals("list")) {
                int taskNo = 1;
                String list = "Here are all the habits in your list:" + lineSeperator;
                for(AtomicHabit ab : habitList.getAllHabits()) {
                    list += taskNo + "." + ab + " " + ab.getCount() + lineSeperator;
                    taskNo += 1;
                }
                System.out.println(HABIT_DIVIDER);
                System.out.println(list);
                System.out.println(HABIT_DIVIDER);
            }
        }
    }

    public static String[] takeInput() {
        String line = myScanner.nextLine();
        userInput = line.split(" ");
        return userInput;
    }

    public void start() {
        habitList = new AtomicHabitList();
    }

}
