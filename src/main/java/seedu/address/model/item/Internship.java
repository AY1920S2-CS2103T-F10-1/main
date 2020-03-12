package seedu.address.model.item;

import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * The Internship item.
 */
public class Internship extends Item {

    public Internship(Name name, Set<Tag> tags) {
        super(name, tags);
    }
}
