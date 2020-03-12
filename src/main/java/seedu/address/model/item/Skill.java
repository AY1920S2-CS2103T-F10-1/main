package seedu.address.model.item;

import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * The Skill item.
 */
public class Skill extends Item {

    public Skill(Name name, Set<Tag> tags) {
        super(name, tags);
    }

}
