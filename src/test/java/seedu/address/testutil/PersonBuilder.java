package seedu.address.testutil;

import seedu.address.model.item.Person;
import seedu.address.model.item.field.DisplayPicture;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {
    public static final String DEFAULT_DP = "/images/Duke.png";
    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_GITHUB = "alicepauline";
    public static final String DEFAULT_UNIVERSITY = "National University of Singapore";
    public static final String DEFAULT_MAJOR = "Bachelor of Computing";
    public static final String DEFAULT_FROM = "08-2018";
    public static final String DEFAULT_TO = "05-2022";
    public static final double DEFAULT_CAP = 5.0;

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

    public PersonBuilder() {
        displayPicture = new DisplayPicture(DEFAULT_DP);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        github = new Github(DEFAULT_GITHUB);
        university = DEFAULT_UNIVERSITY;
        major = DEFAULT_MAJOR;
        from = new Time(DEFAULT_FROM);
        to = new Time(DEFAULT_TO);
        cap = DEFAULT_CAP;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        displayPicture = personToCopy.getDisplayPicture();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        github = personToCopy.getGithub();
        university = personToCopy.getUniversity();
        major = personToCopy.getMajor();
        from = personToCopy.getFrom();
        to = personToCopy.getTo();
        cap = personToCopy.getCap();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Person build() {
        return new Person(displayPicture, name, phone, email, github, university, major, from, to, cap);
    }

}
