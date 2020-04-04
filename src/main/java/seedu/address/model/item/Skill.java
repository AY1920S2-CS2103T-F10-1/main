package seedu.address.model.item;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.model.item.field.Level;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Type;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ItemUtil;

/**
 * The Skill item.
 */
public class Skill extends Item {

    // Data fields
    private Level level;

    public Skill(Name name, Level level, Set<Tag> tags) {
        this(name, level, tags, ItemUtil.yieldId(ItemUtil.SKILL_ALIAS));
    }

    public Skill(Name name, Level level, Set<Tag> tags, int id) {
        super(name, id, tags);
        requireNonNull(level);
        this.type = Type.generate(ItemUtil.SKILL_ALIAS);
        this.level = level;
    }

    public Level getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = Level.valueOf(level);
    }

    /**
     * Gets the string representation of Skill to preview.
     * @return String representation of Skill
     */
    public String toPreview() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ").append(getName()).append("\n")
                .append("Level: ").append(getLevel()).append("\n");
        return builder.toString();
    }

    @Override
    public String getSummary() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Level: ").append(getLevel());
        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).append("\n")
                .append(getSummary());
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Skill // instanceof handles nulls
                && getName().equals(((Skill) other).getName())
                && level.getLevelCode() == ((Skill) other).level.getLevelCode());
    }
}
