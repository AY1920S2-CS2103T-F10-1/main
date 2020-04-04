package seedu.address.logic.commands.edit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Website;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the project with. Each non-empty field value will replace the
 * corresponding field value of the project.
 */
public class EditProjectDescriptor extends EditItemDescriptor {
    private Time time;
    private Website website;
    private String description;
    private Set<Tag> tags;

    public EditProjectDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditProjectDescriptor(EditProjectDescriptor toCopy) {
        setName(toCopy.name);
        setTime(toCopy.time);
        setDescription(toCopy.description);
        setWebsite(toCopy.website);
        setTags(toCopy.tags);
    }

    public void setTime(Time to) {
        this.time = to;
    }

    public Optional<Time> getTime() {
        return Optional.ofNullable(time);
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

    public void setWebsite(Website website) {
        this.website = website;
    }

    public Optional<Website> getWebsite() {
        return Optional.ofNullable(website);
    }

    /**
     * Returns true if at least one field is edited.
     */
    @Override
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, website, time, description, tags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditProjectDescriptor)) {
            return false;
        }

        // state check
        EditProjectDescriptor e = (EditProjectDescriptor) other;

        return getName().equals(e.getName())
                && getTime().equals(e.getTime())
                && getWebsite().equals(e.getWebsite())
                && getDescription().equals(e.getDescription())
                && getTags().equals(e.getTags());
    }
}
