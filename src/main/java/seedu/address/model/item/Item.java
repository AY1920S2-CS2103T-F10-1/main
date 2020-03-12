package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * Represents an Item in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Item {

    // item-level fields
    protected static int itemCount = 0;
    protected String type = "item";
    protected final int id;

    // Identity fields
    private final Name name;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Item(Name name, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        itemCount += 1;
        this.id = itemCount;
        this.name = name;
        this.tags.addAll(tags);
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
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
     * Returns true if both items have the same name.
     * This defines a weaker notion of equality between two items.
     */
    public boolean isSame(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
                && otherItem.getName().equals(getName());
    }

    /**
     * Returns true if itemType matches any of the known item types.
     *
     * @param itemType The itemType String.
     * @return true if the itemType matches any of the known item types.
     */
    public static boolean isValidItemType(String itemType) {
        String[] correctFormats = new String[] {"pd", "res"};
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
        builder.append(getId())
                .append(" Name: ")
                .append(getName())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
