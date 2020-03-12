package seedu.address.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;

/**
 * Wraps all data at the resume-book level
 * Duplicates are not allowed (by .isSame comparison)
 */
public class ResumeBook implements ReadOnlyResumeBook {

    // Should be all caps but check style complain
    private final String PERSONAL_DETAIL_TYPE = "pd";
    private final String RESUME_TYPE = "res";
    private final UniqueItemList itemsToDisplay;
    private final UniqueItemList personalDetails;
    private final UniqueItemList resumes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        itemsToDisplay = new UniqueItemList();
        personalDetails = new UniqueItemList();
        resumes = new UniqueItemList();
    }

    public ResumeBook() {}

    /**
     * Creates an ResumeBook using the Items in the {@code toBeCopied}
     */
    public ResumeBook(ReadOnlyResumeBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the item list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setItemsToDisplay(UniqueItemList itemsToDisplay) {
        this.itemsToDisplay.setItems(itemsToDisplay);
    }

    /**
     * Replaces the contents of the item list of type {@code type}.
     */
    public void setItemsToDisplay(String type) {
        switch (type) {
        case PERSONAL_DETAIL_TYPE:
            setItemsToDisplay(personalDetails);
            break;
        case RESUME_TYPE:
            setItemsToDisplay(resumes);
            break;
        default:
            // Redundant call first, to be replaced with an exception
            // TODO: Remove this call
            setItemsToDisplay(resumes);
        }
    }

    /**
     * Replaces the contents of the personal detail list with {@code personalDetails}.
     * {@code personalDetails} must not contain duplicate items.
     */
    public void setPersonalDetails(UniqueItemList personalDetails) {
        this.personalDetails.setItems(personalDetails);
    }

    /**
     * Resets the existing data of this {@code ResumeBook} with {@code newData}.
     */
    public void resetData(ReadOnlyResumeBook newData) {
        requireNonNull(newData);
        setPersonalDetails(newData.getPersonalDetailList());
    }

    //// item-level operations

    /**
     * Returns true if an item with the same identity as {@code person} exists in the resume book.
     */
    public boolean hasItem(Item item) {
        requireNonNull(item);
        switch (item.getType()) {

        case PERSONAL_DETAIL_TYPE:
            return personalDetails.contains(item);
        case RESUME_TYPE:
            return resumes.contains(item);
        default:
            return itemsToDisplay.contains(item);
        }
    }

    /**
     * Adds an item to the resume book.
     * The item must not already exist in the resume book.
     */
    public void addItem(Item item) {
        requireNonNull(item);
        switch (item.getType()) {
        case PERSONAL_DETAIL_TYPE:
            personalDetails.add(item);
            setItemsToDisplay(personalDetails);
            break;
        case RESUME_TYPE:
            resumes.add(item);
            setItemsToDisplay(resumes);
            break;
        default:
            itemsToDisplay.add(item);
        }
    }

    /**
     * Replaces the given item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedItem} must not be the same as another existing item in the resume book.
     */
    public void setItem(Item target, Item editedItem) {
        switch (editedItem.getType()) {

        case PERSONAL_DETAIL_TYPE:
            personalDetails.setItem(target, editedItem);
            setItemsToDisplay(personalDetails);
            break;
        case RESUME_TYPE:
            personalDetails.setItem(target, editedItem);
            setItemsToDisplay(resumes);
            break;
        default:
            itemsToDisplay.setItem(target, editedItem);
        }
    }

    /**
     * Removes {@code key} from this {@code ResumeBook}.
     * {@code key} must exist in the resume book.
     */
    public void removeItem(Item key) {
        switch (key.getType()) {

        case PERSONAL_DETAIL_TYPE:
            personalDetails.remove(key);
            setItemsToDisplay(personalDetails);
            break;

        case RESUME_TYPE:
            resumes.remove(key);
            setItemsToDisplay(resumes);
            break;
        default:
            itemsToDisplay.remove(key);
        }
    }

    //// util methods

    @Override
    public String toString() {
        return itemsToDisplay.asUnmodifiableObservableList().size() + " items";
        // TODO: refine later
    }

    @Override
    public ObservableList<Item> getItemToDisplayList() {
        return itemsToDisplay.asUnmodifiableObservableList();
    }

    @Override
    public UniqueItemList getPersonalDetailList() {
        return personalDetails;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResumeBook // instanceof handles nulls
                && itemsToDisplay.equals(((ResumeBook) other).itemsToDisplay))
                && personalDetails.equals(((ResumeBook) other).personalDetails);
    }

    @Override
    public int hashCode() {
        return itemsToDisplay.hashCode();
    }
}
