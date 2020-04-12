package seedu.address.logic.commands;

import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.ExitCommandResult;
import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Resume Book as requested ...";

    /**
     * Exits the program.
     *
     * @param model {@code Model} not practically relevant in this command.
     * @return      {@code CommandResult} that describes delete command for UI to close.
     */
    @Override
    public CommandResult execute(Model model) {
        return new ExitCommandResult("Exiting", MESSAGE_EXIT_ACKNOWLEDGEMENT, model.getDisplayType());
    }
}
