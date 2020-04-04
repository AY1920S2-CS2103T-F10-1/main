package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Type;
import seedu.address.model.tag.Tag;

/**
 * Represents an Item in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Item {

    public static final String MESSAGE_CONSTRAINTS = "You are required to specify an item type! For example: i/ res";


    // Item-level fields

    // Identity fields
    protected Type type;
    protected final int id;
    // Index refers to the position of the item in an item list. id is unique identifier for the item
    protected int index;
    protected final Name name;

    // Data fields
    protected final Set<Tag> tags = new HashSet<>();

    public Item(Name name, int id, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.id = id;
        this.name = name;
        this.index = 0;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     */
    public Item(Name name, Set<Tag> tags) {
        this(name, 0, tags);
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public abstract String getSummary();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both items have the same name and are of the same type.
     * This defines a weaker notion of equality between two items.
     */
    public boolean isSame(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
                && otherItem.getName().equals(getName())
                && otherItem.getType().getFullType().equals(getType().getFullType());
    }

    /**
     * Returns true if itemType matches any of the known item types.
     *
     * @param itemType The itemType String.
     * @return true if the itemType matches any of the known item types.
     */
    public static boolean isValidItemType(String itemType) {
        String[] correctFormats = new String[] {"int", "proj", "ski", "res"};
        for (String correctFormat: correctFormats) {
            if (itemType.equals(correctFormat)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if both items have the same identity.
     * This defines a stronger notion of equality between two items.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Item)) {
            return false;
        }

        Item otherItem = (Item) other;
        return otherItem.getId() == getId();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, name, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName()).append("\n")
                .append("Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
