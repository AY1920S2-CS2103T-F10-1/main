package seedu.address.model.item.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException() {
        super("Operation would result in duplicate persons");
    }
}
