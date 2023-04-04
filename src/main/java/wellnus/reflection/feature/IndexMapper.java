package wellnus.reflection.feature;

import java.util.HashMap;
import java.util.Set;

/**
 * Map display index(1...n) onto a set of integers.
 */
public class IndexMapper {
    private static final int INDEX_ONE = 1;
    private Set<Integer> targetedSet;
    public IndexMapper(Set<Integer> targetedSet) {
        this.targetedSet = targetedSet;
    }

    /**
     * The display index(integer) ranges from 1 to n.<br/>
     * This function maps display index to each integer in the set.
     *
     * @return indexMap The hashmap with display index as key and real integer as value.
     */
    public HashMap<Integer, Integer> mapIndex() {
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        int displayIndex = INDEX_ONE;
        for (int index : this.targetedSet) {
            indexMap.put(displayIndex, index);
            displayIndex += INDEX_ONE;
        }
        return indexMap;
    }
}
