package seedu.address.logic.parser;

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
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_SKILL;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_BASIC;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_DESCRIPTION_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_DESCRIPTION_ORBITAL;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_INTERMEDIATE;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_INTERNSHIP_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_DUKE;
import static seedu.address.logic.commands.CommandTestUtil.PREFIXED_NAME_GOOGLE;
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
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERNSHIP_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRONTEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_JAVA;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccessNotStrictlyEqual;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalInternship.GOOGLE;
import static seedu.address.testutil.TypicalNote.FINISH_CS_2103;
import static seedu.address.testutil.TypicalProject.ORBITAL;
import static seedu.address.testutil.TypicalSkill.REACT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.edit.EditInternshipCommand;
import seedu.address.logic.commands.edit.EditInternshipDescriptor;
import seedu.address.logic.commands.edit.EditNoteCommand;
import seedu.address.logic.commands.edit.EditNoteDescriptor;
import seedu.address.logic.commands.edit.EditProjectCommand;
import seedu.address.logic.commands.edit.EditProjectDescriptor;
import seedu.address.logic.commands.edit.EditResumeCommand;
import seedu.address.logic.commands.edit.EditResumeDescriptor;
import seedu.address.logic.commands.edit.EditSkillCommand;
import seedu.address.logic.commands.edit.EditSkillDescriptor;
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
import seedu.address.testutil.SkillBuilder;

public class EditCommandParserTest {
    private EditCommandParser parser = new EditCommandParser();
    private EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
    private EditProjectDescriptor editProjectDescriptor = new EditProjectDescriptor();
    private EditSkillDescriptor editSkillDescriptor = new EditSkillDescriptor();
    private EditResumeDescriptor editResumeDescriptor = new EditResumeDescriptor();
    private EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptor();

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

    /**
     * A method to set fields in the edit note descriptor.
     */
    public void setEditNoteDescriptor(Note sampleEditedNote) {
        editNoteDescriptor.setName(sampleEditedNote.getName());
        editNoteDescriptor.setTime(sampleEditedNote.getTime());
    }

    /**
     * A method to set fields in the resume skill descriptor.
     */
    public void setEditResumeDescriptor(Resume sampleEditedResume) {
        editResumeDescriptor.setName(sampleEditedResume.getName());
        editSkillDescriptor.setTags(sampleEditedResume.getTags());
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

    @Test
    public void parse_allAddNoteFieldsPresent_success() {
        Note expectedNote = new NoteBuilder(FINISH_CS_2103).build();
        setEditNoteDescriptor(expectedNote);

        // Standard
        assertParseSuccess(parser, VALID_NOTE_INDEX +
                ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO,
                new EditNoteCommand(INDEX_FIRST_ITEM, editNoteDescriptor));

        // multiple item types - last type accepted
        assertParseSuccess(parser, VALID_NOTE_INDEX +
                ITEM_TYPE_INTERNSHIP + ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO,
                new EditNoteCommand(INDEX_FIRST_ITEM, editNoteDescriptor));


        // multiple item names - last name accepted
        assertParseSuccess(parser,VALID_NOTE_INDEX +
                ITEM_TYPE_NOTE + PREFIXED_NAME_REACT + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO,
                new EditNoteCommand(INDEX_FIRST_ITEM, editNoteDescriptor));

        // multiple item time - last time accepted
        assertParseSuccess(parser,VALID_NOTE_INDEX +
                ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + " t/ 12-2021" + PREFIXED_TIME_TO,
                new EditNoteCommand(INDEX_FIRST_ITEM, editNoteDescriptor));
    }

    @Test
    public void parse_editAllProjectFieldsPresent_success() {
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
//        setEditResumeDescriptor(expectedResume);
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
//        setEditResumeDescriptor(expectedResume);
//
//        assertParseSuccess(parser, VALID_RESUME_INDEX +
//                ITEM_TYPE_RESUME + PREFIXED_NAME_REACT + PREFIXED_NAME_ME + PREFIXED_TAG_FRONTEND + PREFIXED_TAG_JAVA,
//                new EditResumeCommand(INDEX_FIRST_ITEM, editResumeDescriptor));
//    }

    @Test
    public void parse_editAllSkillFieldsPresent_success() {
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

    @Test
    public void parse_optionalFieldsMissing_success() {
        Internship expectedInternship = new InternshipBuilder(GOOGLE).withTags().build();
        setEditInternshipDescriptor(expectedInternship);

        // 0 tags
        assertParseSuccessNotStrictlyEqual(parser, VALID_INTERNSHIP_INDEX +
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION);

        Note expectedNote = new NoteBuilder(FINISH_CS_2103).withTags().build();
        setEditNoteDescriptor(expectedNote);

        // 0 tags
        assertParseSuccess(parser, VALID_NOTE_INDEX +
                ITEM_TYPE_NOTE + PREFIXED_NAME_NOTE + PREFIXED_TIME_TO,
                new EditNoteCommand(INDEX_FIRST_ITEM, editNoteDescriptor));


        Project expectedProject = new ProjectBuilder(ORBITAL).withTags().build();
        setEditProjectDescriptor(expectedProject);

        // 0 tags
        assertParseSuccessNotStrictlyEqual(parser, VALID_PROJECT_INDEX +
                ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_ORBITAL
                        + PREFIXED_WEBSITE_ORBITAL + PREFIXED_DESCRIPTION_ORBITAL);

//        Resume expectedResume = new ResumeBuilder(ME_RESUME).withTags().build();
//
//        // 0 tags
//        assertParseSuccess(parser,
//                ITEM_TYPE_RESUME + PREFIXED_NAME_ME,
//                new AddResumeCommand(expectedResume));


        Skill expectedSkill = new SkillBuilder(REACT).withTags().build();
        setEditSkillDescriptor(expectedSkill);

        // 0 tags
        assertParseSuccessNotStrictlyEqual(parser, VALID_SKILL_INDEX +
                ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + PREFIXED_BASIC);
    }

    @Test
    public void parse_compulsoryItemTypeMissing_failure() {
        // missing item prefix
        assertParseFailure(parser,
                VALID_INTERNSHIP_INDEX + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND,
                Item.MESSAGE_CONSTRAINTS);

        // invalid item prefix
        assertParseFailure(parser,
                VALID_INTERNSHIP_INDEX + INVALID_TYPE_DESC + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND
                        + PREFIXED_TIME_FROM + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION
                        + PREFIXED_TAG_FRONTEND,
                "Not a valid item type!");
    }

    @Test
    public void parse_allFieldMissing_failure() {
        String expectedInternshipErrorMessage =
                EditInternshipCommand.MESSAGE_NOT_EDITED;

        // missing all fields in internship prefix
        assertParseFailure(parser, VALID_INTERNSHIP_INDEX + ITEM_TYPE_INTERNSHIP,
                expectedInternshipErrorMessage);

        String expectedNoteErrorMessage =
                EditNoteCommand.MESSAGE_NOT_EDITED;

        // missing all fields in note prefix
        assertParseFailure(parser, VALID_NOTE_INDEX + ITEM_TYPE_NOTE,
                expectedNoteErrorMessage);

        String expectedProjectErrorMessage =
                EditProjectCommand.MESSAGE_NOT_EDITED;

        // missing all fields in project prefix
        assertParseFailure(parser, VALID_PROJECT_INDEX + ITEM_TYPE_PROJECT,
                expectedProjectErrorMessage);

        String expectedResumeErrorMessage =
                EditResumeCommand.MESSAGE_NOT_EDITED;

        // missing name prefix
//        assertParseFailure(parser, ITEM_TYPE_RESUME + PREFIXED_TAG_JAVA, expectedResumeErrorMessage);

        String expectedSkillErrorMessage =
                EditSkillCommand.MESSAGE_NOT_EDITED;

        // missing all fields in skill prefix
        assertParseFailure(parser, VALID_SKILL_INDEX + ITEM_TYPE_SKILL,
                expectedSkillErrorMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, VALID_INTERNSHIP_INDEX +
                ITEM_TYPE_INTERNSHIP + INVALID_NAME_DESC + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                Name.MESSAGE_CONSTRAINTS);

        // invalid from
        assertParseFailure(parser, VALID_INTERNSHIP_INDEX +
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + INVALID_FROM_DESC
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                Time.MESSAGE_CONSTRAINTS);

        // invalid TO
        assertParseFailure(parser, VALID_INTERNSHIP_INDEX +
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
                        + INVALID_TO_DESC + PREFIXED_INTERNSHIP_DESCRIPTION + PREFIXED_TAG_FRONTEND,
                Time.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, VALID_INTERNSHIP_INDEX +
                ITEM_TYPE_INTERNSHIP + PREFIXED_NAME_GOOGLE + PREFIXED_ROLE_FRONTEND + PREFIXED_TIME_FROM
                        + PREFIXED_TIME_TO + PREFIXED_INTERNSHIP_DESCRIPTION + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid website
        assertParseFailure(parser, VALID_INTERNSHIP_INDEX +
                ITEM_TYPE_PROJECT + PREFIXED_NAME_ORBITAL + PREFIXED_TIME_TO
                        + INVALID_WEBSITE_DESC + PREFIXED_DESCRIPTION_ORBITAL,
                Website.MESSAGE_CONSTRAINTS);

        // invalid level
        assertParseFailure(parser, VALID_INTERNSHIP_INDEX +
                ITEM_TYPE_SKILL + PREFIXED_NAME_REACT + INVALID_LEVEL_DESC + PREFIXED_TAG_FRONTEND,
                "Level of proficiency can only be one of these three types: basic, intermediate, "
                        + "advanced.");
    }
}
