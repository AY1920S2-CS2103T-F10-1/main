package seedu.address.logic.commands.help;

import seedu.address.logic.commands.Command;

/**
 * Format help instructions.
 */
public abstract class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_OPTION_SUMMARY = "command";
    public static final String COMMAND_OPTION_START = "start";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + COMMAND_WORD + " OPTION\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_OPTION_SUMMARY;

    public static final String MESSAGE_INVALID_OPTION = "The help option is invalid! "
            + "Try \"help command\" or \"help start\" instead.";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";
}
