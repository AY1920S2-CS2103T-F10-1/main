package seedu.address.model.item;

import java.util.Set;
import java.util.TreeSet;

import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Type;
import seedu.address.model.tag.Tag;

/**
 * The Resume item.
 */
public class Resume extends Item {

    // Item-level fields

    // Data fields
    private final TreeSet<Integer> internships = new TreeSet<>();
    private final TreeSet<Integer> projects = new TreeSet<>();
    private final TreeSet<Integer> skills = new TreeSet<>();

    public Resume(Name name, Set<Tag> tags) {
        super(name, tags);
        this.type = Type.generate("res");
    }
    public Resume(Name name, int id, Set<Tag> tags) {
        super(name, id, tags);
        this.type = Type.generate("res");
    }

    public void addInternships(int value) {
        internships.add(value);
    }

    public void setInternships(int... values) {
        for (int i : values) {
            internships.add(i);
        }
    }

    public TreeSet<Integer> getInternships() {
        return this.internships;
    }

    public TreeSet<Integer> getProjects() {
        return this.projects;
    }

    public TreeSet<Integer> getSkills() {
        return this.skills;
    }

    @Override
    public String getSummary() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getInternships().size())
                .append(" internship(s) ")
                .append(getProjects().size())
                .append(" project(s) ")
                .append(getSkills().size())
                .append(" skill(s).");
        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString())
                .append(getSummary());
        return builder.toString();
    }
}
