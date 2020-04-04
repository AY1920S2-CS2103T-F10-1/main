package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.item.Person;
import seedu.address.model.item.field.DisplayPicture;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String dp;
    private final String name;
    private final String phone;
    private final String email;
    private final String github;
    private final String university;
    private final String major;
    private final String from;
    private final String to;
    private final double cap;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("dp") String dp, @JsonProperty("name") String name,
                             @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                             @JsonProperty("github") String github, @JsonProperty("university") String university,
                             @JsonProperty("major") String major, @JsonProperty("from") String from,
                             @JsonProperty("to") String to, @JsonProperty("cap") String cap) {
        this.dp = dp;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.github = github;
        this.university = university;
        this.major = major;
        this.from = from;
        this.to = to;
        this.cap = Double.valueOf(cap);
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        dp = source.getDisplayPicture().toString();
        name = source.getName().toString();
        phone = source.getPhone().toString();
        email = source.getEmail().toString();
        github = source.getGithub().toString();
        university = source.getUniversity();
        major = source.getMajor();
        from = source.getFrom().toString();
        to = source.getTo().toString();
        cap = source.getCap();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     */
    public Person toModelType() {
        return new Person(new DisplayPicture(dp), new Name(name), new Phone(phone), new Email(email),
                new Github(github), university, major, new Time(from), new Time(to), cap);
    }
}
