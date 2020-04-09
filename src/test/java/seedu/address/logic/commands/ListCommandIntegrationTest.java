package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.commands.list.ListInternshipCommand;
import seedu.address.logic.commands.list.ListNoteCommand;
import seedu.address.logic.commands.list.ListProjectCommand;
import seedu.address.logic.commands.list.ListResumeCommand;
import seedu.address.logic.commands.list.ListSkillCommand;
import seedu.address.logic.commands.results.ListCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.util.ItemUtil;
import seedu.address.testutil.TypicalResumeBook;

public class ListCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalResumeBook.TYPICAL, new UserPrefs());
    }

    @Test
    public void execute_listInternship_success() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());
        expectedModel.setInternshipToDisplay();

        assertCommandSuccess(new ListInternshipCommand(),
                model,
                new ListCommandResult("",
                        String.format(ListCommand.MESSAGE_SUCCESS, "Internship"),
                        ItemUtil.INTERNSHIP_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_listNote_success() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());

        assertCommandSuccess(new ListNoteCommand(),
                model,
                new ListCommandResult("",
                        String.format(ListCommand.MESSAGE_SUCCESS, "Note"),
                        expectedModel.getDisplayType()),
                expectedModel);
    }

    @Test
    public void execute_listProject_success() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());
        expectedModel.setProjectToDisplay();

        assertCommandSuccess(new ListProjectCommand(),
                model,
                new ListCommandResult("",
                        String.format(ListCommand.MESSAGE_SUCCESS, "Project"),
                        ItemUtil.INTERNSHIP_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_listResume_success() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());
        expectedModel.setResumeToDisplay();

        assertCommandSuccess(new ListResumeCommand(),
                model,
                new ListCommandResult("",
                        String.format(ListCommand.MESSAGE_SUCCESS, "Resume"),
                        ItemUtil.RESUME_ALIAS),
                expectedModel);
    }

    @Test
    public void execute_listSkill_success() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());
        expectedModel.setSkillToDisplay();

        assertCommandSuccess(new ListSkillCommand(),
                model,
                new ListCommandResult("",
                        String.format(ListCommand.MESSAGE_SUCCESS, "Skill"),
                        ItemUtil.INTERNSHIP_ALIAS),
                expectedModel);
    }

    @Test
    public void equals() {
        ListCommand listInternships = new ListInternshipCommand();
        ListCommand listProjects = new ListProjectCommand();
        ListCommand listResumes = new ListResumeCommand();
        ListCommand listSkills = new ListSkillCommand();
        ListCommand listNotes = new ListNoteCommand();

        assertNotEquals(listInternships, listNotes);
        assertNotEquals(listInternships, listProjects);
        assertNotEquals(listInternships, listResumes);
        assertNotEquals(listInternships, listSkills);
        assertNotEquals(listNotes, listProjects);
        assertNotEquals(listNotes, listResumes);
        assertNotEquals(listNotes, listSkills);
        assertNotEquals(listProjects, listResumes);
        assertNotEquals(listProjects, listSkills);
        assertNotEquals(listResumes, listSkills);
    }

}
