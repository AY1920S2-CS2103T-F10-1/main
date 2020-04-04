package seedu.address.logic.commands.edit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.item.field.Level;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the skill with. Each non-empty field value will replace the
 * corresponding field value of the skill.
 */
public class EditSkillDescriptor extends EditItemDescriptor {
    private Set<Tag> tags;
    private Level level;

    public EditSkillDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditSkillDescriptor(EditSkillDescriptor toCopy) {
        setName(toCopy.name);
        setTags(toCopy.tags);
        setLevel(toCopy.level);
    }

    /**
     * Returns true if at least one field is edited.
     */
    @Override
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, level, tags);
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Optional<Level> getLevel() {
        return Optional.ofNullable(level);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditSkillDescriptor)) {
            return false;
        }

        // state check
        EditSkillDescriptor e = (EditSkillDescriptor) other;

        return getName().equals(e.getName())
                && getTags().equals(e.getTags());
    }
}
