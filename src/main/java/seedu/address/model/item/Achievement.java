package seedu.address.model.item;

import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * The Achievement item.
 */
public class Achievement extends Item {
    public Achievement(Name name, Set<Tag> tags) {
        super(name, tags);
    }
}
