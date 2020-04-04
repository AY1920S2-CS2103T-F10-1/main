package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
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
    private String role;
    private Time from;
    private Time to;
    private String description;

    public Internship(Name name, String role, Time from, Time to, String description, Set<Tag> tags) {
        this(name, role, from, to, description, tags, ItemUtil.yieldId(ItemUtil.INTERNSHIP_ALIAS));
    }

    public Internship(Name name, String role, Time from, Time to, String description, Set<Tag> tags, int id) {
        super(name, id, tags);
        requireAllNonNull(role, from, to, description);
        this.type = Type.generate(ItemUtil.INTERNSHIP_ALIAS);
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

    /**
     * Returns true if both internships have the same name, role, from and to.
     * This defines a weaker notion of equality between two items.
     */
    @Override
    public boolean isSame(Item otherInternship) {
        return super.isSame(otherInternship)
                && ((Internship) otherInternship).getRole().equals(getRole())
                && ((Internship) otherInternship).getFrom().equals(getFrom())
                && ((Internship) otherInternship).getTo().equals(getTo());
    }

    /**
     * Gets the string representation of Internship to preview.
     * @return String representation of Internship
     */
    public String toPreview() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Company: ").append(getName()).append("\n")
                .append("Role: ").append(getRole()).append("\n")
                .append("From: ").append(getFrom()).append(" - ")
                .append("To: ").append(getTo()).append("\n");
        Arrays.stream(getDescription().split("\\.")).map(x -> "- " + x.trim() + ".\n").forEach(builder::append);
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Internship // instanceof handles nulls
                && getName().equals(((Internship) other).getName())
                && role.equals(((Internship) other).role) // state check
                && from.equals(((Internship) other).from)
                && to.equals(((Internship) other).to)
                && description.equals(((Internship) other).description))
                && getTags().equals(((Internship) other).getTags());
    }
}
