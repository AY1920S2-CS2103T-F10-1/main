package seedu.address.model.item.field;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an Item's time (start/end date) in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time {

    public static final String MESSAGE_CONSTRAINTS = "Time should be in the format MM-yyyy";
    public static final String VALIDATION_REGEX = "^(1[0-2]|0[1-9])-[0-9]{4}$";
    public final String value;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time in format MM/yyyy.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        value = time;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Formats {@code Time} to "MMM yyyy" format.
     * @return formatted {@String}.
     */
    public String format() {
        SimpleDateFormat parser = new SimpleDateFormat("MM-yyyy");
        try {
            Date date = parser.parse(this.value);
            SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy");
            return formatter.format(date);
        } catch (ParseException e) {
            return this.value;
        }

    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && value.equals(((Time) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
