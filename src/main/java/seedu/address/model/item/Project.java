package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Type;
import seedu.address.model.item.field.Website;
import seedu.address.model.tag.Tag;

/**
 * The Project item.
 */
public class Project extends Item {

    // Data fields
    private Time time;
    private Website website;
    private String description;

    public Project(Name name, Time time, Website website, String description, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(time, website, description);
        this.type = new Type("proj");
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
        builder.append(" Time: ")
                .append(getTime());
        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString())
                .append(getSummary())
                .append(" Website: ")
                .append(getWebsite())
                .append(getDescription());
        return builder.toString();
    }
}
