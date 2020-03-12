package seedu.address.model.item;

import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * The Resume item.
 */
public class Resume extends Item {

    public Resume(Name name, Set<Tag> tags) {
        super(name, tags);
        this.type = "res";
    }
}
