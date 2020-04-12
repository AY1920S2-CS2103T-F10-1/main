package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIVERSITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEBSITE;
import static seedu.address.testutil.Assert.assertThrows;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_DP_AMY = "/images/amy.png";
    public static final String VALID_DP_BOB = "/images/bob.png";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_DESCRIPTION_AMY = "I am Bob's wife";
    public static final String VALID_DESCRIPTION_BOB = "I am amy's husband";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_GITHUB_AMY = "amychee";
    public static final String VALID_GITHUB_BOB = "bobchoo";
    public static final String VALID_UNIVERSITY_AMY = "National University of Singapore";
    public static final String VALID_UNIVERSITY_BOB = "Nanyang Technological University";
    public static final String VALID_MAJOR_AMY = "Computer Science";
    public static final String VALID_MAJOR_BOB = "Information Systems";
    public static final String VALID_FROM_AMY = "08-2019";
    public static final String VALID_FROM_BOB = "08-2018";
    public static final String VALID_TO_AMY = "05-2022";
    public static final String VALID_TO_BOB = "05-2023";
    public static final String VALID_CURRENT_CAP_AMY = "4.5";
    public static final String VALID_CAP_AMY = "4.5 5.0";
    public static final String VALID_CAP_BOB = "4.4 5.0";
    public static final String VALID_MAX_CAP = "5.0";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_ITEM_INDEX = " " + 1;

    // Person with prefixes
    public static final String PREFIXED_DP_AMY = " " + PREFIX_DP + " " + VALID_DP_AMY;
    public static final String PREFIXED_NAME_AMY = " " + PREFIX_NAME + " " + VALID_NAME_AMY;
    public static final String PREFIXED_DESCRIPTION_AMY = " " + PREFIX_DESCRIPTION + " " + VALID_DESCRIPTION_AMY;
    public static final String PREFIXED_PHONE_AMY = " " + PREFIX_PHONE + " " + VALID_PHONE_AMY;
    public static final String PREFIXED_EMAIL_AMY = " " + PREFIX_EMAIL + " " + VALID_EMAIL_AMY;
    public static final String PREFIXED_GITHUB_AMY = " " + PREFIX_GITHUB + " " + VALID_GITHUB_AMY;
    public static final String PREFIXED_UNIVERSITY_AMY = " " + PREFIX_UNIVERSITY + " " + VALID_UNIVERSITY_AMY;
    public static final String PREFIXED_MAJOR_AMY = " " + PREFIX_MAJOR + " " + VALID_MAJOR_AMY;
    public static final String PREFIXED_FROM_AMY = " " + PREFIX_FROM + " " + VALID_FROM_AMY;
    public static final String PREFIXED_TO_AMY = " " + PREFIX_TO + " " + VALID_TO_AMY;
    public static final String PREFIXED_CAP_AMY = " " + PREFIX_CAP + " " + VALID_CAP_AMY;
    public static final String PREFIXED_DP_BOB = " " + PREFIX_DP + " " + VALID_DP_BOB;
    public static final String PREFIXED_NAME_BOB = " " + PREFIX_NAME + " " + VALID_NAME_BOB;
    public static final String PREFIXED_DESCRIPTION_BOB = " " + PREFIX_DESCRIPTION + " " + VALID_DESCRIPTION_BOB;
    public static final String PREFIXED_PHONE_BOB = " " + PREFIX_PHONE + " " + VALID_PHONE_BOB;
    public static final String PREFIXED_EMAIL_BOB = " " + PREFIX_EMAIL + " " + VALID_EMAIL_BOB;
    public static final String PREFIXED_GITHUB_BOB = " " + PREFIX_GITHUB + " " + VALID_GITHUB_BOB;
    public static final String PREFIXED_UNIVERSITY_BOB = " " + PREFIX_UNIVERSITY + " " + VALID_UNIVERSITY_BOB;
    public static final String PREFIXED_MAJOR_BOB = " " + PREFIX_MAJOR + " " + VALID_MAJOR_BOB;
    public static final String PREFIXED_FROM_BOB = " " + PREFIX_FROM + " " + VALID_FROM_BOB;
    public static final String PREFIXED_TO_BOB = " " + PREFIX_TO + " " + VALID_TO_BOB;
    public static final String PREFIXED_CAP_BOB = " " + PREFIX_CAP + " " + VALID_CAP_BOB;

    // RESUME
    public static final String VALID_RESUME_NAME_SE = "Software Engineering Intern Resume";
    public static final String VALID_RESUME_NAME_ME = "Mechanical Engineering Intern Resume";
    public static final String PREFIXED_NAME_ME = " " + PREFIX_NAME + " " + VALID_RESUME_NAME_ME;
    public static final String ITEM_TYPE_RESUME = " " + PREFIX_ITEM + " res";
    public static final String VALID_RESUME_NAME_FILLED = "An already filled Resume";

    // SKILL
    public static final String VALID_SKILL_NAME_GIT = "Git and Github";
    public static final String VALID_SKILL_NAME_REACT = "React";
    public static final String PREFIXED_NAME_REACT = " " + PREFIX_NAME + " " + VALID_SKILL_NAME_REACT;
    public static final String LEVEL_BASIC = "BASIC";
    public static final String LEVEL_INTERMEDIATE = "INTERMEDIATE";
    public static final String PREFIXED_BASIC = " " + PREFIX_LEVEL + " " + LEVEL_BASIC;
    public static final String PREFIXED_INTERMEDIATE = " " + PREFIX_LEVEL + " " + LEVEL_INTERMEDIATE;
    public static final String ITEM_TYPE_SKILL = " " + PREFIX_ITEM + " ski";

    // PROJECT
    public static final String ITEM_TYPE_PROJECT = " " + PREFIX_ITEM + " proj";
    public static final String VALID_PROJECT_NAME_ORBITAL = "Orbital";
    public static final String VALID_PROJECT_NAME_DUKE = "Duke";
    public static final String PREFIXED_NAME_ORBITAL = " " + PREFIX_NAME + " " + VALID_PROJECT_NAME_ORBITAL;
    public static final String PREFIXED_NAME_DUKE = " " + PREFIX_NAME + " " + VALID_PROJECT_NAME_DUKE;
    public static final String VALID_TIME_1 = "06-2020";
    public static final String VALID_TIME_2 = "08-2018";
    public static final String PREFIXED_TIME_ORBITAL = " " + PREFIX_TIME + " " + VALID_TIME_1;
    public static final String PREFIXED_TIME_2 = " " + PREFIX_TIME + " " + VALID_TIME_2;
    public static final String VALID_WEBSITE_ORBITAL = "myorbital.github.io";
    public static final String VALID_WEBSITE_DUKE = "www.duke.org";
    public static final String PREFIXED_WEBSITE_ORBITAL = " " + PREFIX_WEBSITE + " " + VALID_WEBSITE_ORBITAL;
    public static final String PREFIXED_WEBSITE_DUKE = " " + PREFIX_WEBSITE + " " + VALID_WEBSITE_DUKE;
    public static final String VALID_DESCRIPTION_ORBITAL = "My first summer project!";
    public static final String VALID_DESCRIPTION_DUKE = "For a little mod named CS2103T";
    public static final String PREFIXED_DESCRIPTION_ORBITAL = " " + PREFIX_DESCRIPTION
            + " " + VALID_DESCRIPTION_ORBITAL;
    public static final String PREFIXED_DESCRIPTION_DUKE = " " + PREFIX_DESCRIPTION + " " + VALID_DESCRIPTION_DUKE;
    public static final String VALID_TAG_JAVA = "Java";
    public static final String PREFIXED_TAG_JAVA = " " + PREFIX_TAG + " " + VALID_TAG_JAVA;

    // INTERNSHIP
    public static final String ITEM_TYPE_INTERNSHIP = " " + PREFIX_ITEM + " int";
    public static final String VALID_INTERNSHIP_NAME_GOOGLE = "Google";
    public static final String VALID_INTERNSHIP_NAME_PAYPAL = "PayPal";
    public static final String VALID_INTERNSHIP_NAME_NINJAVAN = "Ninja Van";
    public static final String PREFIXED_NAME_GOOGLE = " " + PREFIX_NAME + " " + VALID_INTERNSHIP_NAME_GOOGLE;
    public static final String VALID_INTERNSHIP_ROLE_FRONTEND = "Frontend Web Engineer";
    public static final String VALID_INTERNSHIP_ROLE_BACKEND = "Backend Software Intern";
    public static final String VALID_INTERNSHIP_ROLE_NINJA = "Ninja Intern";
    public static final String PREFIXED_ROLE_FRONTEND = " " + PREFIX_ROLE + " " + VALID_INTERNSHIP_ROLE_FRONTEND;
    public static final String PREFIXED_ROLE_BACKEND = " " + PREFIX_ROLE + " " + VALID_INTERNSHIP_ROLE_BACKEND;
    public static final String VALID_FROM = "06-2020";
    public static final String VALID_FROM_2 = "07-2020";
    public static final String VALID_TO = "12-2020";
    public static final String VALID_TO_2 = "11-2020";
    public static final String PREFIXED_TIME_FROM = " " + PREFIX_FROM + " " + VALID_FROM;
    public static final String PREFIXED_TIME_FROM_2 = " " + PREFIX_FROM + " " + VALID_FROM_2;
    public static final String PREFIXED_TIME_TO = " " + PREFIX_TO + " " + VALID_TO;
    public static final String PREFIXED_TIME_TO_2 = " " + PREFIX_TO + " " + VALID_TO_2;
    public static final String VALID_INTERNSHIP_DESCRIPTION = "I did work, I made money";
    public static final String VALID_INTERNSHIP_LEARN_DESCRIPTION = "I am learning new things";
    public static final String VALID_INTERNSHIP_NINJA_DESCRIPTION = "My journey to become a better ninja";
    public static final String PREFIXED_INTERNSHIP_DESCRIPTION = " " + PREFIX_DESCRIPTION + " "
            + VALID_INTERNSHIP_DESCRIPTION;
    public static final String VALID_TAG_FRONTEND = "Frontend";
    public static final String PREFIXED_TAG_FRONTEND = " " + PREFIX_TAG + " " + VALID_TAG_FRONTEND;

    // NOTES
    public static final String ITEM_TYPE_NOTE = " " + PREFIX_ITEM + " note";
    public static final String VALID_TAG_URGENT = "urgent";
    public static final String PREFIXED_TAG_URGENT = " " + PREFIX_TAG + " " + VALID_TAG_URGENT;
    public static final String VALID_NOTE_NAME_CS2103 = "Finish CS2103";
    public static final String PREFIXED_NOTE_NAME = " " + PREFIX_NAME + " " + VALID_NOTE_NAME_CS2103;
    public static final String VALID_NOTE_TIME = "12-2020";
    public static final String VALID_NOTE_IS_DONE = "false";

    // INVALID ITEM
    public static final String ITEM_TYPE_INVALID = " " + PREFIX_ITEM + " notee";

    // TAGS
    public static final String VALID_TAG_TECH = "tech";
    public static final String PREFIXED_TAG_TECH = " " + PREFIX_TAG + " " + VALID_TAG_TECH;
    public static final String VALID_TAG_BACKEND = "backend";
    public static final String PREFIXED_TAG_BACKEND = " " + PREFIX_TAG + " " + VALID_TAG_BACKEND;
    public static final String VALID_TAG_SE = "SE";
    public static final String VALID_TAG_UX = "UX";
    public static final String VALID_TAG_NONE_USAGE = "noOneUseThis";

    // Invalid
    public static final String INVALID_NAME = "James&"; // '&' not allowed in names
    public static final String INVALID_TIME = "123-1998"; // Time must be of format MM-YYYY
    public static final String INVALID_WEBSITE = "dfasdsf"; // Must match regex of website
    public static final String INVALID_TAG = "hubby*"; // '*' not allowed in tags
    public static final String INVALID_DESCRIPTION = ""; // Empty strings are not allowed
    public static final String INVALID_PHONE = "911a"; // 'a' is not allowed in phones
    public static final String INVALID_EMAIL = "example.com"; // missing @
    public static final String INVALID_DP = "/Users/Pictures/someone.gif"; // 'gif' extension not allowed
    public static final String INVALID_GITHUB = "-starthyphen"; // github names cannot start with hyphens
    public static final String INVALID_UNIVERSITY = "here is an invalid university name that should exceed 50 chars";
    public static final String INVALID_MAJOR = "computer@science"; // only alphanumeric in major
    public static final String INVALID_CURRENT_CAP = "6.5"; // CAP should be from 0.0 to 5.0
    public static final String INVALID_CAP = "11.0 10.0"; // CAP exceeds the maximum CAP
    public static final String INVALID_NEGATIVE_MAX_CAP = "-1.0";

    // USER
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + INVALID_NAME;
    public static final String INVALID_ROLE_DESC = " " + PREFIX_NAME + "...";
    public static final String INVALID_FROM_DESC = " " + PREFIX_FROM + "123-1998"; // Time must be of format MM-YYYY
    public static final String INVALID_TO_DESC = " " + PREFIX_TIME + "123-1998"; // Time must be of format MM-YYYY
    public static final String INVALID_WEBSITE_DESC = " " + PREFIX_WEBSITE + INVALID_WEBSITE;
    public static final String INVALID_LEVEL_DESC =
            " " + PREFIX_LEVEL + "basii"; // basic / intermediate / advanced only
    public static final String INVALID_TYPE_DESC = " " + PREFIX_ITEM + "skil"; // ski / int / res / proj only
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + INVALID_PHONE; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + " bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + INVALID_TAG;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */

    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel.getStatelessResumeBook(), actualModel.getStatelessResumeBook());
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */

    public static void assertCommandFailure(Command command, Model actualModel, CommandException exception) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        assertThrows(CommandException.class, exception.getMessage(), () -> command.execute(actualModel));
    }


    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    /*
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonalDetailList().size());

        Item item = model.getFilteredPersonalDetailList().get(targetIndex.getZeroBased());
        final String[] splitName = item.getName().fullName.split("\\s+");
        model.updateFilteredPersonalDetailList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonalDetailList().size());
    }
     */

}
