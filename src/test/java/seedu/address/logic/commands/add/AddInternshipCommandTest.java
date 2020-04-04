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

import seedu.address.model.item.Internship;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.ModelStub;

public class AddInternshipCommandTest {
    @Test
    public void constructor_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInternshipCommand(null));
    }

    @Test
    public void execute_internshipAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingInternshipAdded modelStub = new ModelStubAcceptingInternshipAdded();
        Internship validItem = new InternshipBuilder().build();

        CommandResult commandResult = new AddInternshipCommand(validItem).execute(modelStub);

        assertEquals(String.format(AddInternshipCommand.MESSAGE_SUCCESS, validItem.getType().getFullType()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validItem), modelStub.internships);
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship validItem = new InternshipBuilder().build();
        AddInternshipCommand addCommand = new AddInternshipCommand(validItem);
        ModelStub modelStub = new ModelStubWithInternship(validItem);

        assertThrows(CommandException.class,
                AddInternshipCommand.MESSAGE_DUPLICATE_ITEM, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Internship itemA = new InternshipBuilder().withName("NameA").build();
        Internship itemB = new InternshipBuilder().withName("NameB").build();
        AddInternshipCommand addACommand = new AddInternshipCommand(itemA);
        AddInternshipCommand addBCommand = new AddInternshipCommand(itemB);

        // same object -> returns true
        assertTrue(addACommand.equals(addACommand));

        // same values -> returns true
        AddInternshipCommand addACommandCopy = new AddInternshipCommand(itemA);
        assertTrue(addACommand.equals(addACommandCopy));

        // different types -> returns false
        assertFalse(addACommand.equals(1));

        // null -> returns false
        assertFalse(addACommand.equals(null));

        // different internship -> returns false
        assertFalse(addACommand.equals(addBCommand));
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
     * A Model stub that always accept the Internship being added.
     */
    private class ModelStubAcceptingInternshipAdded extends ModelStub {
        final ArrayList<Internship> internships = new ArrayList<>();

        @Override
        public void addInternship(Internship internship) {
            internships.add(internship);
        }
    }

}
