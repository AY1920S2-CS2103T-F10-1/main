package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CAP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MAJOR;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_UNIVERSITY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_CAP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_CAP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_FROM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_FROM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_MAJOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_TO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_TO_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_UNIVERSITY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_UNIVERSITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FROM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FROM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TO_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIVERSITY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIVERSITY_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIVERSITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.edit.EditUserCommand;
import seedu.address.logic.commands.edit.EditUserDescriptor;
import seedu.address.model.item.field.Cap;
import seedu.address.model.item.field.Description;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Major;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.University;
import seedu.address.testutil.EditUserDescriptorBuilder;

// Note: Display Picture is currently not being tested here; because it assumes the existence of the file in the path
public class EditUserParserTest {
    private EditUserParser parser = new EditUserParser();

    @Test
    public void parse_changeAllFields_success() {
        EditUserDescriptor expectedEditUserDescriptor = new EditUserDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withDescription(VALID_DESCRIPTION_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY)
                .withGithub(VALID_GITHUB_AMY)
                .withUniversity(VALID_UNIVERSITY_AMY)
                .withMajor(VALID_MAJOR_AMY)
                .withFrom(VALID_FROM_AMY)
                .withTo(VALID_TO_AMY)
                .withCap(VALID_CAP_AMY)
                .build();

        // Standard
        assertParseSuccess(parser,
                PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_AMY + PREFIXED_PHONE_AMY
                        + PREFIXED_EMAIL_AMY + PREFIXED_GITHUB_AMY + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY
                        + PREFIXED_FROM_AMY + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple names - last one accepted
        assertParseSuccess(parser,
                PREFIXED_NAME_BOB + PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_AMY + PREFIXED_PHONE_AMY
                        + PREFIXED_EMAIL_AMY + PREFIXED_GITHUB_AMY + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY
                        + PREFIXED_FROM_AMY + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple descriptions - last one accepted
        assertParseSuccess(parser,
                PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_BOB + PREFIXED_DESCRIPTION_AMY + PREFIXED_PHONE_AMY
                        + PREFIXED_EMAIL_AMY + PREFIXED_GITHUB_AMY + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY
                        + PREFIXED_FROM_AMY + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple phones - last one accepted
        assertParseSuccess(parser,
                PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_AMY + PREFIXED_PHONE_BOB + PREFIXED_PHONE_AMY
                        + PREFIXED_EMAIL_AMY + PREFIXED_GITHUB_AMY + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY
                        + PREFIXED_FROM_AMY + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple emails - last one accepted
        assertParseSuccess(parser,
                PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_AMY + PREFIXED_PHONE_AMY + PREFIXED_EMAIL_BOB
                        + PREFIXED_EMAIL_AMY + PREFIXED_GITHUB_AMY + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY
                        + PREFIXED_FROM_AMY + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple githubs - last one accepted
        assertParseSuccess(parser,
                PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_AMY + PREFIXED_PHONE_AMY + PREFIXED_GITHUB_BOB
                        + PREFIXED_EMAIL_AMY + PREFIXED_GITHUB_AMY + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY
                        + PREFIXED_FROM_AMY + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));
    }

    @Test
    public void parse_changeHalfFields_success() {
        EditUserDescriptor expectedEditUserDescriptor = new EditUserDescriptorBuilder()
                .withUniversity(VALID_UNIVERSITY_AMY)
                .withMajor(VALID_MAJOR_AMY)
                .withFrom(VALID_FROM_AMY)
                .withTo(VALID_TO_AMY)
                .withCap(VALID_CAP_AMY)
                .build();

        // Standard
        assertParseSuccess(parser,
                        PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY + PREFIXED_FROM_AMY
                                + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple universities - last one accepted
        assertParseSuccess(parser,
                PREFIXED_UNIVERSITY_BOB + PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY + PREFIXED_FROM_AMY
                        + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple majors - last one accepted
        assertParseSuccess(parser,
                PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_BOB + PREFIXED_MAJOR_AMY + PREFIXED_FROM_AMY
                        + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple froms - last one accepted
        assertParseSuccess(parser,
                PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY + PREFIXED_FROM_BOB + PREFIXED_FROM_AMY
                        + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple tos - last one accepted
        assertParseSuccess(parser,
                PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY + PREFIXED_FROM_AMY + PREFIXED_TO_BOB
                        + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));

        // multiple caps - last one accepted
        assertParseSuccess(parser,
                        PREFIXED_UNIVERSITY_AMY + PREFIXED_MAJOR_AMY + PREFIXED_FROM_AMY + PREFIXED_CAP_BOB
                                + PREFIXED_TO_AMY + PREFIXED_CAP_AMY,
                new EditUserCommand(expectedEditUserDescriptor));
    }

    @Test
    public void parse_changeSingleField_success() {
        assertParseSuccess(parser,
                PREFIXED_NAME_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withName(VALID_NAME_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_DESCRIPTION_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withDescription(VALID_DESCRIPTION_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_PHONE_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withPhone(VALID_PHONE_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_EMAIL_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withEmail(VALID_EMAIL_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_GITHUB_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withGithub(VALID_GITHUB_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_UNIVERSITY_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withUniversity(VALID_UNIVERSITY_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_MAJOR_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withMajor(VALID_MAJOR_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_FROM_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withFrom(VALID_FROM_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_TO_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withTo(VALID_TO_BOB).build()));

        assertParseSuccess(parser,
                PREFIXED_CAP_BOB,
                new EditUserCommand(new EditUserDescriptorBuilder().withCap(VALID_CAP_BOB).build()));
    }

    @Test
    public void parse_preamblePresent_failure() {
        assertParseFailure(parser,
                "a preamble" + PREFIXED_NAME_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditUserCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noArgs_failure() {
        assertParseFailure(parser,
                "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditUserCommand.MESSAGE_NO_ARGS));
    }

    @Test
    public void parse_invalidChanges_failure() {
        assertParseFailure(parser,
                " " + PREFIX_NAME + " " + INVALID_NAME,
                Name.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                " " + PREFIX_DESCRIPTION + " " + INVALID_DESCRIPTION,
                Description.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                " " + PREFIX_PHONE + " " + INVALID_PHONE,
                Phone.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                " " + PREFIX_EMAIL + " " + INVALID_EMAIL,
                Email.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                " " + PREFIX_GITHUB + " " + INVALID_GITHUB,
                Github.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                " " + PREFIX_UNIVERSITY + " " + INVALID_UNIVERSITY,
                University.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                " " + PREFIX_MAJOR + " " + INVALID_MAJOR,
                Major.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                " " + PREFIX_FROM + " " + INVALID_TIME,
                Time.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                " " + PREFIX_TO + " " + INVALID_TIME,
                Time.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                " " + PREFIX_CAP + " " + INVALID_CAP,
                Cap.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidChangesWithOtherValidFields_failure() {
        // Name invalid, but description valid
        assertParseFailure(parser,
                " " + PREFIX_NAME + " " + INVALID_NAME + PREFIXED_DESCRIPTION_AMY,
                Name.MESSAGE_CONSTRAINTS);

        // Name invalid, but description valid, order swapped
        assertParseFailure(parser,
                PREFIXED_DESCRIPTION_AMY + " " + PREFIX_NAME + " " + INVALID_NAME,
                Name.MESSAGE_CONSTRAINTS);

        // Phone invalid, but email valid
        assertParseFailure(parser,
                " " + PREFIX_PHONE + " " + INVALID_PHONE + PREFIXED_EMAIL_AMY,
                Phone.MESSAGE_CONSTRAINTS);

        // Github invalid, but university valid
        assertParseFailure(parser,
                " " + PREFIX_GITHUB + " " + INVALID_GITHUB + PREFIXED_UNIVERSITY_AMY,
                Github.MESSAGE_CONSTRAINTS);

        // University invalid, but description valid
        assertParseFailure(parser,
                " " + PREFIX_UNIVERSITY + " " + INVALID_UNIVERSITY + PREFIXED_DESCRIPTION_AMY,
                University.MESSAGE_CONSTRAINTS);

        // Cap invalid, but sevaral other fields are valid
        assertParseFailure(parser,
                " " + PREFIX_CAP + " " + INVALID_CAP + PREFIXED_NAME_AMY + PREFIXED_DESCRIPTION_AMY
                + PREFIXED_PHONE_AMY + PREFIXED_GITHUB_AMY,
                Cap.MESSAGE_CONSTRAINTS);
    }
}
