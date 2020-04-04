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
import seedu.address.model.item.Internship;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.ModelStub;

public class DeleteInternshipCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteInternshipCommand(null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Internship validItem = new InternshipBuilder().build();
        ModelStub modelStub = new ModelStubWithInternship(validItem);
        Index invalidIndex = INDEX_THIRD_ITEM;
        DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(invalidIndex);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_INDEX, () -> deleteInternshipCommand.execute(modelStub));
    }

    @Test
    public void execute_validIndex_deleteSuccessful() throws CommandException {
        ModelStubContainingInternshipDeleted modelStub = new ModelStubContainingInternshipDeleted();
        Index validIndex = INDEX_FIRST_ITEM;
        Internship toDelete = modelStub.getInternshipByIndex(validIndex);
        DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(validIndex);
        assertEquals(Arrays.asList(toDelete), modelStub.internships);

        CommandResult commandResult = deleteInternshipCommand.execute(modelStub);
        assertEquals(String.format(DeleteInternshipCommand.MESSAGE_DELETE_ITEM_SUCCESS,
                toDelete.getType().getFullType()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Index indexA = Index.fromZeroBased(5);
        Index indexB = Index.fromOneBased(19);
        DeleteInternshipCommand deleteACommand = new DeleteInternshipCommand(indexA);
        DeleteInternshipCommand deleteBCommand = new DeleteInternshipCommand(indexB);

        // same object -> returns true
        assertTrue(deleteACommand.equals(deleteACommand));

        // same value -> returns true
        DeleteInternshipCommand deleteACommandCopy = new DeleteInternshipCommand(indexA);
        assertTrue(deleteACommand.equals(deleteACommandCopy));

        // different types -> returns false
        assertFalse(deleteACommand.equals(1));

        // null -> returns false
        assertFalse(deleteACommand.equals(null));

        // different index -> returns false
        assertFalse(deleteACommand.equals(deleteBCommand));
    }

    /**
     * A Model stub that contains a single Internship.
     */
    private class ModelStubWithInternship extends ModelStub {
        private final Internship item;

        ModelStubWithInternship(Internship item) {
            requireNonNull(item);
            this.item = item;
        }

        @Override
        public boolean hasInternship(Internship item) {
            requireNonNull(item);
            return this.item.isSame(item);
        }
    }

    /**
     * A Model stub that always contain the Internship being deleted.
     */
    private class ModelStubContainingInternshipDeleted extends ModelStub {
        final ArrayList<Internship> internships = new ArrayList<>();
        ModelStubContainingInternshipDeleted() {
            internships.add(new InternshipBuilder().withName("Stub internship").build());
        }

        @Override
        public Internship getInternshipByIndex(Index index) {
            return internships.get(index.getZeroBased());
        }

        @Override
        public int getInternshipSize() {
            return internships.size();
        }

        @Override
        public void deleteInternship(Internship internship) {
            internships.remove(internship);
        }
    }
}
