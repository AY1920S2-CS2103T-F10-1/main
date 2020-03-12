package seedu.address.model.item.exceptions;

/**
 * Signals that the operation involves adding an item to a list of incompatible type.
 */
public class IncompatibleTypeException extends RuntimeException {
    public IncompatibleTypeException() {
        super("Operation involves incompatible type");
    }
}
