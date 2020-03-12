package seedu.address.model.item;

import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * The Set item.
 */
public class Education extends Item {
    public Education(Name name, Set<Tag> tags) {
        super(name, tags);
    }
}
