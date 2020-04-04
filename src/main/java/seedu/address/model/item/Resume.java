package seedu.address.model.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Type;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ItemUtil;

/**
 * The Resume item.
 */
public class Resume extends Item {


    // Item-level fields

    // Data fields
    private List<Integer> internshipIds = new ArrayList<>();
    private List<Integer> projectIds = new ArrayList<>();
    private List<Integer> skillIds = new ArrayList<>();

    public Resume(Name name, Set<Tag> tags) {
        this(name, ItemUtil.yieldId(ItemUtil.RESUME_ALIAS), tags);
    }

    public Resume(Name name, int id, Set<Tag> tags) {
        super(name, id, tags);
        this.type = Type.generate(ItemUtil.RESUME_ALIAS);
        // TODO: change Resume constructor to take in existing lists of internships, projects or skills
    }

    public void addInternshipId(int value) {
        internshipIds.add(value);
    }

    public void setInternshipIds(List<Integer> internshipIds) {
        this.internshipIds = internshipIds;
    }

    public List<Integer> getInternshipIds() {
        return this.internshipIds;
    }

    public void addProjectId(int value) {
        projectIds.add(value);
    }

    public List<Integer> getProjectIds() {
        return this.projectIds;
    }

    public void setProjectIds(List<Integer> projectIds) {
        this.projectIds = projectIds;
    }

    public void addSkillId(int value) {
        skillIds.add(value);
    }

    public List<Integer> getSkillIds() {
        return this.skillIds;
    }

    public void setSkillIds(List<Integer> skillIds) {
        this.skillIds = skillIds;
    }

    @Override
    public String getSummary() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getInternshipIds().size())
                .append(" internship(s), ")
                .append(getProjectIds().size())
                .append(" project(s), ")
                .append(getSkillIds().size())
                .append(" skill(s).");
        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Resume: ")
                .append(super.toString()).append("\n")
                .append(getSummary());
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Resume // instanceof handles nulls
                && getName().equals(((Resume) other).getName())
                && getTags().equals(((Resume) other).getTags()));
    }
}
