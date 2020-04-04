package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;

import seedu.address.model.item.Resume;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.ResumeBuilder;

public class AddResumeCommandTest {
    @Test
    public void constructor_nullResume_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddResumeCommand(null));
    }

    @Test
    public void execute_resumeAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingResumeAdded modelStub = new ModelStubAcceptingResumeAdded();
        Resume validItem = new ResumeBuilder().build();

        CommandResult commandResult = new AddResumeCommand(validItem).execute(modelStub);

        assertEquals(String.format(AddResumeCommand.MESSAGE_SUCCESS, validItem.getType().getFullType()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validItem), modelStub.resumes);
    }

    @Test
    public void execute_duplicateResume_throwsCommandException() {
        Resume validItem = new ResumeBuilder().build();
        AddResumeCommand addCommand = new AddResumeCommand(validItem);
        ModelStub modelStub = new ModelStubWithResume(validItem);

        assertThrows(CommandException.class,
                AddResumeCommand.MESSAGE_DUPLICATE_ITEM, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Resume java = new ResumeBuilder().withName("Java").build();
        Resume c = new ResumeBuilder().withName("C").build();
        AddResumeCommand addJavaCommand = new AddResumeCommand(java);
        AddResumeCommand addCCommand = new AddResumeCommand(c);

        // same object -> returns true
        assertTrue(addJavaCommand.equals(addJavaCommand));

        // same values -> returns true
        AddResumeCommand addJavaCommandCopy = new AddResumeCommand(java);
        assertTrue(addJavaCommand.equals(addJavaCommandCopy));

        // different types -> returns false
        assertFalse(addJavaCommand.equals(1));

        // null -> returns false
        assertFalse(addJavaCommand.equals(null));

        // different resume -> returns false
        assertFalse(addJavaCommand.equals(addCCommand));
    }

    /**
     * A Model stub that contains a single resume.
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
     * A Model stub that always accept the resume being added.
     */
    private class ModelStubAcceptingResumeAdded extends ModelStub {
        final ArrayList<Resume> resumes = new ArrayList<>();

        @Override
        public void addResume(Resume resume) {
            resumes.add(resume);
        }
    }

}
