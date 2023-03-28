package wellnus.storage;

import java.util.ArrayList;

import wellnus.exception.TokenizerException;

/**
 * Template for all Tokenizers in WellNUS++ that are responsible for converting
 *     Managers into Strings(for storage) and also Strings(from storage) back
 *     into Managers with the previously saved state.<br/>
 *
 * Example of how to implement this in a feature: <code>public class AtomicHabitTokenizer
 *     implements Tokenizer&lt;AtomicHabit&gt;</code>.
 * @param <T> Data type of the corresponding feature, e.g. <code>AtomicHabit</code>
 *     the atomic habit feature
 */
public interface Tokenizer<T> {
    /**
     * Converts the attributes of the given <code>Manager</code> into a String representation to be
     *     saved to storage.
     * @param dataObjects List of Objects which represent data we want to convert into a String representation
     * @return ArrayList of Strings representing the data objects that we can write to storage
     * @throws TokenizerException If tokenizing fails and state cannot be converted into a valid String
     *     representation
     */
    ArrayList<String> tokenize(ArrayList<T> dataObjects);

    /**
     * Converts the String representation of a <code>Manager</code>'s state back into an
     *     <code>ArrayList</code> of the feature's data type class that can be used to restore that
     *     <code>Manager</code>'s previous state.
     * @param tokenizedDataObjects String representation of the Data Objects whose state we want to restore
     * @return ArrayList containing all the data from the Manager's previously saved state
     * @throws TokenizerException If detokenizing fails and valid state cannot be restored
     */
    ArrayList<T> detokenize(ArrayList<String> tokenizedDataObjects) throws TokenizerException;
}
