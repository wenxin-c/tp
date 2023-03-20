package wellnus.storage;

import java.util.ArrayList;

import wellnus.exception.TokenizerException;
import wellnus.manager.Manager;
import wellnus.reflection.ReflectionQuestion;

/**
 * Home command to return back to WellNUS++ main interface.
 */
public class ReflectionTokenizer implements Tokenizer<ReflectionQuestion> {
    /**
     * Home command to return back to WellNUS++ main interface.
     */
    public ArrayList<String> tokenize(Manager managerToTokenize) throws TokenizerException {
        return null;
    }
    /**
     * Home command to return back to WellNUS++ main interface.
     */
    public ArrayList<ReflectionQuestion> detokenize(ArrayList<String> tokenizedManager) throws TokenizerException {
        return null;
    }
}

