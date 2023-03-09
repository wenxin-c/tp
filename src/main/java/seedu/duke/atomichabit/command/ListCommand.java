package seedu.duke.atomichabit.command;

import seedu.duke.atomichabit.atomichabit.AtomicHabit;
import seedu.duke.atomichabit.atomichabit.AtomicHabitList;

public class ListCommand extends Command{

    private final String LINE_SEPARATOR = System.lineSeparator();
    private final String DOT = ".";
    private final String FIRST_STRING = "Here are all the habits in your list:";
    public static final String COMMAND_WORD = "list";
    @Override
    public CommandResult execute(AtomicHabitList atomicHabitList) {
        int taskNo = 1;
        String list = FIRST_STRING + LINE_SEPARATOR;
        for(AtomicHabit ab : atomicHabitList.getAllHabits()) {
            list += taskNo + DOT + ab + " " + "[" + ab.getCount() + "]" + LINE_SEPARATOR;
            taskNo += 1;
        }
        return new CommandResult(list.substring(0,list.length() - 1));
    }

}
