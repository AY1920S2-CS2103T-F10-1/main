package seedu.address.model.item;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Type;
import seedu.address.model.tag.Tag;

/**
 * The Resume item.
 */
public class Resume extends Item {

    // Item-level fields

    // Data fields
    private final ArrayList<Internship> internships = new ArrayList<>();
    private final ArrayList<Project> projects = new ArrayList<>();
    private final ArrayList<Skill> skills = new ArrayList<>();

    public Resume(Name name, Set<Tag> tags) {
        super(name, tags);
        this.type = new Type("res");
    }

    public ArrayList<Internship> getInternships() {
        return this.internships;
    }

    public ArrayList<Project> getProjects() {
        return this.projects;
    }

    public ArrayList<Skill> getSkills() {
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
