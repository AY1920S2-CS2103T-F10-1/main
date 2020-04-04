package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.item.Resume;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.ResumeBuilder;

public class DeleteResumeCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteResumeCommand(null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Resume validItem = new ResumeBuilder().build();
        ModelStub modelStub = new ModelStubWithResume(validItem);
        Index invalidIndex = INDEX_THIRD_ITEM;
        DeleteResumeCommand deleteResumeCommand = new DeleteResumeCommand(invalidIndex);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_INDEX, () -> deleteResumeCommand.execute(modelStub));
    }

    @Test
    public void execute_validIndex_deleteSuccessful() throws CommandException {
        ModelStubContainingResumeDeleted modelStub = new ModelStubContainingResumeDeleted();
        Index validIndex = INDEX_FIRST_ITEM;
        Resume toDelete = modelStub.getResumeByIndex(validIndex);
        DeleteResumeCommand deleteResumeCommand = new DeleteResumeCommand(validIndex);
        assertEquals(Arrays.asList(toDelete), modelStub.resumes);

        CommandResult commandResult = deleteResumeCommand.execute(modelStub);
        assertEquals(String.format(DeleteResumeCommand.MESSAGE_DELETE_ITEM_SUCCESS,
                toDelete.getType().getFullType()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Index indexA = Index.fromZeroBased(5);
        Index indexB = Index.fromOneBased(19);
        DeleteResumeCommand deleteACommand = new DeleteResumeCommand(indexA);
        DeleteResumeCommand deleteBCommand = new DeleteResumeCommand(indexB);

        // same object -> returns true
        assertTrue(deleteACommand.equals(deleteACommand));

        // same value -> returns true
        DeleteResumeCommand deleteACommandCopy = new DeleteResumeCommand(indexA);
        assertTrue(deleteACommand.equals(deleteACommandCopy));

        // different types -> returns false
        assertFalse(deleteACommand.equals(1));

        // null -> returns false
        assertFalse(deleteACommand.equals(null));

        // different index -> returns false
        assertFalse(deleteACommand.equals(deleteBCommand));
    }

    /**
     * A Model stub that contains a single Resume.
     */
    private class ModelStubWithResume extends ModelStub {
        private final Resume item;

        ModelStubWithResume(Resume item) {
            requireNonNull(item);
            this.item = item;
        }

        @Override
        public boolean hasResume(Resume item) {
            requireNonNull(item);
            return this.item.isSame(item);
        }
    }

    /**
     * A Model stub that always contain the Resume being deleted.
     */
    private class ModelStubContainingResumeDeleted extends ModelStub {
        final ArrayList<Resume> resumes = new ArrayList<>();
        ModelStubContainingResumeDeleted() {
            resumes.add(new ResumeBuilder().withName("Stub resume").build());
        }

        @Override
        public Resume getResumeByIndex(Index index) {
            return resumes.get(index.getZeroBased());
        }

        @Override
        public int getResumeSize() {
            return resumes.size();
        }

        @Override
        public void deleteResume(Resume resume) {
            resumes.remove(resume);
        }
    }
}
