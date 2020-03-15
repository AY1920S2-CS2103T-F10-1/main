package seedu.address.logic.commands;

/*import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyResumeBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ResumeBook;

import seedu.address.model.item.Item;
import seedu.address.testutil.PersonalDetailBuilder;*/

public class AddCommandTest {
//    TO DO: RESUME TESTS
//    @Test
//    public void constructor_nullPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> new AddCommand(null));
//    }
//
//    @Test
//    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
//        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
//        Item validItem = new PersonalDetailBuilder().build();
//
//        CommandResult commandResult = new AddCommand(validItem).execute(modelStub);
//
//        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validItem), commandResult.getFeedbackToUser());
//        assertEquals(Arrays.asList(validItem), modelStub.personsAdded);
//    }
//
//    @Test
//    public void execute_duplicatePerson_throwsCommandException() {
//        Item validItem = new PersonalDetailBuilder().build();
//        AddCommand addCommand = new AddCommand(validItem);
//        ModelStub modelStub = new ModelStubWithPerson(validItem);
//
//        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON,
//        () -> addCommand.execute(modelStub));
//    }
//
//    @Test
//    public void equals() {
//        Item alice = new PersonalDetailBuilder().withName("Alice").build();
//        Item bob = new PersonalDetailBuilder().withName("Bob").build();
//        AddCommand addAliceCommand = new AddCommand(alice);
//        AddCommand addBobCommand = new AddCommand(bob);
//
//        // same object -> returns true
//        assertTrue(addAliceCommand.equals(addAliceCommand));
//
//        // same values -> returns true
//        AddCommand addAliceCommandCopy = new AddCommand(alice);
//        assertTrue(addAliceCommand.equals(addAliceCommandCopy));
//
//        // different types -> returns false
//        assertFalse(addAliceCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(addAliceCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(addAliceCommand.equals(addBobCommand));
//    }
//
//    /**
//     * A default model stub that have all of the methods failing.
//     */
//    private class ModelStub implements Model {
//        @Override
//        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyUserPrefs getUserPrefs() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public GuiSettings getGuiSettings() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setGuiSettings(GuiSettings guiSettings) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Path getResumeBookFilePath() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setResumeBookFilePath(Path addressBookFilePath) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setResumeBook(ReadOnlyResumeBook newData) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyResumeBook getResumeBook() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addPersonalDetail(Item item) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasPersonalDetail(Item item) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deletePersonalDetail(Item target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setPersonalDetail(Item target, Item editedItem) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Item> getFilteredPersonalDetailList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredPersonalDetailList(Predicate<Item> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addResume(Item item) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasResume(Item item) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deleteResume(Item target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setResume(Item target, Item editedItem) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Item> getFilteredResumeList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredResumeList(Predicate<Item> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//    }
//
//    /**
//     * A Model stub that contains a single person.
//     */
//    private class ModelStubWithPerson extends ModelStub {
//        private final Item item;
//
//        ModelStubWithPerson(Item item) {
//            requireNonNull(item);
//            this.item = item;
//        }
//
//        @Override
//        public boolean hasPersonalDetail(Item item) {
//            requireNonNull(item);
//            return this.item.isSame(item);
//        }
//    }
//
//    /**
//     * A Model stub that always accept the person being added.
//     */
//    private class ModelStubAcceptingPersonAdded extends ModelStub {
//        final ArrayList<Item> personsAdded = new ArrayList<>();
//
//        @Override
//        public boolean hasPersonalDetail(Item item) {
//            requireNonNull(item);
//            return personsAdded.stream().anyMatch(item::isSame);
//        }
//
//        @Override
//        public void addPersonalDetail(Item item) {
//            requireNonNull(item);
//            personsAdded.add(item);
//        }
//
//        @Override
//        public ReadOnlyResumeBook getResumeBook() {
//            return new ResumeBook();
//        }
//    }

}
