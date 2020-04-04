package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Type;
import seedu.address.model.item.field.Website;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ItemUtil;

/**
 * The Project item.
 */
public class Project extends Item {

    // Data fields
    private Time time;
    private Website website;
    private String description;

    public Project(Name name, Time time, Website website, String description, Set<Tag> tags) {
        this(name, time, website, description, tags, ItemUtil.yieldId(ItemUtil.PROJECT_ALIAS));
    }

    public Project(Name name, Time time, Website website, String description, Set<Tag> tags, int id) {
        super(name, id, tags);
        requireAllNonNull(time, website, description);
        this.type = Type.generate(ItemUtil.PROJECT_ALIAS);
        this.time = time;
        this.website = website;
        this.description = description;
    }

    public Time getTime() {
        return this.time;
    }

    public Website getWebsite() {
        return this.website;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String getSummary() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Time: ")
                .append(getTime());
        return builder.toString();
    }

    /**
     * Gets the string representation of Project to preview.
     * @return String representation of project
     */
    public String toPreview() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ").append(getName()).append("\n")
                .append("Website: ").append(getWebsite()).append(" ")
                .append("Time: ").append(getTime()).append("\n");
        Arrays.stream(getDescription().split("\\.")).map(x -> "- " + x.trim() + ".\n").forEach(builder::append);
        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Project: ").append(super.toString()).append("\n")
                .append(getSummary()).append("\n")
                .append(getWebsite()).append("\n")
                .append(getDescription());
        return builder.toString();
    }

    /**
     * Returns true if both projects have the same name, time and website.
     * This defines a weaker notion of equality between two items.
     */
    @Override
    public boolean isSame(Item otherProject) {
        return super.isSame(otherProject)
                && ((Project) otherProject).getTime().equals(getTime())
                && ((Project) otherProject).getWebsite().equals(getWebsite());
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Project // instanceof handles nulls
                && getName().equals(((Project) other).getName())
                && time.equals(((Project) other).time) // state check
                && website.equals(((Project) other).website) // state check
                && description.equals(((Project) other).description)) // state check
                && getTags().equals(((Project) other).getTags());
    }
}
