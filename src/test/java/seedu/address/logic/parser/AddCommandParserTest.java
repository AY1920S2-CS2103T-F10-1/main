package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FROM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LEVEL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TO_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WEBSITE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_INTERNSHIP;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_NOTE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_RESUME;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_SKILL;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_BASIC;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_DESCRIPTION_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_DESCRIPTION_ORBITAL;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_INTERMEDIATE;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_INTERNSHIP_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_ME;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_NOTE;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_ORBITAL;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_REACT;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_ROLE_BACKEND;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_ROLE_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_TAG_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_TAG_JAVA;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_TIME_FROM;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_TIME_FROM_2;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_TIME_ORBITAL;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_TIME_TO;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_TIME_TO_2;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_WEBSITE_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_WEBSITE_ORBITAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_JAVA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalInternship.GOOGLE;
import static seedu.address.testutil.TypicalNote.FINISH_CS_2103;
import static seedu.address.testutil.TypicalProject.ORBITAL;
import static seedu.address.testutil.TypicalResume.ME_RESUME;
import static seedu.address.testutil.TypicalSkill.REACT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.add.AddInternshipCommand;
import seedu.address.logic.commands.add.AddNoteCommand;
import seedu.address.logic.commands.add.AddProjectCommand;
import seedu.address.logic.commands.add.AddResumeCommand;
import seedu.address.logic.commands.add.AddSkillCommand;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.Note;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Website;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.NoteBuilder;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.ResumeBuilder;
import seedu.address.testutil.SkillBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_addAllInternshipFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(GOOGLE).withTags(VALID_TAG_FRONTEND).build();

        // Standard
        assertParseSuccess(parser,
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                new AddInternshipCommand(expectedInternship));

        // multiple item types - last item type accepted
        assertParseSuccess(parser,
                ITEM_TYPE_PROJECT + ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND,
                new AddInternshipCommand(expectedInternship));

        // multiple item names - last name accepted
        assertParseSuccess(parser,
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_REACT + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND,
                new AddInternshipCommand(expectedInternship));

        // multiple item roles - last role accepted
        assertParseSuccess(parser,
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_BACKEND + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND,
                new AddInternshipCommand(expectedInternship));

        // multiple item froms - last from accepted
        assertParseSuccess(parser,
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_BACKEND + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM_2 + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND,
                new AddInternshipCommand(expectedInternship));

        // multiple item to - last to accepted
        assertParseSuccess(parser,
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_BACKEND + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO_2 + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND,
                new AddInternshipCommand(expectedInternship));

        // multiple item description - last description accepted
        assertParseSuccess(parser,
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_BACKEND + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_DESCRIPTION_ORBITAL
                        + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                new AddInternshipCommand(expectedInternship));

        // multiple item tags - all tags added
        expectedInternship = new InternshipBuilder(GOOGLE).withTags(VALID_TAG_FRONTEND, VALID_TAG_JAVA).build();
        assertParseSuccess(parser,
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_BACKEND + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND + PREFIXED_TAG_JAVA,
                new AddInternshipCommand(expectedInternship));
    }

    @Test
    public void parse_allAddNoteFieldsPresent_success() {
        Note expectedNote = new NoteBuilder(FINISH_CS_2103).withTags(VALID_TAG_JAVA).build();
        // Standard
        assertParseSuccess(parser,
                ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO + PREFIXED_TAG_JAVA,
                new AddNoteCommand(expectedNote));

        // multiple item types - last type accepted
        assertParseSuccess(parser,
                ITEM_TYPE_INTERNSHIP + ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO + PREFIXED_TAG_JAVA,
                new AddNoteCommand(expectedNote));


        // multiple item names - last name accepted
        assertParseSuccess(parser,
                ITEM_TYPE_NOTE + PREFIXED_NAME_REACT + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO + PREFIXED_TAG_JAVA,
                new AddNoteCommand(expectedNote));

        // multiple item time - last time accepted
        assertParseSuccess(parser,
                ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + " t/ 12-2021" + PREFIXED_TIME_TO + PREFIXED_TAG_JAVA,
                new AddNoteCommand(expectedNote));

        // multiple item tags - all tags accepted
        expectedNote = new NoteBuilder(FINISH_CS_2103).withTags(VALID_TAG_JAVA, VALID_TAG_FRONTEND).build();
        assertParseSuccess(parser,
                ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO + PREFIXED_TAG_JAVA + PREFIXED_TAG_FRONTEND,
                new AddNoteCommand(expectedNote));
    }

    @Test
    public void parse_allAddProjectFieldsPresent_success() {
        Project expectedProject = new ProjectBuilder(ORBITAL).withTags(VALID_TAG_JAVA).build();

        // Standard
        assertParseSuccess(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_ORBITAL
                + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA, new AddProjectCommand(expectedProject));

        // multiple item types - last type accepted
        assertParseSuccess(parser, ITEM_TYPE_INTERNSHIP + ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL
                + PREFIXED_TIME_ORBITAL + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA, new AddProjectCommand(expectedProject));

        // multiple item names - last name accepted
        assertParseSuccess(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_REACT + PREFIXED_NAME_ORBITAL
                + PREFIXED_TIME_ORBITAL + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA, new AddProjectCommand(expectedProject));

        // multiple item time - last time accepted
        assertParseSuccess(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_TO
                + PREFIXED_TIME_ORBITAL + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA, new AddProjectCommand(expectedProject));

        // multiple item website - last website accepted
        assertParseSuccess(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_ORBITAL
                + PREFIXED_WEBSITE_DUKE + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA, new AddProjectCommand(expectedProject));

        // multiple item description - last description accepted
        assertParseSuccess(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_ORBITAL
                + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_DUKE + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA, new AddProjectCommand(expectedProject));

        // multiple item tags - all tags accepted
        expectedProject = new ProjectBuilder(ORBITAL).withTags(VALID_TAG_JAVA, VALID_TAG_FRONTEND).build();
        assertParseSuccess(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_ORBITAL
                + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA + PREFIXED_TAG_FRONTEND, new AddProjectCommand(expectedProject));
    }

    @Test
    public void parse_addAllResumeFieldsPresent_success() {
        Resume expectedResume = new ResumeBuilder(ME_RESUME).withTags(VALID_TAG_FRONTEND).build();

        // Standard
        assertParseSuccess(parser,
                ITEM_TYPE_RESUME + PREFIXED_NAME_ME + PREFIXED_TAG_FRONTEND,
                new AddResumeCommand(expectedResume));

        // multiple item types - last type accepted
        assertParseSuccess(parser,
                ITEM_TYPE_INTERNSHIP + ITEM_TYPE_RESUME + PREFIXED_NAME_ME + PREFIXED_TAG_FRONTEND,
                new AddResumeCommand(expectedResume));

        // multiple item names - last name accepted
        assertParseSuccess(parser,
                ITEM_TYPE_RESUME + PREFIXED_NAME_REACT + PREFIXED_NAME_ME + PREFIXED_TAG_FRONTEND,
                new AddResumeCommand(expectedResume));

        // multiple item tags - all tags accepted
        expectedResume = new ResumeBuilder(ME_RESUME).withTags(VALID_TAG_FRONTEND, VALID_TAG_JAVA).build();
        assertParseSuccess(parser,
                ITEM_TYPE_RESUME + PREFIXED_NAME_REACT + PREFIXED_NAME_ME + PREFIXED_TAG_FRONTEND + PREFIXED_TAG_JAVA,
                new AddResumeCommand(expectedResume));
    }

    @Test
    public void parse_addAllSkillFieldsPresent_success() {
        Skill expectedSkill = new SkillBuilder(REACT).withTags(VALID_TAG_FRONTEND).build();

        // Standard
        assertParseSuccess(parser,
                ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + PREFIXED_BASIC + PREFIXED_TAG_FRONTEND,
                new AddSkillCommand(expectedSkill));

        // multiple item types - last type accepted
        assertParseSuccess(parser,
                ITEM_TYPE_INTERNSHIP + ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + PREFIXED_BASIC + PREFIXED_TAG_FRONTEND,
                new AddSkillCommand(expectedSkill));

        // multiple item names - last name accepted
        assertParseSuccess(parser,
                ITEM_TYPE_SKILL + PREFIXED_NAME_DUKE + PREFIXED_NAME_REACT + PREFIXED_BASIC + PREFIXED_TAG_FRONTEND,
                new AddSkillCommand(expectedSkill));

        // multiple item levels - last level accepted
        assertParseSuccess(parser,
                ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + PREFIXED_INTERMEDIATE + PREFIXED_BASIC + PREFIXED_TAG_FRONTEND,
                new AddSkillCommand(expectedSkill));

        expectedSkill = new SkillBuilder(REACT).withTags(VALID_TAG_FRONTEND, VALID_TAG_JAVA).build();

        // multiple item tags - all tags accepted
        assertParseSuccess(parser,
                ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + PREFIXED_BASIC + PREFIXED_TAG_FRONTEND + PREFIXED_TAG_JAVA,
                new AddSkillCommand(expectedSkill));
    }


    @Test
    public void parse_optionalFieldsMissing_success() {
        Internship expectedInternship = new InternshipBuilder(GOOGLE).withTags().build();

        // 0 tags
        assertParseSuccess(parser,
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION,
                new AddInternshipCommand(expectedInternship));

        Note expectedNote = new NoteBuilder(FINISH_CS_2103).withTags().build();

        // 0 tags
        assertParseSuccess(parser,
                ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO,
                new AddNoteCommand(expectedNote));


        Project expectedProject = new ProjectBuilder(ORBITAL).withTags().build();

        // 0 tags
        assertParseSuccess(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_ORBITAL
                + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL,
                new AddProjectCommand(expectedProject));

        Resume expectedResume = new ResumeBuilder(ME_RESUME).withTags().build();

        // 0 tags
        assertParseSuccess(parser,
                ITEM_TYPE_RESUME + PREFIXED_NAME_ME,
                new AddResumeCommand(expectedResume));


        Skill expectedSkill = new SkillBuilder(REACT).withTags().build();

        // 0 tags
        assertParseSuccess(parser,
                ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + PREFIXED_BASIC,
                new AddSkillCommand(expectedSkill));
    }

    @Test
    public void parse_compulsoryItemTypeMissing_failure() {
        // missing item prefix
        assertParseFailure(parser, PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                Item.MESSAGE_CONSTRAINTS);

        // invalid item prefix
        assertParseFailure(parser,
                INVALID_TYPE_DESC + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                "Not a valid item type!");
    }

    @Test
    public void parseInternship_fromLaterThanTo_failure() {
        assertParseFailure(parser, ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND
                        + " f/02-2022 " + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                AddInternshipCommand.MESSAGE_FROM_TO_MISORDER);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedInternshipErrorMessage =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInternshipCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, ITEM_TYPE_INTERNSHIP + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                expectedInternshipErrorMessage);

        // missing role prefix
        assertParseFailure(parser, ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_TIME_FROM
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                expectedInternshipErrorMessage);

        // missing from prefix
        assertParseFailure(parser, ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                expectedInternshipErrorMessage);

        // missing to prefix
        assertParseFailure(parser, ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                expectedInternshipErrorMessage);

        // missing description prefix
        assertParseFailure(parser, ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_TAG_FRONTEND,
                expectedInternshipErrorMessage);

        String expectedNoteErrorMessage =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
                ITEM_TYPE_NOTE + PREFIXED_TIME_TO + PREFIXED_TAG_FRONTEND,
                expectedNoteErrorMessage);

        // missing time prefix
        assertParseFailure(parser,
                ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + PREFIXED_TAG_FRONTEND,
                expectedNoteErrorMessage);

        String expectedProjectErrorMessage =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, ITEM_TYPE_PROJECT + PREFIXED_TIME_ORBITAL
                                + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
                                + PREFIXED_TAG_JAVA,
                expectedProjectErrorMessage);

        // missing time prefix
        assertParseFailure(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL
                        + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL + PREFIXED_TAG_JAVA,
                expectedProjectErrorMessage);

        // missing website prefix
        assertParseFailure(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_ORBITAL
                        + PREFIXED_DESCRIPTION_ORBITAL + PREFIXED_TAG_JAVA,
                expectedProjectErrorMessage);

        // missing description prefix
        assertParseFailure(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_ORBITAL
                        + PREFIXED_WEBSITE_ORBITAL + PREFIXED_TAG_JAVA,
                expectedProjectErrorMessage);

        String expectedResumeErrorMessage =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddResumeCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, ITEM_TYPE_RESUME + PREFIXED_TAG_JAVA, expectedResumeErrorMessage);

        String expectedSkillErrorMessage =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSkillCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, ITEM_TYPE_SKILL + PREFIXED_BASIC,
                expectedSkillErrorMessage);

        // missing level prefix
        assertParseFailure(parser, ITEM_TYPE_SKILL + PREFIXED_NAME_REACT,
                expectedSkillErrorMessage);
    }


    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
                    ITEM_TYPE_INTERNSHIP + INVALID_NAME_DESC + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
                    + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                Name.MESSAGE_CONSTRAINTS);

        // invalid from
        assertParseFailure(parser,
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + INVALID_FROM_DESC
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                Time.MESSAGE_CONSTRAINTS);

        // invalid TO
        assertParseFailure(parser,
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
                        + INVALID_TO_DESC + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                Time.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser,
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid website
        assertParseFailure(parser,
                ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_TO
                        + INVALID_WEBSITE_DESC + PREFIXED_DESCRIPTION_ORBITAL,
                Website.MESSAGE_CONSTRAINTS);

        // invalid level
        assertParseFailure(parser,
                ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + INVALID_LEVEL_DESC + PREFIXED_TAG_FRONTEND,
                "Level of proficiency can only be one of these three types: basic, intermediate, "
                        + "advanced.");
    }

}
