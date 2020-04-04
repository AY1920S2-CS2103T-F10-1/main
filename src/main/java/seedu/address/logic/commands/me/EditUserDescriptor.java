package seedu.address.logic.commands.me;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.item.field.DisplayPicture;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;

/**
 * Descriptor for EditUserCommand.
 */
public class EditUserDescriptor {
    private DisplayPicture displayPicture;
    private Name name;
    private Phone phone;
    private Email email;
    private Github github;
    private String university;
    private String major;
    private Time from;
    private Time to;
    private double cap;

    public EditUserDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditUserDescriptor(EditUserDescriptor toCopy) {
        setDisplayPicture(toCopy.displayPicture);
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setEmail(toCopy.email);
        setGithub(toCopy.github);
        setUni(toCopy.university);
        setMajor(toCopy.major);
        setFrom(toCopy.from);
        setTo(toCopy.to);
        setCap(toCopy.cap);
    }

    public void setDisplayPicture(DisplayPicture displayPicture) {
        this.displayPicture = displayPicture;
    }

    public Optional<DisplayPicture> getDisplayPicture() {
        return Optional.ofNullable(displayPicture);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setGithub(Github github) {
        this.github = github;
    }

    public Optional<Github> getGithub() {
        return Optional.ofNullable(github);
    }

    public void setUni(String uni) {
        this.university = uni;
    }

    public Optional<String> getUniversity() {
        return Optional.ofNullable(university);
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Optional<String> getMajor() {
        return Optional.ofNullable(major);
    }

    public void setFrom(Time from) {
        this.from = from;
    }

    public Optional<Time> getFrom() {
        return Optional.ofNullable(from);
    }

    public void setTo(Time to) {
        this.to = to;
    }

    public Optional<Time> getTo() {
        return Optional.ofNullable(to);
    }

    public void setCap(double cap) {
        this.cap = cap;
    }

    public Optional<Double> getCap() {
        return Optional.ofNullable(cap);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(displayPicture, name, phone, email, github, university,
                major, from, to, cap);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditUserDescriptor)) {
            return false;
        }

        // state check
        EditUserDescriptor e = (EditUserDescriptor) other;

        return getDisplayPicture().equals(e.getDisplayPicture())
                && getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getGithub().equals(e.getGithub())
                && getUniversity().equals(e.getUniversity())
                && getMajor().equals(e.getMajor())
                && getFrom().equals(e.getFrom())
                && getTo().equals(e.getTo())
                && getCap().equals(e.getCap());
    }
}
