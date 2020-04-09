package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.results.ClearCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ResumeBook;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalResumeBook;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager(new ResumeBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new ResumeBook(), new UserPrefs());
        assertCommandSuccess(new ClearCommand(),
                model,
                new ClearCommandResult(" ",
                        ClearCommand.MESSAGE_SUCCESS, model.getDisplayType()),
                expectedModel);
    }


    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(TypicalResumeBook.TYPICAL_WITHOUT_GOOGLE, new UserPrefs());
        Model expectedModel = new ModelManager(new ResumeBook(), new UserPrefs());
        assertCommandSuccess(new ClearCommand(),
                model,
                new ClearCommandResult(" ",
                        ClearCommand.MESSAGE_SUCCESS, model.getDisplayType()),
                expectedModel);
    }

}
