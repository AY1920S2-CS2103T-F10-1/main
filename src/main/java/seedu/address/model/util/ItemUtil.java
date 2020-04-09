package seedu.address.model.util;

import java.util.TreeMap;

import seedu.address.model.item.Person;
import seedu.address.model.item.field.DisplayPicture;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;

/**
 * Containing utility methods for Item and its subclasses
 */
public class ItemUtil {

    public static final String INTERNSHIP_ALIAS = "int";
    public static final String PROJECT_ALIAS = "proj";
    public static final String RESUME_ALIAS = "res";
    public static final String SKILL_ALIAS = "ski";
    public static final String NOTE_ALIAS = "note";

    public static final Person DEFAULT_USER = new Person(new DisplayPicture("/images/Duke.png"),
            new Name("Your Name"), "Your Description", new Phone("000"), new Email("youremail@gmail.com"),
            new Github("yourgithub"), "Your University", "Your Major",
            new Time("12-9999"), new Time("12-9999"), 0.0);

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
