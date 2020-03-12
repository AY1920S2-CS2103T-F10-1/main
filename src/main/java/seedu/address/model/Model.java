package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.item.Item;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' resume book file path.
     */
    Path getResumeBookFilePath();

    /**
     * Sets the user prefs' resume book file path.
     */
    void setResumeBookFilePath(Path addressBookFilePath);

    /**
     * Replaces resume book data with the data in {@code resumeBook}.
     */
    void setResumeBook(ReadOnlyResumeBook resumeBook);

    /** Returns the AddressBook */
    ReadOnlyResumeBook getResumeBook();

    /**
     * Returns true if an item with the same identity as {@code pd} exists in the resume book.
     */
    boolean hasItem(Item item);

    /**
     * Deletes the given item.
     * The item must exist in the resume book.
     */
    void deleteItem(Item target);

    /**
     * Adds the given item.
     * {@code item} must not already exist in the resume book.
     */
    void addItem(Item item);

    /**
     * Replaces the given item {@code target} with {@code editedItem}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedItem} must not be the same as another existing item in the resume book.
     */
    void setItem(Item target, Item editedItem);

    /** Returns an unmodifiable view of the filtered item list */
    ObservableList<Item> getFilteredItemList();

    /**
     * Updates the filter of the filtered item list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItemList(Predicate<Item> predicate);

    /**
     * Updates the List as the list with specified type.
     */
    void setItemsToDisplay(String type);
}
