package seedu.address.model.item;

import java.util.Objects;

import seedu.address.model.item.field.DisplayPicture;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    // Identity fields
    private final DisplayPicture displayPicture;
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Github github;

    // Data fields
    private final String university;
    private final String major;
    private final Time from;
    private final Time to;
    private final double cap;

    /**
     * Every field must be present and not null.
     */
    public Person(DisplayPicture displayPicture, Name name, Phone phone, Email email, Github github, String university,
                  String major, Time from, Time to, double cap) {
        this.displayPicture = displayPicture;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.github = github;
        this.university = university;
        this.major = major;
        this.from = from;
        this.to = to;
        this.cap = cap;
    }

    public DisplayPicture getDisplayPicture() {
        return displayPicture;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Github getGithub() {
        return github;
    }

    public String getUniversity() {
        return university;
    }

    public String getMajor() {
        return major;
    }

    public Time getFrom() {
        return from;
    }

    public Time getTo() {
        return to;
    }

    public double getCap() {
        return cap;
    }

    /**
     * Gets the string representation of Person to preview.
     * @return String representation of person
     */
    public String toPreview() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ").append(getName()).append("\n")
                .append("Phone: ").append(getPhone()).append(" | ")
                .append("Email: ").append(getEmail()).append(" | ")
                .append("Github: ").append(getGithub()).append("\n")
                .append("University: ").append(getUniversity()).append(" | ")
                .append("Graduating in: ").append(getTo()).append("\n")
                .append("Major: ").append(getMajor()).append(" | ")
                .append("CAP: ").append(getCap());
        return builder.toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(displayPicture, name, phone, email, github, university, major, from, to, cap);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDisplayPicture()).append("\n")
                .append(getName()).append("\n")
                .append(getPhone()).append(" | ")
                .append(getEmail()).append(" | ")
                .append(getGithub()).append("\n")
                .append(getUniversity()).append(" | ")
                .append(getFrom()).append(" - ")
                .append(getTo()).append("\n")
                .append("Major: ").append(getMajor()).append(" | ")
                .append("CAP: ").append(getCap());
        return builder.toString();
    }
}
