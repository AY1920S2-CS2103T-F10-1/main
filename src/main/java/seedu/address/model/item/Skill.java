package seedu.address.model.item;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Type;
import seedu.address.model.tag.Tag;

/**
 * The Skill item.
 */
public class Skill extends Item {

    // Data fields
    private Level level;

    public Skill(Name name, Level level, Set<Tag> tags) {
        super(name, tags);
        requireNonNull(level);
        this.type = new Type("ski");
        this.level = level;
    }

    public Level getLevel() {
        return this.level;
    }

    @Override
    public String getSummary() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Level: ")
                .append(getLevel());
        return builder.toString();
    }

    @Override
    public String toString() {
        return getSummary();
    }
}
