package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Validations for different input.
 */
public class Verifier {

    public static final int UNIVERSITY_MAX_LENGTH = 100;
    public static final String UNIVERSITY_MESSAGE_CONSTRAINTS =
            "University should only contain alphanumeric characters and spaces, with max length of 50 characters"
                    + " and it should not be blank";
    public static final String UNIVERSITY_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";


    public static final int MAJOR_MAX_LENGTH = 100;
    public static final String MAJOR_MESSAGE_CONSTRAINTS =
            "Major should only contain alphanumeric characters and spaces, with max length of 50 characters"
                    + " and it should not be blank";
    public static final String MAJOR_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final String CAP_MESSAGE_CONSTRAINTS =
            "CAP should only contain numeric characters and must match actual range from 0.0 to 5.0 and it "
                    + "should not be blank";

    public static final int ROLE_MAX_LENGTH = 50;
    public static final String ROLE_MESSAGE_CONSTRAINTS =
            "Major should only contain alphanumeric characters and spaces, with max length of 50 characters"
                    + " and it should not be blank";
    public static final String ROLE_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";


    /**
     * Check if a field is within length limit.
     * @param input field.
     * @param maxLength maximum length allowed.
     * @return true or false.
     */
    public static boolean isCorrectLength(String input, int maxLength) {
        if (input.length() <= maxLength) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if an input is empty.
     * @param input input.
     * @return true or false.
     */
    public static boolean isNotEmpty(String input) {
        if (input.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Get integer value of input.
     * @param input
     * @return
     */
    public static int getInt(String input) {
        return Integer.parseInt(input);
    }

    /**
     * Check if the University field is valid.
     * @param university
     * @return
     */
    public static boolean isValidUniversity(String university) {
        if (isNotEmpty(university) && isCorrectLength(university, UNIVERSITY_MAX_LENGTH)
                && university.matches(UNIVERSITY_VALIDATION_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the Major field is valid.
     * @param major
     * @return
     */
    public static boolean isValidMajor(String major) {
        if (isNotEmpty(major) && isCorrectLength(major, MAJOR_MAX_LENGTH)
                && major.matches(MAJOR_VALIDATION_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the Cap field is valid.
     * @param cap
     * @return
     */
    public static boolean isValidCap(String cap) throws ParseException {
        double userCap;
        try {
            userCap = Double.valueOf(cap);
        } catch (NumberFormatException ex) {
            throw new ParseException(CAP_MESSAGE_CONSTRAINTS);
        }

        if (isNotEmpty(cap) && isWithinRange(userCap, 0, 5)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if a number is within accepted range.
     * @param number
     * @param min
     * @param max
     * @return
     */
    public static boolean isWithinRange(double number, double min, double max) {
        return number >= min && number <= max;
    }

    /**
     * Check if the Role field is valid.
     * @param role
     * @return
     */
    public static boolean isValidRole(String role) {
        if (isNotEmpty(role) && isCorrectLength(role, ROLE_MAX_LENGTH)
                && role.matches(ROLE_VALIDATION_REGEX)) {
            return true;
        } else {
            return false;
        }
    }
}
