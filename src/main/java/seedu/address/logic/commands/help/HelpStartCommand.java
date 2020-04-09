package seedu.address.logic.commands.help;

import static seedu.address.commons.core.Messages.HELP_START;

import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.HelpCommandResult;
import seedu.address.model.Model;

/**
 * Format instructions for getting started.
 */
public class HelpStartCommand extends HelpCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD + " start";

    @Override
    public CommandResult execute(Model model) {
        return new HelpCommandResult("", SHOWING_HELP_MESSAGE, model.getDisplayType(), HELP_START);
    }
}
