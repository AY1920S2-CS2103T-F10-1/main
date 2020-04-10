package seedu.address.logic.commands.edit;

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
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.item.Resume;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.ResumeBuilder;
import seedu.address.testutil.TypicalResume;

public class EditResumeCommandTest {

    private Resume sampleEditedResume = TypicalResume.ME_RESUME;

    private EditResumeDescriptor editResumeDescriptor = new EditResumeDescriptor();

    @Test
    public void constructor_nullResume_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditResumeCommand(null, null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Resume validItem = new ResumeBuilder().build();
        ModelStub modelStub = new ModelStubWithResume(validItem);
        Index invalidIndex = INDEX_THIRD_ITEM;
        setEditResumeDescriptor();
        EditResumeCommand editResumeCommand = new EditResumeCommand(invalidIndex, editResumeDescriptor);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_INDEX, () -> editResumeCommand.execute(modelStub));
    }

    @Test
    public void execute_validIndex_editSuccessful() throws CommandException {
        ModelStubContainingResumeEdited modelStub = new ModelStubContainingResumeEdited();
        setEditResumeDescriptor();
        Index validIndex = INDEX_FIRST_ITEM;

        Resume toEdit = modelStub.getResumeByIndex(validIndex);
        EditResumeCommand editResumeCommand = new EditResumeCommand(validIndex, editResumeDescriptor);
        assertEquals(Arrays.asList(toEdit), modelStub.resumes);

        CommandResult commandResult = editResumeCommand.execute(modelStub);

        assertEquals(String.format(EditResumeCommand.MESSAGE_EDIT_RESUME_SUCCESS, sampleEditedResume),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void create_withNullDescriptorField_editedResume() {
        editResumeDescriptor.setTags(null);
        editResumeDescriptor.setName(null);
        Index validIndex = INDEX_FIRST_ITEM;
        EditResumeCommand editResumeCommand = new EditResumeCommand(validIndex, editResumeDescriptor);
        assertEquals(sampleEditedResume,
                editResumeCommand.createEditedResume(sampleEditedResume, editResumeDescriptor));
    }

    @Test
    public void equals() {
        Index indexA = Index.fromZeroBased(5);
        Index indexB = Index.fromOneBased(19);

        setEditResumeDescriptor();

        EditResumeCommand editACommand = new EditResumeCommand(indexA, editResumeDescriptor);
        EditResumeCommand editBCommand = new EditResumeCommand(indexB, editResumeDescriptor);

        // same object -> returns true
        assertTrue(editACommand.equals(editACommand));

        // same value -> returns true
        EditResumeCommand editACommandCopy = new EditResumeCommand(indexA, editResumeDescriptor);
        assertTrue(editACommand.equals(editACommandCopy));

        // different types -> returns false
        assertFalse(editACommand.equals(1));

        // null -> returns false
        assertFalse(editACommand.equals(null));

        // different index -> returns false
        assertFalse(editACommand.equals(editBCommand));
    }


    /**
     * A method to set fields in the edit resume descriptor.
     */
    public void setEditResumeDescriptor() {
        editResumeDescriptor.setName(sampleEditedResume.getName());
        editResumeDescriptor.setTags(sampleEditedResume.getTags());
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
     * A Model stub that always contain the Resume being edited.
     */
    private class ModelStubContainingResumeEdited extends ModelStub {
        final ArrayList<Resume> resumes = new ArrayList<>();

        ModelStubContainingResumeEdited() {
            resumes.add(new ResumeBuilder().build());
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
        public void addResume(Resume resume) {
            resumes.add(resume);
        }

        @Override
        public void setResume(Resume target, Resume editedResume) {
            int index = resumes.indexOf(target);
            resumes.set(index, editedResume);
        }
    }
}


