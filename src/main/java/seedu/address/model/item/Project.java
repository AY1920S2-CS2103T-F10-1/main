package seedu.address.model.item;

import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * The Project item.
 */
public class Project extends Item {

    public Project(Name name, Set<Tag> tags) {
        super(name, tags);
    }
}
