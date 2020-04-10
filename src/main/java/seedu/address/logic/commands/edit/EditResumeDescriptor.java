package seedu.address.logic.commands.edit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the resume with. Each non-empty field value will replace the
 * corresponding field value of the resume.
 */
public class EditResumeDescriptor extends EditItemDescriptor {
    private Set<Tag> tags;

    public EditResumeDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditResumeDescriptor(EditResumeDescriptor toCopy) {
        setName(toCopy.name);
        setTags(toCopy.tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    @Override
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, tags);
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
        if (!(other instanceof EditResumeDescriptor)) {
            return false;
        }

        // state check
        EditResumeDescriptor e = (EditResumeDescriptor) other;

        return getName().equals(e.getName())
                && getTags().equals(e.getTags());
    }
}
