package seedu.address.model.item.field;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's GitHub account in the resume book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGithub(String)}
 */
public class Github {

    public static final String MESSAGE_CONSTRAINTS =
            "Github usernames should adhere to the following constraints:\n"
            + "1. May only contain alphanumeric characters or hyphens.\n"
            + "2. Cannot have multiple consecutive hyphens.\n"
            + "3. Cannot begin or end with a hyphen.\n"
            + "4. Maximum is 39 characters.";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9\\d](?:[a-zA-Z0-9\\d]|-(?=[a-zA-Z0-9\\d])){0,38}$";
    public final String value;

    /**
     * Constructs a {@code Github}.
     *
     * @param github A valid Github username.
     */
    public Github(String github) {
        requireNonNull(github);
        checkArgument(isValidGithub(github), MESSAGE_CONSTRAINTS);
        value = github;
    }

    /**
     * Returns true if a given string is a valid Github username.
     */
    public static boolean isValidGithub(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Github // instanceof handles nulls
                && value.equals(((Github) other).value)); // state check
    }
}
