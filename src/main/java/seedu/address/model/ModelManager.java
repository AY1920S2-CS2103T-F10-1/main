package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.item.Item;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ResumeBook resumeBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Item> filteredItems;

    /**
     * Initializes a ModelManager with the given resumeBook and userPrefs.
     */
    public ModelManager(ReadOnlyResumeBook resumeBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(resumeBook, userPrefs);

        logger.fine("Initializing with resume book: " + resumeBook + " and user prefs " + userPrefs);

        this.resumeBook = new ResumeBook(resumeBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItems = new FilteredList<>(this.resumeBook.getItemToDisplayList());
    }

    public ModelManager() {
        this(new ResumeBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getResumeBookFilePath() {
        return userPrefs.getResumeBookFilePath();
    }

    @Override
    public void setResumeBookFilePath(Path resumeBookFilePath) {
        requireNonNull(resumeBookFilePath);
        userPrefs.setResumeBookFilePath(resumeBookFilePath);
    }

    //=========== ResumeBook ================================================================================

    @Override
    public void setResumeBook(ReadOnlyResumeBook resumeBook) {
        this.resumeBook.resetData(resumeBook);
    }

    @Override
    public ReadOnlyResumeBook getResumeBook() {
        return resumeBook;
    }

    @Override
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return resumeBook.hasItem(item);
    }

    @Override
    public void deleteItem(Item target) {
        resumeBook.removeItem(target);
    }

    @Override
    public void addItem(Item item) {
        resumeBook.addItem(item);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setItem(Item target, Item editedItem) {
        requireAllNonNull(target, editedItem);

        resumeBook.setItem(target, editedItem);
    }

    //=========== Filtered Item List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Item> getFilteredItemList() {
        return filteredItems;
    }

    @Override
    public void updateFilteredItemList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
    }

    @Override
    public void setItemsToDisplay(String type) {
        resumeBook.setItemsToDisplay(type);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return resumeBook.equals(other.resumeBook)
                && userPrefs.equals(other.userPrefs)
                && filteredItems.equals(other.filteredItems);
    }

}
