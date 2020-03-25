package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Type;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ItemUtil;

/**
 * The Internship item.
 */
public class Internship extends Item {

    // Data fields
    // TODO: convert from and to back to Time
    private String role;
    private Time from;
    private Time to;
    private String description;

    public Internship(Name name, String role, Time from, Time to, String description, Set<Tag> tags) {
        this(name, role, from, to, description, tags, ItemUtil.yieldId("int"));
    }

    public Internship(Name name, String role, Time from, Time to, String description, Set<Tag> tags, int id) {
        super(name, id, tags);
        requireAllNonNull(role, from, to, description);
        this.type = Type.generate("int");
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
        builder.append("Role: ").append(getRole()).append("\n")
                .append(getFrom()).append(" - ")
                .append(getTo());
        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Company: ").append(super.toString()).append("\n")
                .append(getSummary()).append("\n")
                .append(getDescription());
        return builder.toString();
    }
}