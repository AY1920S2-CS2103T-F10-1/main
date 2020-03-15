package seedu.address.logic.commands;

/*import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersonalDetails.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ResumeBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonalDetailBuilder;*/

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {
//
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//
//    @Test
//    public void execute_allFieldsSpecifiedUnfilteredList_success() {
//        Item editedItem = new PersonalDetailBuilder().build();
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedItem).build();
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedItem);
//
//        Model expectedModel = new ModelManager(new ResumeBook(model.getResumeBook()), new UserPrefs());
//        expectedModel.setPersonalDetail(model.getFilteredPersonalDetailList().get(0), editedItem);
//
//        //assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_someFieldsSpecifiedUnfilteredList_success() {
//        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonalDetailList().size());
//        Item lastItem = model.getFilteredPersonalDetailList().get(indexLastPerson.getZeroBased());
//
//        PersonalDetailBuilder personInList = new PersonalDetailBuilder(lastItem);
//        Item editedItem = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
//                .withTags(VALID_TAG_HUSBAND).build();
//
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
//                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
//        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedItem);
//
//        Model expectedModel = new ModelManager(new ResumeBook(model.getResumeBook()), new UserPrefs());
//        expectedModel.setPersonalDetail(lastItem, editedItem);
//
//        //assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_noFieldSpecifiedUnfilteredList_success() {
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
//        Item editedItem = model.getFilteredPersonalDetailList().get(INDEX_FIRST_PERSON.getZeroBased());
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedItem);
//
//        Model expectedModel = new ModelManager(new ResumeBook(model.getResumeBook()), new UserPrefs());
//
//        //assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_filteredList_success() {
//        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//
//        Item itemInFilteredList = model.getFilteredPersonalDetailList().get(INDEX_FIRST_PERSON.getZeroBased());
//        Item editedItem = new PersonalDetailBuilder(itemInFilteredList).withName(VALID_NAME_BOB).build();
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
//                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedItem);
//
//        Model expectedModel = new ModelManager(new ResumeBook(model.getResumeBook()), new UserPrefs());
//        expectedModel.setPersonalDetail(model.getFilteredPersonalDetailList().get(0), editedItem);
//
//        //assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_duplicatePersonUnfilteredList_failure() {
//        Item firstItem = model.getFilteredPersonalDetailList().get(INDEX_FIRST_PERSON.getZeroBased());
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstItem).build();
//        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);
//
//        //assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
//    }
//
//    @Test
//    public void execute_duplicatePersonFilteredList_failure() {
//        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//
//        // edit person in filtered list into a duplicate in address book
//        Item itemInList = model.getResumeBook().getPersonalDetailList().get(INDEX_SECOND_PERSON.getZeroBased());
//        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
//                new EditPersonDescriptorBuilder(itemInList).build());
//
//        //assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
//    }
//
//    @Test
//    public void execute_invalidPersonIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonalDetailList().size() + 1);
//        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
//        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);
//
//        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }
//
//    /**
//     * Edit filtered list where index is larger than size of filtered list,
//     * but smaller than size of address book
//     */
//    @Test
//    public void execute_invalidPersonIndexFilteredList_failure() {
//        showPersonAtIndex(model, INDEX_FIRST_PERSON);
//        Index outOfBoundIndex = INDEX_SECOND_PERSON;
//        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getResumeBook().getPersonalDetailList().size());
//
//        EditCommand editCommand = new EditCommand(outOfBoundIndex,
//                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//    }
//
//    @Test
//    public void equals() {
//        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);
//
//        // same values -> returns true
//        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
//        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
//        assertTrue(standardCommand.equals(commandWithSameValues));
//
//        // same object -> returns true
//        assertTrue(standardCommand.equals(standardCommand));
//
//        // null -> returns false
//        assertFalse(standardCommand.equals(null));
//
//        // different types -> returns false
//        assertFalse(standardCommand.equals(new ClearCommand()));
//
//        // different index -> returns false
//        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
//    }

}
