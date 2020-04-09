package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_ITEM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.DoneCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Note;
import seedu.address.testutil.TypicalResumeBook;

public class DoneCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalResumeBook.TYPICAL, new UserPrefs());
    }

    @Test
    public void execute_setNoteToDone_success() {
        Model expectedModel = new ModelManager(TypicalResumeBook.TYPICAL_COPY, new UserPrefs());
        Index validIndex = INDEX_FIRST_ITEM;
        Note doneItem = expectedModel.getNote(validIndex);
        doneItem.markAsDone();
        DoneCommand doneCommand = new DoneCommand(validIndex);
        assertCommandSuccess(doneCommand,
                model,
                new DoneCommandResult(doneItem.toString(), DoneCommand.MESSAGE_DONE_SUCCESS,
                        model.getDisplayType()),
                expectedModel);
    }

    @Test
    public void execute_invalidNoteIndex_throwsCommandException() {
        Index invalidIndex = INDEX_FOURTH_ITEM;
        DoneCommand doneCommand = new DoneCommand(invalidIndex);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_INDEX, () -> doneCommand.execute(model));
    }

    @Test
    public void execute_setNoteAsDoneTwice_throwsCommandException() throws CommandException {
        Index validIndex = INDEX_FIRST_ITEM;
        DoneCommand doneCommand = new DoneCommand(validIndex);
        doneCommand.execute(model);
        assertThrows(CommandException.class,
                DoneCommand.MESSAGE_DONE_FAILURE, () -> doneCommand.execute(model));
    }

    @Test
    public void equals() {
        DoneCommand doneOneCommand = new DoneCommand(INDEX_FIRST_ITEM);
        DoneCommand doneFourCommand = new DoneCommand(INDEX_FOURTH_ITEM);

        // Copy and original are equals
        assertEquals(doneOneCommand, new DoneCommand(INDEX_FIRST_ITEM));

        // Done command of different indexes are not equals
        assertNotEquals(doneOneCommand, doneFourCommand);

    }

}
