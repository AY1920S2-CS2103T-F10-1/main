package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.HELP_COMMAND_SUMMARY;
import static seedu.address.commons.core.Messages.HELP_START;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.help.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.help.HelpStartCommand;
import seedu.address.logic.commands.help.HelpSummaryCommand;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.HelpCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_helpSummary_success() {
        CommandResult expectedCommandResult = new HelpCommandResult("", SHOWING_HELP_MESSAGE,
                model.getDisplayType(), HELP_COMMAND_SUMMARY);
        assertCommandSuccess(new HelpSummaryCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpStart_success() {
        CommandResult expectedCommandResult = new HelpCommandResult("", SHOWING_HELP_MESSAGE,
                model.getDisplayType(), HELP_START);
        assertCommandSuccess(new HelpStartCommand(), model, expectedCommandResult, expectedModel);
    }
}
