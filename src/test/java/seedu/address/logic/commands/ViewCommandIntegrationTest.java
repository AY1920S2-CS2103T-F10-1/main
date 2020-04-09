package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.view.ViewCommand;
import seedu.address.logic.commands.view.ViewInternshipCommand;
import seedu.address.logic.commands.view.ViewNoteCommand;
import seedu.address.logic.commands.view.ViewProjectCommand;
import seedu.address.logic.commands.view.ViewResumeCommand;
import seedu.address.logic.commands.view.ViewSkillCommand;
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
 * Contains integration tests (interaction with the Model) for {@code ViewCommand}.
 */
public class ViewCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE, new UserPrefs());
    }

    @Test
    public void execute_viewInternship_success() {
        Internship validInternship = TypicalInternship.NINJA_VAN;

        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE_COPY, new UserPrefs());
        expectedModel.setInternshipToDisplay();

        assertCommandSuccess(new ViewInternshipCommand(Index.fromOneBased(1)),
                model,
                new CommandResult(validInternship.toString(),
                        ViewInternshipCommand.MESSAGE_VIEW_SUCCESS,
                        ItemUtil.INTERNSHIP_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_outOfBoundsInternship_throwsCommandException() {
        assertCommandFailure(new ViewInternshipCommand(Index.fromOneBased(3)),
                model,
                new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void execute_viewNote_success() {
        Note validNote = TypicalNote.FINISH_CS_2103;

        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE_COPY, new UserPrefs());

        assertCommandSuccess(new ViewNoteCommand(Index.fromOneBased(1)),
                model,
                new CommandResult(validNote.toString(),
                        ViewNoteCommand.MESSAGE_VIEW_SUCCESS,
                        ItemUtil.INTERNSHIP_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_outOfBoundsNote_throwsCommandException() {
        assertCommandFailure(new ViewNoteCommand(Index.fromOneBased(3)),
                model,
                new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }



    @Test
    public void execute_viewProject_success() {
        Project validProject = TypicalProject.ORBITAL;

        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE_COPY, new UserPrefs());
        expectedModel.setProjectToDisplay();

        assertCommandSuccess(new ViewProjectCommand(Index.fromOneBased(1)),
                model,
                new CommandResult(validProject.toString(),
                        ViewProjectCommand.MESSAGE_VIEW_SUCCESS,
                        ItemUtil.INTERNSHIP_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_outOfBoundsProject_throwsCommandException() {
        assertCommandFailure(new ViewProjectCommand(Index.fromOneBased(2)),
                model,
                new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void execute_viewResume_success() {
        Resume validResume = TypicalResume.ME_RESUME;

        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE_COPY, new UserPrefs());
        expectedModel.setResumeToDisplay();

        assertCommandSuccess(new ViewResumeCommand(Index.fromOneBased(1)),
                model,
                new CommandResult(validResume.toString(),
                        ViewResumeCommand.MESSAGE_VIEW_SUCCESS,
                        ItemUtil.INTERNSHIP_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_outOfBoundsResume_throwsCommandException() {
        assertCommandFailure(new ViewResumeCommand(Index.fromOneBased(2)),
                model,
                new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void execute_viewSkill_success() {
        Skill validSkill = TypicalSkill.REACT;

        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE_COPY, new UserPrefs());
        expectedModel.setSkillToDisplay();

        assertCommandSuccess(new ViewSkillCommand(Index.fromOneBased(1)),
                model,
                new CommandResult(validSkill.toString(),
                        ViewSkillCommand.MESSAGE_VIEW_SUCCESS,
                        ItemUtil.INTERNSHIP_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_outOfBoundsSkill_throwsCommandException() {
        assertCommandFailure(new ViewSkillCommand(Index.fromOneBased(2)),
                model,
                new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void equals() {
        ViewCommand viewInternship = new ViewInternshipCommand(Index.fromOneBased(1));
        ViewCommand viewProject = new ViewProjectCommand(Index.fromOneBased(1));
        ViewCommand viewResume = new ViewResumeCommand(Index.fromOneBased(1));
        ViewCommand viewSkill = new ViewSkillCommand(Index.fromOneBased(1));

        assertNotEquals(viewInternship, viewProject);
        assertNotEquals(viewInternship, viewResume);
        assertNotEquals(viewInternship, viewSkill);
        assertNotEquals(viewProject, viewResume);
        assertNotEquals(viewProject, viewSkill);
        assertNotEquals(viewResume, viewSkill);

    }
}
