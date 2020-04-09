package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE_COPY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.add.AddInternshipCommand;
import seedu.address.logic.commands.add.AddNoteCommand;
import seedu.address.logic.commands.add.AddProjectCommand;
import seedu.address.logic.commands.add.AddResumeCommand;
import seedu.address.logic.commands.add.AddSkillCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.AddCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Note;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.util.ItemUtil;
import seedu.address.testutil.TypicalInternship;
import seedu.address.testutil.TypicalNote;
import seedu.address.testutil.TypicalProject;
import seedu.address.testutil.TypicalResume;
import seedu.address.testutil.TypicalResumeBook;
import seedu.address.testutil.TypicalSkill;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE, new UserPrefs());
    }

    @Test
    public void execute_newInternship_success() {
        Internship validInternship = TypicalInternship.GOOGLE;

        Model expectedModel = new ModelManager(TYPICAL_WITHOUT_GOOGLE_COPY, new UserPrefs());
        expectedModel.addInternship(validInternship);
        expectedModel.setInternshipToDisplay();

        assertCommandSuccess(new AddInternshipCommand(validInternship),
                model,
                new AddCommandResult(validInternship.toString(),
                        String.format(AddInternshipCommand.MESSAGE_SUCCESS,
                                validInternship.getType().getFullType()), ItemUtil.INTERNSHIP_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship validInternship = TypicalInternship.NINJA_VAN;

        assertCommandFailure(new AddInternshipCommand(validInternship),
                model,
                new CommandException(AddCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void execute_newNote_success() {
        Note validNote = TypicalNote.FINISH_HOMEWORK;

        Model expectedModel = new ModelManager(TYPICAL_WITHOUT_GOOGLE_COPY, new UserPrefs());
        expectedModel.addNote(validNote);

        assertCommandSuccess(new AddNoteCommand(validNote),
                model,
                new AddCommandResult(validNote.toString(),
                        AddNoteCommand.MESSAGE_SUCCESS, ItemUtil.INTERNSHIP_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_duplicateNote_throwsCommandException() {
        Note validNote = TypicalNote.FINISH_CS_2103;

        assertCommandFailure(new AddNoteCommand(validNote),
                model,
                new CommandException(AddNoteCommand.MESSAGE_DUPLICATE_ERROR));
    }

    @Test
    public void execute_newResume_success() {
        Resume validResume = TypicalResume.SE_RESUME;

        Model expectedModel = new ModelManager(TYPICAL_WITHOUT_GOOGLE_COPY, new UserPrefs());
        expectedModel.addResume(validResume);
        expectedModel.setResumeToDisplay();

        assertCommandSuccess(new AddResumeCommand(validResume),
                model,
                new AddCommandResult(validResume.toString(),
                        String.format(AddResumeCommand.MESSAGE_SUCCESS,
                                validResume.getType().getFullType()),
                        ItemUtil.RESUME_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_duplicateResume_throwsCommandException() {
        Resume validResume = TypicalResume.ME_RESUME;

        assertCommandFailure(new AddResumeCommand(validResume),
                model,
                new CommandException(AddCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void execute_newSkill_success() {
        Skill validSkill = TypicalSkill.GIT;

        Model expectedModel = new ModelManager(TYPICAL_WITHOUT_GOOGLE_COPY, new UserPrefs());
        expectedModel.addSkill(validSkill);
        expectedModel.setSkillToDisplay();

        assertCommandSuccess(new AddSkillCommand(validSkill),
                model,
                new AddCommandResult(validSkill.toString(),
                        String.format(AddSkillCommand.MESSAGE_SUCCESS,
                                validSkill.getType().getFullType()),
                        ItemUtil.SKILL_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_duplicateSkill_throwsCommandException() {
        Skill validSkill = TypicalSkill.REACT;

        assertCommandFailure(new AddSkillCommand(validSkill),
                model,
                new CommandException(AddCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void execute_newProject_success() {
        Project validProject = TypicalProject.DUKE;

        Model expectedModel = new ModelManager(TYPICAL_WITHOUT_GOOGLE_COPY, new UserPrefs());
        expectedModel.addProject(validProject);
        expectedModel.setProjectToDisplay();

        assertCommandSuccess(new AddProjectCommand(validProject),
                model,
                new AddCommandResult(validProject.toString(),
                        String.format(AddProjectCommand.MESSAGE_SUCCESS,
                                validProject.getType().getFullType()),
                        ItemUtil.PROJECT_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_duplicateProject_throwsCommandException() {
        Project validProject = TypicalProject.ORBITAL;

        assertCommandFailure(new AddProjectCommand(validProject),
                model,
                new CommandException(AddCommand.MESSAGE_DUPLICATE_ITEM));
    }

    @Test
    public void equals() {
        AddCommand validInternship = new AddInternshipCommand(TypicalInternship.GOOGLE);
        AddCommand validProject = new AddProjectCommand(TypicalProject.ORBITAL);
        AddCommand validResume = new AddResumeCommand(TypicalResume.ME_RESUME);
        AddCommand validSkill = new AddSkillCommand(TypicalSkill.REACT);
        AddCommand validNote = new AddNoteCommand(TypicalNote.FINISH_CS_2103);

        assertNotEquals(validInternship, validNote);
        assertNotEquals(validInternship, validProject);
        assertNotEquals(validInternship, validResume);
        assertNotEquals(validInternship, validSkill);
        assertNotEquals(validNote, validProject);
        assertNotEquals(validNote, validResume);
        assertNotEquals(validNote, validSkill);
        assertNotEquals(validProject, validResume);
        assertNotEquals(validProject, validSkill);
        assertNotEquals(validResume, validSkill);
    }
}
