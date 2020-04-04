package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.item.Project;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.ProjectBuilder;

public class DeleteProjectCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteProjectCommand(null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Project validItem = new ProjectBuilder().build();
        ModelStub modelStub = new ModelStubWithProject(validItem);
        Index invalidIndex = INDEX_SECOND_ITEM;
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(invalidIndex);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_INDEX, () -> deleteProjectCommand.execute(modelStub));
    }

    @Test
    public void execute_validIndex_deleteSuccessful() throws CommandException {
        ModelStubContainingProjectDeleted modelStub = new ModelStubContainingProjectDeleted();
        Index validIndex = INDEX_FIRST_ITEM;
        Project toDelete = modelStub.getProjectByIndex(validIndex);
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(validIndex);
        assertEquals(Arrays.asList(toDelete), modelStub.projects);

        CommandResult commandResult = deleteProjectCommand.execute(modelStub);
        assertEquals(String.format(DeleteProjectCommand.MESSAGE_DELETE_ITEM_SUCCESS,
                toDelete.getType().getFullType()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Index indexA = Index.fromZeroBased(5);
        Index indexB = Index.fromOneBased(19);
        DeleteProjectCommand deleteACommand = new DeleteProjectCommand(indexA);
        DeleteProjectCommand deleteBCommand = new DeleteProjectCommand(indexB);

        // same object -> returns true
        assertTrue(deleteACommand.equals(deleteACommand));

        // same value -> returns true
        DeleteProjectCommand deleteACommandCopy = new DeleteProjectCommand(indexA);
        assertTrue(deleteACommand.equals(deleteACommandCopy));

        // different types -> returns false
        assertFalse(deleteACommand.equals(1));

        // null -> returns false
        assertFalse(deleteACommand.equals(null));

        // different index -> returns false
        assertFalse(deleteACommand.equals(deleteBCommand));
    }

    /**
     * A Model stub that contains a single Project.
     */
    private class ModelStubWithProject extends ModelStub {
        private final Project item;

        ModelStubWithProject(Project item) {
            requireNonNull(item);
            this.item = item;
        }

        @Override
        public boolean hasProject(Project item) {
            requireNonNull(item);
            return this.item.isSame(item);
        }
    }

    /**
     * A Model stub that always contains the Project being deleted.
     */
    private class ModelStubContainingProjectDeleted extends ModelStub {
        final ArrayList<Project> projects = new ArrayList<>();
        ModelStubContainingProjectDeleted() {
            projects.add(new ProjectBuilder().withName("Stub project").build());
        }

        @Override
        public Project getProjectByIndex(Index index) {
            return projects.get(index.getZeroBased());
        }

        @Override
        public int getProjectSize() {
            return projects.size();
        }

        @Override
        public void deleteProject(Project project) {
            projects.remove(project);
        }
    }
}
