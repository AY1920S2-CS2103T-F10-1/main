package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;

/**
 * Unmodifiable view of a resume book
 */
public interface ReadOnlyResumeBook {

    /**
     * Returns an unmodifiable view of the current items list.
     * This list will not contain any duplicate items.
     */
    ObservableList<Item> getItemToDisplayList();

    /**
     * Returns an unmodifiable view of the current personal detail list.
     * This list will not contain any duplicate personal detail.
     */
    UniqueItemList getPersonalDetailList();

}
