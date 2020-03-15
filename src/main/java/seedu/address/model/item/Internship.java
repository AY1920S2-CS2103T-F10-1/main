package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Type;
import seedu.address.model.tag.Tag;

/**
 * The Internship item.
 */
public class Internship extends Item {

    // Data fields
    private String role;
    private Time from;
    private Time to;
    private String description;

    public Internship(Name name, String role, Time from, Time to, String description, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(role, from, to, description);
        this.type = new Type("int");
        this.role = role;
        this.from = from;
        this.to = to;
        this.description = description;
    }

    public String getRole() {
        return this.role;
    }

    public Time getFrom() {
        return this.from;
    }

    public Time getTo() {
        return this.to;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String getSummary() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Role: ")
                .append(getRole())
                .append(" From: ")
                .append(getFrom())
                .append(" To: ")
                .append(getTo());
        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString())
                .append(getSummary())
                .append(getDescription());
        return builder.toString();
    }
}
