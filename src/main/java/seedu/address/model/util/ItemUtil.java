package seedu.address.model.util;

import java.util.TreeMap;

/**
 * Containing utility methods for Item and its subclasses
 */
public class ItemUtil {

    public static final String INTERNSHIP_ALIAS = "int";
    public static final String PROJECT_ALIAS = "proj";
    public static final String RESUME_ALIAS = "res";
    public static final String SKILL_ALIAS = "ski";


    private static TreeMap<String, Integer> idGenerator = new TreeMap<>();

    /**
     * Generates an id value for the given item type. Increments the stored value.
     * @param itemType a String representation of the item type
     * @return a non negative integer id value
     */
    public static int yieldId(String itemType) {
        if (idGenerator.containsKey(itemType)) {
            int value = idGenerator.get(itemType);
            idGenerator.put(itemType, value + 1);
            return value;
        } else {
            idGenerator.put(itemType, 1);
            return 0;
        }
    }

    /**
     * Sets the base id value mapped from the given item type to the given value.
     * @param itemType a String representation of the given item type
     * @param value a non negative integer id value
     */
    public static void setBaseIdOfItemType(String itemType, int value) {
        if (value < 0) {
            throw new IllegalArgumentException("The id value cannot be negative.");
        } else {
            idGenerator.put(itemType, value);
        }
    }
}
