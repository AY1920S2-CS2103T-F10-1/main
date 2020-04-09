package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_INTERNSHIP;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_SKILL;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_BASIC;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_DESCRIPTION_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_DESCRIPTION_ORBITAL;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_INTERMEDIATE;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_INTERNSHIP_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_GOOGLE;
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
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_JAVA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalInternship.GOOGLE;
import static seedu.address.testutil.TypicalProject.ORBITAL;
import static seedu.address.testutil.TypicalSkill.REACT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.edit.EditInternshipCommand;
import seedu.address.logic.commands.edit.EditInternshipDescriptor;
import seedu.address.logic.commands.edit.EditProjectCommand;
import seedu.address.logic.commands.edit.EditProjectDescriptor;
import seedu.address.logic.commands.edit.EditResumeDescriptor;
import seedu.address.logic.commands.edit.EditSkillCommand;
import seedu.address.logic.commands.edit.EditSkillDescriptor;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Project;
import seedu.address.model.item.Skill;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.SkillBuilder;

public class EditCommandParserTest {
    private EditCommandParser parser = new EditCommandParser();
    private EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
    private EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
    private EditSkillDescriptor editSkillDescriptor = new EditSkillDescriptor();
    private EditResumeDescriptor editResumeDescriptor = new EditResumeDescriptor();

    /**
     * A method to set fields in the edit internship descriptor.
     */
    public void setEditInternshipDescriptor(Internship sampleEditedInternship) {
        editInternshipDescriptor.setName(sampleEditedInternship.getName());
        editInternshipDescriptor.setFrom(sampleEditedInternship.getFrom());
        editInternshipDescriptor.setTo(sampleEditedInternship.getTo());
        editInternshipDescriptor.setRole(sampleEditedInternship.getRole());
        editInternshipDescriptor.setDescription(sampleEditedInternship.getDescription());
        editInternshipDescriptor.setTags(sampleEditedInternship.getTags());
    }

    /**
     * A method to set fields in the edit project descriptor.
     */
    public void setEditProjectDescriptor(Project sampleEditedProject) {
        editProjectDescriptor.setName(sampleEditedProject.getName());
        editProjectDescriptor.setWebsite(sampleEditedProject.getWebsite());
        editProjectDescriptor.setTime(sampleEditedProject.getTime());
        editProjectDescriptor.setDescription(sampleEditedProject.getDescription());
        editProjectDescriptor.setTags(sampleEditedProject.getTags());
    }

    /**
     * A method to set fields in the edit skill descriptor.
     */
    public void setEditSkillDescriptor(Skill sampleEditedSkill) {
        editSkillDescriptor.setName(sampleEditedSkill.getName());
        editSkillDescriptor.setLevel(sampleEditedSkill.getLevel());
        editSkillDescriptor.setTags(sampleEditedSkill.getTags());
    }

    @Test
    public void parse_editAllInternshipFieldsPresent_success() {
        Internship expectedInternship = new InternshipBuilder(GOOGLE).withTags(VALID_TAG_FRONTEND).build();
        setEditInternshipDescriptor(expectedInternship);

        // Standard
        assertParseSuccess(parser,
                VALID_INTERNSHIP_INDEX + ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE
                        + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor));

        // multiple item types - last item type accepted
        assertParseSuccess(parser,
                VALID_INTERNSHIP_INDEX + ITEM_TYPE_PROJECT + ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE
                        + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND,
                new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor));

        // multiple item names - last name accepted
        assertParseSuccess(parser,
                VALID_INTERNSHIP_INDEX +
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_REACT + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND,
                new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor));

        // multiple item roles - last role accepted
        assertParseSuccess(parser,
                VALID_INTERNSHIP_INDEX +
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_BACKEND + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND,
                new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor));

        // multiple item froms - last from accepted
        assertParseSuccess(parser,
                VALID_INTERNSHIP_INDEX +
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_BACKEND + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM_2 + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND,
                new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor));

        // multiple item to - last to accepted
        assertParseSuccess(parser,
                VALID_INTERNSHIP_INDEX +
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_BACKEND + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO_2 + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND,
                new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor));

        // multiple item description - last description accepted
        assertParseSuccess(parser,
                        VALID_INTERNSHIP_INDEX +
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_BACKEND + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_DESCRIPTION_ORBITAL
                        + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor));

        // multiple item tags - all tags added
        expectedInternship = new InternshipBuilder(GOOGLE).withTags(VALID_TAG_FRONTEND, VALID_TAG_JAVA).build();
        setEditInternshipDescriptor(expectedInternship);
        assertParseSuccess(parser,
                        VALID_INTERNSHIP_INDEX +
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_BACKEND + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND + PREFIXED_TAG_JAVA,
                new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor));
    }

//    @Test
//    public void parse_allAddNoteFieldsPresent_success() {
//        Note expectedNote = new NoteBuilder(FINISH_CS_2103).withTags(VALID_TAG_JAVA).build();
//        // Standard
//        assertParseSuccess(parser,
//                ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO + PREFIXED_TAG_JAVA,
//                new AddNoteCommand(expectedNote));
//
//        // multiple item types - last type accepted
//        assertParseSuccess(parser,
//                ITEM_TYPE_INTERNSHIP + ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO + PREFIXED_TAG_JAVA,
//                new AddNoteCommand(expectedNote));
//
//
//        // multiple item names - last name accepted
//        assertParseSuccess(parser,
//                ITEM_TYPE_NOTE + PREFIXED_NAME_REACT + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO + PREFIXED_TAG_JAVA,
//                new AddNoteCommand(expectedNote));
//
//        // multiple item time - last time accepted
//        assertParseSuccess(parser,
//                ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + " t/ 12-2021" + PREFIXED_TIME_TO + PREFIXED_TAG_JAVA,
//                new AddNoteCommand(expectedNote));
//
//        // multiple item tags - all tags accepted
//        expectedNote = new NoteBuilder(FINISH_CS_2103).withTags(VALID_TAG_JAVA, VALID_TAG_FRONTEND).build();
//        assertParseSuccess(parser,
//                ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO + PREFIXED_TAG_JAVA + PREFIXED_TAG_FRONTEND,
//                new AddNoteCommand(expectedNote));
//    }

    @Test
    public void parse_allAddProjectFieldsPresent_success() {
        Project expectedProject = new ProjectBuilder(ORBITAL).withTags(VALID_TAG_JAVA).build();
        setEditProjectDescriptor(expectedProject);

        // Standard
        assertParseSuccess(parser, VALID_PROJECT_INDEX +
                ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_ORBITAL
                + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA, new EditProjectCommand(INDEX_FIRST_ITEM, editProjectDescriptor));

        // multiple item types - last type accepted
        assertParseSuccess(parser, VALID_PROJECT_INDEX +ITEM_TYPE_INTERNSHIP + ITEM_TYPE_PROJECT
                + PREFIXED_NAME_ORBITAL
                + PREFIXED_TIME_ORBITAL + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA, new EditProjectCommand(INDEX_FIRST_ITEM, editProjectDescriptor));

        // multiple item names - last name accepted
        assertParseSuccess(parser,VALID_PROJECT_INDEX + ITEM_TYPE_PROJECT + PREFIXED_NAME_REACT
                + PREFIXED_NAME_ORBITAL
                + PREFIXED_TIME_ORBITAL + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA, new EditProjectCommand(INDEX_FIRST_ITEM, editProjectDescriptor));

        // multiple item time - last time accepted
        assertParseSuccess(parser,VALID_PROJECT_INDEX + ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL
                + PREFIXED_TIME_TO
                + PREFIXED_TIME_ORBITAL + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA, new EditProjectCommand(INDEX_FIRST_ITEM, editProjectDescriptor));

        // multiple item website - last website accepted
        assertParseSuccess(parser,VALID_PROJECT_INDEX + ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL
                + PREFIXED_TIME_ORBITAL
                + PREFIXED_WEBSITE_DUKE + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA, new EditProjectCommand(INDEX_FIRST_ITEM, editProjectDescriptor));

        // multiple item description - last description accepted
        assertParseSuccess(parser, VALID_PROJECT_INDEX + ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL
                + PREFIXED_TIME_ORBITAL
                + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_DUKE + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA, new EditProjectCommand(INDEX_FIRST_ITEM, editProjectDescriptor));

        // multiple item tags - all tags accepted
        expectedProject = new ProjectBuilder(ORBITAL).withTags(VALID_TAG_JAVA, VALID_TAG_FRONTEND).build();
        setEditProjectDescriptor(expectedProject);
        assertParseSuccess(parser,VALID_PROJECT_INDEX + ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL
                + PREFIXED_TIME_ORBITAL
                + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
                + PREFIXED_TAG_JAVA + PREFIXED_TAG_FRONTEND, new EditProjectCommand(INDEX_FIRST_ITEM,
                editProjectDescriptor));
    }

//    @Test
//    public void parse_addAllResumeFieldsPresent_success() {
//        Resume expectedResume = new ResumeBuilder(ME_RESUME).withTags(VALID_TAG_FRONTEND).build();
//
//        // Standard
//        assertParseSuccess(parser, VALID_RESUME_INDEX +
//                ITEM_TYPE_RESUME + PREFIXED_NAME_ME + PREFIXED_TAG_FRONTEND,
//                new EditResumeCommand(INDEX_FIRST_ITEM, editResumeDescriptor));
//
//        // multiple item types - last type accepted
//        assertParseSuccess(parser, VALID_RESUME_INDEX +
//                ITEM_TYPE_INTERNSHIP + ITEM_TYPE_RESUME + PREFIXED_NAME_ME + PREFIXED_TAG_FRONTEND,
//                new EditResumeCommand(INDEX_FIRST_ITEM, editResumeDescriptor));
//
//        // multiple item names - last name accepted
//        assertParseSuccess(parser, VALID_RESUME_INDEX +
//                ITEM_TYPE_RESUME + PREFIXED_NAME_REACT + PREFIXED_NAME_ME + PREFIXED_TAG_FRONTEND,
//                new EditResumeCommand(INDEX_FIRST_ITEM, editResumeDescriptor));
//
//        // multiple item tags - all tags accepted
//        expectedResume = new ResumeBuilder(ME_RESUME).withTags(VALID_TAG_FRONTEND, VALID_TAG_JAVA).build();
//        assertParseSuccess(parser, VALID_RESUME_INDEX +
//                ITEM_TYPE_RESUME + PREFIXED_NAME_REACT + PREFIXED_NAME_ME + PREFIXED_TAG_FRONTEND + PREFIXED_TAG_JAVA,
//                new EditResumeCommand(INDEX_FIRST_ITEM, editResumeDescriptor));
//    }

    @Test
    public void parse_addAllSkillFieldsPresent_success() {
        Skill expectedSkill = new SkillBuilder(REACT).withTags(VALID_TAG_FRONTEND).build();
        setEditSkillDescriptor(expectedSkill);

        // Standard
        assertParseSuccess(parser, VALID_SKILL_INDEX +
                ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + PREFIXED_BASIC + PREFIXED_TAG_FRONTEND,
                new EditSkillCommand(INDEX_FIRST_ITEM, editSkillDescriptor));

        // multiple item types - last type accepted
        assertParseSuccess(parser, VALID_SKILL_INDEX +
                ITEM_TYPE_INTERNSHIP + ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + PREFIXED_BASIC + PREFIXED_TAG_FRONTEND,
                new EditSkillCommand(INDEX_FIRST_ITEM, editSkillDescriptor));

        // multiple item names - last name accepted
        assertParseSuccess(parser, VALID_SKILL_INDEX +
                ITEM_TYPE_SKILL + PREFIXED_NAME_DUKE + PREFIXED_NAME_REACT + PREFIXED_BASIC + PREFIXED_TAG_FRONTEND,
                new EditSkillCommand(INDEX_FIRST_ITEM, editSkillDescriptor));

        // multiple item levels - last level accepted
        assertParseSuccess(parser, VALID_SKILL_INDEX +
                ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + PREFIXED_INTERMEDIATE + PREFIXED_BASIC + PREFIXED_TAG_FRONTEND,
                new EditSkillCommand(INDEX_FIRST_ITEM, editSkillDescriptor));

        expectedSkill = new SkillBuilder(REACT).withTags(VALID_TAG_FRONTEND, VALID_TAG_JAVA).build();

        // multiple item tags - all tags accepted
        setEditSkillDescriptor(expectedSkill);
        assertParseSuccess(parser, VALID_SKILL_INDEX +
                ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + PREFIXED_BASIC + PREFIXED_TAG_FRONTEND + PREFIXED_TAG_JAVA,
                new EditSkillCommand(INDEX_FIRST_ITEM, editSkillDescriptor));
    }

//    @Test
//    public void parse_optionalFieldsMissing_success() {
//        Internship expectedInternship = new InternshipBuilder(GOOGLE).withTags().build();
//        setEditInternshipDescriptor(expectedInternship);
//
//        // 0 tags
//        assertParseSuccess(parser, VALID_INTERNSHIP_INDEX +
//                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
//                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION,
//                new EditInternshipCommand(INDEX_FIRST_ITEM, editInternshipDescriptor));
//
//        Note expectedNote = new NoteBuilder(FINISH_CS_2103).withTags().build();
//
//        // 0 tags
//        assertParseSuccess(parser,
//                ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO,
//                new AddNoteCommand(expectedNote));
//
//
//        Project expectedProject = new ProjectBuilder(ORBITAL).withTags().build();
//
//        // 0 tags
//        assertParseSuccess(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_ORBITAL
//                        + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL,
//                new AddProjectCommand(expectedProject));
//
//        Resume expectedResume = new ResumeBuilder(ME_RESUME).withTags().build();
//
//        // 0 tags
//        assertParseSuccess(parser,
//                ITEM_TYPE_RESUME + PREFIXED_NAME_ME,
//                new AddResumeCommand(expectedResume));
//
//
//        Skill expectedSkill = new SkillBuilder(REACT).withTags().build();
//
//        // 0 tags
//        assertParseSuccess(parser,
//                ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + PREFIXED_BASIC,
//                new AddSkillCommand(expectedSkill));
//    }
//
//    @Test
//    public void parse_compulsoryItemTypeMissing_failure() {
//        // missing item prefix
//        assertParseFailure(parser, PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
//                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
//                Item.MESSAGE_CONSTRAINTS);
//
//        // invalid item prefix
//        assertParseFailure(parser,
//                INVALID_TYPE_DESC + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
//                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
//                "Not a valid item type!");
//    }
//
//    @Test
//    public void parseInternship_fromLaterThanTo_failure() {
//        assertParseFailure(parser, ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND
//                        + " f/02-2022 " + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
//                AddInternshipCommand.MESSAGE_FROM_TO_MISORDER);
//    }
//
//    @Test
//    public void parse_compulsoryFieldMissing_failure() {
//        String expectedInternshipErrorMessage =
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInternshipCommand.MESSAGE_USAGE);
//
//        // missing name prefix
//        assertParseFailure(parser, ITEM_TYPE_INTERNSHIP + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
//                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
//                expectedInternshipErrorMessage);
//
//        // missing role prefix
//        assertParseFailure(parser, ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_TIME_FROM
//                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
//                expectedInternshipErrorMessage);
//
//        // missing from prefix
//        assertParseFailure(parser, ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND
//                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
//                expectedInternshipErrorMessage);
//
//        // missing to prefix
//        assertParseFailure(parser, ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND
//                        + PREFIXED_TIME_FROM + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
//                expectedInternshipErrorMessage);
//
//        // missing description prefix
//        assertParseFailure(parser, ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND
//                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_TAG_FRONTEND,
//                expectedInternshipErrorMessage);
//
//        String expectedNoteErrorMessage =
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE);
//
//        // missing name prefix
//        assertParseFailure(parser,
//                ITEM_TYPE_NOTE + PREFIXED_TIME_TO + PREFIXED_TAG_FRONTEND,
//                expectedNoteErrorMessage);
//
//        // missing time prefix
//        assertParseFailure(parser,
//                ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + PREFIXED_TAG_FRONTEND,
//                expectedNoteErrorMessage);
//
//        String expectedProjectErrorMessage =
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProjectCommand.MESSAGE_USAGE);
//
//        // missing name prefix
//        assertParseFailure(parser, ITEM_TYPE_PROJECT + PREFIXED_TIME_ORBITAL
//                        + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL
//                        + PREFIXED_TAG_JAVA,
//                expectedProjectErrorMessage);
//
//        // missing time prefix
//        assertParseFailure(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL
//                        + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL + PREFIXED_TAG_JAVA,
//                expectedProjectErrorMessage);
//
//        // missing website prefix
//        assertParseFailure(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_ORBITAL
//                        + PREFIXED_DESCRIPTION_ORBITAL + PREFIXED_TAG_JAVA,
//                expectedProjectErrorMessage);
//
//        // missing description prefix
//        assertParseFailure(parser, ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_ORBITAL
//                        + PREFIXED_WEBSITE_ORBITAL + PREFIXED_TAG_JAVA,
//                expectedProjectErrorMessage);
//
//        String expectedResumeErrorMessage =
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddResumeCommand.MESSAGE_USAGE);
//
//        // missing name prefix
//        assertParseFailure(parser, ITEM_TYPE_RESUME + PREFIXED_TAG_JAVA, expectedResumeErrorMessage);
//
//        String expectedSkillErrorMessage =
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSkillCommand.MESSAGE_USAGE);
//
//        // missing name prefix
//        assertParseFailure(parser, ITEM_TYPE_SKILL + PREFIXED_BASIC,
//                expectedSkillErrorMessage);
//
//        // missing level prefix
//        assertParseFailure(parser, ITEM_TYPE_SKILL + PREFIXED_NAME_REACT,
//                expectedSkillErrorMessage);
//    }
//
//
//    @Test
//    public void parse_invalidValue_failure() {
//        // invalid name
//        assertParseFailure(parser,
//                ITEM_TYPE_INTERNSHIP + INVALID_NAME_DESC + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
//                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
//                Name.MESSAGE_CONSTRAINTS);
//
//        // invalid from
//        assertParseFailure(parser,
//                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + INVALID_FROM_DESC
//                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
//                Time.MESSAGE_CONSTRAINTS);
//
//        // invalid TO
//        assertParseFailure(parser,
//                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
//                        + INVALID_TO_DESC + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
//                Time.MESSAGE_CONSTRAINTS);
//
//        // invalid tag
//        assertParseFailure(parser,
//                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
//                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + INVALID_TAG_DESC,
//                Tag.MESSAGE_CONSTRAINTS);
//
//        // invalid website
//        assertParseFailure(parser,
//                ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_TO
//                        + INVALID_WEBSITE_DESC + PREFIXED_DESCRIPTION_ORBITAL,
//                Website.MESSAGE_CONSTRAINTS);
//
//        // invalid level
//        assertParseFailure(parser,
//                ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + INVALID_LEVEL_DESC + PREFIXED_TAG_FRONTEND,
//                "Level of proficiency can only be one of these three types: basic, intermediate, "
//                        + "advanced.");
//    }

}
