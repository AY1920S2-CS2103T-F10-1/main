package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.delete.DeleteCommand;
import seedu.address.logic.commands.delete.DeleteInternshipCommand;
import seedu.address.logic.commands.delete.DeleteProjectCommand;
import seedu.address.logic.commands.delete.DeleteResumeCommand;
import seedu.address.logic.commands.delete.DeleteSkillCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.testutil.TypicalResumeBook;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 * TODO: TEST UNDO AND REDO TOO!
 */
public class DeleteCommandIntegrationTest {

    private Model model = new ModelManager(TypicalResumeBook.TYPICAL, new UserPrefs());

    @Test
    public void execute_deleteFirstInternship_success() {
        Internship internshipToDelete = model.getInternshipByIndex(INDEX_FIRST_ITEM);
        DeleteInternshipCommand deleteCommand = new DeleteInternshipCommand(INDEX_FIRST_ITEM);

        String expectedFeedback = String.format(DeleteCommand.MESSAGE_DELETE_ITEM_SUCCESS, "Internship");
        String expectedData = internshipToDelete.toString();

        ModelManager expectedModel = new ModelManager(TypicalResumeBook.TYPICAL, new UserPrefs());
        expectedModel.deleteInternship(internshipToDelete);
        expectedModel.setInternshipToDisplay();

        assertCommandSuccess(deleteCommand, model, expectedData, expectedFeedback, expectedModel);
    }

    @Test
    public void execute_deleteSecondInternship_success() {
        Internship internshipToDelete = model.getInternshipByIndex(INDEX_SECOND_ITEM);
        DeleteInternshipCommand deleteCommand = new DeleteInternshipCommand(INDEX_SECOND_ITEM);

        String expectedFeedback = String.format(DeleteCommand.MESSAGE_DELETE_ITEM_SUCCESS, "Internship");
        String expectedData = internshipToDelete.toString();

        ModelManager expectedModel = new ModelManager(TypicalResumeBook.TYPICAL, new UserPrefs());
        expectedModel.deleteInternship(internshipToDelete);
        expectedModel.setInternshipToDisplay();

        assertCommandSuccess(deleteCommand, model, expectedData, expectedFeedback, expectedModel);
    }

    @Test
    public void execute_invalidInternshipIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getInternshipSize() + 1);
        DeleteInternshipCommand deleteCommand = new DeleteInternshipCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void execute_deleteFirstProject_success() {
        Project projectToDelete = model.getProjectByIndex(INDEX_FIRST_ITEM);
        DeleteProjectCommand deleteCommand = new DeleteProjectCommand(INDEX_FIRST_ITEM);

        String expectedFeedback = String.format(DeleteCommand.MESSAGE_DELETE_ITEM_SUCCESS, "Project");
        String expectedData = projectToDelete.toString();

        ModelManager expectedModel = new ModelManager(TypicalResumeBook.TYPICAL, new UserPrefs());
        expectedModel.deleteProject(projectToDelete);
        expectedModel.setProjectToDisplay();

        assertCommandSuccess(deleteCommand, model, expectedData, expectedFeedback, expectedModel);
    }

    @Test
    public void execute_deleteSecondProject_success() {
        Project projectToDelete = model.getProjectByIndex(INDEX_SECOND_ITEM);
        DeleteProjectCommand deleteCommand = new DeleteProjectCommand(INDEX_SECOND_ITEM);

        String expectedFeedback = String.format(DeleteCommand.MESSAGE_DELETE_ITEM_SUCCESS, "Project");
        String expectedData = projectToDelete.toString();

        ModelManager expectedModel = new ModelManager(TypicalResumeBook.TYPICAL, new UserPrefs());
        expectedModel.deleteProject(projectToDelete);
        expectedModel.setProjectToDisplay();

        assertCommandSuccess(deleteCommand, model, expectedData, expectedFeedback, expectedModel);
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getProjectSize() + 1);
        DeleteProjectCommand deleteCommand = new DeleteProjectCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void execute_deleteFirstSkill_success() {
        Skill skillToDelete = model.getSkillByIndex(INDEX_FIRST_ITEM);
        DeleteSkillCommand deleteCommand = new DeleteSkillCommand(INDEX_FIRST_ITEM);

        String expectedFeedback = String.format(DeleteCommand.MESSAGE_DELETE_ITEM_SUCCESS, "Skill");
        String expectedData = skillToDelete.toString();

        ModelManager expectedModel = new ModelManager(TypicalResumeBook.TYPICAL, new UserPrefs());
        expectedModel.deleteSkill(skillToDelete);
        expectedModel.setSkillToDisplay();

        assertCommandSuccess(deleteCommand, model, expectedData, expectedFeedback, expectedModel);
    }

    @Test
    public void execute_deleteSecondSkill_success() {
        Skill skillToDelete = model.getSkillByIndex(INDEX_SECOND_ITEM);
        DeleteSkillCommand deleteCommand = new DeleteSkillCommand(INDEX_SECOND_ITEM);

        String expectedFeedback = String.format(DeleteCommand.MESSAGE_DELETE_ITEM_SUCCESS, "Skill");
        String expectedData = skillToDelete.toString();

        ModelManager expectedModel = new ModelManager(TypicalResumeBook.TYPICAL, new UserPrefs());
        expectedModel.deleteSkill(skillToDelete);
        expectedModel.setSkillToDisplay();

        assertCommandSuccess(deleteCommand, model, expectedData, expectedFeedback, expectedModel);
    }

    @Test
    public void execute_invalidSkillIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSkillSize() + 1);
        DeleteSkillCommand deleteCommand = new DeleteSkillCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    @Test
    public void execute_deleteFirstResume_success() {
        Resume resumeToDelete = model.getResumeByIndex(INDEX_FIRST_ITEM);
        DeleteResumeCommand deleteCommand = new DeleteResumeCommand(INDEX_FIRST_ITEM);

        String expectedFeedback = String.format(DeleteCommand.MESSAGE_DELETE_ITEM_SUCCESS, "Resume");
        String expectedData = resumeToDelete.toString();

        ModelManager expectedModel = new ModelManager(TypicalResumeBook.TYPICAL, new UserPrefs());
        expectedModel.deleteResume(resumeToDelete);
        expectedModel.setResumeToDisplay();

        assertCommandSuccess(deleteCommand, model, expectedData, expectedFeedback, expectedModel);
    }

    @Test
    public void execute_deleteSecondResume_success() {
        Resume resumeToDelete = model.getResumeByIndex(INDEX_SECOND_ITEM);
        DeleteResumeCommand deleteCommand = new DeleteResumeCommand(INDEX_SECOND_ITEM);

        String expectedFeedback = String.format(DeleteCommand.MESSAGE_DELETE_ITEM_SUCCESS, "Resume");
        String expectedData = resumeToDelete.toString();

        ModelManager expectedModel = new ModelManager(TypicalResumeBook.TYPICAL, new UserPrefs());
        expectedModel.deleteResume(resumeToDelete);
        expectedModel.setResumeToDisplay();

        assertCommandSuccess(deleteCommand, model, expectedData, expectedFeedback, expectedModel);
    }

    @Test
    public void execute_invalidResumeIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getResumeSize() + 1);
        DeleteResumeCommand deleteCommand = new DeleteResumeCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, new CommandException(Messages.MESSAGE_INVALID_INDEX));
    }

    /*@Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        PersonalDetail personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getResumeBook(), new UserPrefs());
        expectedModel.deleteItem(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }*/

    /*
    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResumeBook().getItemToDisplayList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
    */

    @Test
    public void equals() {
        DeleteCommand deleteFirstInternshipCommand = new DeleteInternshipCommand(INDEX_FIRST_ITEM);
        DeleteCommand deleteSecondInternshipCommand = new DeleteInternshipCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertEquals(deleteFirstInternshipCommand, deleteFirstInternshipCommand);

        // same values -> returns true
        DeleteCommand deleteFirstInternshipCommandCopy = new DeleteInternshipCommand(INDEX_FIRST_ITEM);
        assertEquals(deleteFirstInternshipCommand, deleteFirstInternshipCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstInternshipCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstInternshipCommand);

        // different internship -> returns false
        assertNotEquals(deleteFirstInternshipCommand, deleteSecondInternshipCommand);

        DeleteCommand deleteFirstProjectCommand = new DeleteProjectCommand(INDEX_FIRST_ITEM);
        DeleteCommand deleteSecondProjectCommand = new DeleteProjectCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertEquals(deleteFirstProjectCommand, deleteFirstProjectCommand);

        // same values -> returns true
        DeleteCommand deleteFirstProjectCommandCopy = new DeleteProjectCommand(INDEX_FIRST_ITEM);
        assertEquals(deleteFirstProjectCommand, deleteFirstProjectCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstProjectCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstProjectCommand);

        // different project -> returns false
        assertNotEquals(deleteFirstProjectCommand, deleteSecondProjectCommand);

        DeleteCommand deleteFirstSkillCommand = new DeleteSkillCommand(INDEX_FIRST_ITEM);
        DeleteCommand deleteSecondSkillCommand = new DeleteSkillCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertEquals(deleteFirstSkillCommand, deleteFirstSkillCommand);

        // same values -> returns true
        DeleteSkillCommand deleteFirstSkillCommandCopy = new DeleteSkillCommand(INDEX_FIRST_ITEM);
        assertEquals(deleteFirstSkillCommand, deleteFirstSkillCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstSkillCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstSkillCommand);

        // different skill -> returns false
        assertNotEquals(deleteFirstSkillCommand, deleteSecondSkillCommand);

        DeleteCommand deleteFirstResumeCommand = new DeleteResumeCommand(INDEX_FIRST_ITEM);
        DeleteCommand deleteSecondResumeCommand = new DeleteResumeCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertEquals(deleteFirstResumeCommand, deleteFirstResumeCommand);

        // same values -> returns true
        DeleteCommand deleteFirstResumeCommandCopy = new DeleteResumeCommand(INDEX_FIRST_ITEM);
        assertEquals(deleteFirstResumeCommand, deleteFirstResumeCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstResumeCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstResumeCommand);

        // different resume -> returns false
        assertNotEquals(deleteFirstResumeCommand, deleteSecondResumeCommand);

        // different type -> returns false
        assertNotEquals(deleteFirstInternshipCommand, deleteFirstProjectCommand);
    }


    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    /*
    private void showNoPerson(Model model) {
        model.updateFilteredItemList(p -> false);

        assertTrue(model.getFilteredItemList().isEmpty());
    }
    */
}
