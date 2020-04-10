package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;
import static seedu.address.testutil.TypicalInternship.GOOGLE;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.item.Internship;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.ModelStub;

public class EditInternshipCommandTest {

    private Internship sampleEditedInternship = GOOGLE;

    private EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();

    @Test
    public void constructor_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditInternshipCommand(null, null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Internship validItem = new InternshipBuilder().build();
        ModelStub modelStub = new EditInternshipCommandTest.ModelStubWithInternship(validItem);
        Index invalidIndex = INDEX_THIRD_ITEM;
        setEditInternshipDescriptor();
        EditInternshipCommand editInternshipCommand = new EditInternshipCommand(invalidIndex, editInternshipDescriptor);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_INDEX, () -> editInternshipCommand.execute(modelStub));
    }

    @Test
    public void execute_validIndex_editSuccessful() throws CommandException {
        ModelStubContainingInternshipEdited modelStub = new ModelStubContainingInternshipEdited(sampleEditedInternship);
        setEditInternshipDescriptor();
        Index validIndex = INDEX_FIRST_ITEM;

        Internship toEdit = modelStub.getInternshipByIndex(validIndex);
        EditInternshipCommand editInternshipCommand = new EditInternshipCommand(validIndex, editInternshipDescriptor);
        assertEquals(Arrays.asList(toEdit), modelStub.internships);

        CommandResult commandResult = editInternshipCommand.execute(modelStub);

        assertEquals(String.format(EditInternshipCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS, sampleEditedInternship),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() throws CommandException {
        Index validIndex = INDEX_FIRST_ITEM;
        setEditInternshipDescriptor();
        EditInternshipCommand editCommand = new EditInternshipCommand(validIndex, editInternshipDescriptor);
        ModelStubContainingInternshipEdited modelStub = new ModelStubContainingInternshipEdited(
                new InternshipBuilder().build());
        modelStub.addInternship(sampleEditedInternship);

        //      I'm actually not sure how to test that if we edit it into an existing Internship with same name then
        //      it throws Duplicate exception.
        //      assertThrows(CommandException.class,
        //          EditInternshipCommand.MESSAGE_DUPLICATE_ITEM, () -> editCommand.execute(modelStub));
    }


    @Test
    public void create_withNullDescriptorField_editedInternship() {
        editInternshipDescriptor.setTags(null);
        editInternshipDescriptor.setName(null);
        Index validIndex = INDEX_FIRST_ITEM;
        EditInternshipCommand editInternshipCommand = new EditInternshipCommand(validIndex, editInternshipDescriptor);
        assertEquals(sampleEditedInternship,
                editInternshipCommand.createEditedInternship(sampleEditedInternship, editInternshipDescriptor));
    }

    @Test
    public void equals() {
        Index indexA = Index.fromZeroBased(5);
        Index indexB = Index.fromOneBased(19);

        setEditInternshipDescriptor();

        EditInternshipCommand editACommand = new EditInternshipCommand(indexA, editInternshipDescriptor);
        EditInternshipCommand editBCommand = new EditInternshipCommand(indexB, editInternshipDescriptor);

        // same object -> returns true
        assertTrue(editACommand.equals(editACommand));

        // same value -> returns true
        EditInternshipCommand editACommandCopy = new EditInternshipCommand(indexA, editInternshipDescriptor);
        assertTrue(editACommand.equals(editACommandCopy));

        // different types -> returns false
        assertFalse(editACommand.equals(1));

        // null -> returns false
        assertFalse(editACommand.equals(null));

        // different index -> returns false
        assertFalse(editACommand.equals(editBCommand));
    }


    /**
     * A method to set fields in the edit internship descriptor.
     */
    public void setEditInternshipDescriptor() {
        editInternshipDescriptor.setName(sampleEditedInternship.getName());
        editInternshipDescriptor.setFrom(sampleEditedInternship.getFrom());
        editInternshipDescriptor.setTo(sampleEditedInternship.getTo());
        editInternshipDescriptor.setRole(sampleEditedInternship.getRole());
        editInternshipDescriptor.setDescription(sampleEditedInternship.getDescription());
        editInternshipDescriptor.setTags(sampleEditedInternship.getTags());
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
     * A Model stub that always contain the Internship being edited.
     */
    private class ModelStubContainingInternshipEdited extends ModelStub {
        final ArrayList<Internship> internships = new ArrayList<>();
        private final Internship item;

        ModelStubContainingInternshipEdited(Internship item) {
            requireNonNull(item);
            this.item = item;
            internships.add(item);
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
        public void addInternship(Internship internship) {
            internships.add(internship);
        }

        @Override
        public void setInternship(Internship target, Internship editedInternship) {
            int index = internships.indexOf(target);
            internships.set(index, editedInternship);
        }

        @Override
        public boolean hasInternship(Internship item) {
            requireNonNull(item);
            return this.item.isSame(item);
        }
    }
}

