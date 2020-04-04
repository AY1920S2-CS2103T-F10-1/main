package seedu.address.logic.commands.edit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.item.field.Time;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the internship with. Each non-empty field value will replace the
 * corresponding field value of the internship.
 */
public class EditInternshipDescriptor extends EditItemDescriptor {
    private String role;
    private Time from;
    private Time to;
    private String description;
    private Set<Tag> tags;

    public EditInternshipDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditInternshipDescriptor(EditInternshipDescriptor toCopy) {
        setName(toCopy.name);
        setRole(toCopy.role);
        setFrom(toCopy.from);
        setTo(toCopy.to);
        setDescription(toCopy.description);
        setTags(toCopy.tags);
    }

    public void setFrom(Time from) {
        this.from = from;
    }

    public Optional<Time> getFrom() {
        return Optional.ofNullable(from);
    }

    public void setTo(Time to) {
        this.to = to;
    }

    public Optional<Time> getTo() {
        return Optional.ofNullable(to);
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Optional<String> getRole() {
        return Optional.ofNullable(role);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
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

    /**
     * Returns true if at least one field is edited.
     */
    @Override
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, role, from, to, description, tags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInternshipDescriptor)) {
            return false;
        }

        // state check
        EditInternshipDescriptor e = (EditInternshipDescriptor) other;

        return getName().equals(e.getName())
                && getRole().equals(e.getRole())
                && getFrom().equals(e.getFrom())
                && getTo().equals(e.getTo())
                && getDescription().equals(e.getDescription())
                && getTags().equals(e.getTags());

    }
}
