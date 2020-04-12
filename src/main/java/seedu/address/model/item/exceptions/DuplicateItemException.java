package seedu.address.model.item.exceptions;

/**
 * Signals that the operation will result in duplicate Items (Item are considered duplicates if {@Code isSame} function
 * returns true).
 */
public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException() {
        super("Operation would result in duplicate items");
    }
}
