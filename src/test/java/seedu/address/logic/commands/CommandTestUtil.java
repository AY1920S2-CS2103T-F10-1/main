package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEBSITE;
import static seedu.address.testutil.Assert.assertThrows;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_RESUME_NAME_SE = "Software Engineering Intern Resume";
    public static final String VALID_RESUME_NAME_ME = "Mechanical Engineering Intern Resume";

    // SKILL
    public static final String VALID_SKILL_NAME_GIT = "Git and Github";
    public static final String VALID_SKILL_NAME_REACT = "React";

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
    public static final String PREFIXED_NAME_GOOGLE = " " + PREFIX_NAME + " " + VALID_INTERNSHIP_NAME_GOOGLE;
    public static final String VALID_INTERNSHIP_ROLE_FRONTEND = "Frontend Web Engineer";
    public static final String PREFIXED_ROLE_FRONTEND = " " + PREFIX_ROLE + " " + VALID_INTERNSHIP_ROLE_FRONTEND;
    public static final String VALID_FROM = "06-2020";
    public static final String VALID_TO = "12-2020";
    public static final String PREFIXED_TIME_FROM = " " + PREFIX_FROM + " " + VALID_FROM;
    public static final String PREFIXED_TIME_TO = " " + PREFIX_TO + " " + VALID_TO;
    public static final String VALID_INTERNSHIP_DESCRIPTION = "I did work, I made money";
    public static final String PREFIXED_INTERNSHIP_DESCRIPTION = " " + PREFIX_DESCRIPTION + " "
            + VALID_INTERNSHIP_DESCRIPTION;
    public static final String VALID_TAG_FRONTEND = "Frontend";
    public static final String PREFIXED_TAG_FRONTEND = " " + PREFIX_TAG + " " + VALID_TAG_FRONTEND;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /*
    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

 */

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */

    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);

            assertEquals(expectedCommandResult.getFeedbackToUser(), result.getFeedbackToUser());
            assertEquals(expectedModel.getStatelessResumeBook(), actualModel.getStatelessResumeBook());
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }


    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */

    public static void assertCommandSuccess(Command command, Model actualModel, String expectedData,
                                            String expectedFeedback, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedData, expectedFeedback, "");
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
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
