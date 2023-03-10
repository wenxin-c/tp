package seedu.duke.reflection;

public class ReturnCommand extends Command{

    /**
     * Return back to WellNUS++ main interface
     */
    @Override
    public void execute() {
        SelfReflection.setIsExit();
    }
}


